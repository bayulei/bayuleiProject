package com.adc.da.sys.controller;

import com.adc.da.base.page.Pager;
import com.adc.da.base.web.BaseController;
import com.adc.da.sys.common.LayUiResult;
import com.adc.da.sys.constant.ValidFlagEnum;
import com.adc.da.sys.entity.OrgEO;
import com.adc.da.sys.entity.RoleEO;
import com.adc.da.sys.entity.UserEO;
import com.adc.da.sys.page.UserEOPage;
import com.adc.da.sys.service.OrgEOService;
import com.adc.da.sys.service.UserEOService;
import com.adc.da.sys.util.LoginUserUtil;
import com.adc.da.sys.vo.UserVO;
import com.adc.da.util.http.PageInfo;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.utils.BeanMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping("/${restPath}/sys/user")
@Api(description = "用户管理")
public class UserEOController extends BaseController<UserEO> {

    private static final Logger logger = LoggerFactory.getLogger(UserEOController.class);

    @Autowired
    private UserEOService userEOService;
    @Autowired
    private OrgEOService orgEOService;
    @Autowired
    BeanMapper beanMapper;

    @ApiOperation(value = "|UserEO|详情")
    @GetMapping("/{id}")
//	@RequiresPermissions("sys:user:get")
    public ResponseMessage<UserVO> getById(@NotNull @PathVariable("id") String id) throws Exception {
        UserVO userVO = beanMapper.map(userEOService.getUserWithRoles(id), UserVO.class);
        return Result.success(userVO);
    }

    @ApiOperation(value = "|UserEO|分页查询")
    @GetMapping("")
//	@RequiresPermissions("sys:user:list")
    public ResponseMessage<PageInfo<UserEO>> UserInfoPage(Integer pageNo, Integer pageSize, String userType, String userName, String roleId, String disableFlag) throws Exception {
        UserEOPage page = new UserEOPage();
        if (pageNo != null) {
            page.setPage(pageNo);
        }
        if (pageSize != null) {
            page.setPageSize(pageSize);
        }
        if (StringUtils.isNotEmpty(userName)) {
            page.setUname("%" + userName + "%");
            page.setUname("LIKE");
        }
        if (StringUtils.isNotEmpty(userType)) {
            page.setUserType(userType);
        }
        if (StringUtils.isNotEmpty(disableFlag)) {
            page.setDisableFlag(disableFlag);
        }
        if (StringUtils.isNotEmpty(roleId)) {
            page.setRoleId(roleId);
        }
        page.setValidFlag(ValidFlagEnum.VALID_TRUE.getValue() + "");
        page.setPager(new Pager());

        List<UserEO> userEOs = userEOService.queryUserInfoByPage(page);
        return Result.success(getPageInfo(page.getPager(), userEOs));
    }


