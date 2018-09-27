package com.adc.da.person.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.Date;
import java.util.List;
import java.util.Map;
import com.adc.da.sys.entity.RoleEO;
import com.adc.da.sys.entity.UserRoleEO;
import com.adc.da.sys.util.UUIDUtils;
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

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/${restPath}/person/personConf")
@Api(description = "|PersonConfEO|")
public class PersonConfEOController extends BaseController<PersonConfEO> {

    private static final Logger logger = LoggerFactory.getLogger(PersonConfEOController.class);

    @Autowired
    private PersonConfEOService personConfEOService;



    @ApiOperation(value = "|PersonConfEO|分页查询")
    @GetMapping("/page")
    //@RequiresPermissions("person:personConf:page")
    public ResponseMessage<PageInfo<PersonConfEO>> page(PersonConfEOPage page) throws Exception {
        List<PersonConfEO> rows = personConfEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

    @ApiOperation(value = "|PersonConfEO|查询")
    @GetMapping("")
    //@RequiresPermissions("person:personConf:list")
    public ResponseMessage<List<PersonConfEO>> list(PersonConfEOPage page) throws Exception {
        return Result.success(personConfEOService.queryByList(page));
    }

    @ApiOperation(value = "|PersonConfEO|详情")
    @GetMapping("/{id}")
    //@RequiresPermissions("person:personConf:get")
    public ResponseMessage<PersonConfEO> find(@PathVariable String id) throws Exception {
        return Result.success(personConfEOService.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "|PersonConfEO|新增")
    //@PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    // @RequiresPermissions("person:personConf:save")
    public ResponseMessage<PersonConfEO> create(@RequestBody PersonConfEO personConfEO) throws Exception {
        personConfEO.setId(UUIDUtils.randomUUID20());
        personConfEOService.save(personConfEO);
        return Result.success(personConfEO);
    }

    /*
     * @Author liuyinnan
     * @Description //按一个对象更新个人板块
     * @Date 8:38 2018/9/25
     * @Param [personConfEO]
     * @return com.adc.da.util.http.ResponseMessage<com.adc.da.person.entity.PersonConfEO>
     **/
    @ApiOperation(value = "|PersonConfEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    //@RequiresPermissions("person:personConf:update")
    public ResponseMessage<PersonConfEO> update(@RequestBody PersonConfEO personConfEO) throws Exception {
        personConfEOService.updateByPrimaryKeySelective(personConfEO);
        personConfEO.setCreationTime(new Date());
        personConfEO.setModifyTime(new Date());
        int i=personConfEOService.updateByPrimaryKeySelective(personConfEO);
        if(i==0){
            return Result.error("修改失败");
        }
        return Result.success(personConfEO);
    }


//    @ApiOperation(value = "根据前台传来的对象保存")
//    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
//    public ResponseMessage<PersonConfEO> insertByList(PersonConfEO personConfEO)throws Exception{
//        personConfEOService.updateByPrimaryKeySelective(personConfEO);
//        personConfEOService.insertByList(personConfEO);
//        return Result.success(personConfEO);
//    }


    @ApiOperation(value = "根据前台传来的对象保存")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    public ResponseMessage insertByList(@RequestBody List<PersonConfEO> personConfEOList) throws Exception {
        if (personConfEOList != null && personConfEOList.size() > 0) {

            // String[] personConfEO=personConfEO.split(",");
            for (int i = 0; i < personConfEOList.size(); i++) {
                PersonConfEO personConfEO = personConfEOList.get(i);
                personConfEO.setUserId("1");
                personConfEO.setDisplaySeq(i+1);
                personConfEO.setCreationTime(new Date());
                personConfEO.setModifyTime(new Date());
                System.out.println(personConfEOList.get(i));
                personConfEOService.updateByPrimaryKeySelective(personConfEOList.get(i));
                personConfEOService.insert1(personConfEOList.get(i));
            }
        }else {
            return Result.error("操作失败");
        }
        return Result.success("","操作成功",personConfEOList);
    }


    @ApiOperation(value = "|PersonConfEO|删除")
    @DeleteMapping("/{id}")
    //@RequiresPermissions("person:personConf:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        personConfEOService.deleteByPrimaryKey(id);
        logger.info("delete from TS_PERSON_CONF where id = {}", id);
        return Result.success();
    }


    /*
     * @Author liuyinnan
     * @Description //排序查询
     * @Date 16:13 2018/9/21
     * @Param []
     * @return com.adc.da.util.http.ResponseMessage<java.util.List<com.adc.da.person.entity.PersonConfEO>>
     **/
//    @ApiOperation(value = "排序查询")
//    @GetMapping("/selectByDisplay")
//    //@RequiresPermissions("person:personConf:list")
//    public ResponseMessage<List<PersonConfEO>> updateById() throws Exception {
//        List<PersonConfEO> personConfEO = personConfEOService.updateById();
//        return Result.success(personConfEO);
//    }


    /*
     * @Author liuyinnan
     * @Description //批量删除
     * @Date 16:12 2018/9/21
     * @Param [ids]
     * @return com.adc.da.util.http.ResponseMessage<java.util.List<com.adc.da.person.entity.PersonConfEO>>
     **/
//    @ApiOperation(value = "批量删除")
//    @DeleteMapping("/{ids}")
//    public ResponseMessage<List<PersonConfEO>> deleteByIdList(@PathVariable String ids) throws Exception {
//        String[] idList = ids.split(",");
//        if (idList != null && idList.length > 0) {
//            for (String id : idList) {
//                List<PersonConfEO> list = personConfEOService.deleteByIdList(id);
//            }
//        }
//        return Result.success();
//    }
}