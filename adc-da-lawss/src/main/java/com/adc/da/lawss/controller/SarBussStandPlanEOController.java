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
import com.adc.da.lawss.dto.SarCompileExportDto;
import com.adc.da.lawss.dto.StandPlanExportDto;
import com.adc.da.lawss.entity.SarBusSarCompileEO;
import com.adc.da.util.exception.AdcDaBaseException;
import com.adc.da.util.utils.IOUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.lawss.entity.SarBussStandPlanEO;
import com.adc.da.lawss.page.SarBussStandPlanEOPage;
import com.adc.da.lawss.service.SarBussStandPlanEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/${restPath}/lawss/sarBussStandPlan")
@Api(description = "|SarBussStandPlanEO|")
public class SarBussStandPlanEOController extends BaseController<SarBussStandPlanEO>{

    private static final Logger logger = LoggerFactory.getLogger(SarBussStandPlanEOController.class);

    @Autowired
    private SarBussStandPlanEOService sarBussStandPlanEOService;

	@ApiOperation(value = "|SarBussStandPlanEO|分页查询")
    @GetMapping("/page")
    /*@RequiresPermissions("lawss:sarBussStandPlan:page")*/
    public ResponseMessage<PageInfo<SarBussStandPlanEO>> page(SarBussStandPlanEOPage page) throws Exception {
        page.setValidFlag("0");
        page.setOrderBy("modify_time desc");
        List<SarBussStandPlanEO> rows = sarBussStandPlanEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

	@ApiOperation(value = "|SarBussStandPlanEO|查询")
    @GetMapping("")
    /*@RequiresPermissions("lawss:sarBussStandPlan:list")*/
    public ResponseMessage<List<SarBussStandPlanEO>> list(SarBussStandPlanEOPage page) throws Exception {
        return Result.success(sarBussStandPlanEOService.queryByList(page));
	}

    @ApiOperation(value = "|SarBussStandPlanEO|详情")
    @GetMapping("/{id}")
    /*@RequiresPermissions("lawss:sarBussStandPlan:get")*/
    public ResponseMessage<SarBussStandPlanEO> find(@PathVariable String id) throws Exception {
        return Result.success(sarBussStandPlanEOService.selectByPrimaryKey(id));
    }

    /**
     * @Author yangxuenan
     * @Description 新增标准年度计划
     * Date 2018/9/17 15:08
     * @Param [sarBussStandPlanEO]
     * @return com.adc.da.util.http.ResponseMessage<com.adc.da.lawss.entity.SarBussStandPlanEO>
     **/
    @ApiOperation(value = "|SarBussStandPlanEO|新增")
    @PostMapping("/createStandPlan")
    /*@RequiresPermissions("lawss:sarBussStandPlan:save")*/
    public ResponseMessage<SarBussStandPlanEO> create(SarBussStandPlanEO sarBussStandPlanEO) throws Exception {
        return sarBussStandPlanEOService.insertStandPlan(sarBussStandPlanEO);
    }

    /**
     * @Author yangxuenan
     * @Description 修改标准年度计划
     * Date 2018/9/17 15:08
     * @Param [sarBussStandPlanEO]
     * @return com.adc.da.util.http.ResponseMessage<com.adc.da.lawss.entity.SarBussStandPlanEO>
     **/
    @ApiOperation(value = "|SarBussStandPlanEO|修改")
    @PutMapping("/updateStandPlan")
    /*@RequiresPermissions("lawss:sarBussStandPlan:update")*/
    public ResponseMessage<SarBussStandPlanEO> update(SarBussStandPlanEO sarBussStandPlanEO) throws Exception {
        return sarBussStandPlanEOService.updateStandPlan(sarBussStandPlanEO);
    }

    @ApiOperation(value = "|SarBussStandPlanEO|删除")
    @DeleteMapping("/{id}")
    /*@RequiresPermissions("lawss:sarBussStandPlan:delete")*/
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        sarBussStandPlanEOService.deleteByPrimaryKey(id);
        logger.info("delete from SAR_BUSS_STAND_PLAN where id = {}", id);
        return Result.success();
    }

    /**
     * @Author yangxuenan
     * @Description 删除标准计划
     * Date 2018/9/17 15:31
     * @Param [sarBussStandPlanEO]
     * @return com.adc.da.util.http.ResponseMessage<com.adc.da.lawss.entity.SarBussStandPlanEO>
     **/
    @ApiOperation(value = "|SarBussStandPlanEO|删除标准计划")
    @PutMapping("/deleteStandPlan")
    /*@RequiresPermissions("lawss:sarBussStandPlan:update")*/
    public ResponseMessage<SarBussStandPlanEO> deleteStandPlan(SarBussStandPlanEO sarBussStandPlanEO) throws Exception {
        sarBussStandPlanEO.setValidFlag(1);
        int countDel = sarBussStandPlanEOService.updateByPrimaryKeySelective(sarBussStandPlanEO);
        if(countDel > 0){
            return Result.success("0","删除成功",sarBussStandPlanEO);
        } else {
            return Result.error("删除失败");
        }
    }

    /**
     * @Author yangxuenan
     * @Description 导出标准年度计划
     * Date 2018/9/17 16:07
     * @Param [response, request]
     * @return void
     **/
    @ApiOperation(value = "|SarLawsInfoEO|导出标准年度计划")
    @GetMapping("/exportStandPlan")
    public void exportStandPlan(HttpServletResponse response, HttpServletRequest request) throws Exception{
        OutputStream os = null;
        Workbook workbook = null;
        try{
            response.setHeader("Content-Disposition",
                    "attachment; filename=" + encodeFileName("导出年度标准计划.xlsx",request));
            response.setContentType("application/force-download");
            ExportParams exportParams = new ExportParams();
            exportParams.setType(ExcelType.XSSF);

            //存放需要导出的数据
            SarBussStandPlanEO sarBussStandPlanEO = new SarBussStandPlanEO();
            sarBussStandPlanEO.setStandName("1111");
            //将导出对象与dto对应
            List<StandPlanExportDto> dto = new ArrayList<>();
            BeanUtils.copyProperties(sarBussStandPlanEO, dto);

            //导出数据到Excel
            workbook = ExcelExportUtil.exportExcel(exportParams, StandPlanExportDto.class, dto);
            os = response.getOutputStream();
            workbook.write(os);
            os.flush();
        } catch (Exception e) {
            throw new AdcDaBaseException("导出年度标准计划文件失败，请重试");
        } finally {
            IOUtils.closeQuietly(os);
        }
    }

}
