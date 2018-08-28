package com.adc.da.search.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.search.entity.PartProductEO;
import com.adc.da.search.page.PartProductEOPage;
import com.adc.da.search.service.PartProductEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

@RestController
@RequestMapping("/${restPath}/search/partProduct")
@Api(description = "|PartProductEO|")
public class PartProductEOController extends BaseController<PartProductEO>{

    private static final Logger logger = LoggerFactory.getLogger(PartProductEOController.class);

    @Autowired
    private PartProductEOService partProductEOService;

	@ApiOperation(value = "|PartProductEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("search:partProduct:page")
    public ResponseMessage<PageInfo<PartProductEO>> page(PartProductEOPage page) throws Exception {
        List<PartProductEO> rows = partProductEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

	@ApiOperation(value = "|PartProductEO|查询")
    @GetMapping("")
    @RequiresPermissions("search:partProduct:list")
    public ResponseMessage<List<PartProductEO>> list(PartProductEOPage page) throws Exception {
        return Result.success(partProductEOService.queryByList(page));
	}

    @ApiOperation(value = "|PartProductEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("search:partProduct:get")
    public ResponseMessage<PartProductEO> find(@PathVariable String id) throws Exception {
        return Result.success(partProductEOService.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "|PartProductEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("search:partProduct:save")
    public ResponseMessage<PartProductEO> create(@RequestBody PartProductEO partProductEO) throws Exception {
        partProductEOService.insertSelective(partProductEO);
        return Result.success(partProductEO);
    }

    @ApiOperation(value = "|PartProductEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("search:partProduct:update")
    public ResponseMessage<PartProductEO> update(@RequestBody PartProductEO partProductEO) throws Exception {
        partProductEOService.updateByPrimaryKeySelective(partProductEO);
        return Result.success(partProductEO);
    }

    @ApiOperation(value = "|PartProductEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("search:partProduct:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        partProductEOService.deleteByPrimaryKey(id);
        logger.info("delete from PART_PRODUCT where id = {}", id);
        return Result.success();
    }

}
