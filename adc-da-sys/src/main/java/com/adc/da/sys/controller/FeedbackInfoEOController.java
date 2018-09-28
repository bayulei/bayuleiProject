package com.adc.da.sys.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.adc.da.sys.util.LoginUserUtil;
import com.adc.da.sys.util.UUIDUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.sys.entity.FeedbackInfoEO;
import com.adc.da.sys.page.FeedbackInfoEOPage;
import com.adc.da.sys.service.FeedbackInfoEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

@RestController
@RequestMapping("/${restPath}/sys/feedbackInfo")
@Api(description = "|FeedbackInfoEO|")
public class FeedbackInfoEOController extends BaseController<FeedbackInfoEO> {

    private static final Logger logger = LoggerFactory.getLogger(FeedbackInfoEOController.class);

    @Autowired
    private FeedbackInfoEOService feedbackInfoEOService;

    @ApiOperation(value = "|FeedbackInfoEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("sys:feedbackInfo:page")
    public ResponseMessage<PageInfo<FeedbackInfoEO>> page(FeedbackInfoEOPage page) throws Exception {
        List<FeedbackInfoEO> rows = feedbackInfoEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

    @ApiOperation(value = "|FeedbackInfoEO|查询")
    @GetMapping("")
    @RequiresPermissions("sys:feedbackInfo:list")
    public ResponseMessage<List<FeedbackInfoEO>> list(FeedbackInfoEOPage page) throws Exception {
        return Result.success(feedbackInfoEOService.queryByList(page));
    }

    @ApiOperation(value = "|FeedbackInfoEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("sys:feedbackInfo:get")
    public ResponseMessage<FeedbackInfoEO> find(@PathVariable String id) throws Exception {
        return Result.success(feedbackInfoEOService.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "|FeedbackInfoEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("sys:feedbackInfo:save")
    public ResponseMessage<FeedbackInfoEO> create(@RequestBody FeedbackInfoEO feedbackInfoEO) throws Exception {
        feedbackInfoEO.setId(UUIDUtils.randomUUID20());
        feedbackInfoEO.setCreationTime(new Date());
        feedbackInfoEO.setModifyTime(new Date());
        feedbackInfoEOService.insertSelective(feedbackInfoEO);
        return Result.success(feedbackInfoEO);
    }

    @ApiOperation(value = "|FeedbackInfoEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("sys:feedbackInfo:update")
    public ResponseMessage<FeedbackInfoEO> update(@RequestBody FeedbackInfoEO feedbackInfoEO) throws Exception {
        feedbackInfoEOService.updateByPrimaryKeySelective(feedbackInfoEO);
        return Result.success(feedbackInfoEO);
    }

    @ApiOperation(value = "|FeedbackInfoEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("sys:feedbackInfo:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        feedbackInfoEOService.deleteByPrimaryKey(id);
        logger.info("delete from TS_FEEDBACK_INFO where id = {}", id);
        return Result.success();
    }


    @ApiOperation(value = "保存功能")
    @PostMapping("/save")
   // @RequiresPermissions("sys:feedbackInfo:save")
    public ResponseMessage<FeedbackInfoEO> insert1(String feedBackInfo) throws Exception {
        String userId = LoginUserUtil.getUserId();
        FeedbackInfoEO feedbackInfoEO = new FeedbackInfoEO();
        feedbackInfoEO.setUserId(userId);
        feedbackInfoEO.setId(UUIDUtils.randomUUID20());
        feedbackInfoEO.setFeedbackInfo(feedBackInfo);
        feedbackInfoEO.setValidFlag(0);
        feedbackInfoEO.setModifyTime(new Date());
        feedbackInfoEO.setCreationTime(new Date());
        int i = feedbackInfoEOService.insertSelective(feedbackInfoEO);
        if(i<0){
            return Result.error("保存失败");
        }
        return Result.success("true", "保存成功");
    }
}
