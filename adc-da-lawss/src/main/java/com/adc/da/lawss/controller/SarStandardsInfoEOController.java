package com.adc.da.lawss.controller;

import com.adc.da.att.service.AttFileEOService;
import com.adc.da.att.vo.AttFileVo;
import com.adc.da.base.web.BaseController;
import com.adc.da.excel.poi.excel.ExcelExportUtil;
import com.adc.da.excel.poi.excel.ExcelImportUtil;
import com.adc.da.excel.poi.excel.entity.ExportParams;
import com.adc.da.excel.poi.excel.entity.ImportParams;
import com.adc.da.excel.poi.excel.entity.enums.ExcelType;
import com.adc.da.excel.poi.excel.entity.result.ExcelImportResult;
import com.adc.da.lawss.common.ReadExcel;
import com.adc.da.lawss.common.StandFileClassifyEnum;
import com.adc.da.lawss.entity.SarStandFileEO;
import com.adc.da.lawss.entity.SarStandResEO;
import com.adc.da.lawss.entity.SarStandardsInfoEO;
import com.adc.da.lawss.page.SarStandardsInfoEOPage;
import com.adc.da.lawss.service.SarStandFileEOService;
import com.adc.da.lawss.service.SarStandResEOService;
import com.adc.da.lawss.service.SarStandardsInfoEOService;
import com.adc.da.lawss.dto.SarStandExcelDto;
import com.adc.da.util.exception.AdcDaBaseException;
import com.adc.da.util.http.PageInfo;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.utils.IOUtils;
import com.adc.da.util.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

/**
 * 用户管理模块相关接口
 * 1.详情
 * 2.分页查询
 * 3.新增
 * 4.修改
 * 5.删除
 * 6.配置角色信息
 *
 * @author gaoyan
 * date 2018/09/03
 * @see com.adc.da.sys.dao.UserEODao
 * @see mybatis.mapper.sys
 */
@RestController
@RequestMapping("/${restPath}/lawss/sarStandardsInfo")
@Api(description = "|标准信息管理|")
public class SarStandardsInfoEOController extends BaseController<SarStandardsInfoEO>{

    private static final Logger logger = LoggerFactory.getLogger(SarStandardsInfoEOController.class);

    @Autowired
    private SarStandardsInfoEOService sarStandardsInfoEOService;

    @Autowired
    private SarStandResEOService sarStandResEOService;

    @Autowired
    private SarStandFileEOService sarStandFileEOService;

    @Autowired
    private AttFileEOService attFileEOService;

