package com.adc.da.lawss.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.lawss.entity.SarLawsResEO;
import com.adc.da.lawss.page.SarLawsResEOPage;
import com.adc.da.lawss.service.SarLawsResEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

@RestController
@RequestMapping("/${restPath}/lawss/sarLawsRes")
@Api(description = "|SarLawsResEO|")
public class SarLawsResEOController extends BaseController<SarLawsResEO>{

    private static final Logger logger = LoggerFactory.getLogger(SarLawsResEOController.class);

    @Autowired
    private SarLawsResEOService sarLawsResEOService;

	@ApiOperation(value = "|SarLawsResEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("lawss:sarLawsRes:page")
    public ResponseMessage<PageInfo<SarLawsResEO>> page(SarLawsResEOPage page) throws Exception {
        List<SarLawsResEO> rows = sarLawsResEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

	@ApiOperation(value = "|SarLawsResEO|查询")
    @GetMapping("")
    @RequiresPermissions("lawss:sarLawsRes:list")
    public ResponseMessage<List<SarLawsResEO>> list(SarLawsResEOPage page) throws Exception {
        return Result.success(sarLawsResEOService.queryByList(page));
	}

    @ApiOperation(value = "|SarLawsResEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("lawss:sarLawsRes:get")
    public ResponseMessage<SarLawsResEO> find(@PathVariable String id) throws Exception {
        return Result.success(sarLawsResEOService.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "|SarLawsResEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("lawss:sarLawsRes:save")
    public ResponseMessage<SarLawsResEO> create(@RequestBody SarLawsResEO sarLawsResEO) throws Exception {
        sarLawsResEOService.insertSelective(sarLawsResEO);
        return Result.success(sarLawsResEO);
    }

    @ApiOperation(value = "|SarLawsResEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("lawss:sarLawsRes:update")
    public ResponseMessage<SarLawsResEO> update(@RequestBody SarLawsResEO sarLawsResEO) throws Exception {
        sarLawsResEOService.updateByPrimaryKeySelective(sarLawsResEO);
        return Result.success(sarLawsResEO);
    }

    @ApiOperation(value = "|SarLawsResEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("lawss:sarLawsRes:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        sarLawsResEOService.deleteByPrimaryKey(id);
        logger.info("delete from SAR_LAWS_RES where id = {}", id);
        return Result.success();
    }

}
