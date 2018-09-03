package com.adc.da.lawss.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.lawss.entity.SarBussionessStandEO;
import com.adc.da.lawss.page.SarBussionessStandEOPage;
import com.adc.da.lawss.service.SarBussionessStandEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

@RestController
@RequestMapping("/${restPath}/lawss/sarBussionessStand")
@Api(description = "|SarBussionessStandEO|")
public class SarBussionessStandEOController extends BaseController<SarBussionessStandEO>{

    private static final Logger logger = LoggerFactory.getLogger(SarBussionessStandEOController.class);

    @Autowired
    private SarBussionessStandEOService sarBussionessStandEOService;

	@ApiOperation(value = "|SarBussionessStandEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("lawss:sarBussionessStand:page")
    public ResponseMessage<PageInfo<SarBussionessStandEO>> page(SarBussionessStandEOPage page) throws Exception {
        List<SarBussionessStandEO> rows = sarBussionessStandEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

	@ApiOperation(value = "|SarBussionessStandEO|查询")
    @GetMapping("")
    @RequiresPermissions("lawss:sarBussionessStand:list")
    public ResponseMessage<List<SarBussionessStandEO>> list(SarBussionessStandEOPage page) throws Exception {
        return Result.success(sarBussionessStandEOService.queryByList(page));
	}

    @ApiOperation(value = "|SarBussionessStandEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("lawss:sarBussionessStand:get")
    public ResponseMessage<SarBussionessStandEO> find(@PathVariable String id) throws Exception {
        return Result.success(sarBussionessStandEOService.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "|SarBussionessStandEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("lawss:sarBussionessStand:save")
    public ResponseMessage<SarBussionessStandEO> create(@RequestBody SarBussionessStandEO sarBussionessStandEO) throws Exception {
        sarBussionessStandEOService.insertSelective(sarBussionessStandEO);
        return Result.success(sarBussionessStandEO);
    }

    @ApiOperation(value = "|SarBussionessStandEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("lawss:sarBussionessStand:update")
    public ResponseMessage<SarBussionessStandEO> update(@RequestBody SarBussionessStandEO sarBussionessStandEO) throws Exception {
        sarBussionessStandEOService.updateByPrimaryKeySelective(sarBussionessStandEO);
        return Result.success(sarBussionessStandEO);
    }

    @ApiOperation(value = "|SarBussionessStandEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("lawss:sarBussionessStand:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        sarBussionessStandEOService.deleteByPrimaryKey(id);
        logger.info("delete from SAR_BUSSIONESS_STAND where id = {}", id);
        return Result.success();
    }

}
