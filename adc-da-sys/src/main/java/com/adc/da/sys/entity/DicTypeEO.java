package com.adc.da.sys.entity;

import com.adc.da.base.entity.BaseEntity;

import java.util.Date;
import java.util.List;


/**
 * <b>功能：</b>TS_DICTYPE DictypeEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-03 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class DicTypeEO extends BaseEntity {

    private Integer validFlag;
    private String parentId;
    private String dicTypeCode;
    private String dicTypeName;
    private String dicId;
    private String id;

//    新增字段
    private String creationTime;


    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>validFlag -> valid_flag</li>
     * <li>parentId -> parent_id</li>
     * <li>dicTypeCode -> dic_type_code</li>
     * <li>dicTypeName -> dic_type_name</li>
     * <li>dicId -> dic_id</li>
     * <li>id -> id</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) return null;
        switch (fieldName) {
            case "validFlag": return "valid_flag";
            case "parentId": return "parent_id";
            case "dicTypeCode": return "dic_type_code";
            case "dicTypeName": return "dic_type_name";
            case "dicId": return "dic_id";
            case "id": return "id";
            default: return null;
        }
    }

    /**
     * 原始数据库列名转换为java字段名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>valid_flag -> validFlag</li>
     * <li>parent_id -> parentId</li>
     * <li>dic_type_code -> dicTypeCode</li>
     * <li>dic_type_name -> dicTypeName</li>
     * <li>dic_id -> dicId</li>
     * <li>id -> id</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) return null;
        switch (columnName) {
            case "valid_flag": return "validFlag";
            case "parent_id": return "parentId";
            case "dic_type_code": return "dicTypeCode";
            case "dic_type_name": return "dicTypeName";
            case "dic_id": return "dicId";
            case "id": return "id";
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
    public String getParentId() {
        return this.parentId;
    }

    /**  **/
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**  **/
    public String getDicTypeCode() {
        return this.dicTypeCode;
    }

    /**  **/
    public void setDicTypeCode(String dicTypeCode) {
        this.dicTypeCode = dicTypeCode;
    }

    /**  **/
    public String getDicTypeName() {
        return this.dicTypeName;
    }

    /**  **/
    public void setDicTypeName(String dicTypeName) {
        this.dicTypeName = dicTypeName;
    }

    /**  **/
    public String getDicId() {
        return this.dicId;
    }

    /**  **/
    public void setDicId(String dicId) {
        this.dicId = dicId;
    }

    /**  **/
    public String getId() {
        return this.id;
    }

    /**  **/
    public void setId(String id) {
        this.id = id;
    }


    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }
}
