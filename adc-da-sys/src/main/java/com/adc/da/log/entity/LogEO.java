package com.adc.da.log.entity;

import com.adc.da.base.entity.BaseEntity;

import java.util.Date;

/**
 * <b>功能：</b>TS_LOG LogEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2017-12-24 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class LogEO extends BaseEntity {

    private String ipAddress;   //ip地址
    private String browser;     //浏览器
    @org.springframework.format.annotation.DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date startTime;     //日志生成时间
    @org.springframework.format.annotation.DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date endTime;       //日志结束时间（意义不明，数值与日志生成时间相同）
    private String usid;        //操作用户的ID
    @org.springframework.format.annotation.DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date operateTime;   //默认为空，意义不明
    private String method;      //日志涉及的操作方法
    private String description; //默认为空
    private String className;   //日志涉及的类名
    private String account;     //操作用户的账号
    private String id;          //日志ID

/**
 * 以下代码没有使用
 */
    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>ipAddress -> ip_address</li>
     * <li>browser -> browser</li>
     * <li>startTime -> start_time</li>
     * <li>endTime -> end_time</li>
     * <li>usid -> usid</li>
     * <li>operateTime -> operate_time</li>
     * <li>method -> method</li>
     * <li>description -> description</li>
     * <li>className -> class_name</li>
     * <li>account -> account</li>
     * <li>id -> id</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) return null;
        switch (fieldName) {
            case "ipAddress": return "ip_address";
            case "browser": return "browser";
            case "startTime": return "start_time";
            case "endTime": return "end_time";
            case "usid": return "usid";
            case "operateTime": return "operate_time";
            case "method": return "method";
            case "description": return "description";
            case "className": return "class_name";
            case "account": return "account";
            case "id": return "id";
            default: return null;
        }
    }

    /**
     * 原始数据库列名转换为java字段名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>ip_address -> ipAddress</li>
     * <li>browser -> browser</li>
     * <li>start_time -> startTime</li>
     * <li>end_time -> endTime</li>
     * <li>usid -> usid</li>
     * <li>operate_time -> operateTime</li>
     * <li>method -> method</li>
     * <li>description -> description</li>
     * <li>class_name -> className</li>
     * <li>account -> account</li>
     * <li>id -> id</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) return null;
        switch (columnName) {
            case "ip_address": return "ipAddress";
            case "browser": return "browser";
            case "start_time": return "startTime";
            case "end_time": return "endTime";
            case "usid": return "usid";
            case "operate_time": return "operateTime";
            case "method": return "method";
            case "description": return "description";
            case "class_name": return "className";
            case "account": return "account";
            case "id": return "id";
            default: return null;
        }
    }

    /**
     * 以上代码没有使用
     */

    /**  **/
    public String getIpAddress() {
        return this.ipAddress;
    }

    /**  **/
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    /**  **/
    public String getBrowser() {
        return this.browser;
    }

    /**  **/
    public void setBrowser(String browser) {
        this.browser = browser;
    }

    /**  **/
    public Date getStartTime() {
        return this.startTime;
    }

    /**  **/
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**  **/
    public Date getEndTime() {
        return this.endTime;
    }

    /**  **/
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**  **/
    public String getUsid() {
        return this.usid;
    }

    /**  **/
    public void setUsid(String usid) {
        this.usid = usid;
    }

    /**  **/
    public Date getOperateTime() {
        return this.operateTime;
    }

    /**  **/
    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    /**  **/
    public String getMethod() {
        return this.method;
    }

    /**  **/
    public void setMethod(String method) {
        this.method = method;
    }

    /**  **/
    public String getDescription() {
        return this.description;
    }

    /**  **/
    public void setDescription(String description) {
        this.description = description;
    }

    /**  **/
    public String getClassName() {
        return this.className;
    }

    /**  **/
    public void setClassName(String className) {
        this.className = className;
    }

    /**  **/
    public String getAccount() {
        return this.account;
    }

    /**  **/
    public void setAccount(String account) {
        this.account = account;
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
