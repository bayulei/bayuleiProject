package com.adc.da.person.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;

import com.adc.da.sys.util.UUIDUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.adc.da.base.web.BaseController;
import com.adc.da.person.entity.PersonNoteEO;
import com.adc.da.person.page.PersonNoteEOPage;
import com.adc.da.person.service.PersonNoteEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

@RestController
@RequestMapping("/${restPath}/person/personNote")
@Api(description = "|PersonNoteEO|")
public class PersonNoteEOController extends BaseController<PersonNoteEO> {

    private static final Logger logger = LoggerFactory.getLogger(PersonNoteEOController.class);

    @Autowired
    private PersonNoteEOService personNoteEOService;


    @ApiOperation(value = "|PersonNoteEO|分页查询")
    @GetMapping("/page")
    //@RequiresPermissions("person:personNote:page")
    public ResponseMessage<PageInfo<PersonNoteEO>> page(PersonNoteEOPage page) throws Exception {
        List<PersonNoteEO> rows = personNoteEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

    @ApiOperation(value = "|PersonNoteEO|查询")
    @GetMapping("")
    //@RequiresPermissions("person:personNote:list")
    public ResponseMessage<List<PersonNoteEO>> list(PersonNoteEOPage page) throws Exception {
        return Result.success(personNoteEOService.queryByList(page));
    }

    @ApiOperation(value = "|PersonNoteEO|详情")
    @GetMapping("/{id}")
    //@RequiresPermissions("person:personNote:get")
    public ResponseMessage<PersonNoteEO> find(@PathVariable String id) throws Exception {
        return Result.success(personNoteEOService.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "|PersonNoteEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    //@RequiresPermissions("person:personNote:save")
    public ResponseMessage<PersonNoteEO> create(@RequestBody PersonNoteEO personNoteEO) throws Exception {
        personNoteEO.setId(UUIDUtils.randomUUID20());
        personNoteEOService.save(personNoteEO);
        return Result.success(personNoteEO);
    }

/*    @ApiOperation(value = "|PersonNoteEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("person:personNote:update")
    public ResponseMessage<PersonNoteEO> update(@RequestBody PersonNoteEO personNoteEO) throws Exception {
        personNoteEOService.updateByPrimaryKeySelective(personNoteEO);
        return Result.success(personNoteEO);
    }*/

    @ApiOperation(value = "|PersonNoteEO|删除")
    @DeleteMapping("/{id}")
    // @RequiresPermissions("person:personNote:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        personNoteEOService.deleteByPrimaryKey(id);
        logger.info("delete from TS_PERSON_NOTE where id = {}", id);
        return Result.success();
    }


    //通过传入的收藏表的id和笔记表中的内容对笔记表进行修改
    @ApiOperation(value = "|PersonNoteEO|修改1")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    //@RequiresPermissions("person:personNote:update")
    public ResponseMessage<Integer> updateByCollectId(@RequestBody PersonNoteEO personNoteEO) throws Exception {
        Integer integer = personNoteEOService.updateByCollectId(personNoteEO);
        if (integer == 0) {
            return Result.error("修改失败");
        }
        return Result.success("true", "修改成功");
    }

    //通过传入的收藏表的id对笔记表进行查询
    @ApiOperation(value = "根据收藏id查询")
    @GetMapping("/collectId")
    public ResponseMessage<PersonNoteEO> queryByCollectId(String collectId) throws Exception {
        PersonNoteEO personNoteEO = personNoteEOService.queryByCollectId(collectId);
        return Result.success(personNoteEO);
    }

    @ApiOperation(value = "根据收藏id删除")
    @DeleteMapping("/collectId")
    public ResponseMessage deleteByCollectId(String collectId) throws Exception {
        personNoteEOService.deleteByCollectId(collectId);
        return Result.success("true", "删除成功");

    }
}
