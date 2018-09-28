package com.adc.da.person.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.adc.da.sys.util.UUIDUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.person.entity.PersonSearchEO;
import com.adc.da.person.page.PersonSearchEOPage;
import com.adc.da.person.service.PersonSearchEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

@RestController
@RequestMapping("/${restPath}/person/personSearch")
@Api(description = "|PersonSearchEO|")
public class PersonSearchEOController extends BaseController<PersonSearchEO>{

    private static final Logger logger = LoggerFactory.getLogger(PersonSearchEOController.class);

    @Autowired
    private PersonSearchEOService personSearchEOService;

	@ApiOperation(value = "|PersonSearchEO|分页查询")
    @GetMapping("/page")
    //@RequiresPermissions("person:personSearch:page")
    public ResponseMessage<PageInfo<PersonSearchEO>> page(PersonSearchEOPage page) throws Exception {
        List<PersonSearchEO> rows = personSearchEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

	@ApiOperation(value = "|PersonSearchEO|查询")
    @GetMapping("")
    //@RequiresPermissions("person:personSearch:list")
    public ResponseMessage<List<PersonSearchEO>> list(PersonSearchEOPage page) throws Exception {
        return Result.success(personSearchEOService.queryByList(page));
	}

    @ApiOperation(value = "|PersonSearchEO|详情")
    @GetMapping("/{id}")
    //@RequiresPermissions("person:personSearch:get")
    public ResponseMessage<PersonSearchEO> find(@PathVariable String id) throws Exception {
        return Result.success(personSearchEOService.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "|PersonSearchEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    //@RequiresPermissions("person:personSearch:save")
    public ResponseMessage<PersonSearchEO> create(@RequestBody PersonSearchEO personSearchEO) throws Exception {
	    personSearchEO.setId(UUIDUtils.randomUUID20());
	    personSearchEO.setCreationTime(new Date());
	    personSearchEO.setModifyTime(new Date());
        personSearchEOService.insertSelective(personSearchEO);
        return Result.success(personSearchEO);
    }

    @ApiOperation(value = "|PersonSearchEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    //@RequiresPermissions("person:personSearch:update")
    public ResponseMessage<PersonSearchEO> update(@RequestBody PersonSearchEO personSearchEO) throws Exception {
        personSearchEOService.updateByPrimaryKeySelective(personSearchEO);
        return Result.success(personSearchEO);
    }

    @ApiOperation(value = "|PersonSearchEO|删除")
    @DeleteMapping("/{id}")
    //@RequiresPermissions("person:personSearch:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        personSearchEOService.deleteByPrimaryKey(id);
        logger.info("delete from TS_PERSON_SEARCH where id = {}", id);
        return Result.success();
    }

}
