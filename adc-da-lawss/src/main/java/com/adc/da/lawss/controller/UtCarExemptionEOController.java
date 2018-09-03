package com.adc.da.lawss.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.lawss.entity.UtCarExemptionEO;
import com.adc.da.lawss.page.UtCarExemptionEOPage;
import com.adc.da.lawss.service.UtCarExemptionEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

@RestController
@RequestMapping("/${restPath}/lawss/utCarExemption")
@Api(description = "|UtCarExemptionEO|")
public class UtCarExemptionEOController extends BaseController<UtCarExemptionEO>{

    private static final Logger logger = LoggerFactory.getLogger(UtCarExemptionEOController.class);

    @Autowired
    private UtCarExemptionEOService utCarExemptionEOService;

	@ApiOperation(value = "|UtCarExemptionEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("lawss:utCarExemption:page")
    public ResponseMessage<PageInfo<UtCarExemptionEO>> page(UtCarExemptionEOPage page) throws Exception {
        List<UtCarExemptionEO> rows = utCarExemptionEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

	@ApiOperation(value = "|UtCarExemptionEO|查询")
    @GetMapping("")
    @RequiresPermissions("lawss:utCarExemption:list")
    public ResponseMessage<List<UtCarExemptionEO>> list(UtCarExemptionEOPage page) throws Exception {
        return Result.success(utCarExemptionEOService.queryByList(page));
	}

    @ApiOperation(value = "|UtCarExemptionEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("lawss:utCarExemption:get")
    public ResponseMessage<UtCarExemptionEO> find(@PathVariable String id) throws Exception {
        return Result.success(utCarExemptionEOService.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "|UtCarExemptionEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("lawss:utCarExemption:save")
    public ResponseMessage<UtCarExemptionEO> create(@RequestBody UtCarExemptionEO utCarExemptionEO) throws Exception {
        utCarExemptionEOService.insertSelective(utCarExemptionEO);
        return Result.success(utCarExemptionEO);
    }

    @ApiOperation(value = "|UtCarExemptionEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("lawss:utCarExemption:update")
    public ResponseMessage<UtCarExemptionEO> update(@RequestBody UtCarExemptionEO utCarExemptionEO) throws Exception {
        utCarExemptionEOService.updateByPrimaryKeySelective(utCarExemptionEO);
        return Result.success(utCarExemptionEO);
    }

    @ApiOperation(value = "|UtCarExemptionEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("lawss:utCarExemption:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        utCarExemptionEOService.deleteByPrimaryKey(id);
        logger.info("delete from UT_CAR_EXEMPTION where id = {}", id);
        return Result.success();
    }

}
