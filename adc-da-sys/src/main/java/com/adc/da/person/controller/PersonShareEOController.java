package com.adc.da.person.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.person.entity.PersonShareEO;
import com.adc.da.person.page.PersonShareEOPage;
import com.adc.da.person.service.PersonShareEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

@RestController
@RequestMapping("/${restPath}/person/personShare")
@Api(description = "|PersonShareEO|")
public class PersonShareEOController extends BaseController<PersonShareEO>{

    private static final Logger logger = LoggerFactory.getLogger(PersonShareEOController.class);

    @Autowired
    private PersonShareEOService personShareEOService;

	@ApiOperation(value = "|PersonShareEO|分页查询")
    @GetMapping("/page")
    //@RequiresPermissions("person:personShare:page")
    public ResponseMessage<PageInfo<PersonShareEO>> page(PersonShareEOPage page) throws Exception {
        List<PersonShareEO> rows = personShareEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

	@ApiOperation(value = "|PersonShareEO|查询")
    @GetMapping("")
    //@RequiresPermissions("person:personShare:list")
    public ResponseMessage<List<PersonShareEO>> list(PersonShareEOPage page) throws Exception {
        return Result.success(personShareEOService.queryByList(page));
	}

    @ApiOperation(value = "|PersonShareEO|详情")
    @GetMapping("/{id}")
   //@RequiresPermissions("person:personShare:get")
    public ResponseMessage<PersonShareEO> find(@PathVariable String id) throws Exception {
        return Result.success(personShareEOService.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "|PersonShareEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    //@RequiresPermissions("person:personShare:save")
    public ResponseMessage<PersonShareEO> create(@RequestBody PersonShareEO personShareEO) throws Exception {
        personShareEOService.insertSelective(personShareEO);
        return Result.success(personShareEO);
    }

    @ApiOperation(value = "|PersonShareEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    //@RequiresPermissions("person:personShare:update")
    public ResponseMessage<PersonShareEO> update(@RequestBody PersonShareEO personShareEO) throws Exception {
        personShareEOService.updateByPrimaryKeySelective(personShareEO);
        return Result.success(personShareEO);
    }

    @ApiOperation(value = "|PersonShareEO|删除")
    @DeleteMapping("/{id}")
    //@RequiresPermissions("person:personShare:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        personShareEOService.deleteByPrimaryKey(id);
        logger.info("delete from TS_PERSON_SHARE where id = {}", id);
        return Result.success();
    }

}
