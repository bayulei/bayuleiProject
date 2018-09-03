package com.adc.da.lawss.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.lawss.entity.SarProductTestEO;
import com.adc.da.lawss.page.SarProductTestEOPage;
import com.adc.da.lawss.service.SarProductTestEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

@RestController
@RequestMapping("/${restPath}/lawss/sarProductTest")
@Api(description = "|SarProductTestEO|")
public class SarProductTestEOController extends BaseController<SarProductTestEO>{

    private static final Logger logger = LoggerFactory.getLogger(SarProductTestEOController.class);

    @Autowired
    private SarProductTestEOService sarProductTestEOService;

	@ApiOperation(value = "|SarProductTestEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("lawss:sarProductTest:page")
    public ResponseMessage<PageInfo<SarProductTestEO>> page(SarProductTestEOPage page) throws Exception {
        List<SarProductTestEO> rows = sarProductTestEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

	@ApiOperation(value = "|SarProductTestEO|查询")
    @GetMapping("")
    @RequiresPermissions("lawss:sarProductTest:list")
    public ResponseMessage<List<SarProductTestEO>> list(SarProductTestEOPage page) throws Exception {
        return Result.success(sarProductTestEOService.queryByList(page));
	}

    @ApiOperation(value = "|SarProductTestEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("lawss:sarProductTest:get")
    public ResponseMessage<SarProductTestEO> find(@PathVariable String id) throws Exception {
        return Result.success(sarProductTestEOService.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "|SarProductTestEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("lawss:sarProductTest:save")
    public ResponseMessage<SarProductTestEO> create(@RequestBody SarProductTestEO sarProductTestEO) throws Exception {
        sarProductTestEOService.insertSelective(sarProductTestEO);
        return Result.success(sarProductTestEO);
    }

    @ApiOperation(value = "|SarProductTestEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("lawss:sarProductTest:update")
    public ResponseMessage<SarProductTestEO> update(@RequestBody SarProductTestEO sarProductTestEO) throws Exception {
        sarProductTestEOService.updateByPrimaryKeySelective(sarProductTestEO);
        return Result.success(sarProductTestEO);
    }

    @ApiOperation(value = "|SarProductTestEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("lawss:sarProductTest:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        sarProductTestEOService.deleteByPrimaryKey(id);
        logger.info("delete from SAR_PRODUCT_TEST where id = {}", id);
        return Result.success();
    }

}
