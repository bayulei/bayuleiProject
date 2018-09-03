package com.adc.da.person.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.person.entity.PersonMsgEO;
import com.adc.da.person.page.PersonMsgEOPage;
import com.adc.da.person.service.PersonMsgEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

@RestController
@RequestMapping("/${restPath}/person/personMsg")
@Api(description = "|PersonMsgEO|")
public class PersonMsgEOController extends BaseController<PersonMsgEO>{

    private static final Logger logger = LoggerFactory.getLogger(PersonMsgEOController.class);

    @Autowired
    private PersonMsgEOService personMsgEOService;

	@ApiOperation(value = "|PersonMsgEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("person:personMsg:page")
    public ResponseMessage<PageInfo<PersonMsgEO>> page(PersonMsgEOPage page) throws Exception {
        List<PersonMsgEO> rows = personMsgEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

	@ApiOperation(value = "|PersonMsgEO|查询")
    @GetMapping("")
    @RequiresPermissions("person:personMsg:list")
    public ResponseMessage<List<PersonMsgEO>> list(PersonMsgEOPage page) throws Exception {
        return Result.success(personMsgEOService.queryByList(page));
	}

    @ApiOperation(value = "|PersonMsgEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("person:personMsg:get")
    public ResponseMessage<PersonMsgEO> find(@PathVariable String id) throws Exception {
        return Result.success(personMsgEOService.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "|PersonMsgEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("person:personMsg:save")
    public ResponseMessage<PersonMsgEO> create(@RequestBody PersonMsgEO personMsgEO) throws Exception {
        personMsgEOService.insertSelective(personMsgEO);
        return Result.success(personMsgEO);
    }

    @ApiOperation(value = "|PersonMsgEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("person:personMsg:update")
    public ResponseMessage<PersonMsgEO> update(@RequestBody PersonMsgEO personMsgEO) throws Exception {
        personMsgEOService.updateByPrimaryKeySelective(personMsgEO);
        return Result.success(personMsgEO);
    }

    @ApiOperation(value = "|PersonMsgEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("person:personMsg:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        personMsgEOService.deleteByPrimaryKey(id);
        logger.info("delete from TS_PERSON_MSG where id = {}", id);
        return Result.success();
    }

}
