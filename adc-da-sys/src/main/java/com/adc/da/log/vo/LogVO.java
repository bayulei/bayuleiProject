package com.adc.da.log.vo;

import com.adc.da.base.entity.BaseEntity;

/**
 * <b>功能：</b>TS_LOG LogEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2017-12-24 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class LogVO extends BaseEntity {


    private String ipAddress;   //ip地址
    private String browser;     //浏览器

    private String startTime;     //日志生成时间

    private String endTime;       //日志结束时间（意义不明，数值与日志生成时间相同）
    private String usid;        //操作用户的ID

    private String operateTime;   //默认为空，意义不明
    private String method;      //日志涉及的操作方法
    private String description; //默认为空
    private String className;   //日志涉及的类名
    private String account;     //操作用户的账号
    private String id;          //日志ID


    
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
    public String getStartTime() {
        return this.startTime;
    }

    /**  **/
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**  **/
    public String getEndTime() {
        return this.endTime;
    }

    /**  **/
    public void setEndTime(String endTime) {
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
    public String getOperateTime() {
        return this.operateTime;
    }

    /**  **/
    public void setOperateTime(String operateTime) {
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
