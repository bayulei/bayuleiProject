package com.adc.da.sys.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.adc.da.sys.entity.OrgEO;
import com.adc.da.sys.entity.RoleEO;

public class UserVO {

	private Integer validFlag;
	@org.springframework.format.annotation.DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date modifyTime;
	@org.springframework.format.annotation.DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date creationTime;
	private String operUser;
	private Object extInfo;
	private String workNum;
	private String email;
	private String userSource;
	private String uname;
	private String password;
	private String account;
	private String usid;
	// 扩展字段
    private String updateTime;
	private List<String> rolesstr = new ArrayList<>();
	private List<RoleEO> roles = new ArrayList<>();
	
	private List<String> orgsstr = new ArrayList<>();
	private List<OrgEO> orgs = new ArrayList<>();
	
	private String useCorpId;
	private String orgUseName;
	private String rname;
	private String deptId;

	public Integer getValidFlag() {
		return validFlag;
	}

	public void setValidFlag(Integer validFlag) {
		this.validFlag = validFlag;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public String getOperUser() {
		return operUser;
	}

	public void setOperUser(String operUser) {
		this.operUser = operUser;
	}

	public Object getExtInfo() {
		return extInfo;
	}

	public void setExtInfo(String extInfo) {
		this.extInfo = extInfo;
	}

	public void setExtInfo(Object extInfo) {
		this.extInfo = extInfo;
	}

	public String getWorkNum() {
		return workNum;
	}

	public void setWorkNum(String workNum) {
		this.workNum = workNum;
	}

	public List<String> getRolesstr() {
		return rolesstr;
	}

	public void setRolesstr(List<String> rolesstr) {
		this.rolesstr = rolesstr;
	}

	public List<String> getOrgsstr() {
		return orgsstr;
	}

	public void setOrgsstr(List<String> orgsstr) {
		this.orgsstr = orgsstr;
	}

	public List<OrgEO> getOrgs() {
		return orgs;
	}

	public void setOrgs(List<OrgEO> orgs) {
		this.orgs = orgs;
	}

	public String getUseCorpId() {
		return useCorpId;
	}

	public void setUseCorpId(String useCorpId) {
		this.useCorpId = useCorpId;
	}

	public String getOrgUseName() {
		return orgUseName;
	}

	public void setOrgUseName(String orgUseName) {
		this.orgUseName = orgUseName;
	}


	public String getRname() {
		return rname;
	}

	public void setRname(String rname) {
		this.rname = rname;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getUserSource() {
		return userSource;
	}

	public void setUserSource(String userSource) {
		this.userSource = userSource;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getUsid() {
		return usid;
	}

	public void setUsid(String usid) {
		this.usid = usid;
	}

	public List<RoleEO> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleEO> roles) {
		this.roles = roles;
		if(roles!=null &&roles.size()>0){
			List<String> roleNameList=new ArrayList<String>();
			for(RoleEO role:roles){
				roleNameList.add(role.getName());
			}
			this.rolesstr=roleNameList;
		}
	}


}
