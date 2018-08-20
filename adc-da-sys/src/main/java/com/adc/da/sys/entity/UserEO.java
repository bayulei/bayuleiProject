package com.adc.da.sys.entity;

import com.adc.da.base.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * <b>功能：</b>TS_USER UserEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2017-12-18 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class UserEO extends BaseEntity implements Serializable{

	private static final long serialVersionUID = 3658632939727891047L;

	private String usname;
    private String password;
    private Integer delFlag;
    private String account;
    private String usid;
    // 扩展字段
    private String extInfo;
    private String orgUseName;
    private String useCorpId;
    private String deptId;


    private String workNum;
    private String email;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date insertTime;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    private List<String> roleIdList = new ArrayList<>();
    private List<RoleEO> roleEOList = new ArrayList<>();
    
    private List<String> orgIdList = new ArrayList<>();
    private List<OrgEO> orgEOList = new ArrayList<>();
    private String deptName;
    private String roleName;
    private String rname;

    private String type;
    private String corpName;
    private String userRoleName;
    private String roleExtInfo;
    private String corpType;

    public String getCorpName() {
        return corpName;
    }

    public void setCorpName(String corpName) {
        this.corpName = corpName;
    }

    public String getDeptName() {
        String name = "";
        for (OrgEO org : orgEOList){
            name+=org.getName()+",";
        }
        if("".equals(name)){
            return null;
        }else{
            return name.substring(0,name.length()-1);
        }

    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getRoleName() {
        String name = "";
        for (RoleEO role : roleEOList){
            name+=role.getName()+",";
        }
        if("".equals(name)){
            return null;
        }else{
            return name.substring(0,name.length()-1);
        }
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }


    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>extInfo -> ext_info</li>
     * <li>usname -> usname</li>
     * <li>password -> password</li>
     * <li>delFlag -> del_flag</li>
     * <li>account -> account</li>
     * <li>usid -> usid</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) return null;
        switch (fieldName) {
            case "extInfo": return "ext_info";
            case "usname": return "usname";
            case "password": return "password";
            case "delFlag": return "del_flag";
            case "account": return "account";
            case "usid": return "usid";
            case "email": return "email";
            case "workNum": return "work_num";
            case "insertTime": return "insert_time";
            case "updateTime": return "update_time";
            case "userRoleName": return "userRoleName";
            case "roleExtInfo": return "roleExtInfo";
            case "corpType": return "corpType";
            default: return null;
        }
    }

    /**
     * 原始数据库列名转换为java字段名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>ext_info -> extInfo</li>
     * <li>usname -> usname</li>
     * <li>password -> password</li>
     * <li>del_flag -> delFlag</li>
     * <li>account -> account</li>
     * <li>usid -> usid</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) return null;
        switch (columnName) {
            case "ext_info": return "extInfo";
            case "usname": return "usname";
            case "password": return "password";
            case "del_flag": return "delFlag";
            case "account": return "account";
            case "usid": return "usid";
            case "email": return "email";
            case "work_num": return "workNum";
            case "insert_time": return "insertTime";
            case "update_time": return "updateTime";
            case "userRoleName": return "userRoleName";
            case "roleExtInfo": return "roleExtInfo";
            case "corpType": return "corpType";
            default: return null;
        }
    }
    
    /**  **/
    public String getExtInfo() {
        return this.extInfo;
    }

    /**  **/
    public void setExtInfo(String extInfo) {
        this.extInfo = extInfo;
    }

    /**  **/
    public String getUsname() {
        return this.usname;
    }

    /**  **/
    public void setUsname(String usname) {
        this.usname = usname;
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
    public Integer getDelFlag() {
        return this.delFlag;
    }

    /**  **/
    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
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

    public String getOrgUseName() {
        return orgUseName;
    }

    public void setOrgUseName(String orgUseName) {
        this.orgUseName = orgUseName;
    }


    public String getUseCorpId() {
        return useCorpId;
    }

    public void setUseCorpId(String useCorpId) {
        this.useCorpId = useCorpId;
    }
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getWorkNum() {
		return workNum;
	}

	public void setWorkNum(String workNum) {
		this.workNum = workNum;
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
	
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserRoleName() {
        return userRoleName;
    }

    public void setUserRoleName(String userRoleName) {
        this.userRoleName = userRoleName;
    }

    public String getRoleExtInfo() {
        return roleExtInfo;
    }

    public void setRoleExtInfo(String roleExtInfo) {
        this.roleExtInfo = roleExtInfo;
    }

    public String getCorpType() {
        return corpType;
    }

    public void setCorpType(String corpType) {
        this.corpType = corpType;
    }
}
