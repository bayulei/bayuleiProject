package com.adc.da.sys.controller;

import com.adc.da.base.page.Pager;
import com.adc.da.base.web.BaseController;
import com.adc.da.sys.common.LayUiResult;
import com.adc.da.sys.constant.IsBelongEnum;
import com.adc.da.sys.entity.RoleEO;
import com.adc.da.sys.entity.UserEO;
import com.adc.da.sys.entity.UserRoleEO;
import com.adc.da.sys.page.RoleEOPage;
import com.adc.da.sys.service.RoleEOService;
import com.adc.da.sys.service.UserEOService;
import com.adc.da.sys.util.LoginUserUtil;
import com.adc.da.sys.vo.RoleVO;
import com.adc.da.util.constant.DeleteFlagEnum;
import com.adc.da.util.http.PageInfo;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.utils.BeanMapper;
import com.adc.da.util.utils.RequestUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping("/${restPath}/sys/role")
@Api(description = "角色管理")
public class RoleEOController extends BaseController<RoleEO> {

	private static final Logger logger = LoggerFactory.getLogger(RoleEOController.class);

	@Autowired
	private RoleEOService roleEOService;

	@Autowired
	BeanMapper beanMapper;
	@Autowired
	private UserEOService userService;

	@ApiOperation(value = "|RoleEO|分页查询")
	@GetMapping("/page")
//	@RequiresPermissions("sys:role:pageList")
	public LayUiResult<RoleVO> page(Integer pageNo, Integer pageSize, String roleName,String useFlag) throws Exception {
		RoleEOPage page = new RoleEOPage();
		if (pageNo != null) {
			page.setPage(pageNo);
		}
		if (pageSize != null) {
			page.setPageSize(pageSize);
		}
		if (StringUtils.isNotEmpty(roleName)) {
			page.setName("%"+roleName+"%");
			page.setNameOperator("LIKE");
		}
		if(StringUtils.isNotEmpty(useFlag)){
			page.setUseFlag(useFlag);
		}
		page.setValidFlag(DeleteFlagEnum.NORMAL.getValue()+"");
		page.setPager(new Pager());
		page.setOrderBy("modify_time desc");
		List<RoleEO> rows = roleEOService.queryByPage(page);
		//此处加载用户名称到前台
/*		if(rows!=null && rows.size()>0){
			for(RoleEO role:rows){
				String userId = role.getOprUser();
				UserEO user = userService.selectByPrimaryKey(userId);
				role.setOperUserName(user!=null?user.getUsname():null);
			}
		}*/
		PageInfo<RoleVO> mapPage = beanMapper.mapPage(getPageInfo(page.getPager(), rows), RoleVO.class);
		return new LayUiResult<RoleVO>(mapPage);
	}

	@ApiOperation(value = "|RoleEO|详情")
	@GetMapping("/{id}")
//	@RequiresPermissions("sys:role:get")
	public ResponseMessage<RoleVO> getById(@NotNull @PathVariable("id") String id) throws Exception {
		RoleEO roleEO = roleEOService.getRoleWithMenus(id);
		return Result.success(beanMapper.map(roleEO, RoleVO.class));
	}
//李文轩：这个功能没用到
	/*@ApiOperation(value = "|RoleEO|列表")
	@GetMapping("")
//	@RequiresPermissions("sys:role:list")
	public ResponseMessage<List<RoleVO>> list(String userId) {
		RoleVO setRole = new RoleVO();
	*//*	String loginUserId = SecurityUtils.getSubject().getSession().getAttribute(RequestUtils.LOGIN_USER_ID).toString();
		if(loginUserId != null && !loginUserId.isEmpty()){
			UserEO getUser = userService.selectOrgByPrimaryKey(loginUserId);
*//**//*			if(getUser != null){
				if(("0").equals(getUser.getCorpType())){
					setRole.setExtInfo("0");
				} else if(("1").equals(getUser.getCorpType())){
					setRole.setExtInfo("1");
				}

			}*//**//*
		}*//*
		List<RoleVO> roleVOs = beanMapper.mapList(roleEOService.findAll(setRole), RoleVO.class);
		if (userId != null) {
			for (RoleVO roleVO : roleVOs) {
				if (roleEOService.isBelong(userId, roleVO.getRid())) {
					roleVO.setBelong(IsBelongEnum.BELONG.getValue());
				}
			}
		}

		return Result.success(roleVOs);
	}*/

	@ApiOperation(value = "|RoleEO|新增")
	@PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
//	@RequiresPermissions("sys:role:save")
	public ResponseMessage<RoleVO> create(@RequestBody RoleVO roleVO) throws Exception {

		   Integer useFlag =  roleVO.getUseFlag();
		if(useFlag!=0 && useFlag !=1){
			return Result.error("0000","使用状态不能为空");
		}
		if(StringUtils.isBlank(roleVO.getRname())){
			return  Result.error("1111","新增角色名不能为空");
		}else if(roleEOService.queryNameExistenceByName(roleVO.getRname())){
              return  Result.error("2222","新增角色名已经存在");
		}


		RoleEO map = beanMapper.map(roleVO, RoleEO.class);
		RoleEO roleEO = roleEOService.save(map);
		return Result.success(roleVO);
	}

