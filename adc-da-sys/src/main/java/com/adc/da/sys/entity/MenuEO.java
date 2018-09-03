package com.adc.da.sys.entity;

import com.adc.da.base.entity.BaseEntity;
import com.adc.da.base.entity.TreeEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * <b>功能：</b>TS_MENU MenuEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-03 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class MenuEO extends TreeEntity implements Serializable{

    @org.springframework.format.annotation.DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;
    @org.springframework.format.annotation.DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date creationTime;
    private Integer validFlag;
    private Object remarks;
    private String permission;
    private Integer isShow;
    private String icon;
    private String href;
    private String parentIds;
    private String parentId;
    private String name;
    private String id;

    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>modifyTime -> modify_time</li>
     * <li>creationTime -> creation_time</li>
     * <li>validFlag -> valid_flag</li>
     * <li>remarks -> remarks</li>
     * <li>permission -> permission</li>
     * <li>isShow -> is_show</li>
     * <li>icon -> icon</li>
     * <li>href -> href</li>
     * <li>parentIds -> parent_ids</li>
     * <li>parentId -> parent_id</li>
     * <li>name -> name</li>
     * <li>id -> id</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) return null;
        switch (fieldName) {
            case "modifyTime": return "modify_time";
            case "creationTime": return "creation_time";
            case "validFlag": return "valid_flag";
            case "remarks": return "remarks";
            case "permission": return "permission";
            case "isShow": return "is_show";
            case "icon": return "icon";
            case "href": return "href";
            case "parentIds": return "parent_ids";
            case "parentId": return "parent_id";
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
     * <li>remarks -> remarks</li>
     * <li>permission -> permission</li>
     * <li>is_show -> isShow</li>
     * <li>icon -> icon</li>
     * <li>href -> href</li>
     * <li>parent_ids -> parentIds</li>
     * <li>parent_id -> parentId</li>
     * <li>name -> name</li>
     * <li>id -> id</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) return null;
        switch (columnName) {
            case "modify_time": return "modifyTime";
            case "creation_time": return "creationTime";
            case "valid_flag": return "validFlag";
            case "remarks": return "remarks";
            case "permission": return "permission";
            case "is_show": return "isShow";
            case "icon": return "icon";
            case "href": return "href";
            case "parent_ids": return "parentIds";
            case "parent_id": return "parentId";
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
    public Object getRemarks() {
        return this.remarks;
    }

    /**  **/
    public void setRemarks(Object remarks) {
        this.remarks = remarks;
    }

    /**  **/
    public String getPermission() {
        return this.permission;
    }

    /**  **/
    public void setPermission(String permission) {
        this.permission = permission;
    }

    /**  **/
    public Integer getIsShow() {
        return this.isShow;
    }

    /**  **/
    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    /**  **/
    public String getIcon() {
        return this.icon;
    }

    /**  **/
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**  **/
    public String getHref() {
        return this.href;
    }

    /**  **/
    public void setHref(String href) {
        this.href = href;
    }

    /**  **/
    public String getParentIds() {
        return this.parentIds;
    }

    /**  **/
    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    /**  **/
    public String getParentId() {
        return this.parentId;
    }

    /**  **/
    public void setParentId(String parentId) {
        this.parentId = parentId;
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

}
