package com.adc.da.sys.vo;

import java.util.ArrayList;
import java.util.List;

import com.adc.da.sys.entity.OrgEO;
import com.adc.da.sys.entity.RoleEO;

public class UserVO {
  
	private String usid;
	private String account;
	private String usname;
	private String password;
	// 扩展字段
    private String extInfo;
    private String email;
    private String updateTime;
    private String workNum;
	private List<String> rolesstr = new ArrayList<>();
	private List<RoleEO> roles = new ArrayList<>();
	
	private List<String> orgsstr = new ArrayList<>();
	private List<OrgEO> orgs = new ArrayList<>();
	
	private String useCorpId;
	private String orgUseName;
	private String rname;
	private String deptId;
	
	public String getUsid() {
		return usid;
	}

	public void setUsid(String usid) {
		this.usid = usid;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getUsname() {
		return usname;
	}

	public void setUsname(String usname) {
		this.usname = usname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getExtInfo() {
		return extInfo;
	}

	public void setExtInfo(String extInfo) {
		this.extInfo = extInfo;
	}

	public List<String> getRolesstr() {
		return rolesstr;
	}

	public void setRolesstr(List<String> rolesstr) {
		this.rolesstr = rolesstr;
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

	public String getWorkNum() {
		return workNum;
	}

	public void setWorkNum(String workNum) {
		this.workNum = workNum;
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
	
}
