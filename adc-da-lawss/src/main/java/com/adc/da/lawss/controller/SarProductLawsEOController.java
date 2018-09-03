package com.adc.da.lawss.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.lawss.entity.SarProductLawsEO;
import com.adc.da.lawss.page.SarProductLawsEOPage;
import com.adc.da.lawss.service.SarProductLawsEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

@RestController
@RequestMapping("/${restPath}/lawss/sarProductLaws")
@Api(description = "|SarProductLawsEO|")
public class SarProductLawsEOController extends BaseController<SarProductLawsEO>{

    private static final Logger logger = LoggerFactory.getLogger(SarProductLawsEOController.class);

    @Autowired
    private SarProductLawsEOService sarProductLawsEOService;

	@ApiOperation(value = "|SarProductLawsEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("lawss:sarProductLaws:page")
    public ResponseMessage<PageInfo<SarProductLawsEO>> page(SarProductLawsEOPage page) throws Exception {
        List<SarProductLawsEO> rows = sarProductLawsEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

	@ApiOperation(value = "|SarProductLawsEO|查询")
    @GetMapping("")
    @RequiresPermissions("lawss:sarProductLaws:list")
    public ResponseMessage<List<SarProductLawsEO>> list(SarProductLawsEOPage page) throws Exception {
        return Result.success(sarProductLawsEOService.queryByList(page));
	}

    @ApiOperation(value = "|SarProductLawsEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("lawss:sarProductLaws:save")
    public ResponseMessage<SarProductLawsEO> create(@RequestBody SarProductLawsEO sarProductLawsEO) throws Exception {
        sarProductLawsEOService.insertSelective(sarProductLawsEO);
        return Result.success(sarProductLawsEO);
    }

}
