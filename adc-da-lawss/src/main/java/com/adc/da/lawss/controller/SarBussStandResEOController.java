package com.adc.da.lawss.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.lawss.entity.SarBussStandResEO;
import com.adc.da.lawss.page.SarBussStandResEOPage;
import com.adc.da.lawss.service.SarBussStandResEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

@RestController
@RequestMapping("/${restPath}/lawss/sarBussStandRes")
@Api(description = "|SarBussStandResEO|")
public class SarBussStandResEOController extends BaseController<SarBussStandResEO>{

    private static final Logger logger = LoggerFactory.getLogger(SarBussStandResEOController.class);

    @Autowired
    private SarBussStandResEOService sarBussStandResEOService;

	@ApiOperation(value = "|SarBussStandResEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("lawss:sarBussStandRes:page")
    public ResponseMessage<PageInfo<SarBussStandResEO>> page(SarBussStandResEOPage page) throws Exception {
        List<SarBussStandResEO> rows = sarBussStandResEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

	@ApiOperation(value = "|SarBussStandResEO|查询")
    @GetMapping("")
    @RequiresPermissions("lawss:sarBussStandRes:list")
    public ResponseMessage<List<SarBussStandResEO>> list(SarBussStandResEOPage page) throws Exception {
        return Result.success(sarBussStandResEOService.queryByList(page));
	}

    @ApiOperation(value = "|SarBussStandResEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("lawss:sarBussStandRes:get")
    public ResponseMessage<SarBussStandResEO> find(@PathVariable String id) throws Exception {
        return Result.success(sarBussStandResEOService.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "|SarBussStandResEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("lawss:sarBussStandRes:save")
    public ResponseMessage<SarBussStandResEO> create(@RequestBody SarBussStandResEO sarBussStandResEO) throws Exception {
        sarBussStandResEOService.insertSelective(sarBussStandResEO);
        return Result.success(sarBussStandResEO);
    }

    @ApiOperation(value = "|SarBussStandResEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("lawss:sarBussStandRes:update")
    public ResponseMessage<SarBussStandResEO> update(@RequestBody SarBussStandResEO sarBussStandResEO) throws Exception {
        sarBussStandResEOService.updateByPrimaryKeySelective(sarBussStandResEO);
        return Result.success(sarBussStandResEO);
    }

    @ApiOperation(value = "|SarBussStandResEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("lawss:sarBussStandRes:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        sarBussStandResEOService.deleteByPrimaryKey(id);
        logger.info("delete from SAR_BUSS_STAND_RES where id = {}", id);
        return Result.success();
    }

}
