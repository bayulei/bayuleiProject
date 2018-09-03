package com.adc.da.lawss.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.lawss.entity.OtStandItemEO;
import com.adc.da.lawss.page.OtStandItemEOPage;
import com.adc.da.lawss.service.OtStandItemEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

@RestController
@RequestMapping("/${restPath}/lawss/otStandItem")
@Api(description = "|OtStandItemEO|")
public class OtStandItemEOController extends BaseController<OtStandItemEO>{

    private static final Logger logger = LoggerFactory.getLogger(OtStandItemEOController.class);

    @Autowired
    private OtStandItemEOService otStandItemEOService;

	@ApiOperation(value = "|OtStandItemEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("lawss:otStandItem:page")
    public ResponseMessage<PageInfo<OtStandItemEO>> page(OtStandItemEOPage page) throws Exception {
        List<OtStandItemEO> rows = otStandItemEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

	@ApiOperation(value = "|OtStandItemEO|查询")
    @GetMapping("")
    @RequiresPermissions("lawss:otStandItem:list")
    public ResponseMessage<List<OtStandItemEO>> list(OtStandItemEOPage page) throws Exception {
        return Result.success(otStandItemEOService.queryByList(page));
	}

    @ApiOperation(value = "|OtStandItemEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("lawss:otStandItem:get")
    public ResponseMessage<OtStandItemEO> find(@PathVariable String id) throws Exception {
        return Result.success(otStandItemEOService.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "|OtStandItemEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("lawss:otStandItem:save")
    public ResponseMessage<OtStandItemEO> create(@RequestBody OtStandItemEO otStandItemEO) throws Exception {
        otStandItemEOService.insertSelective(otStandItemEO);
        return Result.success(otStandItemEO);
    }

    @ApiOperation(value = "|OtStandItemEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("lawss:otStandItem:update")
    public ResponseMessage<OtStandItemEO> update(@RequestBody OtStandItemEO otStandItemEO) throws Exception {
        otStandItemEOService.updateByPrimaryKeySelective(otStandItemEO);
        return Result.success(otStandItemEO);
    }

    @ApiOperation(value = "|OtStandItemEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("lawss:otStandItem:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        otStandItemEOService.deleteByPrimaryKey(id);
        logger.info("delete from OT_STAND_ITEM where id = {}", id);
        return Result.success();
    }

}
