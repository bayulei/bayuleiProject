package com.adc.da.activiti.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.activiti.entity.BusNoticecheckProcessEO;
import com.adc.da.activiti.page.BusNoticecheckProcessEOPage;
import com.adc.da.activiti.service.BusNoticecheckProcessEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

@RestController
@RequestMapping("/${restPath}/activiti/busNoticecheckProcess")
@Api(description = "|BusNoticecheckProcessEO|")
public class BusNoticecheckProcessEOController extends BaseController<BusNoticecheckProcessEO>{

    private static final Logger logger = LoggerFactory.getLogger(BusNoticecheckProcessEOController.class);

    @Autowired
    private BusNoticecheckProcessEOService busNoticecheckProcessEOService;

	@ApiOperation(value = "|BusNoticecheckProcessEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("activiti:busNoticecheckProcess:page")
    public ResponseMessage<PageInfo<BusNoticecheckProcessEO>> page(BusNoticecheckProcessEOPage page) throws Exception {
        List<BusNoticecheckProcessEO> rows = busNoticecheckProcessEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

	@ApiOperation(value = "|BusNoticecheckProcessEO|查询")
    @GetMapping("")
    @RequiresPermissions("activiti:busNoticecheckProcess:list")
    public ResponseMessage<List<BusNoticecheckProcessEO>> list(BusNoticecheckProcessEOPage page) throws Exception {
        return Result.success(busNoticecheckProcessEOService.queryByList(page));
	}

    @ApiOperation(value = "|BusNoticecheckProcessEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("activiti:busNoticecheckProcess:get")
    public ResponseMessage<BusNoticecheckProcessEO> find(@PathVariable String id) throws Exception {
        return Result.success(busNoticecheckProcessEOService.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "|BusNoticecheckProcessEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("activiti:busNoticecheckProcess:save")
    public ResponseMessage<BusNoticecheckProcessEO> create(@RequestBody BusNoticecheckProcessEO busNoticecheckProcessEO) throws Exception {
        busNoticecheckProcessEOService.insertSelective(busNoticecheckProcessEO);
        return Result.success(busNoticecheckProcessEO);
    }

    @ApiOperation(value = "|BusNoticecheckProcessEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("activiti:busNoticecheckProcess:update")
    public ResponseMessage<BusNoticecheckProcessEO> update(@RequestBody BusNoticecheckProcessEO busNoticecheckProcessEO) throws Exception {
        busNoticecheckProcessEOService.updateByPrimaryKeySelective(busNoticecheckProcessEO);
        return Result.success(busNoticecheckProcessEO);
    }

    @ApiOperation(value = "|BusNoticecheckProcessEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("activiti:busNoticecheckProcess:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        busNoticecheckProcessEOService.deleteByPrimaryKey(id);
        logger.info("delete from BUS_NOTICECHECK_PROCESS where id = {}", id);
        return Result.success();
    }

}
