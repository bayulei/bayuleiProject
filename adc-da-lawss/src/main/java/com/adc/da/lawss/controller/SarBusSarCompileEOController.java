package com.adc.da.lawss.controller;

import static com.adc.da.lawss.common.ReadExcel.encodeFileName;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.adc.da.excel.poi.excel.ExcelExportUtil;
import com.adc.da.excel.poi.excel.entity.ExportParams;
import com.adc.da.excel.poi.excel.entity.enums.ExcelType;
import com.adc.da.lawss.dto.LawsInfoExportDto;
import com.adc.da.lawss.dto.SarCompileExportDto;
import com.adc.da.lawss.entity.SarLawsInfoEO;
import com.adc.da.util.exception.AdcDaBaseException;
import com.adc.da.util.utils.IOUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.lawss.entity.SarBusSarCompileEO;
import com.adc.da.lawss.page.SarBusSarCompileEOPage;
import com.adc.da.lawss.service.SarBusSarCompileEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/${restPath}/lawss/sarBusSarCompile")
@Api(description = "|SarBusSarCompileEO|")
public class SarBusSarCompileEOController extends BaseController<SarBusSarCompileEO>{

    private static final Logger logger = LoggerFactory.getLogger(SarBusSarCompileEOController.class);

    @Autowired
    private SarBusSarCompileEOService sarBusSarCompileEOService;

	@ApiOperation(value = "|SarBusSarCompileEO|分页查询")
    @GetMapping("/page")
    /*@RequiresPermissions("lawss:sarBusSarCompile:page")*/
    public ResponseMessage<PageInfo<SarBusSarCompileEO>> page(SarBusSarCompileEOPage page) throws Exception {
        page.setValidFlag("0");
        List<SarBusSarCompileEO> rows = sarBusSarCompileEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

	@ApiOperation(value = "|SarBusSarCompileEO|查询")
    @GetMapping("")
    /*@RequiresPermissions("lawss:sarBusSarCompile:list")*/
    public ResponseMessage<List<SarBusSarCompileEO>> list(SarBusSarCompileEOPage page) throws Exception {
        return Result.success(sarBusSarCompileEOService.queryByList(page));
	}

    @ApiOperation(value = "|SarBusSarCompileEO|详情")
    @GetMapping("/{id}")
    /*@RequiresPermissions("lawss:sarBusSarCompile:get")*/
    public ResponseMessage<SarBusSarCompileEO> find(@PathVariable String id) throws Exception {
        return Result.success(sarBusSarCompileEOService.selectByPrimaryKey(id));
    }

    /**
     * @Author yangxuenan
     * @Description 新增标准台账
     * Date 2018/9/17 9:08
     * @Param [sarBusSarCompileEO]
     * @return com.adc.da.util.http.ResponseMessage<com.adc.da.lawss.entity.SarBusSarCompileEO>
     **/
    @ApiOperation(value = "|SarBusSarCompileEO|新增")
    @PostMapping("/createSarBusSarCompile")
    /*@RequiresPermissions("lawss:sarBusSarCompile:save")*/
    public ResponseMessage<SarBusSarCompileEO> create(SarBusSarCompileEO sarBusSarCompileEO) throws Exception {
        return sarBusSarCompileEOService.insertSarBusSarCompile(sarBusSarCompileEO);
    }

    /**
     * @Author yangxuenan
     * @Description 修改标准台账
     * Date 2018/9/17 9:45
     * @Param [sarBusSarCompileEO]
     * @return com.adc.da.util.http.ResponseMessage<com.adc.da.lawss.entity.SarBusSarCompileEO>
     **/
    @ApiOperation(value = "|SarBusSarCompileEO|修改")
    @PutMapping("/updateSarBusSarCompile")
    /*@RequiresPermissions("lawss:sarBusSarCompile:update")*/
    public ResponseMessage<SarBusSarCompileEO> update(SarBusSarCompileEO sarBusSarCompileEO) throws Exception {
        return sarBusSarCompileEOService.updateSarBusSarCompile(sarBusSarCompileEO);
    }

    @ApiOperation(value = "|SarBusSarCompileEO|删除")
    @DeleteMapping("/{id}")
    /*@RequiresPermissions("lawss:sarBusSarCompile:delete")*/
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        sarBusSarCompileEOService.deleteByPrimaryKey(id);
        logger.info("delete from SAR_BUS_SAR_COMPILE where id = {}", id);
        return Result.success();
    }

    /**
     * @Author yangxuenan
     * @Description 删除标准台账
     * Date 2018/9/17 10:21
     * @Param [sarBusSarCompileEO]
     * @return com.adc.da.util.http.ResponseMessage<com.adc.da.lawss.entity.SarBusSarCompileEO>
     **/
    @ApiOperation(value = "|SarBusSarCompileEO|删除台账")
    @PutMapping("/deleteSarBusSarCompile")
    /*@RequiresPermissions("lawss:sarBusSarCompile:update")*/
    public ResponseMessage<SarBusSarCompileEO> deleteSarBusSarCompile(SarBusSarCompileEO sarBusSarCompileEO) throws Exception {
        sarBusSarCompileEO.setValidFlag(1);
        int countDel = sarBusSarCompileEOService.updateByPrimaryKeySelective(sarBusSarCompileEO);
        if(countDel > 0){
            return Result.success("0","删除成功",sarBusSarCompileEO);
        } else {
            return Result.error("删除失败");
        }
    }


    /**
     * @Author yangxuenan
     * @Description 导出标准台账
     * Date 2018/9/17 10:47
     * @Param [response, request]
     * @return void
     **/
    @ApiOperation(value = "|SarLawsInfoEO|导出标准台账")
    @GetMapping("/exportSarBusSarCompile")
    public void exportSarBusSarCompile(HttpServletResponse response, HttpServletRequest request) throws Exception{
        OutputStream os = null;
        Workbook workbook = null;
        try{
            response.setHeader("Content-Disposition",
                    "attachment; filename=" + encodeFileName("导出标准台账.xlsx",request));
            response.setContentType("application/force-download");
            ExportParams exportParams = new ExportParams();
            exportParams.setType(ExcelType.XSSF);

            //存放需要导出的数据
            SarBusSarCompileEO sarBusSarCompileEO = new SarBusSarCompileEO();
            sarBusSarCompileEO.setStandCode("111");
            //将导出对象与dto对应
            List<SarCompileExportDto> dto = new ArrayList<>();
            BeanUtils.copyProperties(sarBusSarCompileEO, dto);

            //导出数据到Excel
            workbook = ExcelExportUtil.exportExcel(exportParams, SarCompileExportDto.class, dto);
            os = response.getOutputStream();
            workbook.write(os);
            os.flush();
        } catch (Exception e) {
            throw new AdcDaBaseException("导出标准台账文件失败，请重试");
        } finally {
            IOUtils.closeQuietly(os);
        }
    }


}
