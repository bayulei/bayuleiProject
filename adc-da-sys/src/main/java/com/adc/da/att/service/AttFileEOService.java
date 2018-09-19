package com.adc.da.att.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.adc.da.att.vo.AttFileVo;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.adc.da.base.service.BaseService;
import com.adc.da.sys.constant.ValidFlagEnum;
import com.adc.da.sys.util.UUIDUtils;
import com.adc.da.util.utils.FileUtil;
import com.adc.da.att.dao.AttFileEODao;
import com.adc.da.att.entity.AttFileEO;


/**
 * <br>
 * <b>功能：</b>ATT_FILE AttFileEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-07 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("attFileEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class AttFileEOService extends BaseService<AttFileEO, String> {

    private static final Logger logger = LoggerFactory.getLogger(AttFileEOService.class);

    @Autowired
    private AttFileEODao dao;


    @Value("${file.path}")
    private String filePath;//文件存储路径

    public AttFileEODao getDao() {
        return dao;
    }

    /**
     * 保存文件并返回文件ID
     *
     * @param file
     */
    @Transactional(rollbackFor = Exception.class)
    public String saveFileInfo(File file) {
        /**
         * 1、首先生成文件保存的主键ID
         * 2、根据文件ID生成随机路径
         * 3、生成文件名
         * 3、保存文件
         * 4、获取文件相关信息
         * 5、判断当前表是否有存在，如果存在则执行insert语句 如果不存在则创建表结构
         * 5、保存入库并返回主键ID
         */
        String fileId = UUIDUtils.randomUUID20();
        try {
            String uuidPath = UUIDUtils.getUUIDPath(fileId);

            String FileSavePath = filePath + uuidPath + "/";
            File dir = new File(FileSavePath);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            String fileName = file.getName();
            String fileSuffix = fileName.substring(fileName.lastIndexOf("."), fileName.length());
            String newFileName = fileId + fileSuffix;
            File saveFile = new File(FileSavePath + newFileName);
            FileUtil.moveFileToDirectory(file, saveFile, true);
            //开始存储文件信息
            String tableName = UUIDUtils.getAttTable();
            int existTable = dao.existTable(tableName);
            if (existTable == 0) {
                dao.creatTableInfo(tableName);
            }
            AttFileEO attFileEO = new AttFileEO();
            attFileEO.setTableName(tableName);
            attFileEO.setId(tableName + "_" + fileId);
            attFileEO.setOldFileName(fileName);
            attFileEO.setFileSuffix(fileSuffix);
            attFileEO.setFilePath(FileSavePath);
            attFileEO.setFileName(newFileName);
            attFileEO.setValidFlag(ValidFlagEnum.VALID_TRUE.getValue());
            attFileEO.setCreationTime(new Date());
            attFileEO.setModifyTime(new Date());
            dao.insert(attFileEO);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            FileUtil.deleteQuietly(file);
        }
        return fileId;
    }

    /**
     * 保存文件并返回文件ID
     *
     * @param file
     */
    @Transactional(rollbackFor = Exception.class)
    public AttFileVo saveFileInfo(MultipartFile file) {
        /**
         * 1、首先生成文件保存的主键ID
         * 2、根据文件ID生成随机路径
         * 3、生成文件名
         * 3、保存文件
         * 4、获取文件相关信息
         * 5、判断当前表是否有存在，如果存在则执行insert语句 如果不存在则创建表结构
         * 5、保存入库并返回主键ID
         */
        AttFileVo attFileVo = new AttFileVo();
        String fileId = UUIDUtils.randomUUID20();
        try {
            String uuidPath = UUIDUtils.getUUIDPath(fileId);

            String FileSavePath = filePath + uuidPath;
            File dir = new File(FileSavePath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            //开始存储文件
            String fileName = file.getOriginalFilename();
            String fileSuffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
            String newFileName = fileId + "." + fileSuffix;
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File(FileSavePath + newFileName));
            //开始存储文件信息
            String tableName = UUIDUtils.getAttTable();
            int existTable = dao.existTable(tableName);
            if (existTable == 0) {
                dao.creatTableInfo(tableName);
            }
            fileId = tableName + "_" + fileId;
            AttFileEO attFileEO = new AttFileEO();
            attFileEO.setTableName(tableName);
            attFileEO.setId(fileId);
            attFileEO.setOldFileName(fileName);
            attFileEO.setFileSuffix(fileSuffix);
            attFileEO.setFilePath(uuidPath);
            attFileEO.setFileName(newFileName);
            attFileEO.setValidFlag(ValidFlagEnum.VALID_TRUE.getValue());
            attFileEO.setCreationTime(new Date());
            attFileEO.setModifyTime(new Date());
            dao.insert(attFileEO);
            attFileVo.setId(fileId);
            attFileVo.setFileName(newFileName);
            attFileVo.setFilePath(uuidPath);
            attFileVo.setFileSuffix(fileSuffix);
            attFileVo.setOldFileName(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
//			FileUtil.deleteQuietly(file);
        }
        return attFileVo;
    }


    /***
     *	保存文件列表
     * @MethodName:saveFilesInfo
     * @author: zhangyanduan
     * @param:[files]
     * @return:java.lang.String
     * date: 2018/9/19 9:48
     */
    @Transactional(rollbackFor = Exception.class)
    public List<AttFileVo> saveFilesInfo(MultipartFile[] files) {
        /**
         * 1、首先生成文件保存的主键ID
         * 2、根据文件ID生成随机路径
         * 3、生成文件名
         * 3、保存文件
         * 4、获取文件相关信息
         * 5、判断当前表是否有存在，如果存在则执行insert语句 如果不存在则创建表结构
         * 5、保存入库并返回主键ID
         */
        List<AttFileVo> fileInfoList = new ArrayList<AttFileVo>();
        if (files != null && files.length > 0) {
            for (int index = 0; index < files.length; index++) {
                MultipartFile file = files[index];
                AttFileVo attFileVo = new AttFileVo();
                String fileId = UUIDUtils.randomUUID20();
                try {
                    String uuidPath = UUIDUtils.getUUIDPath(fileId);
                    String FileSavePath = filePath + uuidPath;
                    File dir = new File(FileSavePath);
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }
                    //开始存储文件
                    String fileName = file.getOriginalFilename();

                    String fileSuffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
                    String newFileName = fileId + "." + fileSuffix;
                    FileUtils.copyInputStreamToFile(file.getInputStream(), new File(FileSavePath + newFileName));
                    //开始存储文件信息
                    String tableName = UUIDUtils.getAttTable();
                    int existTable = dao.existTable(tableName);
                    if (existTable == 0) {
                        dao.creatTableInfo(tableName);
                    }
                    fileId = tableName + "_" + fileId;
                    AttFileEO attFileEO = new AttFileEO();
                    attFileEO.setTableName(tableName);
                    attFileEO.setId(fileId);
                    attFileEO.setOldFileName(fileName);
                    attFileEO.setFileSuffix(fileSuffix);
                    attFileEO.setFilePath(uuidPath);
                    attFileEO.setFileName(newFileName);
                    attFileEO.setValidFlag(ValidFlagEnum.VALID_TRUE.getValue());
                    attFileEO.setCreationTime(new Date());
                    attFileEO.setModifyTime(new Date());
                    dao.insert(attFileEO);
                    attFileVo.setId(fileId);
                    attFileVo.setFileName(newFileName);
                    attFileVo.setFilePath(uuidPath);
                    attFileVo.setFileSuffix(fileSuffix);
                    attFileVo.setOldFileName(fileName);
                    fileInfoList.add(attFileVo);
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {

                }
            }
        }

        return fileInfoList;
    }


    /**
     * 根据文件ID获取文件信息
     *
     * @param attId
     * @return
     */
    public AttFileEO getFileInfo(String attId) {
        /**
         * 根据ID获取文件信息
         */
        AttFileEO attFileEO = new AttFileEO();
        attFileEO.setId(attId);
        AttFileEO attFileInfo = dao.selectFileInfoById(attFileEO);
        return attFileInfo;
    }

}
