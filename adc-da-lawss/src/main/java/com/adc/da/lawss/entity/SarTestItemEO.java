package com.adc.da.lawss.entity;

import com.adc.da.base.entity.BaseEntity;

import java.util.Date;

/**
 * <b>功能：</b>SAR_TEST_ITEM SarTestItemEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-03 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class SarTestItemEO extends BaseEntity {

    @org.springframework.format.annotation.DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;
    @org.springframework.format.annotation.DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date creationTime;
    private String creationUser;
    private String energyKind;
    private String applyArctic;
    private String standName;
    private String standCode;
    private String authType;
    private String testItemName;
    private String testObj;
    private String testItemCode;
    private String id;

    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>modifyTime -> modify_time</li>
     * <li>creationTime -> creation_time</li>
     * <li>creationUser -> creation_user</li>
     * <li>energyKind -> energy_kind</li>
     * <li>applyArctic -> apply_arctic</li>
     * <li>standName -> stand_name</li>
     * <li>standCode -> stand_code</li>
     * <li>authType -> auth_type</li>
     * <li>testItemName -> test_item_name</li>
     * <li>testObj -> test_obj</li>
     * <li>testItemCode -> test_item_code</li>
     * <li>id -> id</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) return null;
        switch (fieldName) {
            case "modifyTime": return "modify_time";
            case "creationTime": return "creation_time";
            case "creationUser": return "creation_user";
            case "energyKind": return "energy_kind";
            case "applyArctic": return "apply_arctic";
            case "standName": return "stand_name";
            case "standCode": return "stand_code";
            case "authType": return "auth_type";
            case "testItemName": return "test_item_name";
            case "testObj": return "test_obj";
            case "testItemCode": return "test_item_code";
            case "id": return "id";
            default: return null;
        }
    }

    /**
     * 原始数据库列名转换为java字段名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>modify_time -> modifyTime</li>
     * <li>creation_time -> creationTime</li>
     * <li>creation_user -> creationUser</li>
     * <li>energy_kind -> energyKind</li>
     * <li>apply_arctic -> applyArctic</li>
     * <li>stand_name -> standName</li>
     * <li>stand_code -> standCode</li>
     * <li>auth_type -> authType</li>
     * <li>test_item_name -> testItemName</li>
     * <li>test_obj -> testObj</li>
     * <li>test_item_code -> testItemCode</li>
     * <li>id -> id</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) return null;
        switch (columnName) {
            case "modify_time": return "modifyTime";
            case "creation_time": return "creationTime";
            case "creation_user": return "creationUser";
            case "energy_kind": return "energyKind";
            case "apply_arctic": return "applyArctic";
            case "stand_name": return "standName";
            case "stand_code": return "standCode";
            case "auth_type": return "authType";
            case "test_item_name": return "testItemName";
            case "test_obj": return "testObj";
            case "test_item_code": return "testItemCode";
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
    public String getCreationUser() {
        return this.creationUser;
    }

    /**  **/
    public void setCreationUser(String creationUser) {
        this.creationUser = creationUser;
    }

    /**  **/
    public String getEnergyKind() {
        return this.energyKind;
    }

    /**  **/
    public void setEnergyKind(String energyKind) {
        this.energyKind = energyKind;
    }

    /**  **/
    public String getApplyArctic() {
        return this.applyArctic;
    }

    /**  **/
    public void setApplyArctic(String applyArctic) {
        this.applyArctic = applyArctic;
    }

    /**  **/
    public String getStandName() {
        return this.standName;
    }

    /**  **/
    public void setStandName(String standName) {
        this.standName = standName;
    }

    /**  **/
    public String getStandCode() {
        return this.standCode;
    }

    /**  **/
    public void setStandCode(String standCode) {
        this.standCode = standCode;
    }

    /**  **/
    public String getAuthType() {
        return this.authType;
    }

    /**  **/
    public void setAuthType(String authType) {
        this.authType = authType;
    }

    /**  **/
    public String getTestItemName() {
        return this.testItemName;
    }

    /**  **/
    public void setTestItemName(String testItemName) {
        this.testItemName = testItemName;
    }

    /**  **/
    public String getTestObj() {
        return this.testObj;
    }

    /**  **/
    public void setTestObj(String testObj) {
        this.testObj = testObj;
    }

    /**  **/
    public String getTestItemCode() {
        return this.testItemCode;
    }

    /**  **/
    public void setTestItemCode(String testItemCode) {
        this.testItemCode = testItemCode;
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