	@ApiOperation(value = "|RoleEO|修改")
	@PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
//	@RequiresPermissions("sys:role:update")
	public ResponseMessage<RoleVO> update(@RequestBody RoleVO roleVO) throws Exception {

		Integer useFlag =  roleVO.getUseFlag();
		if(useFlag!=0 && useFlag !=1){
			return Result.error("0000","使用状态不能为空");
		}
		if(StringUtils.isBlank(roleVO.getRname())){
			return  Result.error("1111","新增角色名不能为空");
		}else if(roleEOService.queryNameExistenceByName(roleVO.getRname())){
			return  Result.error("2222","新增角色名已经存在");
		}
//		roleVO.setUpdateTime(new Date());
		roleVO.setModifyTime(new Date());
		RoleEO map = beanMapper.map(roleVO, RoleEO.class);
//		map.setRemarks(roleVO.getRdesc());
		roleEOService.updateByPrimaryKeySelective(map);
		return Result.success(roleVO);
	}

	@ApiOperation(value = "|RoleEO|删除")
	@DeleteMapping("/{id}")
//	@RequiresPermissions("sys:role:delete")
	public ResponseMessage delete(@NotNull @PathVariable("id") String id) throws Exception {
		List<UserRoleEO> list = roleEOService.getUserRoleListByRoleId(id);
		// 如果角色有对应用户，则不允许删除
		if (list != null && list.size() > 0) {
			return Result.error("r0031", "该角色有对应用户，不能删除");
		}
		roleEOService.delete(id);
		return Result.success();
	}
	
	@ApiOperation(value = "|RoleEO|批量删除")
	@DeleteMapping("/deleteList/{idList}")
//	@RequiresPermissions("sys:role:deleteList")
	public ResponseMessage deleteList(@NotNull @PathVariable("idList") String ids) throws Exception {
		String[] idList=ids.split(",");
		if(idList!=null && idList.length>0){
			for(String id:idList){
				List<UserRoleEO> list = roleEOService.getUserRoleListByRoleId(id);
				// 如果角色有对应用户，则不允许删除
				if (list != null && list.size() > 0) {
					return Result.error("r0031", "该角色有对应用户，不能删除");
				}
/*				String loginUserId = SecurityUtils.getSubject().getSession().getAttribute(RequestUtils.LOGIN_USER_ID).toString();
				if(loginUserId != null || loginUserId != ""){
//					根据用户id查询ext-info和userRoleName
					UserEO getUser = userService.selectRoleMessageByPrimaryKey(loginUserId);
					*//*if(getUser != null && ! ("3").equals(getUser.getRoleExtInfo())){
						RoleEO getRole = roleEOService.selectByPrimaryKey(id);
						if(!("").equals(getRole.getExtInfo()) && getRole.getExtInfo() != null){
							if(! getRole.getExtInfo().equals(getUser.getRoleExtInfo())){
								return Result.error("您无权限删除该角色");
							}
						}

					}*//*
				}*/
				roleEOService.delete(id);
			}
		}
		return Result.success();
	}
	
	
	@ApiOperation(value = "配置角色菜单|RoleEO|")
	@PostMapping("/saveRoleMenu")
//	@RequiresPermissions("sys:role:saveRoleMenu")
	public ResponseMessage<RoleVO> saveRoleMenu(@RequestBody RoleVO roleVO) throws Exception {
		String roleIds = roleVO.getRid();
		if(roleIds!=null && roleIds.length()>0){
			String[] roleIdList=roleIds.split(",");
			for(int i=0;i<roleIdList.length;i++){
				String rId=roleIdList[i];
				RoleEO role = beanMapper.map(roleVO, RoleEO.class);
				role.setId(rId);
				roleEOService.saveRoleMenu(role);
			}
		}else{
			return Result.error("r00100","未设置角色信息");
		}
		return Result.success(roleVO);
	}
	//李文轩：这个功能没用到
/*	@ApiOperation(value = "|RoleEO|全部")
	@GetMapping("/findAll")
//	@RequiresPermissions("sys:role:list")
	public ResponseMessage<List<RoleVO>> findAll(String userId) {
		RoleVO setRole = new RoleVO();
		String loginUserId = SecurityUtils.getSubject().getSession().getAttribute(RequestUtils.LOGIN_USER_ID).toString();
		if(loginUserId != null && !loginUserId.isEmpty()){
			UserEO getUser = userService.selectOrgByPrimaryKey(loginUserId);
			*//*if(getUser != null){
				if(("0").equals(getUser.getCorpType())){
					setRole.setExtInfo("0");
				} else if(("1").equals(getUser.getCorpType())){
					setRole.setExtInfo("1");
				}

			}*//*
		}
		List<RoleVO> roleVOs = beanMapper.mapList(roleEOService.findAll(setRole), RoleVO.class);
		return Result.success(roleVOs);
	}*/
}
