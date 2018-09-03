package com.adc.da.lawss.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.lawss.entity.SarBusSarCompileEO;
import com.adc.da.lawss.page.SarBusSarCompileEOPage;
import com.adc.da.lawss.service.SarBusSarCompileEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

@RestController
@RequestMapping("/${restPath}/lawss/sarBusSarCompile")
@Api(description = "|SarBusSarCompileEO|")
public class SarBusSarCompileEOController extends BaseController<SarBusSarCompileEO>{

    private static final Logger logger = LoggerFactory.getLogger(SarBusSarCompileEOController.class);

    @Autowired
    private SarBusSarCompileEOService sarBusSarCompileEOService;

	@ApiOperation(value = "|SarBusSarCompileEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("lawss:sarBusSarCompile:page")
    public ResponseMessage<PageInfo<SarBusSarCompileEO>> page(SarBusSarCompileEOPage page) throws Exception {
        List<SarBusSarCompileEO> rows = sarBusSarCompileEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

	@ApiOperation(value = "|SarBusSarCompileEO|查询")
    @GetMapping("")
    @RequiresPermissions("lawss:sarBusSarCompile:list")
    public ResponseMessage<List<SarBusSarCompileEO>> list(SarBusSarCompileEOPage page) throws Exception {
        return Result.success(sarBusSarCompileEOService.queryByList(page));
	}

    @ApiOperation(value = "|SarBusSarCompileEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("lawss:sarBusSarCompile:get")
    public ResponseMessage<SarBusSarCompileEO> find(@PathVariable String id) throws Exception {
        return Result.success(sarBusSarCompileEOService.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "|SarBusSarCompileEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("lawss:sarBusSarCompile:save")
    public ResponseMessage<SarBusSarCompileEO> create(@RequestBody SarBusSarCompileEO sarBusSarCompileEO) throws Exception {
        sarBusSarCompileEOService.insertSelective(sarBusSarCompileEO);
        return Result.success(sarBusSarCompileEO);
    }

    @ApiOperation(value = "|SarBusSarCompileEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("lawss:sarBusSarCompile:update")
    public ResponseMessage<SarBusSarCompileEO> update(@RequestBody SarBusSarCompileEO sarBusSarCompileEO) throws Exception {
        sarBusSarCompileEOService.updateByPrimaryKeySelective(sarBusSarCompileEO);
        return Result.success(sarBusSarCompileEO);
    }

    @ApiOperation(value = "|SarBusSarCompileEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("lawss:sarBusSarCompile:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        sarBusSarCompileEOService.deleteByPrimaryKey(id);
        logger.info("delete from SAR_BUS_SAR_COMPILE where id = {}", id);
        return Result.success();
    }

}
