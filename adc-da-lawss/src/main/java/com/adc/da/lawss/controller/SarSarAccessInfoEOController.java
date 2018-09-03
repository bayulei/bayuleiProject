package com.adc.da.lawss.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.lawss.entity.SarSarAccessInfoEO;
import com.adc.da.lawss.page.SarSarAccessInfoEOPage;
import com.adc.da.lawss.service.SarSarAccessInfoEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

@RestController
@RequestMapping("/${restPath}/lawss/sarSarAccessInfo")
@Api(description = "|SarSarAccessInfoEO|")
public class SarSarAccessInfoEOController extends BaseController<SarSarAccessInfoEO>{

    private static final Logger logger = LoggerFactory.getLogger(SarSarAccessInfoEOController.class);

    @Autowired
    private SarSarAccessInfoEOService sarSarAccessInfoEOService;

	@ApiOperation(value = "|SarSarAccessInfoEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("lawss:sarSarAccessInfo:page")
    public ResponseMessage<PageInfo<SarSarAccessInfoEO>> page(SarSarAccessInfoEOPage page) throws Exception {
        List<SarSarAccessInfoEO> rows = sarSarAccessInfoEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

	@ApiOperation(value = "|SarSarAccessInfoEO|查询")
    @GetMapping("")
    @RequiresPermissions("lawss:sarSarAccessInfo:list")
    public ResponseMessage<List<SarSarAccessInfoEO>> list(SarSarAccessInfoEOPage page) throws Exception {
        return Result.success(sarSarAccessInfoEOService.queryByList(page));
	}

    @ApiOperation(value = "|SarSarAccessInfoEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("lawss:sarSarAccessInfo:get")
    public ResponseMessage<SarSarAccessInfoEO> find(@PathVariable String id) throws Exception {
        return Result.success(sarSarAccessInfoEOService.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "|SarSarAccessInfoEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("lawss:sarSarAccessInfo:save")
    public ResponseMessage<SarSarAccessInfoEO> create(@RequestBody SarSarAccessInfoEO sarSarAccessInfoEO) throws Exception {
        sarSarAccessInfoEOService.insertSelective(sarSarAccessInfoEO);
        return Result.success(sarSarAccessInfoEO);
    }

    @ApiOperation(value = "|SarSarAccessInfoEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("lawss:sarSarAccessInfo:update")
    public ResponseMessage<SarSarAccessInfoEO> update(@RequestBody SarSarAccessInfoEO sarSarAccessInfoEO) throws Exception {
        sarSarAccessInfoEOService.updateByPrimaryKeySelective(sarSarAccessInfoEO);
        return Result.success(sarSarAccessInfoEO);
    }

    @ApiOperation(value = "|SarSarAccessInfoEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("lawss:sarSarAccessInfo:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        sarSarAccessInfoEOService.deleteByPrimaryKey(id);
        logger.info("delete from SAR_SAR_ACCESS_INFO where id = {}", id);
        return Result.success();
    }

}
