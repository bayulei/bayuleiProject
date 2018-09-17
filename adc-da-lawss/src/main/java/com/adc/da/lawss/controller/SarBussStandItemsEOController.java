package com.adc.da.lawss.controller;

import com.adc.da.base.web.BaseController;
import com.adc.da.excel.poi.excel.ExcelExportUtil;
import com.adc.da.excel.poi.excel.ExcelImportUtil;
import com.adc.da.excel.poi.excel.entity.ExportParams;
import com.adc.da.excel.poi.excel.entity.ImportParams;
import com.adc.da.excel.poi.excel.entity.enums.ExcelType;
import com.adc.da.excel.poi.excel.entity.result.ExcelImportResult;
import com.adc.da.lawss.common.ReadExcel;
import com.adc.da.lawss.dto.SarBussStandtemsExcelDto;
import com.adc.da.lawss.entity.SarBussStandItemsEO;
import com.adc.da.lawss.page.SarBussStandItemsEOPage;
import com.adc.da.lawss.service.SarBussStandItemValEOService;
import com.adc.da.lawss.service.SarBussStandItemsEOService;
import com.adc.da.util.exception.AdcDaBaseException;
import com.adc.da.util.http.PageInfo;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.utils.IOUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.beanutils.BeanUtils;
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
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping("/${restPath}/lawss/sarBussStandItems")
@Api(description = "|SarBussStandItemsEO|")
public class SarBussStandItemsEOController extends BaseController<SarBussStandItemsEO>{

    private static final Logger logger = LoggerFactory.getLogger(SarBussStandItemsEOController.class);

    @Autowired
    private SarBussStandItemsEOService sarBussStandItemsEOService;

    @Autowired
    private SarBussStandItemValEOService sarBussStandItemValEOService;

