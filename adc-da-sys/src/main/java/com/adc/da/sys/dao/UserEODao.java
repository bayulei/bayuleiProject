package com.adc.da.sys.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.base.page.BasePage;
import com.adc.da.sys.entity.UserEO;
import org.apache.ibatis.annotations.Param;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.bouncycastle.asn1.mozilla.PublicKeyAndChallenge;

import java.util.List;

/**
 *
 * <br>
 * <b>功能：</b>TS_USER UserEODao<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2017-12-18 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public interface UserEODao extends BaseDao<UserEO> {

	public void updatePassword(@Param("userId") String  userId, @Param("oldPassword") String oldPassword,
			@Param("newPassword") String newPassword);

	public void updateUserEO(UserEO userEO);

	public List<Integer> getRoleIdListByUserId(Integer usid);

	public void saveUserRole(@Param("usid") String usid, @Param("roleId") String roleId);
	
	public void saveUserOrg(@Param("usid") String usid, @Param("orgId") String orgId);

	/***
	 * 用户管理查询分页
	 * @MethodName:queryUserInfoByCount
	 * @author: zhangyanduan
	 * @param:[basePage]
	 * @return:int
	 * date: 2018/9/15 15:59
	 */
	public int queryUserInfoByCount(BasePage basePage);

	public List<UserEO> queryUserInfoByPage(BasePage basePage);

	/**
	 * 物理删除用户角色关联
	 * 
	 * @param usid
	 *            用户ID
	 */
	public void deleteUserRoleByUsid(String usid);
	/**
	 * 物理删除用户组织机构关联
	 * 
	 * @param usid
	 *            用户ID
	 */
	public void deleteUserOrgByUsid(String usid);

	public int deleteLogicInBatch(List<String> usids);

	/**
	 * 批量删除用户角色关联
	 * 
	 * @param usids
	 *            用户ID集合
	 */
	public int deleteUserRoleByUsidInBatch(List<String> usids);
	
	/**
	 * 批量删除用户组织机构关联
	 * 
	 * @param usids
	 *            用户ID集合
	 */
	public int deleteUserOrgByUsidInBatch(List<String> usids);

	public UserEO getUserEOByAccount(String account);

	/**
	 * 查询用户及用户所对应的角色
	 */
	public UserEO getUserWithRoles(String id);

	public UserEO get(String id);

	/**
	 * 根据当前登录用户id查询个人信息及部门
	 * @param usid
	 * @return
	 */
	UserEO selectOrgByPrimaryKey(String usid);

	Integer selectOrgCountByPrimaryKey(String usid);

	UserEO selectRoleMessageByPrimaryKey(String usid);

	public List<UserEO> queryByOrg(BasePage basePage);

	public UserEO selectByUnameAndPwd(UserEO userEO);
	public int queryByOrgCount(BasePage basePage);

	public int updatePasswordByPrimaryKey(UserEO userEO);

	public List<UserEO> queryOrgByAccount(String account);

//liwenxuan:查找未分配组织结构的用户的行数
	public int findBySetOrgCount(BasePage basePage);
//liwenxuan：查询未分配组织机构人员信息
	public List<UserEO> findBySetOrg(BasePage basePage);
}