	@ApiOperation(value = "|SarStandardsInfoEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("lawss:sarStandardsInfo:page")
    public ResponseMessage<PageInfo<SarStandardsInfoEO>> page(SarStandardsInfoEOPage page) throws Exception {
        List<SarStandardsInfoEO> rows = sarStandardsInfoEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }



	@ApiOperation(value = "|SarStandardsInfoEO|查询")
    @GetMapping("")
    @RequiresPermissions("lawss:sarStandardsInfo:list")
    public ResponseMessage<List<SarStandardsInfoEO>> list(SarStandardsInfoEOPage page) throws Exception {
        return Result.success(sarStandardsInfoEOService.queryByList(page));
	}

    @ApiOperation(value = "|SarStandardsInfoEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("lawss:sarStandardsInfo:get")
    public ResponseMessage<SarStandardsInfoEO> find(@PathVariable String id) throws Exception {
        return Result.success(sarStandardsInfoEOService.selectByPrimaryKey(id));
    }

    /**
     * 自定义分页查询
     * @param page  标准信息
     * @return
     * @author gaoyan
     * date 2018-09-04
     */
    @ApiOperation(value = "|SarStandardsInfoEO|自定义分页查询")
    @GetMapping("/getSarStandardsInfoPage")
    //@RequiresPermissions("lawss:sarStandardsInfo:page")
    public ResponseMessage<PageInfo<SarStandardsInfoEO>> getSarStandardsInfoPage(SarStandardsInfoEOPage page) throws Exception {
        List<SarStandardsInfoEO> rows = sarStandardsInfoEOService.getSarStandardsInfoPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }


    /**
     * 添加一条标准
     * @param sarStandardsInfoEO  标准信息
     * @return
     * @author gaoyan
     * date 2018-09-04
     */
    @ApiOperation(value = "|SarStandardsInfoEO|新增")
    @PostMapping("/addarStandardsInfo")
    //@RequiresPermissions("lawss:sarStandardsInfo:save")
    public ResponseMessage<SarStandardsInfoEO> create(SarStandardsInfoEO sarStandardsInfoEO,HttpServletRequest request) throws Exception {
        ResponseMessage<SarStandardsInfoEO> result = sarStandardsInfoEOService.createSarStandardsInfo(sarStandardsInfoEO);
        //标准文件资源表，标准文件详情表中插入数据，需要下载文件，并保存数据
        List<MultipartFile> standfiles = null;
        List<MultipartFile> standModifyfiles = null;
        //* 2.如果是上传文件请求，获取文件列表*//*
        if (request instanceof MultipartHttpServletRequest) {
            standfiles = ((MultipartHttpServletRequest) request).getFiles("standFile");
            standModifyfiles = ((MultipartHttpServletRequest) request).getFiles("standModifyFile");
        }
        //* 3.如果文件列表不为空，循环文件列表，保存文件 *//*
        if (standfiles != null) {
            for (MultipartFile file : standfiles) {
                //* 3.1保存图片 *//*
                // file = sarStandardsInfoEO.getStandFiles();
                AttFileVo fileInfo = attFileEOService.saveFileInfo(file);
                //* 3.2 其他后续操作 *//*
                //标准文件资源表存数据库
                SarStandResEO sarStandResEO = new SarStandResEO();
                sarStandResEO.setStandId(result.getData().getId());            //标准ID
                sarStandResEO.setStandFileClassify(StandFileClassifyEnum.STAND_FILE.getValue());  //文件分类
                sarStandResEO.setFileName(file.getName());           //文件名称
                String fileTyle=file.getName().substring(file.getName().lastIndexOf("."),file.getName().length());
                sarStandResEO.setFileSuffix(fileTyle);         //文件类型
                sarStandResEO = sarStandResEOService.insertSarStandResEO(sarStandResEO);
                //标准文件详情表存数据库
                SarStandFileEO sarStandFileEO = new SarStandFileEO();
                sarStandFileEO.setStandId(result.getData().getId());     //标准ID
                sarStandFileEO.setResId(sarStandResEO.getId());       //资源ID
                sarStandFileEO.setAttId(fileInfo.getId());       //文件ID
                sarStandFileEO.setUseModel("");  //文件使用模式
                sarStandFileEOService.insertSarStandFileEO(sarStandFileEO);
            }
        }
        if (standModifyfiles != null) {
            for (MultipartFile file : standModifyfiles) {
                //* 3.1保存图片 *//*
                // file = sarStandardsInfoEO.getStandModifyFiles();
                 AttFileVo fileInfo = attFileEOService.saveFileInfo(file);
                //* 3.2 其他后续操作 *//*
                //标准文件资源表存数据库
                SarStandResEO sarStandResEO = new SarStandResEO();
                sarStandResEO.setStandId(result.getData().getId());            //标准ID
                sarStandResEO.setStandFileClassify(StandFileClassifyEnum.STAND_MODIFY_FILE.getValue());  //文件分类
                sarStandResEO.setFileName(file.getName());           //文件名称
                String fileTyle=file.getName().substring(file.getName().lastIndexOf("."),file.getName().length());
                sarStandResEO.setFileSuffix(fileTyle);         //文件类型
                sarStandResEO = sarStandResEOService.insertSarStandResEO(sarStandResEO);
                //标准文件详情表存数据库
                SarStandFileEO sarStandFileEO = new SarStandFileEO();
                sarStandFileEO.setStandId(result.getData().getId());     //标准ID
                sarStandFileEO.setResId(sarStandResEO.getId());       //资源ID
                sarStandFileEO.setAttId(fileInfo.getId());       //文件ID
                sarStandFileEO.setUseModel("");  //文件使用模式
                sarStandFileEOService.insertSarStandFileEO(sarStandFileEO);
           }
        }
        return Result.success("","添加成功",sarStandardsInfoEO);
    }

    /**
     * EXCEL导入标准
     * @param file  标准信息
     * @return
     * @author gaoyan
     * date 2018-09-04
     */
    @ApiOperation(value = "|SarStandardsInfoEO|导入")
    @PostMapping(value = "importStandardsInfo")
    //@RequiresPermissions("lawss:sarStandardsInfo:save")
    public ResponseMessage<SarStandardsInfoEO> importStandardsInfo(@RequestParam(value = "file", required = false) MultipartFile file,String standType) throws Exception {

        //验证文件名是否合格
        ReadExcel readExcel = new ReadExcel();
        if (!readExcel.validateExcel(file.getOriginalFilename())) {
            return Result.error("fail", "请上传excel文件");
        }
        // 获取文件输入流
        InputStream is = file.getInputStream();
        // 导入参数设置，默认即可
        ImportParams params = new ImportParams();
        // 解析excel，并返回校验信息
        ExcelImportResult<SarStandExcelDto> result = ExcelImportUtil.importExcelVerify(is, SarStandExcelDto.class, params);
        // 如果校验不通过，返回错误信息
        if (result.isVerfiyFail()) {
            return Result.error("fail", "excel文件校验失败");
        }
        //excel读取到的数据
        List<SarStandExcelDto> datas = result.getList();
        try {
            return sarStandardsInfoEOService.importSarStandardsInfoData(datas,standType);
        } catch (Exception e) {
            return Result.error("fail", e.getMessage());
        }
    }

    /**
     * 修改标准
     * @param sarStandardsInfoEO  标准信息
     * @return
     * @author gaoyan
     * date 2018-09-04
     */
    @ApiOperation(value = "|SarStandardsInfoEO|修改")
    @PostMapping(value="/updateSarStandardsInfo")
    //@RequiresPermissions("lawss:sarStandardsInfo:update")
    public ResponseMessage<SarStandardsInfoEO> updateSarStandardsInfo(SarStandardsInfoEO sarStandardsInfoEO) throws Exception {
        sarStandardsInfoEOService.updateSarStandardsInfo(sarStandardsInfoEO);
        return Result.success("","修改成功",sarStandardsInfoEO);
    }

    @ApiOperation(value = "|SarStandardsInfoEO|删除")
    @PostMapping("/deleteSarStandards")
    //@RequiresPermissions("lawss:sarStandardsInfo:delete")
    public ResponseMessage delete(String id) throws Exception {
        sarStandardsInfoEOService.deleteByPrimaryKey(id);
        logger.info("delete from SAR_STANDARDS_INFO where id = {}", id);
        return Result.success();
    }

    /**
     * 将查询出来的数据导出到EXCEL表格中
     * @param page  标准信息
     * @return
     * @author gaoyan
     * date 2018-09-04
     */
    @ApiOperation(value = "|SysCorpEO|导出excel")
    @GetMapping(value = "/exportStandardsInfoExcel")
    public void exportStandardsInfoExcel(SarStandardsInfoEOPage page, HttpServletResponse response, HttpServletRequest request) {
        OutputStream os = null;
        Workbook workbook = null;
        try {
            response.setHeader("Content-Disposition",
                    "attachment; filename=" + ReadExcel.encodeFileName("下载文件.xlsx",request));
            response.setContentType("application/force-download");
            ExportParams exportParams = new ExportParams();
            exportParams.setType(ExcelType.XSSF);

            List<SarStandExcelDto> datas =  sarStandardsInfoEOService.getSarStandardsInfo(page);
           /* List<SarStandExcelDto> sarStandExcelVOList = new ArrayList<SarStandExcelDto>();
            if (datas != null && !datas.isEmpty()) {
                for (SarStandExcelEO eo : datas) {
                    SarStandExcelDto dto = new SarStandExcelDto();
                    BeanUtils.copyProperties(eo, dto);
                    sarStandExcelVOList.add(dto);
                }
            }*/
            workbook = ExcelExportUtil.exportExcel(exportParams, SarStandExcelDto.class, datas);
            os = response.getOutputStream();
            workbook.write(os);
            os.flush();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            throw new AdcDaBaseException("下载文件失败，请重试");
        } finally {
            IOUtils.closeQuietly(os);
        }
    }

    /**
     * 查询输入的代替标准号在系统中是否录入
     * @param replaceStandNum  代替标准号
     * @param standType  标准分类
     * @return
     * @author gaoyan
     * date 2018-09-13
     */
    @ApiOperation(value = "|SarStandardsInfoEO|验证输入的代替标准号")
    @PostMapping("/selectReplaceStandNum")
    //@RequiresPermissions("lawss:sarStandardsInfo:delete")
    public ResponseMessage selectReplaceStandNum(String replaceStandNum,String standType) throws Exception {
        if(StringUtils.isNotEmpty(replaceStandNum)){
            if(replaceStandNum.indexOf(",",0)==0){
                replaceStandNum=replaceStandNum.substring(1,replaceStandNum.length());
            }
            if(replaceStandNum.lastIndexOf(",")==replaceStandNum.length()-1){
                replaceStandNum=replaceStandNum.substring(0,replaceStandNum.length()-1);
            }
            String [] numstring = replaceStandNum.split(",");
            List<String> resultstring =  Arrays.asList(numstring);
            for(String tesmun : numstring){
                List<SarStandardsInfoEO> standlist = sarStandardsInfoEOService.selectStandardsByStandnumber(tesmun,standType);
                if(standlist.size()>0){
                    resultstring.remove(tesmun);
                }
            }
            if(resultstring.size()==0){
                return Result.success();
            }
            else{
                return Result.success("00","代替标准号不存在",resultstring);
            }
        }
        logger.info("", replaceStandNum);
        return Result.success();
    }

    /**
     * 查询对应目录下的标准或游离的标准
     * @param standardsInfoEO
     * @return
     * @author gaoyan
     * date 2018-09-13
     */
    @ApiOperation(value = "|SarStandardsInfoEO|查询对应目录下的标准或游离的标准")
    @PostMapping("/selectStandardsInfoByMenu")
    //@RequiresPermissions("lawss:sarStandardsInfo:delete")
    public ResponseMessage selectStandardsInfoByMenu(SarStandardsInfoEOPage standardsInfoEO) throws Exception {
        List<SarStandardsInfoEO> standlist = sarStandardsInfoEOService.getSarStandardsInfoByMenu(standardsInfoEO);
        return Result.success(standlist);
    }


}
