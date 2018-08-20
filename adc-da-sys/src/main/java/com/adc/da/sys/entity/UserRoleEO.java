package com.adc.da.sys.entity;

import com.adc.da.base.entity.BaseEntity;


/**
 * <b>功能：</b>TR_USER_ROLE UserRoleEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2017-11-07 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class UserRoleEO extends BaseEntity {

    private String userId;
    private String roleId;

    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>userId -> user_id</li>
     * <li>roleId -> role_id</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) return null;
        switch (fieldName) {
            case "userId": return "user_id";
            case "roleId": return "role_id";
            default: return null;
        }
    }

    /**
     * 原始数据库列名转换为java字段名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>user_id -> userId</li>
     * <li>role_id -> roleId</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) return null;
        switch (columnName) {
            case "user_id": return "userId";
            case "role_id": return "roleId";
            default: return null;
        }
    }

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
}
