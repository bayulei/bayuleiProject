package com.adc.da.lawss.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.lawss.entity.SarLawsInfoEO;
import com.adc.da.lawss.page.SarLawsInfoEOPage;
import com.adc.da.lawss.service.SarLawsInfoEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

@RestController
@RequestMapping("/${restPath}/lawss/sarLawsInfo")
@Api(description = "|SarLawsInfoEO|")
public class SarLawsInfoEOController extends BaseController<SarLawsInfoEO>{

    private static final Logger logger = LoggerFactory.getLogger(SarLawsInfoEOController.class);

    @Autowired
    private SarLawsInfoEOService sarLawsInfoEOService;

	@ApiOperation(value = "|SarLawsInfoEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("lawss:sarLawsInfo:page")
    public ResponseMessage<PageInfo<SarLawsInfoEO>> page(SarLawsInfoEOPage page) throws Exception {
        List<SarLawsInfoEO> rows = sarLawsInfoEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

	@ApiOperation(value = "|SarLawsInfoEO|查询")
    @GetMapping("")
    @RequiresPermissions("lawss:sarLawsInfo:list")
    public ResponseMessage<List<SarLawsInfoEO>> list(SarLawsInfoEOPage page) throws Exception {
        return Result.success(sarLawsInfoEOService.queryByList(page));
	}

    @ApiOperation(value = "|SarLawsInfoEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("lawss:sarLawsInfo:get")
    public ResponseMessage<SarLawsInfoEO> find(@PathVariable String id) throws Exception {
        return Result.success(sarLawsInfoEOService.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "|SarLawsInfoEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("lawss:sarLawsInfo:save")
    public ResponseMessage<SarLawsInfoEO> create(@RequestBody SarLawsInfoEO sarLawsInfoEO) throws Exception {
        sarLawsInfoEOService.insertSelective(sarLawsInfoEO);
        return Result.success(sarLawsInfoEO);
    }

    @ApiOperation(value = "|SarLawsInfoEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("lawss:sarLawsInfo:update")
    public ResponseMessage<SarLawsInfoEO> update(@RequestBody SarLawsInfoEO sarLawsInfoEO) throws Exception {
        sarLawsInfoEOService.updateByPrimaryKeySelective(sarLawsInfoEO);
        return Result.success(sarLawsInfoEO);
    }

    @ApiOperation(value = "|SarLawsInfoEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("lawss:sarLawsInfo:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        sarLawsInfoEOService.deleteByPrimaryKey(id);
        logger.info("delete from SAR_LAWS_INFO where id = {}", id);
        return Result.success();
    }

}
