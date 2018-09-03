package com.adc.da.lawss.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.lawss.entity.SarSarAccessEO;
import com.adc.da.lawss.page.SarSarAccessEOPage;
import com.adc.da.lawss.service.SarSarAccessEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

@RestController
@RequestMapping("/${restPath}/lawss/sarSarAccess")
@Api(description = "|SarSarAccessEO|")
public class SarSarAccessEOController extends BaseController<SarSarAccessEO>{

    private static final Logger logger = LoggerFactory.getLogger(SarSarAccessEOController.class);

    @Autowired
    private SarSarAccessEOService sarSarAccessEOService;

	@ApiOperation(value = "|SarSarAccessEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("lawss:sarSarAccess:page")
    public ResponseMessage<PageInfo<SarSarAccessEO>> page(SarSarAccessEOPage page) throws Exception {
        List<SarSarAccessEO> rows = sarSarAccessEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

	@ApiOperation(value = "|SarSarAccessEO|查询")
    @GetMapping("")
    @RequiresPermissions("lawss:sarSarAccess:list")
    public ResponseMessage<List<SarSarAccessEO>> list(SarSarAccessEOPage page) throws Exception {
        return Result.success(sarSarAccessEOService.queryByList(page));
	}

    @ApiOperation(value = "|SarSarAccessEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("lawss:sarSarAccess:get")
    public ResponseMessage<SarSarAccessEO> find(@PathVariable String id) throws Exception {
        return Result.success(sarSarAccessEOService.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "|SarSarAccessEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("lawss:sarSarAccess:save")
    public ResponseMessage<SarSarAccessEO> create(@RequestBody SarSarAccessEO sarSarAccessEO) throws Exception {
        sarSarAccessEOService.insertSelective(sarSarAccessEO);
        return Result.success(sarSarAccessEO);
    }

    @ApiOperation(value = "|SarSarAccessEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("lawss:sarSarAccess:update")
    public ResponseMessage<SarSarAccessEO> update(@RequestBody SarSarAccessEO sarSarAccessEO) throws Exception {
        sarSarAccessEOService.updateByPrimaryKeySelective(sarSarAccessEO);
        return Result.success(sarSarAccessEO);
    }

    @ApiOperation(value = "|SarSarAccessEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("lawss:sarSarAccess:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        sarSarAccessEOService.deleteByPrimaryKey(id);
        logger.info("delete from SAR_SAR_ACCESS where id = {}", id);
        return Result.success();
    }

}
