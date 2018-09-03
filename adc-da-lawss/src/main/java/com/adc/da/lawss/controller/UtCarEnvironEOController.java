package com.adc.da.lawss.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.lawss.entity.UtCarEnvironEO;
import com.adc.da.lawss.page.UtCarEnvironEOPage;
import com.adc.da.lawss.service.UtCarEnvironEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

@RestController
@RequestMapping("/${restPath}/lawss/utCarEnviron")
@Api(description = "|UtCarEnvironEO|")
public class UtCarEnvironEOController extends BaseController<UtCarEnvironEO>{

    private static final Logger logger = LoggerFactory.getLogger(UtCarEnvironEOController.class);

    @Autowired
    private UtCarEnvironEOService utCarEnvironEOService;

	@ApiOperation(value = "|UtCarEnvironEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("lawss:utCarEnviron:page")
    public ResponseMessage<PageInfo<UtCarEnvironEO>> page(UtCarEnvironEOPage page) throws Exception {
        List<UtCarEnvironEO> rows = utCarEnvironEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

	@ApiOperation(value = "|UtCarEnvironEO|查询")
    @GetMapping("")
    @RequiresPermissions("lawss:utCarEnviron:list")
    public ResponseMessage<List<UtCarEnvironEO>> list(UtCarEnvironEOPage page) throws Exception {
        return Result.success(utCarEnvironEOService.queryByList(page));
	}

    @ApiOperation(value = "|UtCarEnvironEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("lawss:utCarEnviron:get")
    public ResponseMessage<UtCarEnvironEO> find(@PathVariable String id) throws Exception {
        return Result.success(utCarEnvironEOService.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "|UtCarEnvironEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("lawss:utCarEnviron:save")
    public ResponseMessage<UtCarEnvironEO> create(@RequestBody UtCarEnvironEO utCarEnvironEO) throws Exception {
        utCarEnvironEOService.insertSelective(utCarEnvironEO);
        return Result.success(utCarEnvironEO);
    }

    @ApiOperation(value = "|UtCarEnvironEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("lawss:utCarEnviron:update")
    public ResponseMessage<UtCarEnvironEO> update(@RequestBody UtCarEnvironEO utCarEnvironEO) throws Exception {
        utCarEnvironEOService.updateByPrimaryKeySelective(utCarEnvironEO);
        return Result.success(utCarEnvironEO);
    }

    @ApiOperation(value = "|UtCarEnvironEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("lawss:utCarEnviron:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        utCarEnvironEOService.deleteByPrimaryKey(id);
        logger.info("delete from UT_CAR_ENVIRON where id = {}", id);
        return Result.success();
    }

}
