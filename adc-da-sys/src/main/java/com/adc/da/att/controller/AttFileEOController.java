package com.adc.da.att.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.adc.da.base.web.BaseController;
import com.adc.da.att.entity.AttFileEO;
import com.adc.da.att.page.AttFileEOPage;
import com.adc.da.att.service.AttFileEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.alibaba.fastjson.JSONObject;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.apache.shiro.authz.annotation.RequiresPermissions;

@RestController
@RequestMapping("/${restPath}/att/attFile")
@Api(description = "|AttFileEO|")
public class AttFileEOController extends BaseController<AttFileEO>{

    private static final Logger logger = LoggerFactory.getLogger(AttFileEOController.class);

    @Autowired
    private AttFileEOService attFileEOService;

	@ApiOperation(value = "|AttFileEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("att:attFile:page")
    public ResponseMessage<PageInfo<AttFileEO>> page(AttFileEOPage page) throws Exception {
        List<AttFileEO> rows = attFileEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

	@ApiOperation(value = "|AttFileEO|查询")
    @GetMapping("")
    @RequiresPermissions("att:attFile:list")
    public ResponseMessage<List<AttFileEO>> list(AttFileEOPage page) throws Exception {
        return Result.success(attFileEOService.queryByList(page));
	}

    @ApiOperation(value = "|AttFileEO|详情")
    @GetMapping("/{id}")
//    @RequiresPermissions("att:attFile:get")
    public ResponseMessage<AttFileEO> find(@PathVariable String id) throws Exception {
        return Result.success(attFileEOService.getFileInfo(id));
    }

    @ApiOperation(value = "|AttFileEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("att:attFile:save")
    public ResponseMessage<AttFileEO> create(@RequestBody AttFileEO attFileEO) throws Exception {
        attFileEOService.insertSelective(attFileEO);
        return Result.success(attFileEO);
    }

    @ApiOperation(value = "|AttFileEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("att:attFile:update")
    public ResponseMessage<AttFileEO> update(@RequestBody AttFileEO attFileEO) throws Exception {
        attFileEOService.updateByPrimaryKeySelective(attFileEO);
        return Result.success(attFileEO);
    }

    @ApiOperation(value = "|AttFileEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("att:attFile:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        attFileEOService.deleteByPrimaryKey(id);
        logger.info("delete from ATT_FILE where id = {}", id);
        return Result.success();
    }

    @ApiOperation(value="|File|上传文件")
    @PostMapping(value="/upload",consumes="multipart/*",headers="content-type=multipart/form-data" )
    public ResponseMessage uploadFile(@RequestParam("file") @ApiParam(value="上传文件",required=true) MultipartFile file) throws Exception{
    	
    	String AttId = attFileEOService.saveFileInfo(file);
    	JSONObject jsonObject=new JSONObject();
    	jsonObject.put("ATTId", AttId);
    	return Result.success(jsonObject.toJSONString());
    }
    
    
}
