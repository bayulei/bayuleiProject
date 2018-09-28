package com.adc.da.lawss.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.lawss.entity.SarTestItemValEO;
import com.adc.da.lawss.page.SarTestItemValEOPage;
import com.adc.da.lawss.service.SarTestItemValEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

@RestController
@RequestMapping("/${restPath}/lawss/sarTestItemVal")
@Api(description = "|SarTestItemValEO|")
public class SarTestItemValEOController extends BaseController<SarTestItemValEO>{

    private static final Logger logger = LoggerFactory.getLogger(SarTestItemValEOController.class);

    @Autowired
    private SarTestItemValEOService sarTestItemValEOService;

	@ApiOperation(value = "|SarTestItemValEO|分页查询")
    @GetMapping("/page")
//    @RequiresPermissions("lawss:sarTestItemVal:page")
    public ResponseMessage<PageInfo<SarTestItemValEO>> page(SarTestItemValEOPage page) throws Exception {
        List<SarTestItemValEO> rows = sarTestItemValEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

	@ApiOperation(value = "|SarTestItemValEO|查询")
    @GetMapping("")
//    @RequiresPermissions("lawss:sarTestItemVal:list")
    public ResponseMessage<List<SarTestItemValEO>> list(SarTestItemValEOPage page) throws Exception {
        return Result.success(sarTestItemValEOService.queryByList(page));
	}

    @ApiOperation(value = "|SarTestItemValEO|详情")
    @GetMapping("/{id}")
//    @RequiresPermissions("lawss:sarTestItemVal:get")
    public ResponseMessage<SarTestItemValEO> find(@PathVariable String id) throws Exception {
        return Result.success(sarTestItemValEOService.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "|SarTestItemValEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
//    @RequiresPermissions("lawss:sarTestItemVal:save")
    public ResponseMessage<SarTestItemValEO> create(@RequestBody SarTestItemValEO sarTestItemValEO) throws Exception {
        sarTestItemValEOService.insertSelective(sarTestItemValEO);
        return Result.success(sarTestItemValEO);
    }

    @ApiOperation(value = "|SarTestItemValEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
//    @RequiresPermissions("lawss:sarTestItemVal:update")
    public ResponseMessage<SarTestItemValEO> update(@RequestBody SarTestItemValEO sarTestItemValEO) throws Exception {
        sarTestItemValEOService.updateByPrimaryKeySelective(sarTestItemValEO);
        return Result.success(sarTestItemValEO);
    }

    @ApiOperation(value = "|SarTestItemValEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("lawss:sarTestItemVal:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        sarTestItemValEOService.deleteByPrimaryKey(id);
        logger.info("delete from SAR_TEST_ITEM_VAL where id = {}", id);
        return Result.success();
    }

}
