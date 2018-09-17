package com.adc.da.sys.page;

import com.adc.da.base.page.BasePage;


/**
 * <b>功能：</b>TS_USER UserEOPage<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2017-11-06 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class UserEOPage extends BasePage {

    private String usid;
    private String usidOperator = "=";
    private String account;
    private String accountOperator = "=";
    private String validFlag;
    private String validFlagOperator = "=";
    private String password;
    private String passwordOperator = "=";
    private String uname;
    private String nameOperator = "=";
    private String roleName;
    private String roleNameOperator = "=";
    private String roleId;
    private String roleOperator = "=";
    private String orgName;
    private String orgNameOperator = "=";
    private String workNum;
    private String workNumOperator ="=";

    private String orgId;
    
    private String disableFlag;

    private String disableFlagOperator = "=";
    private String unlockFlag;
    private String unlockFLagOperator = "=";

    private String userType;

    private String userTypeOperator = "=";

    public String getUsid() {
        return this.usid;
    }

	public void setUsid(String usid) {
        this.usid = usid;
    }

    public String getUsidOperator() {
        return this.usidOperator;
    }

    public void setUsidOperator(String usidOperator) {
        this.usidOperator = usidOperator;
    }

    public String getAccount() {
        return this.account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAccountOperator() {
        return this.accountOperator;
    }

    public void setAccountOperator(String accountOperator) {
        this.accountOperator = accountOperator;
    }

    public String getValidFlag() {
        return validFlag;
    }

    public void setValidFlag(String validFlag) {
        this.validFlag = validFlag;
    }

    public String getValidFlagOperator() {
        return validFlagOperator;
    }

    public void setValidFlagOperator(String validFlagOperator) {
        this.validFlagOperator = validFlagOperator;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordOperator() {
        return this.passwordOperator;
    }

    public void setPasswordOperator(String passwordOperator) {
        this.passwordOperator = passwordOperator;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getNameOperator() {
        return this.nameOperator;
    }

    public void setNameOperator(String nameOperator) {
        this.nameOperator = nameOperator;
    }

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgNameOperator() {
		return orgNameOperator;
	}

	public void setOrgNameOperator(String orgNameOperator) {
		this.orgNameOperator = orgNameOperator;
	}

	public String getWorkNum() {
		return workNum;
	}

	public void setWorkNum(String workNum) {
		this.workNum = workNum;
	}

	public String getWorkNumOperator() {
		return workNumOperator;
	}

	public void setWorkNumOperator(String workNumOperator) {
		this.workNumOperator = workNumOperator;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleOperator() {
		return roleOperator;
	}

	public void setRoleOperator(String roleOperator) {
		this.roleOperator = roleOperator;
	}

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public void setDisableFlag(String disableFlag){
        this.disableFlag=disableFlag;
    }

    public String getDisableFlag(){
        return this.disableFlag;
    }

    public void setDisableFlagOperator(String disableFlagOperator){
        this.disableFlagOperator=disableFlagOperator;
    }

    public String getDisableFlagOperator(){
        return this.disableFlagOperator;
    }

    public void setUnlockFlag(String unlockFlag){
        this.unlockFlag = unlockFlag;
    }

    public String getUnlockFlag(){
        return this.unlockFlag;
    }

    public void setUnlockFLagOperator(String unlockFLagOperator){
        this.unlockFLagOperator =unlockFLagOperator;
    }

    public String getUnlockFLagOperator(){
        return this.unlockFLagOperator;
    }

    public void setUserType(String userType){
        this.userType=userType;
    }
    public String getUserType(){
        return this.userType;
    }

    public String getUserTypeOperator(){
        return this.userTypeOperator;
    }
    public void setUserTypeOperator(String userTypeOperator){
        this.userTypeOperator=userTypeOperator;
    }

    public void setRoleNameOperator(String roleNameOperator){
        this.roleNameOperator=roleNameOperator;
    }
    public String getRoleNameOperator(){
        return this.roleNameOperator;
    }

    public void setOrgId(String orgId){
        this.orgId=orgId;
    }

    public String getOrgId(){
        return this.orgId;
    }
}
