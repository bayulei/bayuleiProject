package com.adc.da.sys.dao;

import java.util.List;

import com.adc.da.sys.vo.RoleVO;
import org.apache.ibatis.annotations.Param;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.sys.entity.RoleEO;
import com.adc.da.sys.entity.UserRoleEO;
/**
 *
 * <br>
 * <b>功能：</b>TS_ROLE RoleEODao<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2017-11-06 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public interface RoleEODao extends BaseDao<RoleEO> {
	
	 
	 public void save(RoleEO sysRoleEO);
	 
	 public RoleEO getRoleWithMenus(String id);
	 
	 public List<RoleEO> getRoleListByUserId(String userId);
	 
	 public void deleteLogic(String roleId);
	 
	 public void deleteRoleMenuByRoleId(String roleId);
	 
	 public List<RoleEO> findAll(RoleVO roleVO);
	 
	 /**
	  * 查询角色所对应的用户
	  */
	 public List<UserRoleEO> getUserRoleListByRoleId(String roleId);
	 
	 public List<String> getMenuIdListByRoleId(String roleId);
	 
	 public void saveRoleMenu(@Param("roleId")String roleId, @Param("menuId")String menuId);
	 
	 /**
	  * 判断角色是否属于相应用户
	  */
	 public int isBelong(@Param("userId")String userId,@Param("roleId")String roleId);
	 
	 public List<RoleEO> findByUserId(String userId);
}
