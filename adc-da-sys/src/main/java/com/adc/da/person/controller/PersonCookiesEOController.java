package com.adc.da.person.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.person.entity.PersonCookiesEO;
import com.adc.da.person.page.PersonCookiesEOPage;
import com.adc.da.person.service.PersonCookiesEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

@RestController
@RequestMapping("/${restPath}/person/personCookies")
@Api(description = "|PersonCookiesEO|")
public class PersonCookiesEOController extends BaseController<PersonCookiesEO>{

    private static final Logger logger = LoggerFactory.getLogger(PersonCookiesEOController.class);

    @Autowired
    private PersonCookiesEOService personCookiesEOService;

	@ApiOperation(value = "|PersonCookiesEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("person:personCookies:page")
    public ResponseMessage<PageInfo<PersonCookiesEO>> page(PersonCookiesEOPage page) throws Exception {
        List<PersonCookiesEO> rows = personCookiesEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

	@ApiOperation(value = "|PersonCookiesEO|查询")
    @GetMapping("")
    @RequiresPermissions("person:personCookies:list")
    public ResponseMessage<List<PersonCookiesEO>> list(PersonCookiesEOPage page) throws Exception {
        return Result.success(personCookiesEOService.queryByList(page));
	}

    @ApiOperation(value = "|PersonCookiesEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("person:personCookies:get")
    public ResponseMessage<PersonCookiesEO> find(@PathVariable String id) throws Exception {
        return Result.success(personCookiesEOService.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "|PersonCookiesEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("person:personCookies:save")
    public ResponseMessage<PersonCookiesEO> create(@RequestBody PersonCookiesEO personCookiesEO) throws Exception {
        personCookiesEOService.insertSelective(personCookiesEO);
        return Result.success(personCookiesEO);
    }

    @ApiOperation(value = "|PersonCookiesEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("person:personCookies:update")
    public ResponseMessage<PersonCookiesEO> update(@RequestBody PersonCookiesEO personCookiesEO) throws Exception {
        personCookiesEOService.updateByPrimaryKeySelective(personCookiesEO);
        return Result.success(personCookiesEO);
    }

    @ApiOperation(value = "|PersonCookiesEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("person:personCookies:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        personCookiesEOService.deleteByPrimaryKey(id);
        logger.info("delete from TS_PERSON_COOKIES where id = {}", id);
        return Result.success();
    }

}