    @ApiOperation(value = "|UserEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
//	@RequiresPermissions("sys:user:save")
    public ResponseMessage<UserVO> create(@RequestBody UserVO userVO) throws Exception {
        if (StringUtils.isBlank(userVO.getAccount())) {
            return Result.error("登录名不能为空");
        } else if (userEOService.getUserByLoginName(userVO.getAccount()) != null) {
            return Result.error("账号已存在");
        }
        // 前台如果base64传输密文，则需要解码
        // userVO.setPassword(new String(Encodes.decodeBase64(userVO.getPassword())));
//		userVO.setOperUser(LoginUserUtil.getUserId());
        userVO.setOperUser("111111");
        UserEO userEO = userEOService.save(beanMapper.map(userVO, UserEO.class));
        if(userEO==null){
            return Result.error("操作失败");
        }
        return Result.success("true","操作成功",beanMapper.map(userEO, UserVO.class));
    }
/**
 * @Author liwenxuan
 * @Description 修改时同时对用户表、用户角色表关联表、用户组织机构关联表信息修改
 * @Date Administrator 2018/9/26
 * @Param [userVO]
 * @return com.adc.da.util.http.ResponseMessage<com.adc.da.sys.vo.UserVO>
 **/
    @ApiOperation(value = "|UserEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
//	@RequiresPermissions("sys:user:update")
    public ResponseMessage<UserVO> update(@RequestBody UserVO userVO) throws Exception {
        UserEO userEO = beanMapper.map(userVO, UserEO.class);
        userEO.setModifyTime(new Date());
//        修改用户表信息
        int i = userEOService.updateByPrimaryKeySelective(userEO);
//		  修改用户权限
        int i1 = userEOService.saveUserRole(userEO);
//        更新组织机构
        int i2 = userEOService.updateUserOrg(userEO);
        if(i>0 && i1>0 && i2>0){
            return Result.success("true","操作成功",userVO);
        }
        return Result.error("操作失败");
    }

    @ApiOperation(value = "|UserEO|删除")
    @DeleteMapping("/{ids}")
//	@RequiresPermissions("sys:user:delete")
    public ResponseMessage delete(@NotNull @PathVariable("ids") String[] ids) throws Exception {
        int i = userEOService.delete(Arrays.asList(ids));
        if(i==0){
            return  Result.error("删除失败");
        }
        return Result.success("true","删除成功","");
    }

    @ApiOperation(value = "配置用户角色|UserEO|")
    @PostMapping("/saveUserRole")
//	@RequiresPermissions("sys:user:saveUserRole")
    public ResponseMessage<UserVO> saveUserRole(@RequestBody UserVO userVO) {
        String userIds = userVO.getUsid();
        if (userIds != null && userIds.length() > 0) {
            String[] userIdList = userIds.split(",");
            for (int i = 0; i < userIdList.length; i++) {
                String userId = userIdList[i];
                UserEO user = beanMapper.map(userVO, UserEO.class);
                user.setUsid(userId);
                userEOService.saveUserRole(user);
            }
        } else {
            return Result.error( "未设置角色信息");
        }
        return Result.success("","操作成功",userVO);
    }

    @ApiOperation(value = "|UserEO|重置密码")
    @PostMapping(value = "/resetPassword")
    public ResponseMessage resetPassword(String userId) {
        int line = userEOService.resetPassword(userId);
        if (line == 0) return Result.error();
        else return Result.success();
    }
/**
 * @Author liwenxuan
 * @Description //组织机构分页：
 * 根据组织机构和用户的关联表进行中orgId字段作为判断条件进行查询
 * @Date Administrator 2018/9/25
 * @Param [page]
 * @return com.adc.da.util.http.ResponseMessage<com.adc.da.util.http.PageInfo<com.adc.da.sys.vo.UserVO>>
 **/
    @ApiOperation(value = "|UserEO|组织机构查询用户")
    @GetMapping("/findByOrg")
    public ResponseMessage<PageInfo<UserVO>> queryByOrg(UserEOPage page) {
       /* if (StringUtils.isNotBlank(page.getUname()))
            page.setUname("%" + page.getUname() + "%");*/
        List<UserEO> userEOs = userEOService.queryUserInfoByPage(page);
        PageInfo<UserVO> mapPage = beanMapper.mapPage(getPageInfo(page.getPager(), userEOs), UserVO.class);
        return Result.success(mapPage);
    }

/**
 * @Author liwenxuan
 * @Description 查找未分配组织结构的用户的行数
 * @Date Administrator 2018/9/21
 * @Param [page]
 * @return com.adc.da.util.http.ResponseMessage<com.adc.da.util.http.PageInfo<com.adc.da.sys.entity.UserEO>>
 **/
    @ApiOperation(value = "|UserEO|查询未配置组织机构的用户")
    @GetMapping("/findBySetOrg")
    public  ResponseMessage<PageInfo<UserEO>> findBySetOrg(UserEOPage page) {
        page.setValidFlag(ValidFlagEnum.VALID_TRUE.getValue() + "");
        page.setPager(new Pager());
        List<UserEO> userInfoByPage = userEOService.findUserInfoByPage(page);
        return Result.success(getPageInfo(page.getPager(), userInfoByPage));

    }
//通过userId查询关联表，没有orgId的就是没有组织结构的用户
//    把这些用户对应的信息显示出来

}
