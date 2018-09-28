package com.adc.da.sys.service;

import java.beans.Transient;
import java.util.Date;
import java.util.List;

import com.adc.da.base.page.BasePage;

import com.adc.da.person.dao.UserInfoEODao;
import com.adc.da.person.entity.UserInfoEO;
import com.adc.da.sys.constant.UserSourceEnum;
import com.adc.da.sys.constant.ValidFlagEnum;
import com.adc.da.sys.entity.UserRoleEO;
import com.adc.da.sys.util.UUIDUtils;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.base.service.BaseService;
import com.adc.da.sys.dao.OrgEODao;
import com.adc.da.sys.dao.RoleEODao;
import com.adc.da.sys.dao.UserEODao;
import com.adc.da.sys.entity.UserEO;
import com.adc.da.sys.entity.UserOrgEO;
import com.adc.da.sys.page.UserEOPage;
import com.adc.da.util.constant.DeleteFlagEnum;
import com.adc.da.util.utils.CollectionUtils;
import com.adc.da.util.utils.PasswordUtils;
import com.adc.da.util.utils.UUID;

/**
 *
 * <br>
 * <b>功能：</b>TS_USER UserEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2017-11-06 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("userEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class UserEOService extends BaseService<UserEO, String> {

	private static final Logger logger = LoggerFactory.getLogger(UserEOService.class);

	@Autowired
	private UserEODao dao;
	@Autowired
	private UserInfoEODao userInfoEODao;
	@Autowired
	private OrgEODao orgEODao;

	public UserEODao getDao() {
		return dao;
	}

	@Transactional(rollbackFor = Exception.class)
	public UserEO save(UserEO userEO) {
		userEO.setUsid(UUIDUtils.randomUUID20());
		userEO.setValidFlag(DeleteFlagEnum.NORMAL.getValue());
		userEO.setCreationTime(new Date(System.currentTimeMillis()));
		userEO.setModifyTime(new Date(System.currentTimeMillis()));
		userEO.setPassword(PasswordUtils.encryptPassword(userEO.getPassword()));
		userEO.setUserSource(UserSourceEnum.LOCAL_USER.getValue());
		if(userEO.getExtInfo() != null){
			userEO.setExtInfo(userEO.getExtInfo());
		}else{
			userEO.setExtInfo("");
		}
		dao.insert(userEO);
		//TODO 此处需要维护用户的组织机构
		if(StringUtils.isNotEmpty(userEO.getOrgId())){
			dao.saveUserOrg(userEO.getUsid(),userEO.getOrgId());
		}
		// 此处需要维护用户角色
		if(StringUtils.isNotEmpty(userEO.getRoleId())){
			dao.saveUserRole(userEO.getUsid(),userEO.getRoleId());
		}
		// 此处维护用户的相关电话信息
		if(StringUtils.isNotEmpty(userEO.getMobilePhone())||StringUtils.isNotEmpty(userEO.getOfficePhone())){
			UserInfoEO userInfoEO=new UserInfoEO();
			userInfoEO.setId(UUIDUtils.randomUUID20());
			if(StringUtils.isNotEmpty(userEO.getOfficePhone())){
				userInfoEO.setOfficePhone(userEO.getOfficePhone());
			}
			if(StringUtils.isNotEmpty(userEO.getMobilePhone())){
				userInfoEO.setMobilePhone(userEO.getMobilePhone());
			}
			userInfoEO.setValidFlag(ValidFlagEnum.VALID_TRUE.getValue());
			userInfoEO.setCreationTime(new Date());
			userInfoEO.setModifyTime(new Date());
			userInfoEODao.insert(userInfoEO);
		}

		return userEO;
	}

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public UserEO getUserByLoginName(String userName) {
		return dao.getUserEOByAccount(userName);
	}

	public void updatePassword(String userId, String oldPassword, String newPassword) {
		dao.updatePassword(userId, oldPassword, newPassword);
	}

	public void updateUserEO(UserEO userEO) {
		dao.updateUserEO(dao.get(userEO.getUsid()));
	}

	/***
	 * 用户管理界面分页
	 * @MethodName:queryUserInfoByPage
	 * @author: zhangyanduan
	 * @param:[]
	 * @return:java.util.List<com.adc.da.sys.entity.UserEO>
	 * date: 2018/9/15 16:10
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<UserEO> queryUserInfoByPage(BasePage basePage){
		int rowCount = dao.queryUserInfoByCount(basePage);
		basePage.getPager().setRowCount(rowCount);
		return dao.queryUserInfoByPage(basePage);
	}

	/**
	 * 删除用户及用户角色关联
	 */
	public int delete(List<String> ids) {
		int i  = dao.deleteLogicInBatch(ids);
//		删除用户角色和组织机构的关系
		int i1 = dao.deleteUserRoleByUsidInBatch(ids);
		int i2 = dao.deleteUserOrgByUsidInBatch(ids);
//
		if( i>0 ){
			return 1;
		}
		return 0;
	}

	/**
	 * 查询用户及用户所对应的角色
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public UserEO getUserWithRoles(String id) {
		return dao.getUserWithRoles(id);
	}

	/**
	 * 设置用户角色关联
	 */
	public int saveUserRole(UserEO userEO) {
		if (userEO.getRoleId() !=null) {
			dao.deleteUserRoleByUsid(userEO.getUsid());
			dao.saveUserRole(userEO.getUsid(),userEO.getRoleId());

		}
		return 1;
	}
	
	/**
	 * 设置用户组织机构关联
	 */
	public UserEO saveUserOrg(UserEO userEO) {
		if (CollectionUtils.isNotEmpty(userEO.getOrgIdList())) {
			dao.deleteUserOrgByUsid(userEO.getUsid());
			for (String orgId : userEO.getOrgIdList()) {
				dao.saveUserOrg(userEO.getUsid(), orgId);
			}
		}
		return userEO;
	}
	
	//修改用户组织机构关联
	public int updateUserOrg(UserEO userEO) {
		UserOrgEO userOrgEO = new UserOrgEO();
		userOrgEO.setUserId(userEO.getUsid());
		userOrgEO.setOrgId(userEO.getOrgId());
			//如果编辑之前此用户有组织机构，则进行修改，没有组织机构进行新增
			int i = dao.selectOrgCountByPrimaryKey(userEO.getUsid());
			if(i>0){
			return orgEODao.updateUserOrg(userOrgEO);
			}
			return orgEODao.addOrgRelatedUser(userOrgEO);
	}

	/**
	 * 根据当前登录用户id查询个人信息及部门
	 * @param usid
	 * @return
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public UserEO selectOrgByPrimaryKey(String usid){
		return dao.selectOrgByPrimaryKey(usid);
	}


	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public UserEO selectRoleMessageByPrimaryKey(String usid){
		return dao.selectRoleMessageByPrimaryKey(usid);
	}

	public int resetPassword(String userId) {
		UserEO userEO = new UserEO();
		userEO.setUsid(userId);
		userEO.setPassword(PasswordUtils.encryptPassword("1234qwer"));
		return dao.updatePasswordByPrimaryKey(userEO);
	}

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public UserEO selectByUnameAndPwd(UserEO userEO){
		return dao.selectByUnameAndPwd(userEO);
	}

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<UserEO> queryByOrg(BasePage basePage) {
		int rowCount = dao.queryByCount(basePage);
		basePage.getPager().setRowCount(rowCount);
		return dao.queryByPage(basePage);
	}


	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<UserEO> queryByPageAndParams(UserEOPage page) {
		int rowCount = dao.queryByOrgCount(page);
		page.getPager().setRowCount(rowCount);
		return dao.queryByOrg(page);
	}

	public List<UserEO> queryOrgByAccount(String account){
		return dao.queryOrgByAccount(account);
	}


/**
 * @Author liwenxuan
 * @Description 组织机构查询未配置人员信息
 * @Date Administrator 2018/9/21
 * @Param [basePage]
 * @return java.util.List<com.adc.da.sys.entity.UserEO>
 **/
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<UserEO> findUserInfoByPage(BasePage basePage){
		//liwenxuan:查找未分配组织结构的用户的行数
		int rowCount = dao.findBySetOrgCount(basePage);
		basePage.getPager().setRowCount(rowCount);
		//liwenxuan：查询未分配组织机构人员信息
		return dao.findBySetOrg(basePage);
	}

}
