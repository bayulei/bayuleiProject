package com.adc.da.lawss.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.lawss.entity.SarMenuEO;
import com.adc.da.lawss.page.SarMenuEOPage;
import com.adc.da.lawss.service.SarMenuEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

@RestController
@RequestMapping("/${restPath}/lawss/sarMenu")
@Api(description = "|SarMenuEO|")
public class SarMenuEOController extends BaseController<SarMenuEO>{

    private static final Logger logger = LoggerFactory.getLogger(SarMenuEOController.class);

    @Autowired
    private SarMenuEOService sarMenuEOService;

	@ApiOperation(value = "|SarMenuEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("lawss:sarMenu:page")
    public ResponseMessage<PageInfo<SarMenuEO>> page(SarMenuEOPage page) throws Exception {
        List<SarMenuEO> rows = sarMenuEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

	@ApiOperation(value = "|SarMenuEO|查询")
    @GetMapping("")
    @RequiresPermissions("lawss:sarMenu:list")
    public ResponseMessage<List<SarMenuEO>> list(SarMenuEOPage page) throws Exception {
        return Result.success(sarMenuEOService.queryByList(page));
	}

    @ApiOperation(value = "|SarMenuEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("lawss:sarMenu:get")
    public ResponseMessage<SarMenuEO> find(@PathVariable String id) throws Exception {
        return Result.success(sarMenuEOService.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "|SarMenuEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("lawss:sarMenu:save")
    public ResponseMessage<SarMenuEO> create(@RequestBody SarMenuEO sarMenuEO) throws Exception {
        sarMenuEOService.insertSelective(sarMenuEO);
        return Result.success(sarMenuEO);
    }

    @ApiOperation(value = "|SarMenuEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("lawss:sarMenu:update")
    public ResponseMessage<SarMenuEO> update(@RequestBody SarMenuEO sarMenuEO) throws Exception {
        sarMenuEOService.updateByPrimaryKeySelective(sarMenuEO);
        return Result.success(sarMenuEO);
    }

    @ApiOperation(value = "|SarMenuEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("lawss:sarMenu:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        sarMenuEOService.deleteByPrimaryKey(id);
        logger.info("delete from SAR_MENU where id = {}", id);
        return Result.success();
    }

}
