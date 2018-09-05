package com.adc.da.lawss.controller;

import com.adc.da.base.web.BaseController;
import com.adc.da.excel.poi.excel.ExcelExportUtil;
import com.adc.da.excel.poi.excel.ExcelImportUtil;
import com.adc.da.excel.poi.excel.entity.ExportParams;
import com.adc.da.excel.poi.excel.entity.ImportParams;
import com.adc.da.excel.poi.excel.entity.enums.ExcelType;
import com.adc.da.excel.poi.excel.entity.result.ExcelImportResult;
import com.adc.da.lawss.common.ReadExcel;
import com.adc.da.lawss.entity.SarStandardsInfoEO;
import com.adc.da.lawss.page.SarStandardsInfoEOPage;
import com.adc.da.lawss.service.SarStandardsInfoEOService;
import com.adc.da.lawss.vo.SarStandExcelDto;
import com.adc.da.util.exception.AdcDaBaseException;
import com.adc.da.util.http.PageInfo;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.utils.IOUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

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

	@ApiOperation(value = "|SarStandardsInfoEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("lawss:sarStandardsInfo:page")
    public ResponseMessage<PageInfo<SarStandardsInfoEO>> page(SarStandardsInfoEOPage page) throws Exception {
        List<SarStandardsInfoEO> rows = sarStandardsInfoEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
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
     * 添加一条标准
     * @param sarStandardsInfoEO  标准信息
     * @return
     * @author gaoyan
     * date 2018-09-04
     */
    @ApiOperation(value = "|SarStandardsInfoEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE,value = "addarStandardsInfo")
    @RequiresPermissions("lawss:sarStandardsInfo:save")
    public ResponseMessage<SarStandardsInfoEO> create(@RequestBody SarStandardsInfoEO sarStandardsInfoEO,@RequestParam("idPic") MultipartFile[] multipartfiles) throws Exception {
        sarStandardsInfoEOService.createSarStandardsInfo(sarStandardsInfoEO);

        //标准文件资源表，标准文件详情表中插入数据，需要下载文件，并保存数据

        return Result.success(sarStandardsInfoEO);
    }

    /**
     * EXCEL导入标准
     * @param file  标准信息
     * @return
     * @author gaoyan
     * date 2018-09-04
     */
    @ApiOperation(value = "|SarStandardsInfoEO|导入")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE,value = "importStandardsInfo")
    //@RequiresPermissions("lawss:sarStandardsInfo:save")
    public ResponseMessage<SarStandardsInfoEO> importStandardsInfo(@RequestParam(value = "file", required = false) MultipartFile file) throws Exception {

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
        ExcelImportResult<SarStandardsInfoEO> result = ExcelImportUtil.importExcelVerify(is, SarStandardsInfoEO.class, params);
        // 如果校验不通过，返回错误信息
        if (result.isVerfiyFail()) {
            return Result.error("fail", "excel文件校验失败");
        }
        //excel读取到的数据
        List<SarStandardsInfoEO> datas = result.getList();
        try {
            return sarStandardsInfoEOService.importSarStandardsInfoData(datas);
        } catch (Exception e) {
            return Result.error("fail", e.getMessage());
        }
    }

    @ApiOperation(value = "|SarStandardsInfoEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("lawss:sarStandardsInfo:update")
    public ResponseMessage<SarStandardsInfoEO> update(@RequestBody SarStandardsInfoEO sarStandardsInfoEO) throws Exception {
        sarStandardsInfoEOService.updateByPrimaryKeySelective(sarStandardsInfoEO);
        return Result.success(sarStandardsInfoEO);
    }

    @ApiOperation(value = "|SarStandardsInfoEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("lawss:sarStandardsInfo:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
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

}
