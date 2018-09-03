package com.adc.da.sys.dao;

import java.util.LinkedList;
import java.util.List;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.sys.entity.OrgEO;
import com.adc.da.sys.entity.UserOrgEO;

import org.apache.ibatis.annotations.Param;


public interface OrgEODao extends BaseDao<OrgEO>{

	public List<OrgEO> listOrgEOByOrgName(@Param("orgName") String orgName);
	
	public OrgEO getOrgEOByNameAndPid(String name,String parentId);
	
	public List<OrgEO> getOrgEOByPid(String parentId);

	public OrgEO getOrgEOById(String id);
	
	public int deleteLogic(String id);

	//获取根节点， pid 和 CORP_ID 为 null
	public LinkedList<OrgEO> selectOrgAllNode(OrgEO orgEO);

	public LinkedList<OrgEO> selectRootNode();

	public LinkedList<OrgEO> selectNodeByPid(String id);
	
	public List<OrgEO> queryByObject(OrgEO eo);

	public int delOrgRelatedUser(UserOrgEO userOrgEO);

	public void addOrgRelatedUsers(List<UserOrgEO> userOrgEOs);

	public int delOrgRelatedUserByUserId(String usId);

	public void addOrgRelatedUser(UserOrgEO userOrgEO);

	public int updateUserOrg(UserOrgEO userOrgEO);
	
}
