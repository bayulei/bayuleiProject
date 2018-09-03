package com.adc.da.lawss.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.lawss.entity.MsgSarPutTimeEO;
import com.adc.da.lawss.page.MsgSarPutTimeEOPage;
import com.adc.da.lawss.service.MsgSarPutTimeEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

@RestController
@RequestMapping("/${restPath}/lawss/msgSarPutTime")
@Api(description = "|MsgSarPutTimeEO|")
public class MsgSarPutTimeEOController extends BaseController<MsgSarPutTimeEO>{

    private static final Logger logger = LoggerFactory.getLogger(MsgSarPutTimeEOController.class);

    @Autowired
    private MsgSarPutTimeEOService msgSarPutTimeEOService;

	@ApiOperation(value = "|MsgSarPutTimeEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("lawss:msgSarPutTime:page")
    public ResponseMessage<PageInfo<MsgSarPutTimeEO>> page(MsgSarPutTimeEOPage page) throws Exception {
        List<MsgSarPutTimeEO> rows = msgSarPutTimeEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

	@ApiOperation(value = "|MsgSarPutTimeEO|查询")
    @GetMapping("")
    @RequiresPermissions("lawss:msgSarPutTime:list")
    public ResponseMessage<List<MsgSarPutTimeEO>> list(MsgSarPutTimeEOPage page) throws Exception {
        return Result.success(msgSarPutTimeEOService.queryByList(page));
	}

    @ApiOperation(value = "|MsgSarPutTimeEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("lawss:msgSarPutTime:get")
    public ResponseMessage<MsgSarPutTimeEO> find(@PathVariable String id) throws Exception {
        return Result.success(msgSarPutTimeEOService.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "|MsgSarPutTimeEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("lawss:msgSarPutTime:save")
    public ResponseMessage<MsgSarPutTimeEO> create(@RequestBody MsgSarPutTimeEO msgSarPutTimeEO) throws Exception {
        msgSarPutTimeEOService.insertSelective(msgSarPutTimeEO);
        return Result.success(msgSarPutTimeEO);
    }

    @ApiOperation(value = "|MsgSarPutTimeEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("lawss:msgSarPutTime:update")
    public ResponseMessage<MsgSarPutTimeEO> update(@RequestBody MsgSarPutTimeEO msgSarPutTimeEO) throws Exception {
        msgSarPutTimeEOService.updateByPrimaryKeySelective(msgSarPutTimeEO);
        return Result.success(msgSarPutTimeEO);
    }

    @ApiOperation(value = "|MsgSarPutTimeEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("lawss:msgSarPutTime:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        msgSarPutTimeEOService.deleteByPrimaryKey(id);
        logger.info("delete from MSG_SAR_PUT_TIME where id = {}", id);
        return Result.success();
    }

}
