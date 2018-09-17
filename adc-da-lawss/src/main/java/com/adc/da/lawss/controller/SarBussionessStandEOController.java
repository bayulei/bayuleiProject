package com.adc.da.lawss.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import com.adc.da.att.service.AttFileEOService;
import com.adc.da.excel.poi.excel.ExcelExportUtil;
import com.adc.da.excel.poi.excel.ExcelImportUtil;
import com.adc.da.excel.poi.excel.entity.ExportParams;
import com.adc.da.excel.poi.excel.entity.ImportParams;
import com.adc.da.excel.poi.excel.entity.enums.ExcelType;
import com.adc.da.excel.poi.excel.entity.result.ExcelImportResult;
import com.adc.da.lawss.common.ReadExcel;
import com.adc.da.lawss.common.StandFileClassifyEnum;
import com.adc.da.lawss.dto.SarBussionessStandExcelDto;
import com.adc.da.lawss.entity.*;
import com.adc.da.lawss.service.*;
import com.adc.da.util.exception.AdcDaBaseException;
import com.adc.da.util.utils.IOUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.lawss.page.SarBussionessStandEOPage;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/${restPath}/lawss/sarBussionessStand")
@Api(description = "|SarBussionessStandEO|")
public class SarBussionessStandEOController extends BaseController<SarBussionessStandEO>{

    private static final Logger logger = LoggerFactory.getLogger(SarBussionessStandEOController.class);

    @Autowired
    private SarBussionessStandEOService sarBussionessStandEOService;

    @Autowired
    private AttFileEOService attFileEOService;

    @Autowired
    private SarBussStandResEOService sarBussStandResEOService;

    @Autowired
    private SarBussStandFileEOService sarBussStandFileEOService;



