package com.adc.da.lawss.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.lawss.entity.SarBussStandItemValEO;
import com.adc.da.lawss.page.SarBussStandItemValEOPage;
import com.adc.da.lawss.service.SarBussStandItemValEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

@RestController
@RequestMapping("/${restPath}/lawss/sarBussStandItemVal")
@Api(description = "|SarBussStandItemValEO|")
public class SarBussStandItemValEOController extends BaseController<SarBussStandItemValEO>{

    private static final Logger logger = LoggerFactory.getLogger(SarBussStandItemValEOController.class);

    @Autowired
    private SarBussStandItemValEOService sarBussStandItemValEOService;

	@ApiOperation(value = "|SarBussStandItemValEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("lawss:sarBussStandItemVal:page")
    public ResponseMessage<PageInfo<SarBussStandItemValEO>> page(SarBussStandItemValEOPage page) throws Exception {
        List<SarBussStandItemValEO> rows = sarBussStandItemValEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

	@ApiOperation(value = "|SarBussStandItemValEO|查询")
    @GetMapping("")
    @RequiresPermissions("lawss:sarBussStandItemVal:list")
    public ResponseMessage<List<SarBussStandItemValEO>> list(SarBussStandItemValEOPage page) throws Exception {
        return Result.success(sarBussStandItemValEOService.queryByList(page));
	}

    @ApiOperation(value = "|SarBussStandItemValEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("lawss:sarBussStandItemVal:get")
    public ResponseMessage<SarBussStandItemValEO> find(@PathVariable String id) throws Exception {
        return Result.success(sarBussStandItemValEOService.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "|SarBussStandItemValEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("lawss:sarBussStandItemVal:save")
    public ResponseMessage<SarBussStandItemValEO> create(@RequestBody SarBussStandItemValEO sarBussStandItemValEO) throws Exception {
        sarBussStandItemValEOService.insertSelective(sarBussStandItemValEO);
        return Result.success(sarBussStandItemValEO);
    }

    @ApiOperation(value = "|SarBussStandItemValEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("lawss:sarBussStandItemVal:update")
    public ResponseMessage<SarBussStandItemValEO> update(@RequestBody SarBussStandItemValEO sarBussStandItemValEO) throws Exception {
        sarBussStandItemValEOService.updateByPrimaryKeySelective(sarBussStandItemValEO);
        return Result.success(sarBussStandItemValEO);
    }

    @ApiOperation(value = "|SarBussStandItemValEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("lawss:sarBussStandItemVal:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        sarBussStandItemValEOService.deleteByPrimaryKey(id);
        logger.info("delete from SAR_BUSS_STAND_ITEM_VAL where id = {}", id);
        return Result.success();
    }

}
