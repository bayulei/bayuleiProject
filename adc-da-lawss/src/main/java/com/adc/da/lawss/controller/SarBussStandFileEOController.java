package com.adc.da.lawss.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.lawss.entity.SarBussStandFileEO;
import com.adc.da.lawss.page.SarBussStandFileEOPage;
import com.adc.da.lawss.service.SarBussStandFileEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

@RestController
@RequestMapping("/${restPath}/lawss/sarBussStandFile")
@Api(description = "|SarBussStandFileEO|")
public class SarBussStandFileEOController extends BaseController<SarBussStandFileEO>{

    private static final Logger logger = LoggerFactory.getLogger(SarBussStandFileEOController.class);

    @Autowired
    private SarBussStandFileEOService sarBussStandFileEOService;

	@ApiOperation(value = "|SarBussStandFileEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("lawss:sarBussStandFile:page")
    public ResponseMessage<PageInfo<SarBussStandFileEO>> page(SarBussStandFileEOPage page) throws Exception {
        List<SarBussStandFileEO> rows = sarBussStandFileEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

	@ApiOperation(value = "|SarBussStandFileEO|查询")
    @GetMapping("")
    @RequiresPermissions("lawss:sarBussStandFile:list")
    public ResponseMessage<List<SarBussStandFileEO>> list(SarBussStandFileEOPage page) throws Exception {
        return Result.success(sarBussStandFileEOService.queryByList(page));
	}

    @ApiOperation(value = "|SarBussStandFileEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("lawss:sarBussStandFile:get")
    public ResponseMessage<SarBussStandFileEO> find(@PathVariable String id) throws Exception {
        return Result.success(sarBussStandFileEOService.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "|SarBussStandFileEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("lawss:sarBussStandFile:save")
    public ResponseMessage<SarBussStandFileEO> create(@RequestBody SarBussStandFileEO sarBussStandFileEO) throws Exception {
        sarBussStandFileEOService.insertSelective(sarBussStandFileEO);
        return Result.success(sarBussStandFileEO);
    }

    @ApiOperation(value = "|SarBussStandFileEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("lawss:sarBussStandFile:update")
    public ResponseMessage<SarBussStandFileEO> update(@RequestBody SarBussStandFileEO sarBussStandFileEO) throws Exception {
        sarBussStandFileEOService.updateByPrimaryKeySelective(sarBussStandFileEO);
        return Result.success(sarBussStandFileEO);
    }

    @ApiOperation(value = "|SarBussStandFileEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("lawss:sarBussStandFile:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        sarBussStandFileEOService.deleteByPrimaryKey(id);
        logger.info("delete from SAR_BUSS_STAND_FILE where id = {}", id);
        return Result.success();
    }

}
