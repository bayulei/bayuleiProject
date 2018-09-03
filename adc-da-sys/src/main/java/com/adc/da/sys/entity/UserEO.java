package com.adc.da.sys.entity;

import com.adc.da.base.entity.BaseEntity;

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
    
    private List<String> roleIdList = new ArrayList<>();
    private List<RoleEO> roleEOList = new ArrayList<>();
    
    private List<String> orgIdList = new ArrayList<>();
    private List<OrgEO> orgEOList = new ArrayList<>();
    
    
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
    
    /**  **/
    public Integer getValidFlag() {
        return this.validFlag;
    }

    /**  **/
    public void setValidFlag(Integer validFlag) {
        this.validFlag = validFlag;
    }

    /**  **/
    public Date getModifyTime() {
        return this.modifyTime;
    }

    /**  **/
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**  **/
    public Date getCreationTime() {
        return this.creationTime;
    }

    /**  **/
    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    /**  **/
    public String getOperUser() {
        return this.operUser;
    }

    /**  **/
    public void setOperUser(String operUser) {
        this.operUser = operUser;
    }

    /**  **/
    public Object getExtInfo() {
        return this.extInfo;
    }

    /**  **/
    public void setExtInfo(Object extInfo) {
        this.extInfo = extInfo;
    }

    /**  **/
    public String getWorkNum() {
        return this.workNum;
    }

    /**  **/
    public void setWorkNum(String workNum) {
        this.workNum = workNum;
    }

    /**  **/
    public String getEmail() {
        return this.email;
    }

    /**  **/
    public void setEmail(String email) {
        this.email = email;
    }

    /**  **/
    public String getUserSource() {
        return this.userSource;
    }

    /**  **/
    public void setUserSource(String userSource) {
        this.userSource = userSource;
    }

    /**  **/
    public String getUname() {
        return this.uname;
    }

    /**  **/
    public void setUname(String uname) {
        this.uname = uname;
    }

    /**  **/
    public String getPassword() {
        return this.password;
    }

    /**  **/
    public void setPassword(String password) {
        this.password = password;
    }

    /**  **/
    public String getAccount() {
        return this.account;
    }

    /**  **/
    public void setAccount(String account) {
        this.account = account;
    }

    /**  **/
    public String getUsid() {
        return this.usid;
    }

    /**  **/
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

    
    
}
