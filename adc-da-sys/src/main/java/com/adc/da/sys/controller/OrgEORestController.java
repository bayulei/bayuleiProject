package com.adc.da.sys.controller;

import com.adc.da.base.web.BaseController;
import com.adc.da.sys.entity.OrgEO;
import com.adc.da.sys.service.OrgEOService;
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
	private BeanMapper beanMapper;

	/**
	 * @Author liwenxuan
	 * @Description 需求可能会变成分页查找+模糊查找（根据类型/用户名称/角色名称/用户状态）
	 * @Date Administrator 2018/9/17
	 * @Param [orgName]
	 * @return com.adc.da.util.http.ResponseMessage<java.util.List<com.adc.da.sys.vo.OrgVO>>
	 **/
	@ApiOperation(value = "组织机构列表|OrgEO|")
	@GetMapping("/listOrgByOrgName")
//	@RequiresPermissions("sys:org:listMenuByUserId")
	public ResponseMessage<List<OrgVO>> listOrgByOrgName(String orgName) {
		return Result.success(beanMapper.mapList(orgEOService.listOrgEOByOrgName(orgName), OrgVO.class));
	}

	/**
	 * @Author liwenxuan
	 * @Description 新增组织机构（入参：部门名称+部门简介+部门表述(不必须填写)）
	 * 1.判断部门简称不能为空，不能已经存在
	 * 2.先判断返回对象不为空，然后在进行判断shotname
	 * @Date Administrator 2018/9/17
	 * @Param [orgVO]
	 * @return com.adc.da.util.http.ResponseMessage<com.adc.da.sys.vo.OrgVO>
	 **/
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "|OrgEO|新增")
	@PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
//	@RequiresPermissions("sys:org:save")
	public ResponseMessage<OrgVO> create(@RequestBody OrgVO orgVO) throws Exception {
		if (StringUtils.isBlank(orgVO.getOrgName())) {
			return Result.error("r0014", "组织机构名称不能为空");
		} else if (orgEOService.getOrgEOByNameAndPid(orgVO.getOrgName(),orgVO.getParentId()) != null) {
			return Result.error("r0015", "组织机构名称已存在");
		}
		if (StringUtils.isBlank(orgVO.getShotName())) {
			return Result.error("r0014", "组织机构简称不能为空");
		} else if (orgEOService.getOrgEOByNameAndPid(orgVO.getOrgName(),orgVO.getParentId()) != null) {
			if(orgEOService.getOrgEOByNameAndPid(orgVO.getOrgName(),orgVO.getParentId()).getShotName()!=null){
				return Result.error("r0015", "组织机构简称已存在");
			}
		}
		return orgEOService.save(beanMapper.map(orgVO, OrgEO.class));
	}
	/**
	 * @Author liwenxuan
	 * @Description   修改组织机构（前台需要传入id）
	 * @Date Administrator 2018/9/17
	 * @Param [orgVO]
	 * @return com.adc.da.util.http.ResponseMessage<com.adc.da.sys.vo.OrgVO>
	 **/
	@ApiOperation(value = "|OrgEO|修改")
	@PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
//	@RequiresPermissions("sys:org:update")
	public ResponseMessage<OrgVO> update(@RequestBody OrgVO orgVO) throws Exception {
		return orgEOService.updateById(beanMapper.map(orgVO, OrgEO.class));
	}
	/**
	 * @Author liwenxuan
	 * @Description //详情，根据id查询组织机构
	 * @Date Administrator 2018/9/17
	 * @Param [id]
	 * @return com.adc.da.util.http.ResponseMessage<com.adc.da.sys.vo.OrgVO>
	 **/
	@ApiOperation(value = "|OrgEO|详情")
	@GetMapping("/{id}")
//	@RequiresPermissions("sys:org:get")
	public ResponseMessage<OrgVO> getById(@NotNull @PathVariable("id") String id) throws Exception {
		OrgVO orgVO = beanMapper.map(orgEOService.getOrgEOById(id), OrgVO.class);
		return Result.success(orgVO);
	}

/**
 * @Author liwenxuan
 * @Description 删除组织机构
 * @Date Administrator 2018/9/17
 * @Param [id]
 * @return com.adc.da.util.http.ResponseMessage
 **/
	@ApiOperation(value = "|OrgEO|删除")
	@DeleteMapping("/{id}")
//	@RequiresPermissions("sys:org:delete")
	public ResponseMessage delete(@NotNull @PathVariable("id") String id) throws Exception {
		return orgEOService.delete(id);
	}


	@ApiOperation(value = "|OrgEO|获取树结构")
	@GetMapping("/getTree")
//	@RequiresPermissions("sys:org:getTree")
	public ResponseMessage<List<OrgEO>> getTree(){
		OrgEO orgEO = new OrgEO();
		List<OrgEO> eos = orgEOService.selectOrgAllNode(orgEO);
		return Result.success(eos);
	}


	@ApiOperation(value = "|OrgEO|删除组织机构下的一些用户")
	@DeleteMapping("/{userId}/{orgId}")
//	@RequiresPermissions("sys:org:delOrgOfUser")
	public ResponseMessage<Integer> delOrgRelatedUser(@NotNull @PathVariable("userId") String userId, 
			@NotNull @PathVariable("orgId") String orgId){
		return orgEOService.delOrgRelatedUser(userId, orgId);
	}


	/**
	 * @Author liwenxuan
	 * @Description 前台传入类型(格式)：[{"userId":"QJX2Z8E678","orgId":"5W2J4AQ8KA"}]
	 * @Date Administrator 2018/9/17
	 * @Param [userOrgs]
	 * @return com.adc.da.util.http.ResponseMessage<java.lang.Integer>
	 **/
	@ApiOperation(value = "|OrgEO|给用户设置组织机构")
	@PostMapping("/addOrgRelateUsers")
//	@RequiresPermissions("sys:org:addOrgRelateUsers")
	public ResponseMessage<Integer> addOrgRelatedUser(String userOrgs){

		return orgEOService.addOrgRelatedUser(userOrgs);
	}

	/**
	 * @Author liwenxuan
	 * @Description 传入字段是用户表id的String字符串中间用逗号隔开
	 * 1.删除的是用户和组织机构表中的数据（是真删除，关系表中没有validflag字段）
	 * @Date Administrator 2018/9/18
	 * @Param [ids]
	 * @return com.adc.da.util.http.ResponseMessage<java.lang.Integer>
	 **/
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
