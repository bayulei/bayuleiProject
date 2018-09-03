package com.adc.da.lawss.entity;

import com.adc.da.base.entity.BaseEntity;

import java.util.Date;

/**
 * <b>功能：</b>SAR_LAWS_INFO SarLawsInfoEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-03 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class SarLawsInfoEO extends BaseEntity {

    @org.springframework.format.annotation.DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;
    @org.springframework.format.annotation.DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date creationTime;
    private Integer validFlag;
    private String creationUser;
    private String relevanceFile;
    private String opinionFile;
    private String lawsFile;
    private String linkUri;
    private String responsibleUnit;
    private String applyAuth;
    private String energyKind;
    private String applyArctic;
    private String replaceLawsNum;
    @org.springframework.format.annotation.DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date putTime;
    @org.springframework.format.annotation.DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date issueTime;
    private String lawsState;
    private String issueUnit;
    private String lawsName;
    private String lawsNumber;
    private String lawsProperty;
    private String country;
    private String lawsType;
    private String id;

    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>modifyTime -> modify_time</li>
     * <li>creationTime -> creation_time</li>
     * <li>validFlag -> valid_flag</li>
     * <li>creationUser -> creation_user</li>
     * <li>relevanceFile -> relevance_file</li>
     * <li>opinionFile -> opinion_file</li>
     * <li>lawsFile -> laws_file</li>
     * <li>linkUri -> link_uri</li>
     * <li>responsibleUnit -> responsible_unit</li>
     * <li>applyAuth -> apply_auth</li>
     * <li>energyKind -> energy_kind</li>
     * <li>applyArctic -> apply_arctic</li>
     * <li>replaceLawsNum -> replace_laws_num</li>
     * <li>putTime -> put_time</li>
     * <li>issueTime -> issue_time</li>
     * <li>lawsState -> laws_state</li>
     * <li>issueUnit -> issue_unit</li>
     * <li>lawsName -> laws_name</li>
     * <li>lawsNumber -> laws_number</li>
     * <li>lawsProperty -> laws_property</li>
     * <li>country -> country</li>
     * <li>lawsType -> laws_type</li>
     * <li>id -> id</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) return null;
        switch (fieldName) {
            case "modifyTime": return "modify_time";
            case "creationTime": return "creation_time";
            case "validFlag": return "valid_flag";
            case "creationUser": return "creation_user";
            case "relevanceFile": return "relevance_file";
            case "opinionFile": return "opinion_file";
            case "lawsFile": return "laws_file";
            case "linkUri": return "link_uri";
            case "responsibleUnit": return "responsible_unit";
            case "applyAuth": return "apply_auth";
            case "energyKind": return "energy_kind";
            case "applyArctic": return "apply_arctic";
            case "replaceLawsNum": return "replace_laws_num";
            case "putTime": return "put_time";
            case "issueTime": return "issue_time";
            case "lawsState": return "laws_state";
            case "issueUnit": return "issue_unit";
            case "lawsName": return "laws_name";
            case "lawsNumber": return "laws_number";
            case "lawsProperty": return "laws_property";
            case "country": return "country";
            case "lawsType": return "laws_type";
            case "id": return "id";
            default: return null;
        }
    }

    /**
     * 原始数据库列名转换为java字段名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>modify_time -> modifyTime</li>
     * <li>creation_time -> creationTime</li>
     * <li>valid_flag -> validFlag</li>
     * <li>creation_user -> creationUser</li>
     * <li>relevance_file -> relevanceFile</li>
     * <li>opinion_file -> opinionFile</li>
     * <li>laws_file -> lawsFile</li>
     * <li>link_uri -> linkUri</li>
     * <li>responsible_unit -> responsibleUnit</li>
     * <li>apply_auth -> applyAuth</li>
     * <li>energy_kind -> energyKind</li>
     * <li>apply_arctic -> applyArctic</li>
     * <li>replace_laws_num -> replaceLawsNum</li>
     * <li>put_time -> putTime</li>
     * <li>issue_time -> issueTime</li>
     * <li>laws_state -> lawsState</li>
     * <li>issue_unit -> issueUnit</li>
     * <li>laws_name -> lawsName</li>
     * <li>laws_number -> lawsNumber</li>
     * <li>laws_property -> lawsProperty</li>
     * <li>country -> country</li>
     * <li>laws_type -> lawsType</li>
     * <li>id -> id</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) return null;
        switch (columnName) {
            case "modify_time": return "modifyTime";
            case "creation_time": return "creationTime";
            case "valid_flag": return "validFlag";
            case "creation_user": return "creationUser";
            case "relevance_file": return "relevanceFile";
            case "opinion_file": return "opinionFile";
            case "laws_file": return "lawsFile";
            case "link_uri": return "linkUri";
            case "responsible_unit": return "responsibleUnit";
            case "apply_auth": return "applyAuth";
            case "energy_kind": return "energyKind";
            case "apply_arctic": return "applyArctic";
            case "replace_laws_num": return "replaceLawsNum";
            case "put_time": return "putTime";
            case "issue_time": return "issueTime";
            case "laws_state": return "lawsState";
            case "issue_unit": return "issueUnit";
            case "laws_name": return "lawsName";
            case "laws_number": return "lawsNumber";
            case "laws_property": return "lawsProperty";
            case "country": return "country";
            case "laws_type": return "lawsType";
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
    public Integer getValidFlag() {
        return this.validFlag;
    }

    /**  **/
    public void setValidFlag(Integer validFlag) {
        this.validFlag = validFlag;
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
    public String getRelevanceFile() {
        return this.relevanceFile;
    }

    /**  **/
    public void setRelevanceFile(String relevanceFile) {
        this.relevanceFile = relevanceFile;
    }

    /**  **/
    public String getOpinionFile() {
        return this.opinionFile;
    }

    /**  **/
    public void setOpinionFile(String opinionFile) {
        this.opinionFile = opinionFile;
    }

    /**  **/
    public String getLawsFile() {
        return this.lawsFile;
    }

    /**  **/
    public void setLawsFile(String lawsFile) {
        this.lawsFile = lawsFile;
    }

    /**  **/
    public String getLinkUri() {
        return this.linkUri;
    }

    /**  **/
    public void setLinkUri(String linkUri) {
        this.linkUri = linkUri;
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
    public String getApplyAuth() {
        return this.applyAuth;
    }

    /**  **/
    public void setApplyAuth(String applyAuth) {
        this.applyAuth = applyAuth;
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
    public String getReplaceLawsNum() {
        return this.replaceLawsNum;
    }

    /**  **/
    public void setReplaceLawsNum(String replaceLawsNum) {
        this.replaceLawsNum = replaceLawsNum;
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
    public String getLawsState() {
        return this.lawsState;
    }

    /**  **/
    public void setLawsState(String lawsState) {
        this.lawsState = lawsState;
    }

    /**  **/
    public String getIssueUnit() {
        return this.issueUnit;
    }

    /**  **/
    public void setIssueUnit(String issueUnit) {
        this.issueUnit = issueUnit;
    }

    /**  **/
    public String getLawsName() {
        return this.lawsName;
    }

    /**  **/
    public void setLawsName(String lawsName) {
        this.lawsName = lawsName;
    }

    /**  **/
    public String getLawsNumber() {
        return this.lawsNumber;
    }

    /**  **/
    public void setLawsNumber(String lawsNumber) {
        this.lawsNumber = lawsNumber;
    }

    /**  **/
    public String getLawsProperty() {
        return this.lawsProperty;
    }

    /**  **/
    public void setLawsProperty(String lawsProperty) {
        this.lawsProperty = lawsProperty;
    }

    /**  **/
    public String getCountry() {
        return this.country;
    }

    /**  **/
    public void setCountry(String country) {
        this.country = country;
    }

    /**  **/
    public String getLawsType() {
        return this.lawsType;
    }

    /**  **/
    public void setLawsType(String lawsType) {
        this.lawsType = lawsType;
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
