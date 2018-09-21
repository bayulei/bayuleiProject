package com.adc.da.lawss.entity;

import com.adc.da.base.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * <b>功能：</b>SAR_MENU SarMenuEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-03 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class SarMenuEO extends BaseEntity {

    private String parentId;
    private String menuName;
    private String sorDivide;
    private String id;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date creationTime;
    private Integer validFlag;
    private Integer displaySeq;
    private String parentIds;

    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>parentId -> parent_id</li>
     * <li>menuName -> menu_name</li>
     * <li>sorDivide -> sor_divide</li>
     * <li>id -> id</li>
     * <li>modifyTime -> modify_time</li>
     * <li>creationTime -> creation_time</li>
     * <li>validFlag -> valid_flag</li>
     * <li>displaySeq -> display_seq</li>
     * <li>parentIds -> parent_ids</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) return null;
        switch (fieldName) {
            case "parentId": return "parent_id";
            case "menuName": return "menu_name";
            case "sorDivide": return "sor_divide";
            case "id": return "id";
            case "modifyTime": return "modify_time";
            case "creationTime": return "creation_time";
            case "validFlag": return "valid_flag";
            case "displaySeq": return "display_seq";
            case "parentIds": return "parent_ids";
            default: return null;
        }
    }

    /**
     * 原始数据库列名转换为java字段名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>parent_id -> parentId</li>
     * <li>menu_name -> menuName</li>
     * <li>sor_divide -> sorDivide</li>
     * <li>id -> id</li>
     * <li>modify_time -> modifyTime</li>
     * <li>creation_time -> creationTime</li>
     * <li>valid_flag -> validFlag</li>
     * <li>display_seq -> displaySeq</li>
     * <li>parent_ids -> parentIds</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) return null;
        switch (columnName) {
            case "parent_id": return "parentId";
            case "menu_name": return "menuName";
            case "sor_divide": return "sorDivide";
            case "id": return "id";
            case "modify_time": return "modifyTime";
            case "creation_time": return "creationTime";
            case "valid_flag": return "validFlag";
            case "display_seq": return "displaySeq";
            case "parent_ids": return "parentIds";
            default: return null;
        }
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
    public String getMenuName() {
        return this.menuName;
    }

    /**  **/
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    /**  **/
    public String getSorDivide() {
        return this.sorDivide;
    }

    /**  **/
    public void setSorDivide(String sorDivide) {
        this.sorDivide = sorDivide;
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
    public Integer getDisplaySeq() {
        return this.displaySeq;
    }

    /**  **/
    public void setDisplaySeq(Integer displaySeq) {
        this.displaySeq = displaySeq;
    }

    /**  **/
    public String getParentIds() {
        return this.parentIds;
    }

    /**  **/
    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

}
