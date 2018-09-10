package com.adc.da.sys.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.adc.da.util.utils.RequestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.page.Pager;
import com.adc.da.base.web.BaseController;
import com.adc.da.sys.common.LayUiResult;
import com.adc.da.sys.entity.OrgEO;
import com.adc.da.sys.entity.RoleEO;
import com.adc.da.sys.entity.UserEO;
import com.adc.da.sys.page.UserEOPage;
import com.adc.da.sys.service.OrgEOService;
import com.adc.da.sys.service.UserEOService;
import com.adc.da.sys.vo.RoleVO;
import com.adc.da.sys.vo.UserVO;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.utils.BeanMapper;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

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
	public ResponseMessage<PageInfo<UserEO>> page(Integer pageNo, Integer pageSize, String uname,String roleId,String orgname) throws Exception {
		UserEOPage page = new UserEOPage();
		if (pageNo != null) {
			page.setPage(pageNo);
		}
		if (pageSize != null) {
			page.setPageSize(pageSize);
		}
		if (StringUtils.isNotEmpty(uname)) {
			page.setUname("%"+uname+"%");
			page.setUnameOperator("LIKE");
		}
		if (StringUtils.isNotEmpty(roleId)) {
			page.setRoleId(roleId);
		}
		if (StringUtils.isNotEmpty(orgname)) {
//	此字段是通过下拉菜单传入了具体值
			page.setOrgname(orgname);
			//page.setRolenameOperator("LIKE");
		}
//		String userId = SecurityUtils.getSubject().getSession().getAttribute(RequestUtils.LOGIN_USER_ID).toString();
//		if(userId != null || userId != ""){
//			UserEO getUser = userEOService.selectOrgByPrimaryKey(userId);
//			/*if(getUser != null){
//				if(!("").equals(getUser.getUseCorpId()) && getUser.getUseCorpId() != null){
//					page.setGetCorpId(getUser.getUseCorpId());
//				}
//			}*/
//		}
		page.setPager(new Pager());
		
		List<UserEO> userEOs = userEOService.queryByPageAndParams(page);
//		return new LayUiResult(getPageInfo(page.getPager(), userEOs));
		return  Result.success(getPageInfo(page.getPager(), userEOs));
	}

	@ApiOperation(value = "|UserEO|新增")
	@PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
//	@RequiresPermissions("sys:user:save")
	public ResponseMessage<UserVO> create(@RequestBody UserVO userVO) throws Exception {
		if (StringUtils.isBlank(userVO.getAccount())) {
			return Result.error("r0014", "登录名不能为空");
		} else if (userEOService.getUserByLoginName(userVO.getAccount()) != null) {
			return Result.error("r0015", "账号已存在");
		}
		if(userEOService.getUserWithRoles(userVO.getUsid()).getWorkNum() !=null){
			return  Result.error("r0016", "员工编号已存在");
		}

		// 前台如果base64传输密文，则需要解码
		// userVO.setPassword(new String(Encodes.decodeBase64(userVO.getPassword())));
		UserEO userEO = userEOService.save(beanMapper.map(userVO, UserEO.class));
		return Result.success(beanMapper.map(userEO, UserVO.class));
	}

	@ApiOperation(value = "|UserEO|修改")
	@PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
//	@RequiresPermissions("sys:user:update")
	public ResponseMessage<UserVO> update(@RequestBody UserVO userVO) throws Exception {
		UserEO userEO = beanMapper.map(userVO, UserEO.class);
		userEO.setModifyTime(new Date());

		if(userEOService.getUserWithRoles(userVO.getUsid()).getWorkNum() !=null){
			return  Result.error("r0016", "员工编号已存在");
		}
//		李文轩：修改用户信息的密码是在个人中心中完成
//		userEO.setPassword("");
		userEOService.updateByPrimaryKeySelective(userEO);
//		注：一会看到角色管理时候看是否需要在这里进行修改用户权限
		//userEOService.saveUserRole(userEO);
		userEOService.updateUserOrg(userEO);
		return Result.success(userVO);
	}

	@ApiOperation(value = "|UserEO|删除")
	@DeleteMapping("/{ids}")
//	@RequiresPermissions("sys:user:delete")
	public ResponseMessage delete(@NotNull @PathVariable("ids") String[] ids) throws Exception {
		userEOService.delete(Arrays.asList(ids));
		return Result.success();
	}

	@ApiOperation(value = "配置用户角色|UserEO|")
	@PostMapping("/saveUserRole")
//	@RequiresPermissions("sys:user:saveUserRole")
	public ResponseMessage<UserVO> saveUserRole(@RequestBody UserVO userVO) {
		String userIds = userVO.getUsid();
		if(userIds!=null && userIds.length()>0){
			String[] userIdList=userIds.split(",");
			for(int i=0;i<userIdList.length;i++){
				String userId=userIdList[i];
				UserEO user = beanMapper.map(userVO, UserEO.class);
				user.setUsid(userId);
				userEOService.saveUserRole(user);
			}
		}else{
			return Result.error("r00100","未设置角色信息");
		}
		return Result.success(userVO);
	}
	
	@ApiOperation(value = "|UserEO|重置密码")
	@PostMapping(value = "/resetPassword")
	public ResponseMessage resetPassword(String userId) {
		int line = userEOService.resetPassword(userId);
		if(line == 0)
			return Result.error();
		else 
			return Result.success();
	}
	
	@ApiOperation(value = "|UserEO|组织机构查询用户")
	@GetMapping("/findByOrg")
	public LayUiResult<UserVO> queryByOrg(UserEOPage page){
		if(StringUtils.isNotBlank(page.getUname()))
			page.setUname("%"+page.getUname()+"%");
		List<UserEO> rows = userEOService.queryByOrg(page);
		return new LayUiResult(getPageInfo(page.getPager(), rows));
	}
	
	@ApiOperation(value = "|UserEO|查询未配置组织机构的用户")
	@GetMapping("/findBySetOrg")
	public LayUiResult<UserVO> findBySetOrg(UserEOPage page){
		List<UserEO> rows = userEOService.findBySetOrg(page);
		return new LayUiResult(getPageInfo(page.getPager(), rows));
	}
	
	@ApiOperation(value = "|UserEO|查询组织机构以及子机构用户")
	@GetMapping("/queryByOrgAndChiles")
	public LayUiResult<UserVO> queryByOrgAndChiles(UserEOPage page, String userCorpName){
		List<OrgEO> eos = orgEOService.findById(page.getUseCorpId(), userCorpName);
		StringBuilder corpStr = new StringBuilder();
		for (OrgEO orgEO : eos) {
			corpStr.append("'"+orgEO.getId()+"',");
		}
		page.setUseCorpId(corpStr.substring(0, corpStr.length()-1));
		if(StringUtils.isNotBlank(page.getUname()))
			page.setUname("%"+page.getUname()+"%");
		List<UserEO> rows = userEOService.queryByOrgAndChiles(page);
		return new LayUiResult(getPageInfo(page.getPager(), rows));
	}
	
	
}
