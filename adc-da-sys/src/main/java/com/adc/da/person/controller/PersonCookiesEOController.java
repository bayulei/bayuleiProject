package com.adc.da.person.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;


import java.util.List;
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

@RestController
@RequestMapping("/${restPath}/person/personCookies")
@Api(description = "|PersonCookiesEO|")
public class PersonCookiesEOController extends BaseController<PersonCookiesEO> {

    private static final Logger logger = LoggerFactory.getLogger(PersonCookiesEOController.class);

    @Autowired
    private PersonCookiesEOService personCookiesEOService;

    @ApiOperation(value = "|PersonCookiesEO|分页查询")
    @GetMapping("/page")
    //@RequiresPermissions("person:personCookies:page")
    public ResponseMessage<PageInfo<PersonCookiesEO>> page(PersonCookiesEOPage page) throws Exception {
        List<PersonCookiesEO> rows = personCookiesEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

    @ApiOperation(value = "|PersonCookiesEO|查询")
    @GetMapping("")
    //@RequiresPermissions("person:personCookies:list")
    public ResponseMessage<List<PersonCookiesEO>> list(PersonCookiesEOPage page) throws Exception {
        return Result.success(personCookiesEOService.queryByList(page));
    }

    @ApiOperation(value = "|PersonCookiesEO|详情")
    @GetMapping("/{id}")
    //@RequiresPermissions("person:personCookies:get")
    public ResponseMessage<PersonCookiesEO> find(@PathVariable String id) throws Exception {
        return Result.success(personCookiesEOService.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "|PersonCookiesEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    //@RequiresPermissions("person:personCookies:save")
    public ResponseMessage<PersonCookiesEO> create(@RequestBody PersonCookiesEO personCookiesEO) throws Exception {
        personCookiesEOService.insertSelective(personCookiesEO);
        return Result.success(personCookiesEO);
    }


    /*
     * @Author liuyinnan
     * @Description //批量删除
     * @Date 9:51 2018/9/27
     * @Param [ids]
     * @return com.adc.da.util.http.ResponseMessage
     **/
//    @ApiOperation(value = "|PersonCookiesEO|修改")
//    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
//    //@RequiresPermissions("person:personCookies:update")
//    public ResponseMessage update(@RequestBody String ids) throws Exception {
//        String[] idList = ids.split(",");
//        if(idList!=null && idList.length>0){
//            for(String id:idList){
//                personCookiesEOService.update(id);
//            }
//        }
//        return Result.success();
//    }

    @ApiOperation(value = "|PersonCookiesEO|删除")
    @DeleteMapping("/{id}")
    //@RequiresPermissions("person:personCookies:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        personCookiesEOService.deleteByPrimaryKey(id);
        logger.info("delete from TS_PERSON_COOKIES where id = {}", id);
        return Result.success();
    }


    /*
     * @Author liuyinnan
     * @Description //单句删除
     * @Date 9:51 2018/9/27
     * @Param [personCookiesEO]
     * @return com.adc.da.util.http.ResponseMessage<com.adc.da.person.entity.PersonCookiesEO>
     **/
//    @ApiOperation(value = "根据用户id删除浏览记录")
//    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
//    public ResponseMessage<PersonCookiesEO> updateByUserId(@RequestBody PersonCookiesEO personCookiesEO) throws Exception {
//        PersonCookiesEO personCookiesEO1=personCookiesEOService.updateByUserId(personCookiesEO);
//        if(personCookiesEO1 != null){
//            return Result.error("删除失败");
//        }
//        return Result.success("true","删除成功",personCookiesEO1);
//    }



//    @ApiOperation(value = "根据浏览类型查询")
//    @GetMapping("/cookieType")
//    @ResponseBody
//    public ResponseMessage<List<PersonCookiesEO>> queryByCookieType(String cookieType) throws Exception{
//        List<PersonCookiesEO> personCookiesEO=personCookiesEOService.queryByCookieType(cookieType);
//	    if(personCookiesEO==null){
//	        return Result.error("查询失败");
//        }
//        return Result.success(personCookiesEO);
//    }


//    @ApiOperation(value = "根据用户id查询")
//    @GetMapping("/userId")
//    public ResponseMessage<List<PersonCookiesEO>> queryByUserId(String ids) throws Exception {
//        String[] idList = ids.split(",");
//        if (idList != null && idList.length > 0) {
//            for (String id : idList) {
//                List<PersonConfEO> list = personConfEOService.deleteByIdList(id);
//            }
//        }
//        return Result.success();
//    }
}

