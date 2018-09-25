package com.adc.da.lawss.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import com.adc.da.excel.poi.excel.ExcelExportUtil;
import com.adc.da.excel.poi.excel.ExcelImportUtil;
import com.adc.da.excel.poi.excel.entity.ExportParams;
import com.adc.da.excel.poi.excel.entity.ImportParams;
import com.adc.da.excel.poi.excel.entity.enums.ExcelType;
import com.adc.da.excel.poi.excel.entity.result.ExcelImportResult;
import com.adc.da.lawss.common.ReadExcel;
import com.adc.da.lawss.dto.SarStandExcelDto;
import com.adc.da.lawss.dto.SarStandItemsExcelDto;
import com.adc.da.lawss.page.SarStandardsInfoEOPage;
import com.adc.da.lawss.service.SarStandItemValEOService;
import com.adc.da.lawss.vo.SarStandItemsVO;
import com.adc.da.util.exception.AdcDaBaseException;
import com.adc.da.util.utils.IOUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.lawss.entity.SarStandItemsEO;
import com.adc.da.lawss.page.SarStandItemsEOPage;
import com.adc.da.lawss.service.SarStandItemsEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/${restPath}/lawss/sarStandItems")
@Api(description = "|标准条目信息|")
public class SarStandItemsEOController extends BaseController<SarStandItemsEO>{

    private static final Logger logger = LoggerFactory.getLogger(SarStandItemsEOController.class);

    @Autowired
    private SarStandItemsEOService sarStandItemsEOService;

    @Autowired
    private SarStandItemValEOService sarStandItemValEOService;

	@ApiOperation(value = "|SarStandItemsEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("lawss:sarStandItems:page")
    public ResponseMessage<PageInfo<SarStandItemsEO>> page(SarStandItemsEOPage page) throws Exception {
        List<SarStandItemsEO> rows = sarStandItemsEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

	/*@ApiOperation(value = "|SarStandItemsEO|查询")
    @GetMapping("")
    @RequiresPermissions("lawss:sarStandItems:list")
    public ResponseMessage<List<SarStandItemsEO>> list(SarStandItemsEOPage page) throws Exception {
        return Result.success(sarStandItemsEOService.queryByList(page));
	}*/

    @ApiOperation(value = "|SarStandItemsEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("lawss:sarStandItems:get")
    public ResponseMessage<SarStandItemsEO> find(@PathVariable String id) throws Exception {
        return Result.success(sarStandItemsEOService.selectByPrimaryKey(id));
    }

    /**
     * 新增标准分解单
     * @param sarStandItemsEO
     * @return
     * @author gaoyan
     * date 2018-09-04
     */
    @ApiOperation(value = "|SarStandItemsEO|新增")
    @PostMapping(value = "/addSarStandItemsList")
    //@RequiresPermissions("lawss:sarStandItems:save")
    public ResponseMessage<SarStandItemsEO> addSarStandItems(SarStandItemsEO sarStandItemsEO) throws Exception {
        sarStandItemsEOService.addSarStandItems(sarStandItemsEO);
        return Result.success("","新增标准条目成功",sarStandItemsEO);
    }

    /**
     * EXCEL导入标准分解单
     * @param file
     * @return
     * @author gaoyan
     * date 2018-09-04
     */
    @ApiOperation(value = "|SarStandItemsEO|导入")
    @PostMapping(value = "/importSarStandItemsList")
    //@RequiresPermissions("lawss:sarStandItems:save")
    public ResponseMessage<SarStandItemsEO> importSarStandItemsList(@RequestParam(value = "file", required = false) MultipartFile file ,String standId) throws Exception {
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
        ExcelImportResult<SarStandItemsExcelDto> result = ExcelImportUtil.importExcelVerify(is, SarStandItemsExcelDto.class, params);
        // 如果校验不通过，返回错误信息
        if (result.isVerfiyFail()) {
            return Result.error("fail", "excel文件校验失败");
        }
        //excel读取到的数据
        List<SarStandItemsExcelDto> datas = result.getList();
        try {
            return sarStandItemsEOService.importSarStandItemsData(datas,standId);
        } catch (Exception e) {
            return Result.error("fail", e.getMessage());
        }
    }


    /**
     * 编辑修改标准分解单
     * @param
     * @return
     * @author gaoyan
     * date 2018-09-04
     */
    @ApiOperation(value = "|SarStandItemsEO|修改")
    @PostMapping("/updateStandItem")
    //@RequiresPermissions("/updateStandItem")
    public ResponseMessage<SarStandItemsEO> updateStandItem(SarStandItemsEO sarStandItemsEO) throws Exception {
        sarStandItemsEOService.updateSarStandItemsEO(sarStandItemsEO);
        return Result.success(sarStandItemsEO);
    }

    /**
     * 删除标准分解单
     * @param
     * @return
     * @author gaoyan
     * date 2018-09-04
     */
    @ApiOperation(value = "|SarStandItemsEO|删除")
    @PostMapping("/deleteStandItem")
    //@RequiresPermissions("lawss:sarStandItems:delete")
    public ResponseMessage deleteStandItem( String[] id) throws Exception {
        for (String iditem :id){
            //删除标准条目
            sarStandItemsEOService.deleteByPrimaryKey(iditem);
            //删除标准条目关联表
            sarStandItemValEOService.deleteSarStandItemValByItemid(iditem);
        }
        logger.info("delete from SAR_STAND_ITEMS where id = {}", id);
        return Result.success("00","删除成功");
    }

    /**
     * 查询标准分解单
     * @param page
     * @return
     * @author gaoyan
     * date 2018-09-04
     */
    @ApiOperation(value = "|SarStandItemsEO|查询")
    @GetMapping("/getSarStandItemsList")
    //@RequiresPermissions("lawss:sarStandItems:list")
    public ResponseMessage<List<SarStandItemsVO>> getSarStandItemsList(SarStandItemsEOPage page) throws Exception {
        return Result.success(sarStandItemsEOService.querySarStandItemsList(page));
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
    public void exportStandardItemExcel(SarStandItemsEOPage page, HttpServletResponse response, HttpServletRequest request) throws Exception{
        OutputStream os = null;
        Workbook workbook = null;
        try {
            response.setHeader("Content-Disposition",
                    "attachment; filename=" + ReadExcel.encodeFileName("下载文件.xlsx",request));
            response.setContentType("application/force-download");
            ExportParams exportParams = new ExportParams();
            exportParams.setType(ExcelType.XSSF);

            List<SarStandItemsVO> datas =  sarStandItemsEOService.querySarStandItemsList(page);
            List<SarStandItemsExcelDto> sarStandExcelVOList = new ArrayList<SarStandItemsExcelDto>();
            if (datas != null && !datas.isEmpty()) {
                for (SarStandItemsVO eo : datas) {
                    SarStandItemsExcelDto dto = new SarStandItemsExcelDto();
                    BeanUtils.copyProperties(dto, eo);
                    sarStandExcelVOList.add(dto);
                }
            }
            workbook = ExcelExportUtil.exportExcel(exportParams, SarStandItemsExcelDto.class, sarStandExcelVOList);
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
