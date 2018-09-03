package com.adc.da.lawss.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.lawss.entity.SarProductValEO;
import com.adc.da.lawss.page.SarProductValEOPage;
import com.adc.da.lawss.service.SarProductValEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

@RestController
@RequestMapping("/${restPath}/lawss/sarProductVal")
@Api(description = "|SarProductValEO|")
public class SarProductValEOController extends BaseController<SarProductValEO>{

    private static final Logger logger = LoggerFactory.getLogger(SarProductValEOController.class);

    @Autowired
    private SarProductValEOService sarProductValEOService;

	@ApiOperation(value = "|SarProductValEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("lawss:sarProductVal:page")
    public ResponseMessage<PageInfo<SarProductValEO>> page(SarProductValEOPage page) throws Exception {
        List<SarProductValEO> rows = sarProductValEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

	@ApiOperation(value = "|SarProductValEO|查询")
    @GetMapping("")
    @RequiresPermissions("lawss:sarProductVal:list")
    public ResponseMessage<List<SarProductValEO>> list(SarProductValEOPage page) throws Exception {
        return Result.success(sarProductValEOService.queryByList(page));
	}

    @ApiOperation(value = "|SarProductValEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("lawss:sarProductVal:get")
    public ResponseMessage<SarProductValEO> find(@PathVariable String id) throws Exception {
        return Result.success(sarProductValEOService.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "|SarProductValEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("lawss:sarProductVal:save")
    public ResponseMessage<SarProductValEO> create(@RequestBody SarProductValEO sarProductValEO) throws Exception {
        sarProductValEOService.insertSelective(sarProductValEO);
        return Result.success(sarProductValEO);
    }

    @ApiOperation(value = "|SarProductValEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("lawss:sarProductVal:update")
    public ResponseMessage<SarProductValEO> update(@RequestBody SarProductValEO sarProductValEO) throws Exception {
        sarProductValEOService.updateByPrimaryKeySelective(sarProductValEO);
        return Result.success(sarProductValEO);
    }

    @ApiOperation(value = "|SarProductValEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("lawss:sarProductVal:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        sarProductValEOService.deleteByPrimaryKey(id);
        logger.info("delete from SAR_PRODUCT_VAL where id = {}", id);
        return Result.success();
    }

}
