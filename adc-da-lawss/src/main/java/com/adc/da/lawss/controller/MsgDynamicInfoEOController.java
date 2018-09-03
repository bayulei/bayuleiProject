package com.adc.da.lawss.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.lawss.entity.MsgDynamicInfoEO;
import com.adc.da.lawss.page.MsgDynamicInfoEOPage;
import com.adc.da.lawss.service.MsgDynamicInfoEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

@RestController
@RequestMapping("/${restPath}/lawss/msgDynamicInfo")
@Api(description = "|MsgDynamicInfoEO|")
public class MsgDynamicInfoEOController extends BaseController<MsgDynamicInfoEO>{

    private static final Logger logger = LoggerFactory.getLogger(MsgDynamicInfoEOController.class);

    @Autowired
    private MsgDynamicInfoEOService msgDynamicInfoEOService;

	@ApiOperation(value = "|MsgDynamicInfoEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("lawss:msgDynamicInfo:page")
    public ResponseMessage<PageInfo<MsgDynamicInfoEO>> page(MsgDynamicInfoEOPage page) throws Exception {
        List<MsgDynamicInfoEO> rows = msgDynamicInfoEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

	@ApiOperation(value = "|MsgDynamicInfoEO|查询")
    @GetMapping("")
    @RequiresPermissions("lawss:msgDynamicInfo:list")
    public ResponseMessage<List<MsgDynamicInfoEO>> list(MsgDynamicInfoEOPage page) throws Exception {
        return Result.success(msgDynamicInfoEOService.queryByList(page));
	}

    @ApiOperation(value = "|MsgDynamicInfoEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("lawss:msgDynamicInfo:get")
    public ResponseMessage<MsgDynamicInfoEO> find(@PathVariable String id) throws Exception {
        return Result.success(msgDynamicInfoEOService.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "|MsgDynamicInfoEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("lawss:msgDynamicInfo:save")
    public ResponseMessage<MsgDynamicInfoEO> create(@RequestBody MsgDynamicInfoEO msgDynamicInfoEO) throws Exception {
        msgDynamicInfoEOService.insertSelective(msgDynamicInfoEO);
        return Result.success(msgDynamicInfoEO);
    }

    @ApiOperation(value = "|MsgDynamicInfoEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("lawss:msgDynamicInfo:update")
    public ResponseMessage<MsgDynamicInfoEO> update(@RequestBody MsgDynamicInfoEO msgDynamicInfoEO) throws Exception {
        msgDynamicInfoEOService.updateByPrimaryKeySelective(msgDynamicInfoEO);
        return Result.success(msgDynamicInfoEO);
    }

    @ApiOperation(value = "|MsgDynamicInfoEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("lawss:msgDynamicInfo:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        msgDynamicInfoEOService.deleteByPrimaryKey(id);
        logger.info("delete from MSG_DYNAMIC_INFO where id = {}", id);
        return Result.success();
    }

}
