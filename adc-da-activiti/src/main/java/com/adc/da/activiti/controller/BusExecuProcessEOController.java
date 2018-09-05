package com.adc.da.activiti.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.activiti.entity.BusExecuProcessEO;
import com.adc.da.activiti.page.BusExecuProcessEOPage;
import com.adc.da.activiti.service.BusExecuProcessEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

@RestController
@RequestMapping("/${restPath}/activiti/busExecuProcess")
@Api(description = "|BusExecuProcessEO|")
public class BusExecuProcessEOController extends BaseController<BusExecuProcessEO>{

    private static final Logger logger = LoggerFactory.getLogger(BusExecuProcessEOController.class);

    @Autowired
    private BusExecuProcessEOService busExecuProcessEOService;

	@ApiOperation(value = "|BusExecuProcessEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("activiti:busExecuProcess:page")
    public ResponseMessage<PageInfo<BusExecuProcessEO>> page(BusExecuProcessEOPage page) throws Exception {
        List<BusExecuProcessEO> rows = busExecuProcessEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

	@ApiOperation(value = "|BusExecuProcessEO|查询")
    @GetMapping("")
    @RequiresPermissions("activiti:busExecuProcess:list")
    public ResponseMessage<List<BusExecuProcessEO>> list(BusExecuProcessEOPage page) throws Exception {
        return Result.success(busExecuProcessEOService.queryByList(page));
	}

    @ApiOperation(value = "|BusExecuProcessEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("activiti:busExecuProcess:get")
    public ResponseMessage<BusExecuProcessEO> find(@PathVariable String id) throws Exception {
        return Result.success(busExecuProcessEOService.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "|BusExecuProcessEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("activiti:busExecuProcess:save")
    public ResponseMessage<BusExecuProcessEO> create(@RequestBody BusExecuProcessEO busExecuProcessEO) throws Exception {
        busExecuProcessEOService.insertSelective(busExecuProcessEO);
        return Result.success(busExecuProcessEO);
    }

    @ApiOperation(value = "|BusExecuProcessEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("activiti:busExecuProcess:update")
    public ResponseMessage<BusExecuProcessEO> update(@RequestBody BusExecuProcessEO busExecuProcessEO) throws Exception {
        busExecuProcessEOService.updateByPrimaryKeySelective(busExecuProcessEO);
        return Result.success(busExecuProcessEO);
    }

    @ApiOperation(value = "|BusExecuProcessEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("activiti:busExecuProcess:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        busExecuProcessEOService.deleteByPrimaryKey(id);
        logger.info("delete from BUS_EXECU_PROCESS where id = {}", id);
        return Result.success();
    }

}
