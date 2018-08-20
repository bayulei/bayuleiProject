package com.adc.da.sys.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.adc.da.base.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;


/**
 * <b>功能：</b>TS_ROLE RoleEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2017-12-21 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class RoleEO extends BaseEntity implements Serializable{

	private static final long serialVersionUID = -1386892031737294731L;

	private Integer isDefault;
    private Integer delFlag;
    private Integer dataScope;
    private String id;
    private String remarks;
    private String name;
    // 扩展字段
    private String extInfo;
    
    private Integer useFlag;
    private String oprUser;
    private String operUserName;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date insertTime;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    
    private List<MenuEO> menuEOList = new ArrayList<>();
    private List<String> menuEOIdList = new ArrayList<>();

    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>isDefault -> is_default</li>
     * <li>delFlag -> del_flag</li>
     * <li>dataScope -> data_scope</li>
     * <li>id -> id</li>
     * <li>extInfo -> ext_info</li>
     * <li>remarks -> remarks</li>
     * <li>name -> name</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) return null;
        switch (fieldName) {
            case "isDefault": return "is_default";
            case "delFlag": return "del_flag";
            case "dataScope": return "data_scope";
            case "id": return "id";
            case "extInfo": return "ext_info";
            case "remarks": return "remarks";
            case "name": return "name";
            case "useFlag": return "use_flag";
            case "oprUser": return "opr_user";
            case "insertTime": return "insert_time";
            case "updateTime": return "update_time";
            default: return null;
        }
    }

    /**
     * 原始数据库列名转换为java字段名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>is_default -> isDefault</li>
     * <li>del_flag -> delFlag</li>
     * <li>data_scope -> dataScope</li>
     * <li>id -> id</li>
     * <li>ext_info -> extInfo</li>
     * <li>remarks -> remarks</li>
     * <li>name -> name</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) return null;
        switch (columnName) {
            case "is_default": return "isDefault";
            case "del_flag": return "delFlag";
            case "data_scope": return "dataScope";
            case "id": return "id";
            case "ext_info": return "extInfo";
            case "remarks": return "remarks";
            case "name": return "name";
            case "use_flag": return "useFlag";
            case "opr_user": return "oprUser";
            case "insert_time": return "insertTime";
            case "update_time": return "updateTime";
            default: return null;
        }
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
    public Integer getDelFlag() {
        return this.delFlag;
    }

    /**  **/
    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    /**  **/
    public Integer getDataScope() {
        return this.dataScope;
    }

    /**  **/
    public void setDataScope(Integer dataScope) {
        this.dataScope = dataScope;
    }

    /**  **/
    public String getId() {
        return this.id;
    }

    /**  **/
    public void setId(String id) {
        this.id = id;
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
    public String getRemarks() {
        return this.remarks;
    }

    /**  **/
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**  **/
    public String getName() {
        return this.name;
    }

    /**  **/
    public void setName(String name) {
        this.name = name;
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

	public Integer getUseFlag() {
		return useFlag;
	}

	public void setUseFlag(Integer useFlag) {
		this.useFlag = useFlag;
	}

	public String getOprUser() {
		return oprUser;
	}

	public void setOprUser(String oprUser) {
		this.oprUser = oprUser;
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

	public String getOperUserName() {
		return operUserName;
	}

	public void setOperUserName(String operUserName) {
		this.operUserName = operUserName;
	}

	
	
}
