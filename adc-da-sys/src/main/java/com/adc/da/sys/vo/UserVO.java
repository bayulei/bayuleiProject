package com.adc.da.sys.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.adc.da.sys.entity.OrgEO;
import com.adc.da.sys.entity.RoleEO;
import com.fasterxml.jackson.annotation.JsonFormat;

public class UserVO {

	private Integer validFlag;
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date modifyTime;
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date creationTime;
	private String operUser;
	private String extInfo;
	private String workNum;
	private String email;
	private String userSource;
	private String uname;
	private String password;
	private String account;
	private String usid;
	// 扩展字段
	private List<String> rolesstr = new ArrayList<>();
	private List<RoleEO> roles = new ArrayList<>();

	private List<String> orgsstr = new ArrayList<>();
	private List<OrgEO> orgs = new ArrayList<>();
	private String orgId;
	private String orgName;
	private String rname;
	private String roleId;
	private String roleName;
	private Integer disableFlag;

	private Integer unlockFlag;

	private String userType;

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

	public String getExtInfo() {
		return extInfo;
	}

	public void setExtInfo(String extInfo) {
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

	public String getRname() {
		return rname;
	}

	public void setRname(String rname) {
		this.rname = rname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public void setDisableFlag(Integer disableFlag){
		this.disableFlag=disableFlag;
	}

	public Integer getDisableFlag(){
		return this.disableFlag;
	}

	public void setUnlockFlag(Integer unlockFlag){
		this.unlockFlag=unlockFlag;
	}

	public Integer getUnlockFlag(){
		return this.unlockFlag;
	}

	public void setUserType(String userType){
		this.userType=userType;
	}

	public String getUserType(){
		return this.userType;
	}

	public void setOrgId(String orgId){
		this.orgId=orgId;
	}

	public String getOrgId(){
		return this.orgId;
	}

	public void setOrgName(String orgName){
		this.orgName=orgName;
	}

	public String getOrgName(){
		return this.orgName;
	}

	public void setRoleId(String roleId){ this.roleId=roleId;}

	public String getRoleId(){return this.roleId;}

	public String getRoleName(){return this.roleName;}

	public void setRoleName(String roleName){this.roleName=roleName;}

}
