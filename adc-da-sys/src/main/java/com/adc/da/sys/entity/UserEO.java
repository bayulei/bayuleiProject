package com.adc.da.sys.entity;

import com.adc.da.base.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <b>功能：</b>TS_USER UserEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-03 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class UserEO extends BaseEntity {

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

    private  String userType;
    private String orgId;
    private String orgName;
    private String roleId;
    private String roleName;
    private List<String> roleIdList = new ArrayList<>();
    private List<RoleEO> roleEOList = new ArrayList<>();
    
    private List<String> orgIdList = new ArrayList<>();
    private List<OrgEO> orgEOList = new ArrayList<>();

    private String mobilePhone;
    private String officePhone;

    private int disableFlag;

    private int unlockFlag;
    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>validFlag -> valid_flag</li>
     * <li>modifyTime -> modify_time</li>
     * <li>creationTime -> creation_time</li>
     * <li>operUser -> oper_user</li>
     * <li>extInfo -> ext_info</li>
     * <li>workNum -> work_num</li>
     * <li>email -> email</li>
     * <li>userSource -> user_source</li>
     * <li>uname -> uname</li>
     * <li>password -> password</li>
     * <li>account -> account</li>
     * <li>usid -> usid</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) return null;
        switch (fieldName) {
            case "validFlag": return "valid_flag";
            case "modifyTime": return "modify_time";
            case "creationTime": return "creation_time";
            case "operUser": return "oper_user";
            case "extInfo": return "ext_info";
            case "workNum": return "work_num";
            case "email": return "email";
            case "userSource": return "user_source";
            case "uname": return "uname";
            case "password": return "password";
            case "account": return "account";
            case "usid": return "usid";
            default: return null;
        }
    }

    /**
     * 原始数据库列名转换为java字段名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>valid_flag -> validFlag</li>
     * <li>modify_time -> modifyTime</li>
     * <li>creation_time -> creationTime</li>
     * <li>oper_user -> operUser</li>
     * <li>ext_info -> extInfo</li>
     * <li>work_num -> workNum</li>
     * <li>email -> email</li>
     * <li>user_source -> userSource</li>
     * <li>uname -> uname</li>
     * <li>password -> password</li>
     * <li>account -> account</li>
     * <li>usid -> usid</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) return null;
        switch (columnName) {
            case "valid_flag": return "validFlag";
            case "modify_time": return "modifyTime";
            case "creation_time": return "creationTime";
            case "oper_user": return "operUser";
            case "ext_info": return "extInfo";
            case "work_num": return "workNum";
            case "email": return "email";
            case "user_source": return "userSource";
            case "uname": return "uname";
            case "password": return "password";
            case "account": return "account";
            case "usid": return "usid";
            default: return null;
        }
    }

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

    public List<String> getRoleIdList() {
        return roleIdList;
    }

    public void setRoleIdList(List<String> roleIdList) {
        this.roleIdList = roleIdList;
    }

    public List<RoleEO> getRoleEOList() {
        return roleEOList;
    }

    public void setRoleEOList(List<RoleEO> roleEOList) {
        this.roleEOList = roleEOList;
    }

    public List<String> getOrgIdList() {
        return orgIdList;
    }

    public void setOrgIdList(List<String> orgIdList) {
        this.orgIdList = orgIdList;
    }

    public List<OrgEO> getOrgEOList() {
        return orgEOList;
    }

    public void setOrgEOList(List<OrgEO> orgEOList) {
        this.orgEOList = orgEOList;
    }

    public void setUserType(String userType){
        this.userType=userType;
    }

    public String getUserType(){
        return this.userType;
    }

    public void setOrgName(String orgName){
        this.orgName=orgName;
    }

    public String getOrgName(){
        return this.orgName;
    }

    public void setOrgId(String orgId){
        this.orgId=orgId;
    }

    public String getOrgId(){
        return orgId;
    }

    public void setRoleId(String roleId){
        this.roleId=roleId;
    }

    public String getRoleId(){
        return this.roleId;
    }

    public void setMobilePhone(String mobilePhone){
        this.mobilePhone=mobilePhone;
    }

    public String getMobilePhone(){
        return this.mobilePhone;
    }

    public void setOfficePhone(String officePhone){
        this.officePhone=officePhone;
    }
    public String getOfficePhone(){
        return this.officePhone;
    }

    public void setDisableFlag(int disableFlag){
        this.disableFlag=disableFlag;
    }

    public int getDisableFlag(){
        return this.disableFlag;
    }

    public void setUnlockFlag(int unlockFlag){
        this.unlockFlag=unlockFlag;
    }

    public int getUnlockFlag(){
        return this.unlockFlag;
    }

    public void setRoleName(String roleName){
        this.roleName=roleName;
    }

    public String getRoleName(){
        return this.roleName;
    }

}
