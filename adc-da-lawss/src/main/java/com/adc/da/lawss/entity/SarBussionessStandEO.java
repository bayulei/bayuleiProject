package com.adc.da.lawss.entity;

import com.adc.da.base.entity.BaseEntity;

import java.util.Date;

/**
 * <b>功能：</b>SAR_BUSSIONESS_STAND SarBussionessStandEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-03 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class SarBussionessStandEO extends BaseEntity {

    @org.springframework.format.annotation.DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;
    @org.springframework.format.annotation.DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date creationTime;
    private Integer validTime;
    private String putUser;
    private String citationUser;
    private String responsibleUnit;
    private String standFile;
    private String tags;
    private String standStatus;
    private String replacedStandNum;
    private String replaceStandNum;
    private String quoteStand;
    @org.springframework.format.annotation.DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date firstPutTime;
    @org.springframework.format.annotation.DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date putYear;
    @org.springframework.format.annotation.DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date putTime;
    @org.springframework.format.annotation.DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date issueTime;
    private String energyKind;
    private String applyArctic;
    private String standEnName;
    private String standName;
    private String standCode;
    private String classifyCode;
    private String standSubclass;
    private String standGenera;
    private String id;

    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>modifyTime -> modify_time</li>
     * <li>creationTime -> creation_time</li>
     * <li>validTime -> valid_time</li>
     * <li>putUser -> put_user</li>
     * <li>citationUser -> citation_user</li>
     * <li>responsibleUnit -> responsible_unit</li>
     * <li>standFile -> stand_file</li>
     * <li>tags -> tags</li>
     * <li>standStatus -> stand_status</li>
     * <li>replacedStandNum -> replaced_stand_num</li>
     * <li>replaceStandNum -> replace_stand_num</li>
     * <li>quoteStand -> quote_stand</li>
     * <li>firstPutTime -> first_put_time</li>
     * <li>putYear -> put_year</li>
     * <li>putTime -> put_time</li>
     * <li>issueTime -> issue_time</li>
     * <li>energyKind -> energy_kind</li>
     * <li>applyArctic -> apply_arctic</li>
     * <li>standEnName -> stand_en_name</li>
     * <li>standName -> stand_name</li>
     * <li>standCode -> stand_code</li>
     * <li>classifyCode -> classify_code</li>
     * <li>standSubclass -> stand_subclass</li>
     * <li>standGenera -> stand_genera</li>
     * <li>id -> id</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) return null;
        switch (fieldName) {
            case "modifyTime": return "modify_time";
            case "creationTime": return "creation_time";
            case "validTime": return "valid_time";
            case "putUser": return "put_user";
            case "citationUser": return "citation_user";
            case "responsibleUnit": return "responsible_unit";
            case "standFile": return "stand_file";
            case "tags": return "tags";
            case "standStatus": return "stand_status";
            case "replacedStandNum": return "replaced_stand_num";
            case "replaceStandNum": return "replace_stand_num";
            case "quoteStand": return "quote_stand";
            case "firstPutTime": return "first_put_time";
            case "putYear": return "put_year";
            case "putTime": return "put_time";
            case "issueTime": return "issue_time";
            case "energyKind": return "energy_kind";
            case "applyArctic": return "apply_arctic";
            case "standEnName": return "stand_en_name";
            case "standName": return "stand_name";
            case "standCode": return "stand_code";
            case "classifyCode": return "classify_code";
            case "standSubclass": return "stand_subclass";
            case "standGenera": return "stand_genera";
            case "id": return "id";
            default: return null;
        }
    }

    /**
     * 原始数据库列名转换为java字段名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>modify_time -> modifyTime</li>
     * <li>creation_time -> creationTime</li>
     * <li>valid_time -> validTime</li>
     * <li>put_user -> putUser</li>
     * <li>citation_user -> citationUser</li>
     * <li>responsible_unit -> responsibleUnit</li>
     * <li>stand_file -> standFile</li>
     * <li>tags -> tags</li>
     * <li>stand_status -> standStatus</li>
     * <li>replaced_stand_num -> replacedStandNum</li>
     * <li>replace_stand_num -> replaceStandNum</li>
     * <li>quote_stand -> quoteStand</li>
     * <li>first_put_time -> firstPutTime</li>
     * <li>put_year -> putYear</li>
     * <li>put_time -> putTime</li>
     * <li>issue_time -> issueTime</li>
     * <li>energy_kind -> energyKind</li>
     * <li>apply_arctic -> applyArctic</li>
     * <li>stand_en_name -> standEnName</li>
     * <li>stand_name -> standName</li>
     * <li>stand_code -> standCode</li>
     * <li>classify_code -> classifyCode</li>
     * <li>stand_subclass -> standSubclass</li>
     * <li>stand_genera -> standGenera</li>
     * <li>id -> id</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) return null;
        switch (columnName) {
            case "modify_time": return "modifyTime";
            case "creation_time": return "creationTime";
            case "valid_time": return "validTime";
            case "put_user": return "putUser";
            case "citation_user": return "citationUser";
            case "responsible_unit": return "responsibleUnit";
            case "stand_file": return "standFile";
            case "tags": return "tags";
            case "stand_status": return "standStatus";
            case "replaced_stand_num": return "replacedStandNum";
            case "replace_stand_num": return "replaceStandNum";
            case "quote_stand": return "quoteStand";
            case "first_put_time": return "firstPutTime";
            case "put_year": return "putYear";
            case "put_time": return "putTime";
            case "issue_time": return "issueTime";
            case "energy_kind": return "energyKind";
            case "apply_arctic": return "applyArctic";
            case "stand_en_name": return "standEnName";
            case "stand_name": return "standName";
            case "stand_code": return "standCode";
            case "classify_code": return "classifyCode";
            case "stand_subclass": return "standSubclass";
            case "stand_genera": return "standGenera";
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
    public Integer getValidTime() {
        return this.validTime;
    }

    /**  **/
    public void setValidTime(Integer validTime) {
        this.validTime = validTime;
    }

    /**  **/
    public String getPutUser() {
        return this.putUser;
    }

    /**  **/
    public void setPutUser(String putUser) {
        this.putUser = putUser;
    }

    /**  **/
    public String getCitationUser() {
        return this.citationUser;
    }

    /**  **/
    public void setCitationUser(String citationUser) {
        this.citationUser = citationUser;
    }

    /**  **/
    public String getResponsibleUnit() {
        return this.responsibleUnit;
    }

    /**  **/
    public void setResponsibleUnit(String responsibleUnit) {
        this.responsibleUnit = responsibleUnit;
    }

    /**  **/
    public String getStandFile() {
        return this.standFile;
    }

    /**  **/
    public void setStandFile(String standFile) {
        this.standFile = standFile;
    }

    /**  **/
    public String getTags() {
        return this.tags;
    }

    /**  **/
    public void setTags(String tags) {
        this.tags = tags;
    }

    /**  **/
    public String getStandStatus() {
        return this.standStatus;
    }

    /**  **/
    public void setStandStatus(String standStatus) {
        this.standStatus = standStatus;
    }

    /**  **/
    public String getReplacedStandNum() {
        return this.replacedStandNum;
    }

    /**  **/
    public void setReplacedStandNum(String replacedStandNum) {
        this.replacedStandNum = replacedStandNum;
    }

    /**  **/
    public String getReplaceStandNum() {
        return this.replaceStandNum;
    }

    /**  **/
    public void setReplaceStandNum(String replaceStandNum) {
        this.replaceStandNum = replaceStandNum;
    }

    /**  **/
    public String getQuoteStand() {
        return this.quoteStand;
    }

    /**  **/
    public void setQuoteStand(String quoteStand) {
        this.quoteStand = quoteStand;
    }

    /**  **/
    public Date getFirstPutTime() {
        return this.firstPutTime;
    }

    /**  **/
    public void setFirstPutTime(Date firstPutTime) {
        this.firstPutTime = firstPutTime;
    }

    /**  **/
    public Date getPutYear() {
        return this.putYear;
    }

    /**  **/
    public void setPutYear(Date putYear) {
        this.putYear = putYear;
    }

    /**  **/
    public Date getPutTime() {
        return this.putTime;
    }

    /**  **/
    public void setPutTime(Date putTime) {
        this.putTime = putTime;
    }

    /**  **/
    public Date getIssueTime() {
        return this.issueTime;
    }

    /**  **/
    public void setIssueTime(Date issueTime) {
        this.issueTime = issueTime;
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
    public String getStandEnName() {
        return this.standEnName;
    }

    /**  **/
    public void setStandEnName(String standEnName) {
        this.standEnName = standEnName;
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
    public String getClassifyCode() {
        return this.classifyCode;
    }

    /**  **/
    public void setClassifyCode(String classifyCode) {
        this.classifyCode = classifyCode;
    }

    /**  **/
    public String getStandSubclass() {
        return this.standSubclass;
    }

    /**  **/
    public void setStandSubclass(String standSubclass) {
        this.standSubclass = standSubclass;
    }

    /**  **/
    public String getStandGenera() {
        return this.standGenera;
    }

    /**  **/
    public void setStandGenera(String standGenera) {
        this.standGenera = standGenera;
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
