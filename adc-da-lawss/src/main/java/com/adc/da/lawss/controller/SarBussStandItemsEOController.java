package com.adc.da.lawss.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.lawss.entity.SarBussStandItemsEO;
import com.adc.da.lawss.page.SarBussStandItemsEOPage;
import com.adc.da.lawss.service.SarBussStandItemsEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

@RestController
@RequestMapping("/${restPath}/lawss/sarBussStandItems")
@Api(description = "|SarBussStandItemsEO|")
public class SarBussStandItemsEOController extends BaseController<SarBussStandItemsEO>{

    private static final Logger logger = LoggerFactory.getLogger(SarBussStandItemsEOController.class);

    @Autowired
    private SarBussStandItemsEOService sarBussStandItemsEOService;

	@ApiOperation(value = "|SarBussStandItemsEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("lawss:sarBussStandItems:page")
    public ResponseMessage<PageInfo<SarBussStandItemsEO>> page(SarBussStandItemsEOPage page) throws Exception {
        List<SarBussStandItemsEO> rows = sarBussStandItemsEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

	@ApiOperation(value = "|SarBussStandItemsEO|查询")
    @GetMapping("")
    @RequiresPermissions("lawss:sarBussStandItems:list")
    public ResponseMessage<List<SarBussStandItemsEO>> list(SarBussStandItemsEOPage page) throws Exception {
        return Result.success(sarBussStandItemsEOService.queryByList(page));
	}

    @ApiOperation(value = "|SarBussStandItemsEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("lawss:sarBussStandItems:get")
    public ResponseMessage<SarBussStandItemsEO> find(@PathVariable String id) throws Exception {
        return Result.success(sarBussStandItemsEOService.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "|SarBussStandItemsEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("lawss:sarBussStandItems:save")
    public ResponseMessage<SarBussStandItemsEO> create(@RequestBody SarBussStandItemsEO sarBussStandItemsEO) throws Exception {
        sarBussStandItemsEOService.insertSelective(sarBussStandItemsEO);
        return Result.success(sarBussStandItemsEO);
    }

    @ApiOperation(value = "|SarBussStandItemsEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("lawss:sarBussStandItems:update")
    public ResponseMessage<SarBussStandItemsEO> update(@RequestBody SarBussStandItemsEO sarBussStandItemsEO) throws Exception {
        sarBussStandItemsEOService.updateByPrimaryKeySelective(sarBussStandItemsEO);
        return Result.success(sarBussStandItemsEO);
    }

    @ApiOperation(value = "|SarBussStandItemsEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("lawss:sarBussStandItems:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        sarBussStandItemsEOService.deleteByPrimaryKey(id);
        logger.info("delete from SAR_BUSS_STAND_ITEMS where id = {}", id);
        return Result.success();
    }

}
