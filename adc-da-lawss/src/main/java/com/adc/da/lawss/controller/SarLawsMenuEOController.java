package com.adc.da.lawss.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.lawss.entity.SarLawsMenuEO;
import com.adc.da.lawss.page.SarLawsMenuEOPage;
import com.adc.da.lawss.service.SarLawsMenuEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

@RestController
@RequestMapping("/${restPath}/lawss/sarLawsMenu")
@Api(description = "|SarLawsMenuEO|")
public class SarLawsMenuEOController extends BaseController<SarLawsMenuEO>{

    private static final Logger logger = LoggerFactory.getLogger(SarLawsMenuEOController.class);

    @Autowired
    private SarLawsMenuEOService sarLawsMenuEOService;

	@ApiOperation(value = "|SarLawsMenuEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("lawss:sarLawsMenu:page")
    public ResponseMessage<PageInfo<SarLawsMenuEO>> page(SarLawsMenuEOPage page) throws Exception {
        List<SarLawsMenuEO> rows = sarLawsMenuEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

	@ApiOperation(value = "|SarLawsMenuEO|查询")
    @GetMapping("")
    @RequiresPermissions("lawss:sarLawsMenu:list")
    public ResponseMessage<List<SarLawsMenuEO>> list(SarLawsMenuEOPage page) throws Exception {
        return Result.success(sarLawsMenuEOService.queryByList(page));
	}

    @ApiOperation(value = "|SarLawsMenuEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("lawss:sarLawsMenu:get")
    public ResponseMessage<SarLawsMenuEO> find(@PathVariable String id) throws Exception {
        return Result.success(sarLawsMenuEOService.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "|SarLawsMenuEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("lawss:sarLawsMenu:save")
    public ResponseMessage<SarLawsMenuEO> create(@RequestBody SarLawsMenuEO sarLawsMenuEO) throws Exception {
        sarLawsMenuEOService.insertSelective(sarLawsMenuEO);
        return Result.success(sarLawsMenuEO);
    }

    @ApiOperation(value = "|SarLawsMenuEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("lawss:sarLawsMenu:update")
    public ResponseMessage<SarLawsMenuEO> update(@RequestBody SarLawsMenuEO sarLawsMenuEO) throws Exception {
        sarLawsMenuEOService.updateByPrimaryKeySelective(sarLawsMenuEO);
        return Result.success(sarLawsMenuEO);
    }

    @ApiOperation(value = "|SarLawsMenuEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("lawss:sarLawsMenu:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        sarLawsMenuEOService.deleteByPrimaryKey(id);
        logger.info("delete from SAR_LAWS_MENU where id = {}", id);
        return Result.success();
    }

}
