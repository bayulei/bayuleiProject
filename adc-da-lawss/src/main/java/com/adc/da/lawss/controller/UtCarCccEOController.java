package com.adc.da.lawss.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.lawss.entity.UtCarCccEO;
import com.adc.da.lawss.page.UtCarCccEOPage;
import com.adc.da.lawss.service.UtCarCccEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

@RestController
@RequestMapping("/${restPath}/lawss/utCarCcc")
@Api(description = "|UtCarCccEO|")
public class UtCarCccEOController extends BaseController<UtCarCccEO>{

    private static final Logger logger = LoggerFactory.getLogger(UtCarCccEOController.class);

    @Autowired
    private UtCarCccEOService utCarCccEOService;

	@ApiOperation(value = "|UtCarCccEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("lawss:utCarCcc:page")
    public ResponseMessage<PageInfo<UtCarCccEO>> page(UtCarCccEOPage page) throws Exception {
        List<UtCarCccEO> rows = utCarCccEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

	@ApiOperation(value = "|UtCarCccEO|查询")
    @GetMapping("")
    @RequiresPermissions("lawss:utCarCcc:list")
    public ResponseMessage<List<UtCarCccEO>> list(UtCarCccEOPage page) throws Exception {
        return Result.success(utCarCccEOService.queryByList(page));
	}

    @ApiOperation(value = "|UtCarCccEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("lawss:utCarCcc:get")
    public ResponseMessage<UtCarCccEO> find(@PathVariable String id) throws Exception {
        return Result.success(utCarCccEOService.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "|UtCarCccEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("lawss:utCarCcc:save")
    public ResponseMessage<UtCarCccEO> create(@RequestBody UtCarCccEO utCarCccEO) throws Exception {
        utCarCccEOService.insertSelective(utCarCccEO);
        return Result.success(utCarCccEO);
    }

    @ApiOperation(value = "|UtCarCccEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("lawss:utCarCcc:update")
    public ResponseMessage<UtCarCccEO> update(@RequestBody UtCarCccEO utCarCccEO) throws Exception {
        utCarCccEOService.updateByPrimaryKeySelective(utCarCccEO);
        return Result.success(utCarCccEO);
    }

    @ApiOperation(value = "|UtCarCccEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("lawss:utCarCcc:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        utCarCccEOService.deleteByPrimaryKey(id);
        logger.info("delete from UT_CAR_CCC where id = {}", id);
        return Result.success();
    }

}
