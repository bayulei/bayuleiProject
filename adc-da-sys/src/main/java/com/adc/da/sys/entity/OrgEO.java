package com.adc.da.sys.entity;

import com.adc.da.base.entity.BaseEntity;

import java.util.Date;

/**
 * <b>功能：</b>TS_ORG OrgEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-03 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class OrgEO extends BaseEntity {

    @org.springframework.format.annotation.DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;
    @org.springframework.format.annotation.DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date creationTime;
    private Integer validFlag;
    private Integer isShow;
    private String parentIds;
    private String parentId;
    private String shotName;
    private String remarks;
    private Integer orgDesc;
    private String orgType;
    private String orgCode;
    private String orgName;
    private String id;

    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>modifyTime -> modify_time</li>
     * <li>creationTime -> creation_time</li>
     * <li>validFlag -> valid_flag</li>
     * <li>isShow -> is_show</li>
     * <li>parentIds -> parent_ids</li>
     * <li>parentId -> parent_id</li>
     * <li>shotName -> shot_name</li>
     * <li>remarks -> remarks</li>
     * <li>orgDesc -> org_desc</li>
     * <li>orgType -> org_type</li>
     * <li>orgCode -> org_code</li>
     * <li>orgName -> org_name</li>
     * <li>id -> id</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) return null;
        switch (fieldName) {
            case "modifyTime": return "modify_time";
            case "creationTime": return "creation_time";
            case "validFlag": return "valid_flag";
            case "isShow": return "is_show";
            case "parentIds": return "parent_ids";
            case "parentId": return "parent_id";
            case "shotName": return "shot_name";
            case "remarks": return "remarks";
            case "orgDesc": return "org_desc";
            case "orgType": return "org_type";
            case "orgCode": return "org_code";
            case "orgName": return "org_name";
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
     * <li>is_show -> isShow</li>
     * <li>parent_ids -> parentIds</li>
     * <li>parent_id -> parentId</li>
     * <li>shot_name -> shotName</li>
     * <li>remarks -> remarks</li>
     * <li>org_desc -> orgDesc</li>
     * <li>org_type -> orgType</li>
     * <li>org_code -> orgCode</li>
     * <li>org_name -> orgName</li>
     * <li>id -> id</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) return null;
        switch (columnName) {
            case "modify_time": return "modifyTime";
            case "creation_time": return "creationTime";
            case "valid_flag": return "validFlag";
            case "is_show": return "isShow";
            case "parent_ids": return "parentIds";
            case "parent_id": return "parentId";
            case "shot_name": return "shotName";
            case "remarks": return "remarks";
            case "org_desc": return "orgDesc";
            case "org_type": return "orgType";
            case "org_code": return "orgCode";
            case "org_name": return "orgName";
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
    public Integer getIsShow() {
        return this.isShow;
    }

    /**  **/
    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
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
    public String getShotName() {
        return this.shotName;
    }

    /**  **/
    public void setShotName(String shotName) {
        this.shotName = shotName;
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
    public Integer getOrgDesc() {
        return this.orgDesc;
    }

    /**  **/
    public void setOrgDesc(Integer orgDesc) {
        this.orgDesc = orgDesc;
    }

    /**  **/
    public String getOrgType() {
        return this.orgType;
    }

    /**  **/
    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    /**  **/
    public String getOrgCode() {
        return this.orgCode;
    }

    /**  **/
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    /**  **/
    public String getOrgName() {
        return this.orgName;
    }

    /**  **/
    public void setOrgName(String orgName) {
        this.orgName = orgName;
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
