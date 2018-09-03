package com.adc.da.lawss.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.lawss.entity.UtCarExpandEO;
import com.adc.da.lawss.page.UtCarExpandEOPage;
import com.adc.da.lawss.service.UtCarExpandEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

@RestController
@RequestMapping("/${restPath}/lawss/utCarExpand")
@Api(description = "|UtCarExpandEO|")
public class UtCarExpandEOController extends BaseController<UtCarExpandEO>{

    private static final Logger logger = LoggerFactory.getLogger(UtCarExpandEOController.class);

    @Autowired
    private UtCarExpandEOService utCarExpandEOService;

	@ApiOperation(value = "|UtCarExpandEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("lawss:utCarExpand:page")
    public ResponseMessage<PageInfo<UtCarExpandEO>> page(UtCarExpandEOPage page) throws Exception {
        List<UtCarExpandEO> rows = utCarExpandEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

	@ApiOperation(value = "|UtCarExpandEO|查询")
    @GetMapping("")
    @RequiresPermissions("lawss:utCarExpand:list")
    public ResponseMessage<List<UtCarExpandEO>> list(UtCarExpandEOPage page) throws Exception {
        return Result.success(utCarExpandEOService.queryByList(page));
	}

    @ApiOperation(value = "|UtCarExpandEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("lawss:utCarExpand:get")
    public ResponseMessage<UtCarExpandEO> find(@PathVariable String id) throws Exception {
        return Result.success(utCarExpandEOService.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "|UtCarExpandEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("lawss:utCarExpand:save")
    public ResponseMessage<UtCarExpandEO> create(@RequestBody UtCarExpandEO utCarExpandEO) throws Exception {
        utCarExpandEOService.insertSelective(utCarExpandEO);
        return Result.success(utCarExpandEO);
    }

    @ApiOperation(value = "|UtCarExpandEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("lawss:utCarExpand:update")
    public ResponseMessage<UtCarExpandEO> update(@RequestBody UtCarExpandEO utCarExpandEO) throws Exception {
        utCarExpandEOService.updateByPrimaryKeySelective(utCarExpandEO);
        return Result.success(utCarExpandEO);
    }

    @ApiOperation(value = "|UtCarExpandEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("lawss:utCarExpand:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        utCarExpandEOService.deleteByPrimaryKey(id);
        logger.info("delete from UT_CAR_EXPAND where id = {}", id);
        return Result.success();
    }

}
