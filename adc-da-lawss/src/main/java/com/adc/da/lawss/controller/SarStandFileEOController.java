package com.adc.da.lawss.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.lawss.entity.SarStandFileEO;
import com.adc.da.lawss.page.SarStandFileEOPage;
import com.adc.da.lawss.service.SarStandFileEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

@RestController
@RequestMapping("/${restPath}/lawss/sarStandFile")
@Api(description = "|SarStandFileEO|")
public class SarStandFileEOController extends BaseController<SarStandFileEO>{

    private static final Logger logger = LoggerFactory.getLogger(SarStandFileEOController.class);

    @Autowired
    private SarStandFileEOService sarStandFileEOService;

	@ApiOperation(value = "|SarStandFileEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("lawss:sarStandFile:page")
    public ResponseMessage<PageInfo<SarStandFileEO>> page(SarStandFileEOPage page) throws Exception {
        List<SarStandFileEO> rows = sarStandFileEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

	@ApiOperation(value = "|SarStandFileEO|查询")
    @GetMapping("")
    @RequiresPermissions("lawss:sarStandFile:list")
    public ResponseMessage<List<SarStandFileEO>> list(SarStandFileEOPage page) throws Exception {
        return Result.success(sarStandFileEOService.queryByList(page));
	}

    @ApiOperation(value = "|SarStandFileEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("lawss:sarStandFile:get")
    public ResponseMessage<SarStandFileEO> find(@PathVariable String id) throws Exception {
        return Result.success(sarStandFileEOService.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "|SarStandFileEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("lawss:sarStandFile:save")
    public ResponseMessage<SarStandFileEO> create(@RequestBody SarStandFileEO sarStandFileEO) throws Exception {
        sarStandFileEOService.insertSelective(sarStandFileEO);
        return Result.success(sarStandFileEO);
    }

    @ApiOperation(value = "|SarStandFileEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("lawss:sarStandFile:update")
    public ResponseMessage<SarStandFileEO> update(@RequestBody SarStandFileEO sarStandFileEO) throws Exception {
        sarStandFileEOService.updateByPrimaryKeySelective(sarStandFileEO);
        return Result.success(sarStandFileEO);
    }

    @ApiOperation(value = "|SarStandFileEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("lawss:sarStandFile:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        sarStandFileEOService.deleteByPrimaryKey(id);
        logger.info("delete from SAR_STAND_FILE where id = {}", id);
        return Result.success();
    }

}
