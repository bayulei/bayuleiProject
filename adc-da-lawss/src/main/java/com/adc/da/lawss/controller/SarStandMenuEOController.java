package com.adc.da.lawss.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.lawss.entity.SarStandMenuEO;
import com.adc.da.lawss.page.SarStandMenuEOPage;
import com.adc.da.lawss.service.SarStandMenuEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

@RestController
@RequestMapping("/${restPath}/lawss/sarStandMenu")
@Api(description = "|SarStandMenuEO|")
public class SarStandMenuEOController extends BaseController<SarStandMenuEO>{

    private static final Logger logger = LoggerFactory.getLogger(SarStandMenuEOController.class);

    @Autowired
    private SarStandMenuEOService sarStandMenuEOService;

	@ApiOperation(value = "|SarStandMenuEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("lawss:sarStandMenu:page")
    public ResponseMessage<PageInfo<SarStandMenuEO>> page(SarStandMenuEOPage page) throws Exception {
        List<SarStandMenuEO> rows = sarStandMenuEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

	@ApiOperation(value = "|SarStandMenuEO|查询")
    @GetMapping("")
    @RequiresPermissions("lawss:sarStandMenu:list")
    public ResponseMessage<List<SarStandMenuEO>> list(SarStandMenuEOPage page) throws Exception {
        return Result.success(sarStandMenuEOService.queryByList(page));
	}

    @ApiOperation(value = "|SarStandMenuEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("lawss:sarStandMenu:get")
    public ResponseMessage<SarStandMenuEO> find(@PathVariable String id) throws Exception {
        return Result.success(sarStandMenuEOService.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "|SarStandMenuEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("lawss:sarStandMenu:save")
    public ResponseMessage<SarStandMenuEO> create(@RequestBody SarStandMenuEO sarStandMenuEO) throws Exception {
        sarStandMenuEOService.insertSelective(sarStandMenuEO);
        return Result.success(sarStandMenuEO);
    }

    @ApiOperation(value = "|SarStandMenuEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("lawss:sarStandMenu:update")
    public ResponseMessage<SarStandMenuEO> update(@RequestBody SarStandMenuEO sarStandMenuEO) throws Exception {
        sarStandMenuEOService.updateByPrimaryKeySelective(sarStandMenuEO);
        return Result.success(sarStandMenuEO);
    }

    @ApiOperation(value = "|SarStandMenuEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("lawss:sarStandMenu:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        sarStandMenuEOService.deleteByPrimaryKey(id);
        logger.info("delete from SAR_STAND_MENU where id = {}", id);
        return Result.success();
    }

}
