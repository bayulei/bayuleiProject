package com.adc.da.lawss.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.lawss.entity.MsgFileEO;
import com.adc.da.lawss.page.MsgFileEOPage;
import com.adc.da.lawss.service.MsgFileEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

@RestController
@RequestMapping("/${restPath}/lawss/msgFile")
@Api(description = "|MsgFileEO|")
public class MsgFileEOController extends BaseController<MsgFileEO>{

    private static final Logger logger = LoggerFactory.getLogger(MsgFileEOController.class);

    @Autowired
    private MsgFileEOService msgFileEOService;

	@ApiOperation(value = "|MsgFileEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("lawss:msgFile:page")
    public ResponseMessage<PageInfo<MsgFileEO>> page(MsgFileEOPage page) throws Exception {
        List<MsgFileEO> rows = msgFileEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

	@ApiOperation(value = "|MsgFileEO|查询")
    @GetMapping("")
    @RequiresPermissions("lawss:msgFile:list")
    public ResponseMessage<List<MsgFileEO>> list(MsgFileEOPage page) throws Exception {
        return Result.success(msgFileEOService.queryByList(page));
	}

    @ApiOperation(value = "|MsgFileEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("lawss:msgFile:get")
    public ResponseMessage<MsgFileEO> find(@PathVariable String id) throws Exception {
        return Result.success(msgFileEOService.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "|MsgFileEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("lawss:msgFile:save")
    public ResponseMessage<MsgFileEO> create(@RequestBody MsgFileEO msgFileEO) throws Exception {
        msgFileEOService.insertSelective(msgFileEO);
        return Result.success(msgFileEO);
    }

    @ApiOperation(value = "|MsgFileEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("lawss:msgFile:update")
    public ResponseMessage<MsgFileEO> update(@RequestBody MsgFileEO msgFileEO) throws Exception {
        msgFileEOService.updateByPrimaryKeySelective(msgFileEO);
        return Result.success(msgFileEO);
    }

    @ApiOperation(value = "|MsgFileEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("lawss:msgFile:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        msgFileEOService.deleteByPrimaryKey(id);
        logger.info("delete from MSG_FILE where id = {}", id);
        return Result.success();
    }

}
