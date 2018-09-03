package com.adc.da.lawss.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.lawss.entity.SarBussStandValEO;
import com.adc.da.lawss.page.SarBussStandValEOPage;
import com.adc.da.lawss.service.SarBussStandValEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

@RestController
@RequestMapping("/${restPath}/lawss/sarBussStandVal")
@Api(description = "|SarBussStandValEO|")
public class SarBussStandValEOController extends BaseController<SarBussStandValEO>{

    private static final Logger logger = LoggerFactory.getLogger(SarBussStandValEOController.class);

    @Autowired
    private SarBussStandValEOService sarBussStandValEOService;

	@ApiOperation(value = "|SarBussStandValEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("lawss:sarBussStandVal:page")
    public ResponseMessage<PageInfo<SarBussStandValEO>> page(SarBussStandValEOPage page) throws Exception {
        List<SarBussStandValEO> rows = sarBussStandValEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

	@ApiOperation(value = "|SarBussStandValEO|查询")
    @GetMapping("")
    @RequiresPermissions("lawss:sarBussStandVal:list")
    public ResponseMessage<List<SarBussStandValEO>> list(SarBussStandValEOPage page) throws Exception {
        return Result.success(sarBussStandValEOService.queryByList(page));
	}

    @ApiOperation(value = "|SarBussStandValEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("lawss:sarBussStandVal:save")
    public ResponseMessage<SarBussStandValEO> create(@RequestBody SarBussStandValEO sarBussStandValEO) throws Exception {
        sarBussStandValEOService.insertSelective(sarBussStandValEO);
        return Result.success(sarBussStandValEO);
    }

}
