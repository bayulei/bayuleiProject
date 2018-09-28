package com.adc.da.activiti.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.activiti.entity.BusResAnalyProcessEO;
import com.adc.da.activiti.page.BusResAnalyProcessEOPage;
import com.adc.da.activiti.service.BusResAnalyProcessEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

@RestController
@RequestMapping("/${restPath}/activiti/busResAnalyProcess")
@Api(description = "|BusResAnalyProcessEO|")
public class BusResAnalyProcessEOController extends BaseController<BusResAnalyProcessEO>{

    private static final Logger logger = LoggerFactory.getLogger(BusResAnalyProcessEOController.class);

    @Autowired
    private BusResAnalyProcessEOService busResAnalyProcessEOService;

	@ApiOperation(value = "|BusResAnalyProcessEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("activiti:busResAnalyProcess:page")
    public ResponseMessage<PageInfo<BusResAnalyProcessEO>> page(BusResAnalyProcessEOPage page) throws Exception {
        List<BusResAnalyProcessEO> rows = busResAnalyProcessEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

	@ApiOperation(value = "|BusResAnalyProcessEO|查询")
    @GetMapping("")
    @RequiresPermissions("activiti:busResAnalyProcess:list")
    public ResponseMessage<List<BusResAnalyProcessEO>> list(BusResAnalyProcessEOPage page) throws Exception {
        return Result.success(busResAnalyProcessEOService.queryByList(page));
	}

    @ApiOperation(value = "|BusResAnalyProcessEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("activiti:busResAnalyProcess:get")
    public ResponseMessage<BusResAnalyProcessEO> find(@PathVariable String id) throws Exception {
        return Result.success(busResAnalyProcessEOService.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "|BusResAnalyProcessEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("activiti:busResAnalyProcess:save")
    public ResponseMessage<BusResAnalyProcessEO> create(@RequestBody BusResAnalyProcessEO busResAnalyProcessEO) throws Exception {
        busResAnalyProcessEOService.insertSelective(busResAnalyProcessEO);
        return Result.success(busResAnalyProcessEO);
    }

    @ApiOperation(value = "|BusResAnalyProcessEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("activiti:busResAnalyProcess:update")
    public ResponseMessage<BusResAnalyProcessEO> update(@RequestBody BusResAnalyProcessEO busResAnalyProcessEO) throws Exception {
        busResAnalyProcessEOService.updateByPrimaryKeySelective(busResAnalyProcessEO);
        return Result.success(busResAnalyProcessEO);
    }

    @ApiOperation(value = "|BusResAnalyProcessEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("activiti:busResAnalyProcess:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        busResAnalyProcessEOService.deleteByPrimaryKey(id);
        logger.info("delete from BUS_RES_ANALY_PROCESS where id = {}", id);
        return Result.success();
    }

}
