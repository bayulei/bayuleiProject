package com.adc.da.lawss.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.lawss.entity.SarLawsItemValEO;
import com.adc.da.lawss.page.SarLawsItemValEOPage;
import com.adc.da.lawss.service.SarLawsItemValEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

@RestController
@RequestMapping("/${restPath}/lawss/sarLawsItemVal")
@Api(description = "|SarLawsItemValEO|")
public class SarLawsItemValEOController extends BaseController<SarLawsItemValEO>{

    private static final Logger logger = LoggerFactory.getLogger(SarLawsItemValEOController.class);

    @Autowired
    private SarLawsItemValEOService sarLawsItemValEOService;

	@ApiOperation(value = "|SarLawsItemValEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("lawss:sarLawsItemVal:page")
    public ResponseMessage<PageInfo<SarLawsItemValEO>> page(SarLawsItemValEOPage page) throws Exception {
        List<SarLawsItemValEO> rows = sarLawsItemValEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

	@ApiOperation(value = "|SarLawsItemValEO|查询")
    @GetMapping("")
    @RequiresPermissions("lawss:sarLawsItemVal:list")
    public ResponseMessage<List<SarLawsItemValEO>> list(SarLawsItemValEOPage page) throws Exception {
        return Result.success(sarLawsItemValEOService.queryByList(page));
	}

    @ApiOperation(value = "|SarLawsItemValEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("lawss:sarLawsItemVal:get")
    public ResponseMessage<SarLawsItemValEO> find(@PathVariable String id) throws Exception {
        return Result.success(sarLawsItemValEOService.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "|SarLawsItemValEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("lawss:sarLawsItemVal:save")
    public ResponseMessage<SarLawsItemValEO> create(@RequestBody SarLawsItemValEO sarLawsItemValEO) throws Exception {
        sarLawsItemValEOService.insertSelective(sarLawsItemValEO);
        return Result.success(sarLawsItemValEO);
    }

    @ApiOperation(value = "|SarLawsItemValEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("lawss:sarLawsItemVal:update")
    public ResponseMessage<SarLawsItemValEO> update(@RequestBody SarLawsItemValEO sarLawsItemValEO) throws Exception {
        sarLawsItemValEOService.updateByPrimaryKeySelective(sarLawsItemValEO);
        return Result.success(sarLawsItemValEO);
    }

    @ApiOperation(value = "|SarLawsItemValEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("lawss:sarLawsItemVal:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        sarLawsItemValEOService.deleteByPrimaryKey(id);
        logger.info("delete from SAR_LAWS_ITEM_VAL where id = {}", id);
        return Result.success();
    }

}
