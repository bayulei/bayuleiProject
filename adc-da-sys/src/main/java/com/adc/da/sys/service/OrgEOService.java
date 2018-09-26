package com.adc.da.sys.service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.adc.da.sys.util.UUIDUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.base.service.BaseService;
import com.adc.da.sys.dao.OrgEODao;
import com.adc.da.sys.entity.OrgEO;
import com.adc.da.sys.entity.UserOrgEO;
import com.adc.da.util.constant.DeleteFlagEnum;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.utils.UUID;
import com.alibaba.fastjson.JSON;

@Service("orgEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class OrgEOService extends BaseService<OrgEO, String> {

	private static final Logger logger = LoggerFactory.getLogger(OrgEOService.class);

	@Autowired
	private OrgEODao dao;

	@Override
	public OrgEODao getDao() {
		return dao;
	}
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<OrgEO> listOrgEOByOrgName(String orgName) {
		return dao.listOrgEOByOrgName(orgName);
	}
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public OrgEO getOrgEOByNameAndPid(String orgName,String pId) {
		return dao.getOrgEOByNameAndPid(orgName,pId);
	}
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<OrgEO> getOrgEOByPid(String pId) {
		return dao.getOrgEOByPid(pId);
	}
	/**
	 * @Author liwenxuan
	 * @Description
	 * @Date Administrator 2018/9/21
	 * @Param [orgEO]
	 * @return com.adc.da.util.http.ResponseMessage
	 **/
	public ResponseMessage save(OrgEO orgEO) {

		String orgName = orgEO.getOrgName();
		String shotName = orgEO.getShotName();
		String getpId = orgEO.getpId();
		String id = orgEO.getId();//id在这里为空，只是为了使用getOrgEOByShotNameAndPidAndId方法填进去的

		if(StringUtils.isEmpty(orgName)){
			return Result.error("组织机构名不能为空");
		}else if(dao.getOrgEOByorgNameAndPidAndId(orgName, getpId,id)!=null){
			return  Result.error("组织机构名称不能重复");
		}

		if(StringUtils.isEmpty(shotName)){
			return Result.error("组织机构简称不能为空");
		}else if(dao.getOrgEOByShotNameAndPidAndId(shotName,getpId,id)!=null){
			return Result.error("组织机构简称不能重复");
		}


		orgEO.setId(UUIDUtils.randomUUID20());
		orgEO.setValidFlag(DeleteFlagEnum.NORMAL.getValue());
		orgEO.setIsShow(0);
		orgEO.setCreationTime(new Date());
		orgEO.setModifyTime(new Date());
		int line = dao.insert(orgEO);
		if(line > 0) {
			return Result.success("true","新增成功","");
		} else {
			return Result.error("false","新增失败");
		}
	}
	
	/**
	 * 组织机构详情
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public OrgEO getOrgEOById(String id) {
		return dao.getOrgEOById(id);
	}
	
	/**
	 * 删除组织机构
	 * @return 
	 */
	public ResponseMessage delete(String id) {
		List<OrgEO> list = dao.getOrgEOByPid(id);
// 如果该组织机构下有子机构，则不允许删除
		if (list != null && !list.isEmpty()) {
			return Result.error( "该组织机构下有子机构，不能删除");
		}
		int line = dao.deleteLogic(id);
		if(line > 0) {
			return Result.success("true","操作成功","");
		} else {
			return Result.error("操作失败");
		}
	}
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public LinkedList<OrgEO> selectOrgAllNode() {
		LinkedList<OrgEO> rootNodes = dao.selectOrgAllNode();
		return rootNodes;
	}



	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public LinkedList<OrgEO> getTree() {
		LinkedList<OrgEO> rootNodes = dao.selectRootNode();
		addNodes(rootNodes, 0);
		return rootNodes;
	}

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	private void addNodes(LinkedList<OrgEO> rootNodes, int i) {
		if(i < rootNodes.size() || rootNodes.size() == 1) {
			OrgEO orgEO = rootNodes.get(i);
			LinkedList<OrgEO> nodes = dao.selectNodeByPid(orgEO.getId());
			if(nodes != null) {
				rootNodes.addAll(nodes);
			}
			if(rootNodes.size() > 1) {
				addNodes(rootNodes, ++i);
			}
		}
	}
/**
 * @Author liwenxuan
 * @Description 修改组织机构简称和名称不能为空也不可以重复
 * @Date Administrator 2018/9/18
 * @Param [orgEO]
 * @return com.adc.da.util.http.ResponseMessage
 **/
	public ResponseMessage updateById(OrgEO orgEO) {

		String orgName = orgEO.getOrgName();
		String shotName = orgEO.getShotName();
		String getpId = orgEO.getpId();
		String id = orgEO.getId();

		if(StringUtils.isEmpty(orgName)){
			return Result.error("组织机构名不能为空");
		}else if(dao.getOrgEOByorgNameAndPidAndId(orgName, getpId,id)!=null){
			return  Result.error("组织机构名称不能重复");
		}

		if(StringUtils.isEmpty(shotName)){
			return Result.error("组织机构简称不能为空");
		}else if(dao.getOrgEOByShotNameAndPidAndId(shotName,getpId,id)!=null){
			return Result.error("组织机构简称不能重复");
		}
		orgEO.setModifyTime(new Date());
		int line = dao.updateByPrimaryKeySelective(orgEO);
		if(line > 0) {
			return Result.success("true","操作成功","");
		} else {
			return Result.error("操作失败");
		}
	}

	public ResponseMessage<Integer> delOrgRelatedUser(String userId, String orgId) {
		UserOrgEO userOrgEO = new UserOrgEO();
		userOrgEO.setUserId(userId);
		userOrgEO.setOrgId(orgId);
		int i = dao.delOrgRelatedUser(userOrgEO);
		if(i>0){
			return Result.success("","操作成功",1);
		}
			return Result.error("操作失败");
	}

	public ResponseMessage<Integer> addOrgRelatedUser(String userOrgs) {
		List<UserOrgEO> userOrgEOs = JSON.parseArray(userOrgs, UserOrgEO.class);

		int i = dao.addOrgRelatedUsers(userOrgEOs);
		if(i>0){
			return Result.success("","操作成功",1);
		}
		return Result.error("操作失败");
	}

	public int delOrgRelatedUserByUserId(String usId) {
		return dao.delOrgRelatedUserByUserId(usId);
	}

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<OrgEO> findById(String id, String orgName) {
		LinkedList<OrgEO> rootNodes = new LinkedList<OrgEO>();
		OrgEO orgEO = new OrgEO();
		orgEO.setId(id);
		orgEO.setOrgName(orgName);
		rootNodes.add(orgEO);
		addNodes(rootNodes, 0);
		return rootNodes;
	}

}
