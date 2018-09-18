package com.adc.da.sys.entity;

import com.adc.da.base.entity.BaseEntity;


/**
 * <b>功能：</b>TS_WARN_TIME WarnTimeEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-17 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class WarnTimeEO extends BaseEntity {

    private String id;
    private String warnType;

    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>id -> id</li>
     * <li>warnType -> warn_type</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) return null;
        switch (fieldName) {
            case "id": return "id";
            case "warnType": return "warn_type";
            default: return null;
        }
    }

    /**
     * 原始数据库列名转换为java字段名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>id -> id</li>
     * <li>warn_type -> warnType</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) return null;
        switch (columnName) {
            case "id": return "id";
            case "warn_type": return "warnType";
            default: return null;
        }
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
    public String getWarnType() {
        return this.warnType;
    }

    /**  **/
    public void setWarnType(String warnType) {
        this.warnType = warnType;
    }

}
