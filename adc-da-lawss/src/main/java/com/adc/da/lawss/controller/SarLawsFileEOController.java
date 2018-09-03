package com.adc.da.lawss.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.lawss.entity.SarLawsFileEO;
import com.adc.da.lawss.page.SarLawsFileEOPage;
import com.adc.da.lawss.service.SarLawsFileEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

@RestController
@RequestMapping("/${restPath}/lawss/sarLawsFile")
@Api(description = "|SarLawsFileEO|")
public class SarLawsFileEOController extends BaseController<SarLawsFileEO>{

    private static final Logger logger = LoggerFactory.getLogger(SarLawsFileEOController.class);

    @Autowired
    private SarLawsFileEOService sarLawsFileEOService;

	@ApiOperation(value = "|SarLawsFileEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("lawss:sarLawsFile:page")
    public ResponseMessage<PageInfo<SarLawsFileEO>> page(SarLawsFileEOPage page) throws Exception {
        List<SarLawsFileEO> rows = sarLawsFileEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

	@ApiOperation(value = "|SarLawsFileEO|查询")
    @GetMapping("")
    @RequiresPermissions("lawss:sarLawsFile:list")
    public ResponseMessage<List<SarLawsFileEO>> list(SarLawsFileEOPage page) throws Exception {
        return Result.success(sarLawsFileEOService.queryByList(page));
	}

    @ApiOperation(value = "|SarLawsFileEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("lawss:sarLawsFile:get")
    public ResponseMessage<SarLawsFileEO> find(@PathVariable String id) throws Exception {
        return Result.success(sarLawsFileEOService.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "|SarLawsFileEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("lawss:sarLawsFile:save")
    public ResponseMessage<SarLawsFileEO> create(@RequestBody SarLawsFileEO sarLawsFileEO) throws Exception {
        sarLawsFileEOService.insertSelective(sarLawsFileEO);
        return Result.success(sarLawsFileEO);
    }

    @ApiOperation(value = "|SarLawsFileEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("lawss:sarLawsFile:update")
    public ResponseMessage<SarLawsFileEO> update(@RequestBody SarLawsFileEO sarLawsFileEO) throws Exception {
        sarLawsFileEOService.updateByPrimaryKeySelective(sarLawsFileEO);
        return Result.success(sarLawsFileEO);
    }

    @ApiOperation(value = "|SarLawsFileEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("lawss:sarLawsFile:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        sarLawsFileEOService.deleteByPrimaryKey(id);
        logger.info("delete from SAR_LAWS_FILE where id = {}", id);
        return Result.success();
    }

}
