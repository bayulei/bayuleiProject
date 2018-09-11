package com.adc.da.lawss.service;

import com.adc.da.lawss.dao.SarLawsMenuEODao;
import com.adc.da.lawss.dao.SarLawsValEODao;
import com.adc.da.lawss.dto.LawsInfoImportDto;
import com.adc.da.lawss.entity.SarLawsMenuEO;
import com.adc.da.lawss.entity.SarLawsValEO;
import com.adc.da.lawss.page.SarLawsMenuEOPage;
import com.adc.da.lawss.page.SarLawsValEOPage;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.utils.UUID;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.base.service.BaseService;
import com.adc.da.lawss.dao.SarLawsInfoEODao;
import com.adc.da.lawss.entity.SarLawsInfoEO;

import java.util.*;


/**
 * @Author yangxuenan
 * @Description 法规信息
 * Date 2018/9/3 16:36
 * @Param 
 * @return 
 **/
@Service("sarLawsInfoEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class SarLawsInfoEOService extends BaseService<SarLawsInfoEO, String> {

    private static final Logger logger = LoggerFactory.getLogger(SarLawsInfoEOService.class);

    @Autowired
    private SarLawsInfoEODao dao;

    @Autowired
    private SarLawsMenuEODao sarLawsMenuEODao;

    @Autowired
    private SarLawsValEODao sarLawsValEODao;

    public SarLawsInfoEODao getDao() {
        return dao;
    }

    /**
     * @Author yangxuenan
     * @Description 新增法规信息
     * Date 2018/9/3 20:47
     * @Param [sarLawsInfoEO]
     * @return com.adc.da.util.http.ResponseMessage<com.adc.da.lawss.entity.SarLawsInfoEO>
     **/
    public ResponseMessage<SarLawsInfoEO> createLawsInfo(SarLawsInfoEO sarLawsInfoEO) throws Exception {
        String infoId = UUID.randomUUID(20);
        sarLawsInfoEO.setId(infoId);
        sarLawsInfoEO.setValidFlag(0);
        sarLawsInfoEO.setCreationTime(new Date());
        sarLawsInfoEO.setModifyTime(new Date());
        //向法规信息表添加数据
        Integer countInfo = dao.insertSelective(sarLawsInfoEO);
        if(countInfo > 0){
            String menuId = sarLawsInfoEO.getMenuId();
            //判断新增时，是否已选文件夹
            if(StringUtils.isNotEmpty(menuId)){
                SarLawsMenuEO sarLawsMenuEO = new SarLawsMenuEO();
                sarLawsMenuEO.setId(UUID.randomUUID(20));
                sarLawsMenuEO.setLawsId(infoId);
                sarLawsMenuEO.setMenuId(menuId);
                sarLawsMenuEO.setValidFlag(0);
                //向法规目录关联表添加数据
                sarLawsMenuEODao.insertSelective(sarLawsMenuEO);
            }

            SarLawsValEO sarLawsValEO = new SarLawsValEO();
            sarLawsValEO.setId(UUID.randomUUID(20));
            sarLawsValEO.setLawsId(infoId);
            sarLawsValEO.setValidFlag(0);
            sarLawsValEO.setCreationTime(new Date());
            sarLawsValEO.setModifyTime(new Date());
            //向法规关联表添加数据
            sarLawsValEODao.insertSelective(sarLawsValEO);
            return Result.success("0","新增成功",sarLawsInfoEO);
        } else {
            return Result.error("新增失败");
        }
    }

    /**
     * @Author yangxuenan
     * @Description 删除法规信息
     * Date 2018/9/4 9:11
     * @Param [sarLawsInfoEO]
     * @return com.adc.da.util.http.ResponseMessage<com.adc.da.lawss.entity.SarLawsInfoEO>
     **/
    public ResponseMessage<SarLawsInfoEO> deleteLawsInfo(String infoId) throws Exception {
        SarLawsInfoEO sarLawsInfoEO = new SarLawsInfoEO();
        sarLawsInfoEO.setId(infoId);
        sarLawsInfoEO.setValidFlag(1);
        //法规信息表修改有效标识
        Integer countInfo = dao.updateByPrimaryKeySelective(sarLawsInfoEO);
        if(countInfo > 0){
            SarLawsMenuEOPage sarLawsMenuEOpage = new SarLawsMenuEOPage();
            sarLawsMenuEOpage.setLawsId(infoId);
            sarLawsMenuEOpage.setValidFlag("0");
            List<SarLawsMenuEO> listLawsMenu =  sarLawsMenuEODao.selectByLawsInfo(sarLawsMenuEOpage);
            //判断法规目录表是否包含该条法规
            if(!listLawsMenu.isEmpty()){
                for(int j=0;j<listLawsMenu.size();j++){
                    String lawsMenuId = listLawsMenu.get(j).getId();
                    SarLawsMenuEO sarLawsMenuEO = new SarLawsMenuEO();
                    sarLawsMenuEO.setId(lawsMenuId);
                    sarLawsMenuEO.setLawsId(infoId);
                    sarLawsMenuEO.setValidFlag(0);
                    //法规目录关联表修改有效标识
                    sarLawsMenuEODao.updateByPrimaryKeySelective(sarLawsMenuEO);
                }

            }

            SarLawsValEOPage sarLawsValEOPage = new SarLawsValEOPage();
            sarLawsValEOPage.setLawsId(infoId);
            sarLawsValEOPage.setValidFlag("0");
            List<SarLawsValEO> listLawsVal = sarLawsValEODao.selectByLawsVal(sarLawsValEOPage);
            //判断法规关联表是否包含该条法规
            if(!listLawsVal.isEmpty()){
                //法规关联表修改有效标识
                for(int i=0;i<listLawsVal.size();i++){
                    String lawsValId = listLawsVal.get(i).getId();
                    SarLawsValEO sarLawsValEO = new SarLawsValEO();
                    sarLawsValEO.setId(lawsValId);
                    sarLawsValEO.setLawsId(infoId);
                    sarLawsValEO.setValidFlag(1);
                    sarLawsValEODao.updateByPrimaryKeySelective(sarLawsValEO);
                }

            }

            return Result.success("0","删除成功",sarLawsInfoEO);
        } else {
            return Result.error("删除失败！");
        }


    }


    /**
     * @Author yangxuenan
     * @Description 导入法规信息
     * Date 2018/9/4 14:28
     * @Param [datas]
     * @return com.adc.da.util.http.ResponseMessage
     **/
    public ResponseMessage importLawsInfoDatas(List<LawsInfoImportDto> datas) throws Exception{
        try{
            //验证导入数据是否符合规则
            Map map = validateImportDatas(datas);
            boolean isOk = (boolean) map.get("result");
            if (!isOk) {
                //验证没有通过
                String message = (String) map.get("message");
                return Result.error("fail", message);
            }
            //将导入数据循环新增至相应表
            for(LawsInfoImportDto importDto : datas){
                SarLawsInfoEO sarLawsInfoEO = new SarLawsInfoEO();
                BeanUtils.copyProperties(importDto,sarLawsInfoEO);
                createLawsInfo(sarLawsInfoEO);
            }
            return new ResponseMessage("0","导入成功",true);
        } catch (Exception e){
            return Result.error("导入失败");
        }


    }

    /**
     * @Author yangxuenan
     * @Description 验证导入数据是否符合规则
     * Date 2018/9/5 10:15
     * @Param [datas]
     * @return java.util.Map
     **/
    public Map validateImportDatas(List<LawsInfoImportDto> datas){
        //存放数据验证结果信息
        List<String>  stringMessage = new ArrayList<>();
        int i=1;    //记录行号
        //循环验证数据
        for(LawsInfoImportDto dto : datas){
            i++;
            int countError = 0;   //记录失败数据数量
            String errorMsg = "第"+ i +"行：";
            if(StringUtils.isNotEmpty(dto.getLawsNumber())){
                if(dto.getLawsNumber().length() > 100){
                    errorMsg += "文件号不能超过100个字符";
                    countError++;
                }
            }
            if(StringUtils.isNotEmpty(dto.getLawsName())){
                if(dto.getLawsName().length() > 500){
                    errorMsg += "文件名称不能超过500个字符";
                    countError++;
                }
            } else {
                errorMsg += "文件名称不能为空";
                countError++;
            }
            if(StringUtils.isNotEmpty(dto.getIssueUnit())){
                if(dto.getIssueUnit().length() > 500){
                    errorMsg += "发布单位不能超过500个字符";
                    countError++;
                }
            }
            if(StringUtils.isNotEmpty(dto.getLawsStatus())){
                if(dto.getLawsStatus().length() > 500){
                    errorMsg += "文件状态不能超过500个字符";
                    countError++;
                }
            } else {
                errorMsg += "文件状态不能为空";
                countError++;
            }
            if(dto.getIssueTime() != null){
                /*if(dto.getIssueTime().toString().length() > 500){
                    errorMsg += "发布日期不能超过500个字符";
                    countError++;
                }*/
            } else {
                errorMsg += "发布日期不能为空";
                countError++;
            }
            if(dto.getPutTime() != null){
                /*if(dto.getIssueTime().toString().length() > 500){
                    errorMsg += "发布日期不能超过500个字符";
                    countError++;
                }*/
            } else {
                errorMsg += "实施日期不能为空";
                countError++;
            }
            if(StringUtils.isNotEmpty(dto.getReplaceLawsNum())){
                if(dto.getReplaceLawsNum().length() > 100){
                    errorMsg += "代替文件号不能超过100个字符";
                    countError++;
                }
            }
            if(StringUtils.isNotEmpty(dto.getResponsibleUnit())){
                if(dto.getResponsibleUnit().length() > 100){
                    errorMsg += "责任部门不能超过100个字符";
                    countError++;
                }
            }

            if(countError > 0){
                stringMessage.add(errorMsg);
            }
        }
        Map map = new HashMap();
        if (stringMessage.isEmpty()) {
            map.put("result", true);
            map.put("message", "");
        } else {
            map.put("result", false);
            String html ="";
            for (String message : stringMessage){
                html += message +"</br>";
            }
            map.put("message", html);
        }
        return map;
    }

}
