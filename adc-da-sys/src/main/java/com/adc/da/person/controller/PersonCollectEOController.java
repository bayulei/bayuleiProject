package com.adc.da.person.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.person.entity.PersonCollectEO;
import com.adc.da.person.page.PersonCollectEOPage;
import com.adc.da.person.service.PersonCollectEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

@RestController
@RequestMapping("/${restPath}/person/personCollect")
@Api(description = "|PersonCollectEO|")
public class PersonCollectEOController extends BaseController<PersonCollectEO>{

    private static final Logger logger = LoggerFactory.getLogger(PersonCollectEOController.class);

    @Autowired
    private PersonCollectEOService personCollectEOService;

	@ApiOperation(value = "|PersonCollectEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("person:personCollect:page")
    public ResponseMessage<PageInfo<PersonCollectEO>> page(PersonCollectEOPage page) throws Exception {
        List<PersonCollectEO> rows = personCollectEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

	@ApiOperation(value = "|PersonCollectEO|查询")
    @GetMapping("")
    @RequiresPermissions("person:personCollect:list")
    public ResponseMessage<List<PersonCollectEO>> list(PersonCollectEOPage page) throws Exception {
        return Result.success(personCollectEOService.queryByList(page));
	}

    @ApiOperation(value = "|PersonCollectEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("person:personCollect:get")
    public ResponseMessage<PersonCollectEO> find(@PathVariable String id) throws Exception {
        return Result.success(personCollectEOService.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "|PersonCollectEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("person:personCollect:save")
    public ResponseMessage<PersonCollectEO> create(@RequestBody PersonCollectEO personCollectEO) throws Exception {
        personCollectEOService.insertSelective(personCollectEO);
        return Result.success(personCollectEO);
    }

    @ApiOperation(value = "|PersonCollectEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("person:personCollect:update")
    public ResponseMessage<PersonCollectEO> update(@RequestBody PersonCollectEO personCollectEO) throws Exception {
        personCollectEOService.updateByPrimaryKeySelective(personCollectEO);
        return Result.success(personCollectEO);
    }

    @ApiOperation(value = "|PersonCollectEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("person:personCollect:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        personCollectEOService.deleteByPrimaryKey(id);
        logger.info("delete from TS_PERSON_COLLECT where id = {}", id);
        return Result.success();
    }

}
