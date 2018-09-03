package com.adc.da.lawss.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.lawss.entity.SarProductInfoEO;
import com.adc.da.lawss.page.SarProductInfoEOPage;
import com.adc.da.lawss.service.SarProductInfoEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

@RestController
@RequestMapping("/${restPath}/lawss/sarProductInfo")
@Api(description = "|SarProductInfoEO|")
public class SarProductInfoEOController extends BaseController<SarProductInfoEO>{

    private static final Logger logger = LoggerFactory.getLogger(SarProductInfoEOController.class);

    @Autowired
    private SarProductInfoEOService sarProductInfoEOService;

	@ApiOperation(value = "|SarProductInfoEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("lawss:sarProductInfo:page")
    public ResponseMessage<PageInfo<SarProductInfoEO>> page(SarProductInfoEOPage page) throws Exception {
        List<SarProductInfoEO> rows = sarProductInfoEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

	@ApiOperation(value = "|SarProductInfoEO|查询")
    @GetMapping("")
    @RequiresPermissions("lawss:sarProductInfo:list")
    public ResponseMessage<List<SarProductInfoEO>> list(SarProductInfoEOPage page) throws Exception {
        return Result.success(sarProductInfoEOService.queryByList(page));
	}

    @ApiOperation(value = "|SarProductInfoEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("lawss:sarProductInfo:get")
    public ResponseMessage<SarProductInfoEO> find(@PathVariable String id) throws Exception {
        return Result.success(sarProductInfoEOService.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "|SarProductInfoEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("lawss:sarProductInfo:save")
    public ResponseMessage<SarProductInfoEO> create(@RequestBody SarProductInfoEO sarProductInfoEO) throws Exception {
        sarProductInfoEOService.insertSelective(sarProductInfoEO);
        return Result.success(sarProductInfoEO);
    }

    @ApiOperation(value = "|SarProductInfoEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("lawss:sarProductInfo:update")
    public ResponseMessage<SarProductInfoEO> update(@RequestBody SarProductInfoEO sarProductInfoEO) throws Exception {
        sarProductInfoEOService.updateByPrimaryKeySelective(sarProductInfoEO);
        return Result.success(sarProductInfoEO);
    }

    @ApiOperation(value = "|SarProductInfoEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("lawss:sarProductInfo:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        sarProductInfoEOService.deleteByPrimaryKey(id);
        logger.info("delete from SAR_PRODUCT_INFO where id = {}", id);
        return Result.success();
    }

}