	@ApiOperation(value = "|SarBussStandItemsEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("lawss:sarBussStandItems:page")
    public ResponseMessage<PageInfo<SarBussStandItemsEO>> page(SarBussStandItemsEOPage page) throws Exception {
        List<SarBussStandItemsEO> rows = sarBussStandItemsEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

	@ApiOperation(value = "|SarBussStandItemsEO|查询")
    @GetMapping("")
    @RequiresPermissions("lawss:sarBussStandItems:list")
    public ResponseMessage<List<SarBussStandItemsEO>> list(SarBussStandItemsEOPage page) throws Exception {
        return Result.success(sarBussStandItemsEOService.queryByList(page));
	}

    @ApiOperation(value = "|SarBussStandItemsEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("lawss:sarBussStandItems:get")
    public ResponseMessage<SarBussStandItemsEO> find(@PathVariable String id) throws Exception {
        return Result.success(sarBussStandItemsEOService.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "|SarBussStandItemsEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("lawss:sarBussStandItems:save")
    public ResponseMessage<SarBussStandItemsEO> create(@RequestBody SarBussStandItemsEO sarBussStandItemsEO) throws Exception {
        sarBussStandItemsEOService.insertSelective(sarBussStandItemsEO);
        return Result.success(sarBussStandItemsEO);
    }

    @ApiOperation(value = "|SarBussStandItemsEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("lawss:sarBussStandItems:update")
    public ResponseMessage<SarBussStandItemsEO> update(@RequestBody SarBussStandItemsEO sarBussStandItemsEO) throws Exception {
        sarBussStandItemsEOService.updateByPrimaryKeySelective(sarBussStandItemsEO);
        return Result.success(sarBussStandItemsEO);
    }

    @ApiOperation(value = "|SarBussStandItemsEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("lawss:sarBussStandItems:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        sarBussStandItemsEOService.deleteByPrimaryKey(id);
        logger.info("delete from SAR_BUSS_STAND_ITEMS where id = {}", id);
        return Result.success();
    }

    @ApiOperation(value = "|SarBussStandItemsEO|查询")
    @GetMapping("/getSarBussStandItemsList")
    //@RequiresPermissions("lawss:sarStandItems:list")
    public ResponseMessage<List<SarBussStandItemsEO>> getSarBussStandItemsEOList(SarBussStandItemsEOPage page) throws Exception {
        return Result.success(sarBussStandItemsEOService.querySarBussStandItemsList(page));
    }

    /**
     * 新增标准分解单
     * @param
     * @return
     * @author gaoyan
     * date 2018-09-04
     */
    @ApiOperation(value = "|SarBussStandItemsEO|新增")
    @PostMapping(value = "/addSarBussStandItemsList")
    //@RequiresPermissions("lawss:sarStandItems:save")
    public ResponseMessage<SarBussStandItemsEO> addSarBussStandItemsList(SarBussStandItemsEO sarBussStandItemsEO) throws Exception {
        sarBussStandItemsEOService.addSarBussStandItems(sarBussStandItemsEO);
        return Result.success("00","添加成功",sarBussStandItemsEO);
    }

    @ApiOperation(value = "|SarBussStandItemsEO|修改")
    @PostMapping("/updateSarBussStandItems")
    //@RequiresPermissions("/updateStandItem")
    public ResponseMessage<SarBussStandItemsEO> updateSarBussStandItems(SarBussStandItemsEO sarBussStandItemsEO) throws Exception {
        sarBussStandItemsEOService.updateSarBussStandItemsEO(sarBussStandItemsEO);
        return Result.success(sarBussStandItemsEO);
    }

    @ApiOperation(value = "|SarBussStandItemsEO|删除")
    @PostMapping("/deleteSarBussStandItems")
    //@RequiresPermissions("lawss:sarStandItems:delete")
    public ResponseMessage deleteSarBussStandItems( String[] id) throws Exception {
        for (String iditem :id){
            //删除标准条目
            sarBussStandItemsEOService.deleteByPrimaryKey(iditem);
            //删除标准条目关联表
            sarBussStandItemValEOService.deleteSarBussStandItemByItemid(iditem);
        }
        logger.info("delete from SAR_STAND_ITEMS where id = {}", id);
        return Result.success("00","删除成功",id);
    }

    @ApiOperation(value = "|SarBussStandItemsEO|导入企业标准分解单")
    @PostMapping(value = "/importSarBussStandItemsList")
    //@RequiresPermissions("lawss:sarStandItems:save")
    public ResponseMessage<SarBussStandItemsEO> importSarBussStandItemsList(@RequestParam(value = "file", required = false) MultipartFile file , String standId) throws Exception {
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
        ExcelImportResult<SarBussStandtemsExcelDto> result = ExcelImportUtil.importExcelVerify(is, SarBussStandtemsExcelDto.class, params);
        // 如果校验不通过，返回错误信息
        if (result.isVerfiyFail()) {
            return Result.error("fail", "excel文件校验失败");
        }
        //excel读取到的数据
        List<SarBussStandtemsExcelDto> datas = result.getList();
        try {
            return sarBussStandItemsEOService.importSarBussStandItemsData(datas,standId);
        } catch (Exception e) {
            return Result.error("fail", e.getMessage());
        }
    }

    /**
     * 导出标准分解单
     * @param page
     * @return
     * @author gaoyan
     * date 2018-09-17
     */
    @ApiOperation(value = "|SysCorpEO|导出excel")
    @GetMapping(value = "/exportStandardItemExcel")
    public void exportStandardItemExcel(SarBussStandItemsEOPage page, HttpServletResponse response, HttpServletRequest request) throws Exception{
        OutputStream os = null;
        Workbook workbook = null;
        try {
            response.setHeader("Content-Disposition",
                    "attachment; filename=" + ReadExcel.encodeFileName("下载文件.xlsx",request));
            response.setContentType("application/force-download");
            ExportParams exportParams = new ExportParams();
            exportParams.setType(ExcelType.XSSF);

            List<SarBussStandItemsEO> datas =  sarBussStandItemsEOService.querySarBussStandItemsList(page);
            List<SarBussStandtemsExcelDto> sarStandExcelVOList = new ArrayList<SarBussStandtemsExcelDto>();
            if (datas != null && !datas.isEmpty()) {
                for (SarBussStandItemsEO eo : datas) {
                    SarBussStandtemsExcelDto dto = new SarBussStandtemsExcelDto();
                    BeanUtils.copyProperties(dto, eo);
                    sarStandExcelVOList.add(dto);
                }
            }
            workbook = ExcelExportUtil.exportExcel(exportParams, SarBussStandtemsExcelDto.class, sarStandExcelVOList);
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
