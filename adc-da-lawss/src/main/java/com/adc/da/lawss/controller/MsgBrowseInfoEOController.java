package com.adc.da.lawss.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;
import java.util.Map;

import com.adc.da.lawss.page.MsgDynamicInfoEOPage;
import org.eclipse.jetty.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.lawss.entity.MsgBrowseInfoEO;
import com.adc.da.lawss.page.MsgBrowseInfoEOPage;
import com.adc.da.lawss.service.MsgBrowseInfoEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

@RestController
@RequestMapping("/${restPath}/lawss/msgBrowseInfo")
@Api(description = "|MsgBrowseInfoEO|")
public class MsgBrowseInfoEOController extends BaseController<MsgBrowseInfoEO>{

    private static final Logger logger = LoggerFactory.getLogger(MsgBrowseInfoEOController.class);

    @Autowired
    private MsgBrowseInfoEOService msgBrowseInfoEOService;

	@ApiOperation(value = "|MsgBrowseInfoEO|分页查询")
    @GetMapping("/page")
//    @RequiresPermissions("lawss:msgBrowseInfo:page")
//    public ResponseMessage<PageInfo<MsgBrowseInfoEO>> page(MsgBrowseInfoEOPage page) throws Exception {
        public ResponseMessage<PageInfo<MsgBrowseInfoEO>> page(Integer pageNo,Integer pageSize) throws Exception {
        MsgBrowseInfoEOPage page = new MsgBrowseInfoEOPage();
        if (pageNo != null) {
            page.setPage(pageNo);
        }
        if (pageSize != null) {
            page.setPageSize(pageSize);
        }
        List<MsgBrowseInfoEO> rows = msgBrowseInfoEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }
//
	@ApiOperation(value = "|MsgBrowseInfoEO|查询")
    @GetMapping("")
//    @RequiresPermissions("lawss:msgBrowseInfo:list")
    public ResponseMessage<List<MsgBrowseInfoEO>> list(MsgBrowseInfoEOPage page) throws Exception {
        return Result.success(msgBrowseInfoEOService.queryByList(page));
	}
//
    @ApiOperation(value = "|MsgBrowseInfoEO|详情")
    @GetMapping("/{id}")
//    @RequiresPermissions("lawss:msgBrowseInfo:get")
    public ResponseMessage<MsgBrowseInfoEO> find(@PathVariable String id) throws Exception {
        return Result.success(msgBrowseInfoEOService.selectByPrimaryKey(id));
    }
//李文轩：前台需要传入消息id/浏览人id/浏览时间（待做判断）
    @ApiOperation(value = "|MsgBrowseInfoEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
//    @RequiresPermissions("lawss:msgBrowseInfo:save")
    public ResponseMessage<MsgBrowseInfoEO> create(@RequestBody MsgBrowseInfoEO msgBrowseInfoEO) throws Exception {
	    if(StringUtil.isBlank(msgBrowseInfoEO.getMsgId())){
	        return Result.error("1000","浏览的消息id不能为空");
        }
        if(StringUtil.isBlank(msgBrowseInfoEO.getBrowseUser())){
	        return Result.error("1001","浏览消息的用户不能为空");
        }
        msgBrowseInfoEOService.insertSelective(msgBrowseInfoEO);
        return Result.success(msgBrowseInfoEO);
    }

//    修改必须带id
    @ApiOperation(value = "|MsgBrowseInfoEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
//    @RequiresPermissions("lawss:msgBrowseInfo:update")
    public ResponseMessage<MsgBrowseInfoEO> update(@RequestBody MsgBrowseInfoEO msgBrowseInfoEO) throws Exception {
        msgBrowseInfoEOService.updateByPrimaryKeySelective(msgBrowseInfoEO);
        return Result.success(msgBrowseInfoEO);
    }
//
    @ApiOperation(value = "|MsgBrowseInfoEO|删除")
    @DeleteMapping("/{id}")
//    @RequiresPermissions("lawss:msgBrowseInfo:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        msgBrowseInfoEOService.deleteByPrimaryKey(id);
        logger.info("delete from MSG_BROWSE_INFO where id = {}", id);
        return Result.success();
    }

}
