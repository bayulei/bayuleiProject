package com.adc.da.person.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;
import java.util.Map;


import com.adc.da.sys.entity.UserEO;
import com.adc.da.sys.util.UUIDUtils;
import com.adc.da.util.utils.RequestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.person.entity.UserInfoEO;
import com.adc.da.person.page.UserInfoEOPage;
import com.adc.da.person.service.UserInfoEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

@RestController
@RequestMapping("/${restPath}/person/userInfo")
@Api(description = "|UserInfoEO|")
public class UserInfoEOController extends BaseController<UserInfoEO>{

    private static final Logger logger = LoggerFactory.getLogger(UserInfoEOController.class);

    @Autowired
    private UserInfoEOService userInfoEOService;

    @ApiOperation(value = "|UserInfoEO|分页查询")
    @GetMapping("/page")
    //@RequiresPermissions("person:userInfo:page")
    public ResponseMessage<PageInfo<UserInfoEO>> page(UserInfoEOPage page) throws Exception {
        List<UserInfoEO> rows = userInfoEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }


    @ApiOperation(value = "|UserInfoEO|查询")
    @GetMapping("")
    @RequiresPermissions("person:userInfo:list")
    public ResponseMessage<List<UserInfoEO>> list(UserInfoEOPage page) throws Exception {
        return Result.success(userInfoEOService.queryByList(page));
    }

    /**
     * 刘寅楠
     * @param userId
     * @return com.adc.da.person.entity.UserInfoEO
     * @throws Exception
     */
    @ApiOperation(value = "查找用户信息接口")
    @PostMapping("/getByUserInfoCode")
    public ResponseMessage<UserEO> getByUserInfoCode(String userId) throws Exception {
        UserEO userEO=userInfoEOService.getUserEOAndInfoEOByUserCode(userId);
        return Result.success(userEO);
    }


    /**
     * 刘寅楠
     * @param id
     * @return com.adc.da.person.entity.UserInfoEO
     */
    @ApiOperation(value = "查找用户")
    @GetMapping("/userInfo")
    public ResponseMessage<UserInfoEO> find(String  id){
        UserInfoEO userInfoEOByUserInfoId = userInfoEOService.getUserInfoEOByUserInfoId(id);
        if(userInfoEOByUserInfoId==null){
            return Result.error("沒有查找到用戶");
        }
        return Result.success(userInfoEOByUserInfoId);
    }

/*    @ApiOperation(value = "|UserInfoEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("person:userInfo:get")
    public ResponseMessage<UserInfoEO> find(@PathVariable String id) throws Exception {
        return Result.success(userInfoEOService.selectByPrimaryKey(id));
    }*/


    /**
     * 刘寅楠
     * @param userInfoEO
     * @param UserId
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "|UserInfoEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    // @RequiresPermissions("person:userInfo:save")
    public ResponseMessage<UserInfoEO> create(@RequestBody UserInfoEO userInfoEO,String UserId) throws Exception {
        //判断用户必填信息是否为空
        if(StringUtils.isBlank(userInfoEO.getOfficePhone())){
            return Result.error("r0018","电话号码不能为空");
        }
        if(StringUtils.isBlank(userInfoEO.getAddress())){
            return Result.error("r0019","个人邮箱地址不能为空");
        }
        if(StringUtils.isBlank(userInfoEO.getMobilePhone())){
            return Result.error("r0020","手机号码不能为空");
        }
        if(StringUtils.isBlank(userInfoEO.getFaxAddress())){
            return Result.error("r0021","传真地址不能为空");
        }
        userInfoEO.setId(UUIDUtils.randomUUID20());
        userInfoEO.setUserId(UserId);
        userInfoEOService.save(userInfoEO);
        return Result.success(userInfoEO);
    }



    /*
     * @Author liuyinnan
     * @Description //修改用户详细信息
     * @Date 19:33 2018/9/20
     * @Param [userInfoEO, restPath]
     * @return com.adc.da.util.http.ResponseMessage
     **/
    @ApiOperation(value = "|UserInfoEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    //@RequiresPermissions("person:userInfo:update")
    public ResponseMessage update(@RequestBody UserInfoEO userInfoEO, @PathVariable("restPath") String restPath) throws Exception {
        if(StringUtils.isBlank(userInfoEO.getOfficePhone())){
            return Result.error("r0018","电话号码不能为空");
        }
        if(StringUtils.isBlank(userInfoEO.getAddress())){
            return Result.error("r0019","个人邮箱地址不能为空");
        }
        if(StringUtils.isBlank(userInfoEO.getMobilePhone())){
            return Result.error("r0020","手机号码不能为空");
        }
        if(StringUtils.isBlank(userInfoEO.getFaxAddress())){
            return Result.error("r0021","传真地址不能为空");
        }

        return Result.success(userInfoEOService.updateById(userInfoEO));
    }



    @ApiOperation(value = "|UserInfoEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("person:userInfo:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        userInfoEOService.deleteByPrimaryKey(id);
        logger.info("delete from TS_USER_INFO where id = {}", id);
        return Result.success();
    }

}
