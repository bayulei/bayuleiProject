package com.adc.da.lawss.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.lawss.entity.MsgModuleEO;
import com.adc.da.lawss.page.MsgModuleEOPage;
import com.adc.da.lawss.service.MsgModuleEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

@RestController
@RequestMapping("/${restPath}/lawss/msgModule")
@Api(description = "|MsgModuleEO|")
public class MsgModuleEOController extends BaseController<MsgModuleEO>{

    private static final Logger logger = LoggerFactory.getLogger(MsgModuleEOController.class);

    @Autowired
    private MsgModuleEOService msgModuleEOService;

	@ApiOperation(value = "|MsgModuleEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("lawss:msgModule:page")
    public ResponseMessage<PageInfo<MsgModuleEO>> page(MsgModuleEOPage page) throws Exception {
        List<MsgModuleEO> rows = msgModuleEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

	@ApiOperation(value = "|MsgModuleEO|查询")
    @GetMapping("")
    @RequiresPermissions("lawss:msgModule:list")
    public ResponseMessage<List<MsgModuleEO>> list(MsgModuleEOPage page) throws Exception {
        return Result.success(msgModuleEOService.queryByList(page));
	}

    @ApiOperation(value = "|MsgModuleEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("lawss:msgModule:get")
    public ResponseMessage<MsgModuleEO> find(@PathVariable String id) throws Exception {
        return Result.success(msgModuleEOService.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "|MsgModuleEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("lawss:msgModule:save")
    public ResponseMessage<MsgModuleEO> create(@RequestBody MsgModuleEO msgModuleEO) throws Exception {
        msgModuleEOService.insertSelective(msgModuleEO);
        return Result.success(msgModuleEO);
    }

    @ApiOperation(value = "|MsgModuleEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("lawss:msgModule:update")
    public ResponseMessage<MsgModuleEO> update(@RequestBody MsgModuleEO msgModuleEO) throws Exception {
        msgModuleEOService.updateByPrimaryKeySelective(msgModuleEO);
        return Result.success(msgModuleEO);
    }

    @ApiOperation(value = "|MsgModuleEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("lawss:msgModule:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        msgModuleEOService.deleteByPrimaryKey(id);
        logger.info("delete from MSG_MODULE where id = {}", id);
        return Result.success();
    }

}
