package com.adc.da.lawss.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.lawss.entity.OtConvertMqEO;
import com.adc.da.lawss.page.OtConvertMqEOPage;
import com.adc.da.lawss.service.OtConvertMqEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

@RestController
@RequestMapping("/${restPath}/lawss/otConvertMq")
@Api(description = "|OtConvertMqEO|")
public class OtConvertMqEOController extends BaseController<OtConvertMqEO>{

    private static final Logger logger = LoggerFactory.getLogger(OtConvertMqEOController.class);

    @Autowired
    private OtConvertMqEOService otConvertMqEOService;

	@ApiOperation(value = "|OtConvertMqEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("lawss:otConvertMq:page")
    public ResponseMessage<PageInfo<OtConvertMqEO>> page(OtConvertMqEOPage page) throws Exception {
        List<OtConvertMqEO> rows = otConvertMqEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

	@ApiOperation(value = "|OtConvertMqEO|查询")
    @GetMapping("")
    @RequiresPermissions("lawss:otConvertMq:list")
    public ResponseMessage<List<OtConvertMqEO>> list(OtConvertMqEOPage page) throws Exception {
        return Result.success(otConvertMqEOService.queryByList(page));
	}

    @ApiOperation(value = "|OtConvertMqEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("lawss:otConvertMq:get")
    public ResponseMessage<OtConvertMqEO> find(@PathVariable String id) throws Exception {
        return Result.success(otConvertMqEOService.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "|OtConvertMqEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("lawss:otConvertMq:save")
    public ResponseMessage<OtConvertMqEO> create(@RequestBody OtConvertMqEO otConvertMqEO) throws Exception {
        otConvertMqEOService.insertSelective(otConvertMqEO);
        return Result.success(otConvertMqEO);
    }

    @ApiOperation(value = "|OtConvertMqEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("lawss:otConvertMq:update")
    public ResponseMessage<OtConvertMqEO> update(@RequestBody OtConvertMqEO otConvertMqEO) throws Exception {
        otConvertMqEOService.updateByPrimaryKeySelective(otConvertMqEO);
        return Result.success(otConvertMqEO);
    }

    @ApiOperation(value = "|OtConvertMqEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("lawss:otConvertMq:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        otConvertMqEOService.deleteByPrimaryKey(id);
        logger.info("delete from OT_CONVERT_MQ where id = {}", id);
        return Result.success();
    }

}
