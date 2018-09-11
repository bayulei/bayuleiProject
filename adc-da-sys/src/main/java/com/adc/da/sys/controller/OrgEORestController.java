package com.adc.da.sys.controller;

import com.adc.da.base.web.BaseController;
import com.adc.da.sys.entity.OrgEO;
import com.adc.da.sys.entity.UserEO;
import com.adc.da.sys.service.OrgEOService;
import com.adc.da.sys.service.UserEOService;
import com.adc.da.sys.vo.OrgVO;
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
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping("/${restPath}/sys/org")
@Api(description = "组织机构管理")
public class OrgEORestController extends BaseController<OrgEO>{
	private static final Logger logger = LoggerFactory.getLogger(OrgEORestController.class);
	
	@Autowired
	private OrgEOService orgEOService;
	@Autowired
	private UserEOService userEOService;
	
	@Autowired
	private BeanMapper beanMapper;
	
	@ApiOperation(value = "组织机构列表|OrgEO|")
	@GetMapping("/listOrgByOrgName")
//	@RequiresPermissions("sys:org:listMenuByUserId")
	public ResponseMessage<List<OrgVO>> listOrgByOrgName(String orgName) {
		return Result.success(beanMapper.mapList(orgEOService.listOrgEOByOrgName(orgName), OrgVO.class));
	}

	@SuppressWarnings("unchecked")
	@ApiOperation(value = "|OrgEO|新增")
	@PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
//	@RequiresPermissions("sys:org:save")
	public ResponseMessage<OrgVO> create(@RequestBody OrgVO orgVO) throws Exception {
		if (StringUtils.isBlank(orgVO.getName())) {
			return Result.error("r0014", "组织机构名称不能为空");
		} else if (orgEOService.getOrgEOByNameAndPid(orgVO.getName(),orgVO.getParentId()) != null) {
			return Result.error("r0015", "组织机构名称已存在");
		}
		
		return orgEOService.save(beanMapper.map(orgVO, OrgEO.class));
	}
	
	@ApiOperation(value = "|OrgEO|修改")
	@PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
//	@RequiresPermissions("sys:org:update")
	public ResponseMessage<OrgVO> update(@RequestBody OrgVO orgVO) throws Exception {
		return orgEOService.updateById(beanMapper.map(orgVO, OrgEO.class));//updateByPrimaryKeySelective(beanMapper.map(orgVO, OrgEO.class));
	}
	
	@ApiOperation(value = "|OrgEO|详情")
	@GetMapping("/{id}")
//	@RequiresPermissions("sys:org:get")
	public ResponseMessage<OrgVO> getById(@NotNull @PathVariable("id") String id) throws Exception {
		OrgVO orgVO = beanMapper.map(orgEOService.getOrgEOById(id), OrgVO.class);
		return Result.success(orgVO);
	}
	
	@ApiOperation(value = "|OrgEO|删除")
	@DeleteMapping("/{id}")
	@RequiresPermissions("sys:org:delete")
	public ResponseMessage delete(@NotNull @PathVariable("id") String id) throws Exception {
		return orgEOService.delete(id);
	}
	
	@ApiOperation(value = "|OrgEO|获取树结构")
	@GetMapping("/getTree")
//	@RequiresPermissions("sys:org:getTree")
	public ResponseMessage<List<OrgEO>> getTree(){
		OrgEO orgEO = new OrgEO();
		String userId = SecurityUtils.getSubject().getSession().getAttribute(RequestUtils.LOGIN_USER_ID).toString();
		if(userId != null && !userId.isEmpty()){
			UserEO getUser = userEOService.selectOrgByPrimaryKey(userId);
			/*if(getUser != null){
				if(!("").equals(getUser.getUseCorpId()) && getUser.getUseCorpId() != null){
					orgEO.setCorpId(getUser.getUseCorpId());
				}
			}*/
		}
		List<OrgEO> eos = orgEOService.selectOrgAllNode(orgEO);
		return Result.success(eos);
	}
	
	//未验证是否正确
	@ApiOperation(value = "|OrgEO|删除组织机构下的一些用户")
	@DeleteMapping("/{userId}/{orgId}")
//	@RequiresPermissions("sys:org:delOrgOfUser")
	public ResponseMessage<Integer> delOrgRelatedUser(@NotNull @PathVariable("userId") String userId, 
			@NotNull @PathVariable("orgId") String orgId){
		return orgEOService.delOrgRelatedUser(userId, orgId);
	}
	
	@ApiOperation(value = "|OrgEO|给用户设置组织机构")
	@PostMapping("/addOrgRelateUsers")
	@RequiresPermissions("sys:org:addOrgRelateUsers")
	public ResponseMessage<Integer> addOrgRelatedUser(String userOrgs){
		return orgEOService.addOrgRelatedUser(userOrgs);
	}
	
	@ApiOperation(value = "|OrgEO|批量删除组织机构下的一些用户")
	@DeleteMapping("/deleteList/{idList}")
//	@RequiresPermissions("sys:org:delListOfUser")
	public ResponseMessage<Integer> delOrgRelatedUsers(@NotNull @PathVariable("idList") String ids){
		String[] idList=ids.split(",");
		if(idList!=null && idList.length>0){
			for(String id:idList){
				orgEOService.delOrgRelatedUserByUserId(id);
			}
		}
		return Result.success();
	}
	
	@ApiOperation(value = "|OrgEO|获取树结构")
	@GetMapping("/findById")
//	@RequiresPermissions("sys:org:findById")
	public ResponseMessage<List<OrgEO>> findById(String id, String userCorpName){
		List<OrgEO> eos = orgEOService.findById(id, userCorpName);
		return Result.success(eos);
	}
	
}
