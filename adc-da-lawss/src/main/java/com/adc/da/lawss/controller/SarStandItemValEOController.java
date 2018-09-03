package com.adc.da.lawss.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.lawss.entity.SarStandItemValEO;
import com.adc.da.lawss.page.SarStandItemValEOPage;
import com.adc.da.lawss.service.SarStandItemValEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

@RestController
@RequestMapping("/${restPath}/lawss/sarStandItemVal")
@Api(description = "|SarStandItemValEO|")
public class SarStandItemValEOController extends BaseController<SarStandItemValEO>{

    private static final Logger logger = LoggerFactory.getLogger(SarStandItemValEOController.class);

    @Autowired
    private SarStandItemValEOService sarStandItemValEOService;

	@ApiOperation(value = "|SarStandItemValEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("lawss:sarStandItemVal:page")
    public ResponseMessage<PageInfo<SarStandItemValEO>> page(SarStandItemValEOPage page) throws Exception {
        List<SarStandItemValEO> rows = sarStandItemValEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

	@ApiOperation(value = "|SarStandItemValEO|查询")
    @GetMapping("")
    @RequiresPermissions("lawss:sarStandItemVal:list")
    public ResponseMessage<List<SarStandItemValEO>> list(SarStandItemValEOPage page) throws Exception {
        return Result.success(sarStandItemValEOService.queryByList(page));
	}

    @ApiOperation(value = "|SarStandItemValEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("lawss:sarStandItemVal:get")
    public ResponseMessage<SarStandItemValEO> find(@PathVariable String id) throws Exception {
        return Result.success(sarStandItemValEOService.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "|SarStandItemValEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("lawss:sarStandItemVal:save")
    public ResponseMessage<SarStandItemValEO> create(@RequestBody SarStandItemValEO sarStandItemValEO) throws Exception {
        sarStandItemValEOService.insertSelective(sarStandItemValEO);
        return Result.success(sarStandItemValEO);
    }

    @ApiOperation(value = "|SarStandItemValEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("lawss:sarStandItemVal:update")
    public ResponseMessage<SarStandItemValEO> update(@RequestBody SarStandItemValEO sarStandItemValEO) throws Exception {
        sarStandItemValEOService.updateByPrimaryKeySelective(sarStandItemValEO);
        return Result.success(sarStandItemValEO);
    }

    @ApiOperation(value = "|SarStandItemValEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("lawss:sarStandItemVal:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        sarStandItemValEOService.deleteByPrimaryKey(id);
        logger.info("delete from SAR_STAND_ITEM_VAL where id = {}", id);
        return Result.success();
    }

}
