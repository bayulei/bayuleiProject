package com.adc.da.lawss.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.lawss.entity.SarStandResEO;
import com.adc.da.lawss.page.SarStandResEOPage;
import com.adc.da.lawss.service.SarStandResEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

@RestController
@RequestMapping("/${restPath}/lawss/sarStandRes")
@Api(description = "|SarStandResEO|")
public class SarStandResEOController extends BaseController<SarStandResEO>{

    private static final Logger logger = LoggerFactory.getLogger(SarStandResEOController.class);

    @Autowired
    private SarStandResEOService sarStandResEOService;

	@ApiOperation(value = "|SarStandResEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("lawss:sarStandRes:page")
    public ResponseMessage<PageInfo<SarStandResEO>> page(SarStandResEOPage page) throws Exception {
        List<SarStandResEO> rows = sarStandResEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

	@ApiOperation(value = "|SarStandResEO|查询")
    @GetMapping("")
    @RequiresPermissions("lawss:sarStandRes:list")
    public ResponseMessage<List<SarStandResEO>> list(SarStandResEOPage page) throws Exception {
        return Result.success(sarStandResEOService.queryByList(page));
	}

    @ApiOperation(value = "|SarStandResEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("lawss:sarStandRes:get")
    public ResponseMessage<SarStandResEO> find(@PathVariable String id) throws Exception {
        return Result.success(sarStandResEOService.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "|SarStandResEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("lawss:sarStandRes:save")
    public ResponseMessage<SarStandResEO> create(@RequestBody SarStandResEO sarStandResEO) throws Exception {
        sarStandResEOService.insertSelective(sarStandResEO);
        return Result.success(sarStandResEO);
    }

    @ApiOperation(value = "|SarStandResEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("lawss:sarStandRes:update")
    public ResponseMessage<SarStandResEO> update(@RequestBody SarStandResEO sarStandResEO) throws Exception {
        sarStandResEOService.updateByPrimaryKeySelective(sarStandResEO);
        return Result.success(sarStandResEO);
    }

    @ApiOperation(value = "|SarStandResEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("lawss:sarStandRes:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        sarStandResEOService.deleteByPrimaryKey(id);
        logger.info("delete from SAR_STAND_RES where id = {}", id);
        return Result.success();
    }

}
