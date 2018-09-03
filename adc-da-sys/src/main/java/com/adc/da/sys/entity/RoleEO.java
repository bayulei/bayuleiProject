package com.adc.da.sys.entity;

import com.adc.da.base.entity.BaseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <b>功能：</b>TS_ROLE RoleEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-03 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class RoleEO extends BaseEntity {

    @org.springframework.format.annotation.DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;
    @org.springframework.format.annotation.DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date creationTime;
    private Integer validFlag;
    private String operUser;
    private Object extInfo;
    private String remarks;
    private Integer isDefault;
    private Integer useFlag;
    private String roleType;
    private String name;
    private String id;

    private List<MenuEO> menuEOList = new ArrayList<>();
    private List<String> menuEOIdList = new ArrayList<>();
    
    
    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>modifyTime -> modify_time</li>
     * <li>creationTime -> creation_time</li>
     * <li>validFlag -> valid_flag</li>
     * <li>operUser -> oper_user</li>
     * <li>extInfo -> ext_info</li>
     * <li>remarks -> remarks</li>
     * <li>isDefault -> is_default</li>
     * <li>useFlag -> use_flag</li>
     * <li>roleType -> role_type</li>
     * <li>name -> name</li>
     * <li>id -> id</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) return null;
        switch (fieldName) {
            case "modifyTime": return "modify_time";
            case "creationTime": return "creation_time";
            case "validFlag": return "valid_flag";
            case "operUser": return "oper_user";
            case "extInfo": return "ext_info";
            case "remarks": return "remarks";
            case "isDefault": return "is_default";
            case "useFlag": return "use_flag";
            case "roleType": return "role_type";
            case "name": return "name";
            case "id": return "id";
            default: return null;
        }
    }

    /**
     * 原始数据库列名转换为java字段名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>modify_time -> modifyTime</li>
     * <li>creation_time -> creationTime</li>
     * <li>valid_flag -> validFlag</li>
     * <li>oper_user -> operUser</li>
     * <li>ext_info -> extInfo</li>
     * <li>remarks -> remarks</li>
     * <li>is_default -> isDefault</li>
     * <li>use_flag -> useFlag</li>
     * <li>role_type -> roleType</li>
     * <li>name -> name</li>
     * <li>id -> id</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) return null;
        switch (columnName) {
            case "modify_time": return "modifyTime";
            case "creation_time": return "creationTime";
            case "valid_flag": return "validFlag";
            case "oper_user": return "operUser";
            case "ext_info": return "extInfo";
            case "remarks": return "remarks";
            case "is_default": return "isDefault";
            case "use_flag": return "useFlag";
            case "role_type": return "roleType";
            case "name": return "name";
            case "id": return "id";
            default: return null;
        }
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
    public Integer getValidFlag() {
        return this.validFlag;
    }

    /**  **/
    public void setValidFlag(Integer validFlag) {
        this.validFlag = validFlag;
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
    public String getRemarks() {
        return this.remarks;
    }

    /**  **/
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**  **/
    public Integer getIsDefault() {
        return this.isDefault;
    }

    /**  **/
    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }

    /**  **/
    public Integer getUseFlag() {
        return this.useFlag;
    }

    /**  **/
    public void setUseFlag(Integer useFlag) {
        this.useFlag = useFlag;
    }

    /**  **/
    public String getRoleType() {
        return this.roleType;
    }

    /**  **/
    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    /**  **/
    public String getName() {
        return this.name;
    }

    /**  **/
    public void setName(String name) {
        this.name = name;
    }

    /**  **/
    public String getId() {
        return this.id;
    }

    /**  **/
    public void setId(String id) {
        this.id = id;
    }

	public List<MenuEO> getMenuEOList() {
		return menuEOList;
	}

	public void setMenuEOList(List<MenuEO> menuEOList) {
		this.menuEOList = menuEOList;
	}

	public List<String> getMenuEOIdList() {
		return menuEOIdList;
	}

	public void setMenuEOIdList(List<String> menuEOIdList) {
		this.menuEOIdList = menuEOIdList;
	}

    
    
    
}
