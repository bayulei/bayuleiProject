package com.adc.da.lawss.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.lawss.entity.SarProductStandEO;
import com.adc.da.lawss.page.SarProductStandEOPage;
import com.adc.da.lawss.service.SarProductStandEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

@RestController
@RequestMapping("/${restPath}/lawss/sarProductStand")
@Api(description = "|SarProductStandEO|")
public class SarProductStandEOController extends BaseController<SarProductStandEO>{

    private static final Logger logger = LoggerFactory.getLogger(SarProductStandEOController.class);

    @Autowired
    private SarProductStandEOService sarProductStandEOService;

	@ApiOperation(value = "|SarProductStandEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("lawss:sarProductStand:page")
    public ResponseMessage<PageInfo<SarProductStandEO>> page(SarProductStandEOPage page) throws Exception {
        List<SarProductStandEO> rows = sarProductStandEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

	@ApiOperation(value = "|SarProductStandEO|查询")
    @GetMapping("")
    @RequiresPermissions("lawss:sarProductStand:list")
    public ResponseMessage<List<SarProductStandEO>> list(SarProductStandEOPage page) throws Exception {
        return Result.success(sarProductStandEOService.queryByList(page));
	}

    @ApiOperation(value = "|SarProductStandEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("lawss:sarProductStand:get")
    public ResponseMessage<SarProductStandEO> find(@PathVariable String id) throws Exception {
        return Result.success(sarProductStandEOService.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "|SarProductStandEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("lawss:sarProductStand:save")
    public ResponseMessage<SarProductStandEO> create(@RequestBody SarProductStandEO sarProductStandEO) throws Exception {
        sarProductStandEOService.insertSelective(sarProductStandEO);
        return Result.success(sarProductStandEO);
    }

    @ApiOperation(value = "|SarProductStandEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("lawss:sarProductStand:update")
    public ResponseMessage<SarProductStandEO> update(@RequestBody SarProductStandEO sarProductStandEO) throws Exception {
        sarProductStandEOService.updateByPrimaryKeySelective(sarProductStandEO);
        return Result.success(sarProductStandEO);
    }

    @ApiOperation(value = "|SarProductStandEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("lawss:sarProductStand:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        sarProductStandEOService.deleteByPrimaryKey(id);
        logger.info("delete from SAR_PRODUCT_STAND where id = {}", id);
        return Result.success();
    }

}
