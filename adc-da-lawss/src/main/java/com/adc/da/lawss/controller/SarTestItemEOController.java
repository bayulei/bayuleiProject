package com.adc.da.lawss.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.lawss.entity.SarTestItemEO;
import com.adc.da.lawss.page.SarTestItemEOPage;
import com.adc.da.lawss.service.SarTestItemEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

@RestController
@RequestMapping("/${restPath}/lawss/sarTestItem")
@Api(description = "|SarTestItemEO|")
public class SarTestItemEOController extends BaseController<SarTestItemEO>{

    private static final Logger logger = LoggerFactory.getLogger(SarTestItemEOController.class);

    @Autowired
    private SarTestItemEOService sarTestItemEOService;

	@ApiOperation(value = "|SarTestItemEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("lawss:sarTestItem:page")
    public ResponseMessage<PageInfo<SarTestItemEO>> page(SarTestItemEOPage page) throws Exception {
        List<SarTestItemEO> rows = sarTestItemEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

	@ApiOperation(value = "|SarTestItemEO|查询")
    @GetMapping("")
    @RequiresPermissions("lawss:sarTestItem:list")
    public ResponseMessage<List<SarTestItemEO>> list(SarTestItemEOPage page) throws Exception {
        return Result.success(sarTestItemEOService.queryByList(page));
	}

    @ApiOperation(value = "|SarTestItemEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("lawss:sarTestItem:get")
    public ResponseMessage<SarTestItemEO> find(@PathVariable String id) throws Exception {
        return Result.success(sarTestItemEOService.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "|SarTestItemEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("lawss:sarTestItem:save")
    public ResponseMessage<SarTestItemEO> create(@RequestBody SarTestItemEO sarTestItemEO) throws Exception {
        sarTestItemEOService.insertSelective(sarTestItemEO);
        return Result.success(sarTestItemEO);
    }

    @ApiOperation(value = "|SarTestItemEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("lawss:sarTestItem:update")
    public ResponseMessage<SarTestItemEO> update(@RequestBody SarTestItemEO sarTestItemEO) throws Exception {
        sarTestItemEOService.updateByPrimaryKeySelective(sarTestItemEO);
        return Result.success(sarTestItemEO);
    }

    @ApiOperation(value = "|SarTestItemEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("lawss:sarTestItem:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        sarTestItemEOService.deleteByPrimaryKey(id);
        logger.info("delete from SAR_TEST_ITEM where id = {}", id);
        return Result.success();
    }

}
