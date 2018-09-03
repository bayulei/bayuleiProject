package com.adc.da.person.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.person.entity.UserInfoEO;
import com.adc.da.person.page.UserInfoEOPage;
import com.adc.da.person.service.UserInfoEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

@RestController
@RequestMapping("/${restPath}/person/userInfo")
@Api(description = "|UserInfoEO|")
public class UserInfoEOController extends BaseController<UserInfoEO>{

    private static final Logger logger = LoggerFactory.getLogger(UserInfoEOController.class);

    @Autowired
    private UserInfoEOService userInfoEOService;

	@ApiOperation(value = "|UserInfoEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("person:userInfo:page")
    public ResponseMessage<PageInfo<UserInfoEO>> page(UserInfoEOPage page) throws Exception {
        List<UserInfoEO> rows = userInfoEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

	@ApiOperation(value = "|UserInfoEO|查询")
    @GetMapping("")
    @RequiresPermissions("person:userInfo:list")
    public ResponseMessage<List<UserInfoEO>> list(UserInfoEOPage page) throws Exception {
        return Result.success(userInfoEOService.queryByList(page));
	}

    @ApiOperation(value = "|UserInfoEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("person:userInfo:get")
    public ResponseMessage<UserInfoEO> find(@PathVariable String id) throws Exception {
        return Result.success(userInfoEOService.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "|UserInfoEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("person:userInfo:save")
    public ResponseMessage<UserInfoEO> create(@RequestBody UserInfoEO userInfoEO) throws Exception {
        userInfoEOService.insertSelective(userInfoEO);
        return Result.success(userInfoEO);
    }

    @ApiOperation(value = "|UserInfoEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("person:userInfo:update")
    public ResponseMessage<UserInfoEO> update(@RequestBody UserInfoEO userInfoEO) throws Exception {
        userInfoEOService.updateByPrimaryKeySelective(userInfoEO);
        return Result.success(userInfoEO);
    }

    @ApiOperation(value = "|UserInfoEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("person:userInfo:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        userInfoEOService.deleteByPrimaryKey(id);
        logger.info("delete from TS_USER_INFO where id = {}", id);
        return Result.success();
    }

}
