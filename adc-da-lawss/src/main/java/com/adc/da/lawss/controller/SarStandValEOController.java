package com.adc.da.lawss.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.lawss.entity.SarStandValEO;
import com.adc.da.lawss.page.SarStandValEOPage;
import com.adc.da.lawss.service.SarStandValEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

@RestController
@RequestMapping("/${restPath}/lawss/sarStandVal")
@Api(description = "|SarStandValEO|")
public class SarStandValEOController extends BaseController<SarStandValEO>{

    private static final Logger logger = LoggerFactory.getLogger(SarStandValEOController.class);

    @Autowired
    private SarStandValEOService sarStandValEOService;

	@ApiOperation(value = "|SarStandValEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("lawss:sarStandVal:page")
    public ResponseMessage<PageInfo<SarStandValEO>> page(SarStandValEOPage page) throws Exception {
        List<SarStandValEO> rows = sarStandValEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

	@ApiOperation(value = "|SarStandValEO|查询")
    @GetMapping("")
    @RequiresPermissions("lawss:sarStandVal:list")
    public ResponseMessage<List<SarStandValEO>> list(SarStandValEOPage page) throws Exception {
        return Result.success(sarStandValEOService.queryByList(page));
	}

    @ApiOperation(value = "|SarStandValEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("lawss:sarStandVal:get")
    public ResponseMessage<SarStandValEO> find(@PathVariable String id) throws Exception {
        return Result.success(sarStandValEOService.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "|SarStandValEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("lawss:sarStandVal:save")
    public ResponseMessage<SarStandValEO> create(@RequestBody SarStandValEO sarStandValEO) throws Exception {
        sarStandValEOService.insertSelective(sarStandValEO);
        return Result.success(sarStandValEO);
    }

    @ApiOperation(value = "|SarStandValEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("lawss:sarStandVal:update")
    public ResponseMessage<SarStandValEO> update(@RequestBody SarStandValEO sarStandValEO) throws Exception {
        sarStandValEOService.updateByPrimaryKeySelective(sarStandValEO);
        return Result.success(sarStandValEO);
    }

    @ApiOperation(value = "|SarStandValEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("lawss:sarStandVal:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        sarStandValEOService.deleteByPrimaryKey(id);
        logger.info("delete from SAR_STAND_VAL where id = {}", id);
        return Result.success();
    }

}
