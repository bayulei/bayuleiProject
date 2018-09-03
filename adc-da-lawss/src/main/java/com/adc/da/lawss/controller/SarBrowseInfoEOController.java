package com.adc.da.lawss.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.lawss.entity.SarBrowseInfoEO;
import com.adc.da.lawss.page.SarBrowseInfoEOPage;
import com.adc.da.lawss.service.SarBrowseInfoEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

@RestController
@RequestMapping("/${restPath}/lawss/sarBrowseInfo")
@Api(description = "|SarBrowseInfoEO|")
public class SarBrowseInfoEOController extends BaseController<SarBrowseInfoEO>{

    private static final Logger logger = LoggerFactory.getLogger(SarBrowseInfoEOController.class);

    @Autowired
    private SarBrowseInfoEOService sarBrowseInfoEOService;

	@ApiOperation(value = "|SarBrowseInfoEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("lawss:sarBrowseInfo:page")
    public ResponseMessage<PageInfo<SarBrowseInfoEO>> page(SarBrowseInfoEOPage page) throws Exception {
        List<SarBrowseInfoEO> rows = sarBrowseInfoEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

	@ApiOperation(value = "|SarBrowseInfoEO|查询")
    @GetMapping("")
    @RequiresPermissions("lawss:sarBrowseInfo:list")
    public ResponseMessage<List<SarBrowseInfoEO>> list(SarBrowseInfoEOPage page) throws Exception {
        return Result.success(sarBrowseInfoEOService.queryByList(page));
	}

    @ApiOperation(value = "|SarBrowseInfoEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("lawss:sarBrowseInfo:get")
    public ResponseMessage<SarBrowseInfoEO> find(@PathVariable String id) throws Exception {
        return Result.success(sarBrowseInfoEOService.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "|SarBrowseInfoEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("lawss:sarBrowseInfo:save")
    public ResponseMessage<SarBrowseInfoEO> create(@RequestBody SarBrowseInfoEO sarBrowseInfoEO) throws Exception {
        sarBrowseInfoEOService.insertSelective(sarBrowseInfoEO);
        return Result.success(sarBrowseInfoEO);
    }

    @ApiOperation(value = "|SarBrowseInfoEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("lawss:sarBrowseInfo:update")
    public ResponseMessage<SarBrowseInfoEO> update(@RequestBody SarBrowseInfoEO sarBrowseInfoEO) throws Exception {
        sarBrowseInfoEOService.updateByPrimaryKeySelective(sarBrowseInfoEO);
        return Result.success(sarBrowseInfoEO);
    }

    @ApiOperation(value = "|SarBrowseInfoEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("lawss:sarBrowseInfo:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        sarBrowseInfoEOService.deleteByPrimaryKey(id);
        logger.info("delete from SAR_BROWSE_INFO where id = {}", id);
        return Result.success();
    }

}
