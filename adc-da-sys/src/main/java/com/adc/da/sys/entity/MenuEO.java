package com.adc.da.sys.entity;

import java.io.Serializable;

import com.adc.da.base.entity.TreeEntity;


/**
 * <b>功能：</b>TS_MENU MenuEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2017-12-21 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class MenuEO extends TreeEntity implements Serializable{

	private static final long serialVersionUID = 2497292638985614077L;

	private String href;
    private String icon;
    private String sort;
    private Integer isShow;
    private String permission;
    private String remarks;

    // 扩展字段
    private String extInfo;
    private String checked;

    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>extInfo -> ext_info</li>
     * <li>sort -> sort</li>
     * <li>remarks -> remarks</li>
     * <li>permission -> permission</li>
     * <li>isShow -> is_show</li>
     * <li>icon -> icon</li>
     * <li>href -> href</li>
     * <li>parentIds -> parent_ids</li>
     * <li>parentId -> parent_id</li>
     * <li>name -> name</li>
     * <li>delFlag -> del_flag</li>
     * <li>id -> id</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) return null;
        switch (fieldName) {
            case "extInfo": return "ext_info";
            case "sort": return "sort";
            case "remarks": return "remarks";
            case "permission": return "permission";
            case "isShow": return "is_show";
            case "icon": return "icon";
            case "href": return "href";
            case "parentIds": return "parent_ids";
            case "parentId": return "parent_id";
            case "name": return "name";
            case "delFlag": return "del_flag";
            case "id": return "id";
            default: return null;
        }
    }

    /**
     * 原始数据库列名转换为java字段名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>ext_info -> extInfo</li>
     * <li>sort -> sort</li>
     * <li>remarks -> remarks</li>
     * <li>permission -> permission</li>
     * <li>is_show -> isShow</li>
     * <li>icon -> icon</li>
     * <li>href -> href</li>
     * <li>parent_ids -> parentIds</li>
     * <li>parent_id -> parentId</li>
     * <li>name -> name</li>
     * <li>del_flag -> delFlag</li>
     * <li>id -> id</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) return null;
        switch (columnName) {
            case "ext_info": return "extInfo";
            case "sort": return "sort";
            case "remarks": return "remarks";
            case "permission": return "permission";
            case "is_show": return "isShow";
            case "icon": return "icon";
            case "href": return "href";
            case "parent_ids": return "parentIds";
            case "parent_id": return "parentId";
            case "name": return "name";
            case "del_flag": return "delFlag";
            case "id": return "id";
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
    public String getSort() {
        return this.sort;
    }

    /**  **/
    public void setSort(String sort) {
        this.sort = sort;
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
    public Integer getDelFlag() {
        return this.delFlag;
    }

    /**  **/
    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    /**  **/
    public String getId() {
        return this.id;
    }

    /**  **/
    public void setId(String id) {
        this.id = id;
    }

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

}