	@ApiOperation(value = "|SarBussionessStandEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("lawss:sarBussionessStand:page")
    public ResponseMessage<PageInfo<SarBussionessStandEO>> page(SarBussionessStandEOPage page) throws Exception {
        List<SarBussionessStandEO> rows = sarBussionessStandEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

	@ApiOperation(value = "|SarBussionessStandEO|查询")
    @GetMapping("")
    @RequiresPermissions("lawss:sarBussionessStand:list")
    public ResponseMessage<List<SarBussionessStandEO>> list(SarBussionessStandEOPage page) throws Exception {
        return Result.success(sarBussionessStandEOService.queryByList(page));
	}

    @ApiOperation(value = "|SarBussionessStandEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("lawss:sarBussionessStand:get")
    public ResponseMessage<SarBussionessStandEO> find(@PathVariable String id) throws Exception {
        return Result.success(sarBussionessStandEOService.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "|SarBussionessStandEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("lawss:sarBussionessStand:save")
    public ResponseMessage<SarBussionessStandEO> create(@RequestBody SarBussionessStandEO sarBussionessStandEO) throws Exception {
        sarBussionessStandEOService.insertSelective(sarBussionessStandEO);
        return Result.success(sarBussionessStandEO);
    }


    @ApiOperation(value = "|SarBussionessStandEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("lawss:sarBussionessStand:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        sarBussionessStandEOService.deleteByPrimaryKey(id);
        logger.info("delete from SAR_BUSSIONESS_STAND where id = {}", id);
        return Result.success();
    }


    /**
     * 自定义分页查询列表
     * @param page  标准信息
     * @return
     * @author gaoyan
     * date 2018-09-04
     */
    @ApiOperation(value = "|SarBussionessStandEO|自定义分页查询列表")
    @GetMapping("/getSarBussionStandPage")
    //@RequiresPermissions("lawss:sarStandardsInfo:page")
    public ResponseMessage<PageInfo<SarBussionessStandEO>> getSarBussionStandPage(SarBussionessStandEOPage page) throws Exception {
        List<SarBussionessStandEO> rows = sarBussionessStandEOService.getSarBussionStandPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }


    @ApiOperation(value = "|SarBussionessStandEO|新增")
    @PostMapping("/addSarBussionessStand")
    //@RequiresPermissions("lawss:sarStandardsInfo:save")
    public ResponseMessage<SarBussionessStandEO> addSarBussionessStandEO(SarBussionessStandEO sarBussionessStandEO,HttpServletRequest request) throws Exception {
        ResponseMessage<SarBussionessStandEO> result = sarBussionessStandEOService.createSarBussionessStand(sarBussionessStandEO);
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
                // file = sarBussionessStandEO.getStandFiles();
                String fileid = attFileEOService.saveFileInfo(file);
                //* 3.2 其他后续操作 *//*
                //标准文件资源表存数据库
                SarBussStandResEO sarBussStandResEO = new SarBussStandResEO();
                sarBussStandResEO.setStandId(result.getData().getId());            //标准ID
                sarBussStandResEO.setStandFileClassify(StandFileClassifyEnum.STAND_FILE.getValue());  //文件分类
                sarBussStandResEO.setFileName(file.getName());           //文件名称
                String fileTyle=file.getName().substring(file.getName().lastIndexOf("."),file.getName().length());
                sarBussStandResEO.setFileSuffix(fileTyle);         //文件类型
                sarBussStandResEO = sarBussStandResEOService.insertSarBussStandResEO(sarBussStandResEO);
                //标准文件详情表存数据库
                SarBussStandFileEO sarStandFileEO = new SarBussStandFileEO();
                sarStandFileEO.setStandId(result.getData().getId());     //标准ID
                sarStandFileEO.setResId(sarBussStandResEO.getId());       //资源ID
                sarStandFileEO.setAttId(fileid);       //文件ID
                sarStandFileEO.setUseModule("");  //文件使用模式
                sarBussStandFileEOService.insertSarBussStandFileEO(sarStandFileEO);
            }
        }

        return Result.success("","添加成功",sarBussionessStandEO);
    }

    @ApiOperation(value = "|SarBussionessStandEO|导入")
    @PostMapping(value = "/importSarBussionessStand")
    //@RequiresPermissions("lawss:sarStandardsInfo:save")
    public ResponseMessage<SarBussionessStandEO> importSarBussionessStand(@RequestParam(value = "file", required = false) MultipartFile file) throws Exception {

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
        ExcelImportResult<SarBussionessStandExcelDto> result = ExcelImportUtil.importExcelVerify(is, SarBussionessStandExcelDto.class, params);
        // 如果校验不通过，返回错误信息
        if (result.isVerfiyFail()) {
            return Result.error("fail", "excel文件校验失败");
        }
        //excel读取到的数据
        List<SarBussionessStandExcelDto> datas = result.getList();
        try {
            return sarBussionessStandEOService.importSarBussionessStand(datas);
        } catch (Exception e) {
            return Result.error("fail", e.getMessage());
        }
    }

    @ApiOperation(value = "|SarBussionessStandEO|企业标准修改")
    @PostMapping(value = "/updateSarBussionessStand")
   // @RequiresPermissions("lawss:sarBussionessStand:update")
    public ResponseMessage<SarBussionessStandEO> updateSarBussionessStand(SarBussionessStandEO sarBussionessStandEO) throws Exception {
        sarBussionessStandEOService.updateSarBussionessStand(sarBussionessStandEO);
        return Result.success("","修改成功",sarBussionessStandEO);
    }

    /**
     * 将查询出来的数据导出到EXCEL表格中
     * @param page  标准信息
     * @return
     * @author gaoyan
     * date 2018-09-04
     */
    @ApiOperation(value = "|SarBussionessStandEO|导出excel")
    @GetMapping(value = "/exportSarBussionessStand")
    public void exportSarBussionessStand(SarBussionessStandEOPage page, HttpServletResponse response, HttpServletRequest request) {
        OutputStream os = null;
        Workbook workbook = null;
        try {
            response.setHeader("Content-Disposition",
                    "attachment; filename=" + ReadExcel.encodeFileName("下载文件.xlsx",request));
            response.setContentType("application/force-download");
            ExportParams exportParams = new ExportParams();
            exportParams.setType(ExcelType.XSSF);

            List<SarBussionessStandExcelDto> datas =  sarBussionessStandEOService.getSarBussionessStand(page);
           /* List<SarStandExcelDto> sarStandExcelVOList = new ArrayList<SarStandExcelDto>();
            if (datas != null && !datas.isEmpty()) {
                for (SarStandExcelEO eo : datas) {
                    SarStandExcelDto dto = new SarStandExcelDto();
                    BeanUtils.copyProperties(dto,eo);
                    sarStandExcelVOList.add(dto);
                }
            }*/
            workbook = ExcelExportUtil.exportExcel(exportParams, SarBussionessStandExcelDto.class, datas);
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


}
