package com.adc.da.sys.entity;

import com.adc.da.base.entity.BaseEntity;

public class UserOrgEO extends BaseEntity {

    private String userId;
    private String orgId;

    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>userId -> user_id</li>
     * <li>orgId -> org_id</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) return null;
        switch (fieldName) {
            case "userId": return "user_id";
            case "orgId": return "org_id";
            default: return null;
        }
    }

    /**
     * 原始数据库列名转换为java字段名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>user_id -> userId</li>
     * <li>org_id -> orgId</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) return null;
        switch (columnName) {
            case "user_id": return "userId";
            case "orgId": return "org_id";
            default: return null;
        }
    }

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
}
