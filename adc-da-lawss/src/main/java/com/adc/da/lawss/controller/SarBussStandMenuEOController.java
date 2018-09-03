package com.adc.da.lawss.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.lawss.entity.SarBussStandMenuEO;
import com.adc.da.lawss.page.SarBussStandMenuEOPage;
import com.adc.da.lawss.service.SarBussStandMenuEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

@RestController
@RequestMapping("/${restPath}/lawss/sarBussStandMenu")
@Api(description = "|SarBussStandMenuEO|")
public class SarBussStandMenuEOController extends BaseController<SarBussStandMenuEO>{

    private static final Logger logger = LoggerFactory.getLogger(SarBussStandMenuEOController.class);

    @Autowired
    private SarBussStandMenuEOService sarBussStandMenuEOService;

	@ApiOperation(value = "|SarBussStandMenuEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("lawss:sarBussStandMenu:page")
    public ResponseMessage<PageInfo<SarBussStandMenuEO>> page(SarBussStandMenuEOPage page) throws Exception {
        List<SarBussStandMenuEO> rows = sarBussStandMenuEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

	@ApiOperation(value = "|SarBussStandMenuEO|查询")
    @GetMapping("")
    @RequiresPermissions("lawss:sarBussStandMenu:list")
    public ResponseMessage<List<SarBussStandMenuEO>> list(SarBussStandMenuEOPage page) throws Exception {
        return Result.success(sarBussStandMenuEOService.queryByList(page));
	}

    @ApiOperation(value = "|SarBussStandMenuEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("lawss:sarBussStandMenu:get")
    public ResponseMessage<SarBussStandMenuEO> find(@PathVariable String id) throws Exception {
        return Result.success(sarBussStandMenuEOService.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "|SarBussStandMenuEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("lawss:sarBussStandMenu:save")
    public ResponseMessage<SarBussStandMenuEO> create(@RequestBody SarBussStandMenuEO sarBussStandMenuEO) throws Exception {
        sarBussStandMenuEOService.insertSelective(sarBussStandMenuEO);
        return Result.success(sarBussStandMenuEO);
    }

    @ApiOperation(value = "|SarBussStandMenuEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("lawss:sarBussStandMenu:update")
    public ResponseMessage<SarBussStandMenuEO> update(@RequestBody SarBussStandMenuEO sarBussStandMenuEO) throws Exception {
        sarBussStandMenuEOService.updateByPrimaryKeySelective(sarBussStandMenuEO);
        return Result.success(sarBussStandMenuEO);
    }

    @ApiOperation(value = "|SarBussStandMenuEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("lawss:sarBussStandMenu:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        sarBussStandMenuEOService.deleteByPrimaryKey(id);
        logger.info("delete from SAR_BUSS_STAND_MENU where id = {}", id);
        return Result.success();
    }

}
