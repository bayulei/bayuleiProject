package com.adc.da.lawss.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.lawss.entity.SarLawsValEO;
import com.adc.da.lawss.page.SarLawsValEOPage;
import com.adc.da.lawss.service.SarLawsValEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

@RestController
@RequestMapping("/${restPath}/lawss/sarLawsVal")
@Api(description = "|SarLawsValEO|")
public class SarLawsValEOController extends BaseController<SarLawsValEO>{

    private static final Logger logger = LoggerFactory.getLogger(SarLawsValEOController.class);

    @Autowired
    private SarLawsValEOService sarLawsValEOService;

	@ApiOperation(value = "|SarLawsValEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("lawss:sarLawsVal:page")
    public ResponseMessage<PageInfo<SarLawsValEO>> page(SarLawsValEOPage page) throws Exception {
        List<SarLawsValEO> rows = sarLawsValEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

	@ApiOperation(value = "|SarLawsValEO|查询")
    @GetMapping("")
    @RequiresPermissions("lawss:sarLawsVal:list")
    public ResponseMessage<List<SarLawsValEO>> list(SarLawsValEOPage page) throws Exception {
        return Result.success(sarLawsValEOService.queryByList(page));
	}

    @ApiOperation(value = "|SarLawsValEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("lawss:sarLawsVal:get")
    public ResponseMessage<SarLawsValEO> find(@PathVariable String id) throws Exception {
        return Result.success(sarLawsValEOService.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "|SarLawsValEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("lawss:sarLawsVal:save")
    public ResponseMessage<SarLawsValEO> create(@RequestBody SarLawsValEO sarLawsValEO) throws Exception {
        sarLawsValEOService.insertSelective(sarLawsValEO);
        return Result.success(sarLawsValEO);
    }

    @ApiOperation(value = "|SarLawsValEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("lawss:sarLawsVal:update")
    public ResponseMessage<SarLawsValEO> update(@RequestBody SarLawsValEO sarLawsValEO) throws Exception {
        sarLawsValEOService.updateByPrimaryKeySelective(sarLawsValEO);
        return Result.success(sarLawsValEO);
    }

    @ApiOperation(value = "|SarLawsValEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("lawss:sarLawsVal:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        sarLawsValEOService.deleteByPrimaryKey(id);
        logger.info("delete from SAR_LAWS_VAL where id = {}", id);
        return Result.success();
    }

}
