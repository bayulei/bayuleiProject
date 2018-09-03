package com.adc.da.lawss.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.lawss.entity.UtCarNoticeEO;
import com.adc.da.lawss.page.UtCarNoticeEOPage;
import com.adc.da.lawss.service.UtCarNoticeEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

@RestController
@RequestMapping("/${restPath}/lawss/utCarNotice")
@Api(description = "|UtCarNoticeEO|")
public class UtCarNoticeEOController extends BaseController<UtCarNoticeEO>{

    private static final Logger logger = LoggerFactory.getLogger(UtCarNoticeEOController.class);

    @Autowired
    private UtCarNoticeEOService utCarNoticeEOService;

	@ApiOperation(value = "|UtCarNoticeEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("lawss:utCarNotice:page")
    public ResponseMessage<PageInfo<UtCarNoticeEO>> page(UtCarNoticeEOPage page) throws Exception {
        List<UtCarNoticeEO> rows = utCarNoticeEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

	@ApiOperation(value = "|UtCarNoticeEO|查询")
    @GetMapping("")
    @RequiresPermissions("lawss:utCarNotice:list")
    public ResponseMessage<List<UtCarNoticeEO>> list(UtCarNoticeEOPage page) throws Exception {
        return Result.success(utCarNoticeEOService.queryByList(page));
	}

    @ApiOperation(value = "|UtCarNoticeEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("lawss:utCarNotice:get")
    public ResponseMessage<UtCarNoticeEO> find(@PathVariable String id) throws Exception {
        return Result.success(utCarNoticeEOService.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "|UtCarNoticeEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("lawss:utCarNotice:save")
    public ResponseMessage<UtCarNoticeEO> create(@RequestBody UtCarNoticeEO utCarNoticeEO) throws Exception {
        utCarNoticeEOService.insertSelective(utCarNoticeEO);
        return Result.success(utCarNoticeEO);
    }

    @ApiOperation(value = "|UtCarNoticeEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("lawss:utCarNotice:update")
    public ResponseMessage<UtCarNoticeEO> update(@RequestBody UtCarNoticeEO utCarNoticeEO) throws Exception {
        utCarNoticeEOService.updateByPrimaryKeySelective(utCarNoticeEO);
        return Result.success(utCarNoticeEO);
    }

    @ApiOperation(value = "|UtCarNoticeEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("lawss:utCarNotice:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        utCarNoticeEOService.deleteByPrimaryKey(id);
        logger.info("delete from UT_CAR_NOTICE where id = {}", id);
        return Result.success();
    }

}
