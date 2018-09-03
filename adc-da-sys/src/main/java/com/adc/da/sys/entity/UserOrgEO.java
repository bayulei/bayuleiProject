package com.adc.da.sys.entity;

import com.adc.da.base.entity.BaseEntity;


/**
 * <b>功能：</b>TS_USER_ORG UserOrgEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-03 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class UserOrgEO extends BaseEntity {

    private String orgId;
    private String userId;

    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>orgId -> org_id</li>
     * <li>userId -> user_id</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) return null;
        switch (fieldName) {
            case "orgId": return "org_id";
            case "userId": return "user_id";
            default: return null;
        }
    }

    /**
     * 原始数据库列名转换为java字段名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>org_id -> orgId</li>
     * <li>user_id -> userId</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) return null;
        switch (columnName) {
            case "org_id": return "orgId";
            case "user_id": return "userId";
            default: return null;
        }
    }
    
    /**  **/
    public String getOrgId() {
        return this.orgId;
    }

    /**  **/
    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    /**  **/
    public String getUserId() {
        return this.userId;
    }

    /**  **/
    public void setUserId(String userId) {
        this.userId = userId;
    }

}
