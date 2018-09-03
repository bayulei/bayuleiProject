package com.adc.da.person.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.person.entity.UserConfigEO;
import com.adc.da.person.page.UserConfigEOPage;
import com.adc.da.person.service.UserConfigEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

@RestController
@RequestMapping("/${restPath}/person/userConfig")
@Api(description = "|UserConfigEO|")
public class UserConfigEOController extends BaseController<UserConfigEO>{

    private static final Logger logger = LoggerFactory.getLogger(UserConfigEOController.class);

    @Autowired
    private UserConfigEOService userConfigEOService;

	@ApiOperation(value = "|UserConfigEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("person:userConfig:page")
    public ResponseMessage<PageInfo<UserConfigEO>> page(UserConfigEOPage page) throws Exception {
        List<UserConfigEO> rows = userConfigEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

	@ApiOperation(value = "|UserConfigEO|查询")
    @GetMapping("")
    @RequiresPermissions("person:userConfig:list")
    public ResponseMessage<List<UserConfigEO>> list(UserConfigEOPage page) throws Exception {
        return Result.success(userConfigEOService.queryByList(page));
	}

    @ApiOperation(value = "|UserConfigEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("person:userConfig:get")
    public ResponseMessage<UserConfigEO> find(@PathVariable String id) throws Exception {
        return Result.success(userConfigEOService.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "|UserConfigEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("person:userConfig:save")
    public ResponseMessage<UserConfigEO> create(@RequestBody UserConfigEO userConfigEO) throws Exception {
        userConfigEOService.insertSelective(userConfigEO);
        return Result.success(userConfigEO);
    }

    @ApiOperation(value = "|UserConfigEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("person:userConfig:update")
    public ResponseMessage<UserConfigEO> update(@RequestBody UserConfigEO userConfigEO) throws Exception {
        userConfigEOService.updateByPrimaryKeySelective(userConfigEO);
        return Result.success(userConfigEO);
    }

    @ApiOperation(value = "|UserConfigEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("person:userConfig:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        userConfigEOService.deleteByPrimaryKey(id);
        logger.info("delete from TS_USER_CONFIG where id = {}", id);
        return Result.success();
    }

}
