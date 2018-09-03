package com.adc.da.lawss.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.lawss.entity.SarStandItemsEO;
import com.adc.da.lawss.page.SarStandItemsEOPage;
import com.adc.da.lawss.service.SarStandItemsEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

@RestController
@RequestMapping("/${restPath}/lawss/sarStandItems")
@Api(description = "|SarStandItemsEO|")
public class SarStandItemsEOController extends BaseController<SarStandItemsEO>{

    private static final Logger logger = LoggerFactory.getLogger(SarStandItemsEOController.class);

    @Autowired
    private SarStandItemsEOService sarStandItemsEOService;

	@ApiOperation(value = "|SarStandItemsEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("lawss:sarStandItems:page")
    public ResponseMessage<PageInfo<SarStandItemsEO>> page(SarStandItemsEOPage page) throws Exception {
        List<SarStandItemsEO> rows = sarStandItemsEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

	@ApiOperation(value = "|SarStandItemsEO|查询")
    @GetMapping("")
    @RequiresPermissions("lawss:sarStandItems:list")
    public ResponseMessage<List<SarStandItemsEO>> list(SarStandItemsEOPage page) throws Exception {
        return Result.success(sarStandItemsEOService.queryByList(page));
	}

    @ApiOperation(value = "|SarStandItemsEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("lawss:sarStandItems:get")
    public ResponseMessage<SarStandItemsEO> find(@PathVariable String id) throws Exception {
        return Result.success(sarStandItemsEOService.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "|SarStandItemsEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("lawss:sarStandItems:save")
    public ResponseMessage<SarStandItemsEO> create(@RequestBody SarStandItemsEO sarStandItemsEO) throws Exception {
        sarStandItemsEOService.insertSelective(sarStandItemsEO);
        return Result.success(sarStandItemsEO);
    }

    @ApiOperation(value = "|SarStandItemsEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("lawss:sarStandItems:update")
    public ResponseMessage<SarStandItemsEO> update(@RequestBody SarStandItemsEO sarStandItemsEO) throws Exception {
        sarStandItemsEOService.updateByPrimaryKeySelective(sarStandItemsEO);
        return Result.success(sarStandItemsEO);
    }

    @ApiOperation(value = "|SarStandItemsEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("lawss:sarStandItems:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        sarStandItemsEOService.deleteByPrimaryKey(id);
        logger.info("delete from SAR_STAND_ITEMS where id = {}", id);
        return Result.success();
    }

}
