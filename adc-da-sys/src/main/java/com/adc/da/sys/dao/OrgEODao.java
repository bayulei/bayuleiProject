package com.adc.da.sys.dao;

import java.util.LinkedList;
import java.util.List;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.sys.entity.OrgEO;
import com.adc.da.sys.entity.UserOrgEO;

import org.apache.ibatis.annotations.Param;


public interface OrgEODao extends BaseDao<OrgEO>{

	public List<OrgEO> listOrgEOByOrgName(@Param("orgName") String orgName);

	public OrgEO getOrgEOByNameAndPid(@Param("orgName") String orgName,@Param("pId")String pId);

	//liwenxuan:判断组织机构名称不重复
	public OrgEO getOrgEOByorgNameAndPidAndId(@Param("orgName") String orgName,@Param("pId")String pId,@Param("id")String id);

//liwenxuan:判断组织机构简称不重复
	public OrgEO getOrgEOByShotNameAndPidAndId(@Param("shotName") String shotName,@Param("pId")String pId,@Param("id")String id);

	
	public List<OrgEO> getOrgEOByPid(@Param("pId") String pId);

	public OrgEO getOrgEOById(String id);
	
	public int deleteLogic(String id);

	//获取根节点， pid 和 CORP_ID 为 null
	public LinkedList<OrgEO> selectOrgAllNode();

	public LinkedList<OrgEO> selectRootNode();

	public LinkedList<OrgEO> selectNodeByPid(String id);
	
	public List<OrgEO> queryByObject(OrgEO eo);

	public int delOrgRelatedUser(UserOrgEO userOrgEO);

	public int addOrgRelatedUsers(List<UserOrgEO> userOrgEOs);

	public int delOrgRelatedUserByUserId(String usId);

	public int addOrgRelatedUser(UserOrgEO userOrgEO);

	public int updateUserOrg(UserOrgEO userOrgEO);
	
}
