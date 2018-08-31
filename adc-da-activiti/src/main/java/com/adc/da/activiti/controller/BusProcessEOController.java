package com.adc.da.activiti.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.activiti.entity.BusProcessEO;
import com.adc.da.activiti.page.BusProcessEOPage;
import com.adc.da.activiti.service.BusProcessEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

@RestController
@RequestMapping("/${restPath}/activiti/busProcess")
@Api(description = "|BusProcessEO|")
public class BusProcessEOController extends BaseController<BusProcessEO>{

    private static final Logger logger = LoggerFactory.getLogger(BusProcessEOController.class);

    @Autowired
    private BusProcessEOService busProcessEOService;

	@ApiOperation(value = "|BusProcessEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("activiti:busProcess:page")
    public ResponseMessage<PageInfo<BusProcessEO>> page(BusProcessEOPage page) throws Exception {
        List<BusProcessEO> rows = busProcessEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

	@ApiOperation(value = "|BusProcessEO|查询")
    @GetMapping("")
    @RequiresPermissions("activiti:busProcess:list")
    public ResponseMessage<List<BusProcessEO>> list(BusProcessEOPage page) throws Exception {
        return Result.success(busProcessEOService.queryByList(page));
	}

    @ApiOperation(value = "|BusProcessEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("activiti:busProcess:get")
    public ResponseMessage<BusProcessEO> find(@PathVariable String id) throws Exception {
        return Result.success(busProcessEOService.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "|BusProcessEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("activiti:busProcess:save")
    public ResponseMessage<BusProcessEO> create(@RequestBody BusProcessEO busProcessEO) throws Exception {
        busProcessEOService.insertSelective(busProcessEO);
        return Result.success(busProcessEO);
    }

    @ApiOperation(value = "|BusProcessEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("activiti:busProcess:update")
    public ResponseMessage<BusProcessEO> update(@RequestBody BusProcessEO busProcessEO) throws Exception {
        busProcessEOService.updateByPrimaryKeySelective(busProcessEO);
        return Result.success(busProcessEO);
    }

    @ApiOperation(value = "|BusProcessEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("activiti:busProcess:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        busProcessEOService.deleteByPrimaryKey(id);
        logger.info("delete from BUS_PROCESS where id = {}", id);
        return Result.success();
    }

}
