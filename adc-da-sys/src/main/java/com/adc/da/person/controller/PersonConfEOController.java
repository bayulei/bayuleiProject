package com.adc.da.person.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.person.entity.PersonConfEO;
import com.adc.da.person.page.PersonConfEOPage;
import com.adc.da.person.service.PersonConfEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

@RestController
@RequestMapping("/${restPath}/person/personConf")
@Api(description = "|PersonConfEO|")
public class PersonConfEOController extends BaseController<PersonConfEO>{

    private static final Logger logger = LoggerFactory.getLogger(PersonConfEOController.class);

    @Autowired
    private PersonConfEOService personConfEOService;

	@ApiOperation(value = "|PersonConfEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("person:personConf:page")
    public ResponseMessage<PageInfo<PersonConfEO>> page(PersonConfEOPage page) throws Exception {
        List<PersonConfEO> rows = personConfEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

	@ApiOperation(value = "|PersonConfEO|查询")
    @GetMapping("")
    @RequiresPermissions("person:personConf:list")
    public ResponseMessage<List<PersonConfEO>> list(PersonConfEOPage page) throws Exception {
        return Result.success(personConfEOService.queryByList(page));
	}

    @ApiOperation(value = "|PersonConfEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("person:personConf:get")
    public ResponseMessage<PersonConfEO> find(@PathVariable String id) throws Exception {
        return Result.success(personConfEOService.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "|PersonConfEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("person:personConf:save")
    public ResponseMessage<PersonConfEO> create(@RequestBody PersonConfEO personConfEO) throws Exception {
        personConfEOService.insertSelective(personConfEO);
        return Result.success(personConfEO);
    }

    @ApiOperation(value = "|PersonConfEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("person:personConf:update")
    public ResponseMessage<PersonConfEO> update(@RequestBody PersonConfEO personConfEO) throws Exception {
        personConfEOService.updateByPrimaryKeySelective(personConfEO);
        return Result.success(personConfEO);
    }

    @ApiOperation(value = "|PersonConfEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("person:personConf:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        personConfEOService.deleteByPrimaryKey(id);
        logger.info("delete from TS_PERSON_CONF where id = {}", id);
        return Result.success();
    }

}
