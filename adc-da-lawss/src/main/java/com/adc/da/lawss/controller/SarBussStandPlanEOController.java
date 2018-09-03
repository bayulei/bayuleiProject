package com.adc.da.lawss.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

@RestController
@RequestMapping("/${restPath}/lawss/sarBussStandPlan")
@Api(description = "|SarBussStandPlanEO|")
public class SarBussStandPlanEOController extends BaseController<SarBussStandPlanEO>{

    private static final Logger logger = LoggerFactory.getLogger(SarBussStandPlanEOController.class);

    @Autowired
    private SarBussStandPlanEOService sarBussStandPlanEOService;

	@ApiOperation(value = "|SarBussStandPlanEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("lawss:sarBussStandPlan:page")
    public ResponseMessage<PageInfo<SarBussStandPlanEO>> page(SarBussStandPlanEOPage page) throws Exception {
        List<SarBussStandPlanEO> rows = sarBussStandPlanEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

	@ApiOperation(value = "|SarBussStandPlanEO|查询")
    @GetMapping("")
    @RequiresPermissions("lawss:sarBussStandPlan:list")
    public ResponseMessage<List<SarBussStandPlanEO>> list(SarBussStandPlanEOPage page) throws Exception {
        return Result.success(sarBussStandPlanEOService.queryByList(page));
	}

    @ApiOperation(value = "|SarBussStandPlanEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("lawss:sarBussStandPlan:get")
    public ResponseMessage<SarBussStandPlanEO> find(@PathVariable String id) throws Exception {
        return Result.success(sarBussStandPlanEOService.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "|SarBussStandPlanEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("lawss:sarBussStandPlan:save")
    public ResponseMessage<SarBussStandPlanEO> create(@RequestBody SarBussStandPlanEO sarBussStandPlanEO) throws Exception {
        sarBussStandPlanEOService.insertSelective(sarBussStandPlanEO);
        return Result.success(sarBussStandPlanEO);
    }

    @ApiOperation(value = "|SarBussStandPlanEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("lawss:sarBussStandPlan:update")
    public ResponseMessage<SarBussStandPlanEO> update(@RequestBody SarBussStandPlanEO sarBussStandPlanEO) throws Exception {
        sarBussStandPlanEOService.updateByPrimaryKeySelective(sarBussStandPlanEO);
        return Result.success(sarBussStandPlanEO);
    }

    @ApiOperation(value = "|SarBussStandPlanEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("lawss:sarBussStandPlan:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        sarBussStandPlanEOService.deleteByPrimaryKey(id);
        logger.info("delete from SAR_BUSS_STAND_PLAN where id = {}", id);
        return Result.success();
    }

}
