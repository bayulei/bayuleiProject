package com.adc.da.lawss.entity;

import com.adc.da.att.vo.AttFileVo;
import com.adc.da.base.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <b>功能：</b>SAR_STANDARDS_INFO SarStandardsInfoEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-03 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */

public class SarStandardsInfoEO extends BaseEntity {
    //修改时间
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;
    //创建时间
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date creationTime;
    //是否有效
    private Integer validFlag;
    //创建人
    private String creationUser;
    //备注
    private String remark;
    //所属类别     多选
    private String category;
    //责任部门
    private String responsibleUnit;
    //内容摘要
    private String synopsis;
    //关键词
    private String tags;
    //关联文件  支持PDF、doc、docx文件，
    private String relevanceFile;
    //报批稿  支持PDF、doc、docx文件，
    private String approvalFile;
    //送审稿  支持PDF、doc、docx文件，
    private String sentScreenFile;
    //征求意见稿  支持PDF、doc、docx文件，
    private String opinionFile;
    //草案 支持PDF、doc、docx文件，
    private String draftFile;
    //标准修改单 支持PDF、doc、docx文件，
    private String standModifyFile;
    //标准文本 支持PDF、doc、docx文件，
    private String standFile;
    //起草人
    private String draftUser;
    //起草单位
    private String draftingUnit;
    //新生产车实施日期
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date newproductPutTime;
    //在产车实施日期
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date productPutTime;
    //新定型车实施日期
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date newcarPutTime;
    //实施日期

    //@org.springframework.format.annotation.DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date putTime;
    //发布日期
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date issueTime;
    //适用认证  多选
    private String applyAuth;
    //能源种类  多选
    private String emergyKind;
    //采标程度
    private String adoptExtent;
    //采用国际标准号
    private String interStandNum;
    //被代替标准号
    private String replacedStandNum;
    //代替标准号   手动输入可以多个，用","隔开
    private String replaceStandNum;
    //标准性质
    private String standNature;
    //标准状态
    private String standState;
    //标准英文名称
    private String standEnName;
    //标准名称
    private String standName;
    //标准年份
    private String standYear;
    //标准编号
    private String standNumber;
    //适用车型 多选，用“，”隔开
    private String applyArctic;
    //标准类别
    private String standSort;
    //国家、地区
    private String country;
    //标准分类
    private String standType;
    //主键
    private String id;

    //目录ID   非标准信息表中的字段
    private String  menuId;

    private String countryShow;  //国家地区 --单选
    private String standSortShow; // 标准类别下拉框    --单选
    private String applyArcticShow; // 适用车型下拉框  --多选
    private String standStateShow; // 标准状态下拉框   --单选
    private String standNatureShow; // 标准性质下拉框  --单选
    private String adoptExtentShow; // 采标程度下拉框  --单选
    private String emergyKindShow; // 能源种类下拉框   --多选
    private String applyAuthShow; // 适用认证下拉框    --多选
    private String categoryShow; // 所属类别下拉框     --多选

    private List<AttFileVo> standFileList = new ArrayList<AttFileVo>();
    private List<AttFileVo> relevanceFileList = new ArrayList<AttFileVo>();
    private List<AttFileVo> approvalFileList = new ArrayList<AttFileVo>();
    private List<AttFileVo> sentScreenFileList = new ArrayList<AttFileVo>();
    private List<AttFileVo> opinionFileList = new ArrayList<AttFileVo>();
    private List<AttFileVo> draftFileList = new ArrayList<AttFileVo>();
    private List<AttFileVo> standModifyFileList = new ArrayList<AttFileVo>();

    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>modifyTime -> modify_time</li>
     * <li>creationTime -> creation_time</li>
     * <li>validFlag -> valid_flag</li>
     * <li>creationUser -> creation_user</li>
     * <li>remark -> remark</li>
     * <li>category -> category</li>
     * <li>responsibleUnit -> responsible_unit</li>
     * <li>synopsis -> synopsis</li>
     * <li>tags -> tags</li>
     * <li>relevanceFile -> relevance_file</li>
     * <li>approvalFile -> approval_file</li>
     * <li>sentScreenFile -> sent_screen_file</li>
     * <li>opinionFile -> opinion_file</li>
     * <li>draftFile -> draft_file</li>
     * <li>standModifyFile -> stand_modify_file</li>
     * <li>standFile -> stand_file</li>
     * <li>draftUser -> draft_user</li>
     * <li>draftingUnit -> drafting_unit</li>
     * <li>newproductPutTime -> newproduct_put_time</li>
     * <li>productPutTime -> product_put_time</li>
     * <li>newcarPutTime -> newcar_put_time</li>
     * <li>putTime -> put_time</li>
     * <li>issueTime -> issue_time</li>
     * <li>applyAuth -> apply_auth</li>
     * <li>emergyKind -> emergy_kind</li>
     * <li>adoptExtent -> adopt_extent</li>
     * <li>interStandNum -> inter_stand_num</li>
     * <li>replacedStandNum -> replaced_stand_num</li>
     * <li>replaceStandNum -> replace_stand_num</li>
     * <li>standNature -> stand_nature</li>
     * <li>standState -> stand_state</li>
     * <li>standEnName -> stand_en_name</li>
     * <li>standName -> stand_name</li>
     * <li>standYear -> stand_year</li>
     * <li>standNumber -> stand_number</li>
     * <li>applyArctic -> apply_arctic</li>
     * <li>standSort -> stand_sort</li>
     * <li>country -> country</li>
     * <li>standType -> stand_type</li>
     * <li>id -> id</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) return null;
        switch (fieldName) {
            case "modifyTime": return "modify_time";
            case "creationTime": return "creation_time";
            case "validFlag": return "valid_flag";
            case "creationUser": return "creation_user";
            case "remark": return "remark";
            case "category": return "category";
            case "responsibleUnit": return "responsible_unit";
            case "synopsis": return "synopsis";
            case "tags": return "tags";
            case "relevanceFile": return "relevance_file";
            case "approvalFile": return "approval_file";
            case "sentScreenFile": return "sent_screen_file";
            case "opinionFile": return "opinion_file";
            case "draftFile": return "draft_file";
            case "standModifyFile": return "stand_modify_file";
            case "standFile": return "stand_file";
            case "draftUser": return "draft_user";
            case "draftingUnit": return "drafting_unit";
            case "newproductPutTime": return "newproduct_put_time";
            case "productPutTime": return "product_put_time";
            case "newcarPutTime": return "newcar_put_time";
            case "putTime": return "put_time";
            case "issueTime": return "issue_time";
            case "applyAuth": return "apply_auth";
            case "emergyKind": return "emergy_kind";
            case "adoptExtent": return "adopt_extent";
            case "interStandNum": return "inter_stand_num";
            case "replacedStandNum": return "replaced_stand_num";
            case "replaceStandNum": return "replace_stand_num";
            case "standNature": return "stand_nature";
            case "standState": return "stand_state";
            case "standEnName": return "stand_en_name";
            case "standName": return "stand_name";
            case "standYear": return "stand_year";
            case "standNumber": return "stand_number";
            case "applyArctic": return "apply_arctic";
            case "standSort": return "stand_sort";
            case "country": return "country";
            case "standType": return "stand_type";
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
     * <li>remark -> remark</li>
     * <li>category -> category</li>
     * <li>responsible_unit -> responsibleUnit</li>
     * <li>synopsis -> synopsis</li>
     * <li>tags -> tags</li>
     * <li>relevance_file -> relevanceFile</li>
     * <li>approval_file -> approvalFile</li>
     * <li>sent_screen_file -> sentScreenFile</li>
     * <li>opinion_file -> opinionFile</li>
     * <li>draft_file -> draftFile</li>
     * <li>stand_modify_file -> standModifyFile</li>
     * <li>stand_file -> standFile</li>
     * <li>draft_user -> draftUser</li>
     * <li>drafting_unit -> draftingUnit</li>
     * <li>newproduct_put_time -> newproductPutTime</li>
     * <li>product_put_time -> productPutTime</li>
     * <li>newcar_put_time -> newcarPutTime</li>
     * <li>put_time -> putTime</li>
     * <li>issue_time -> issueTime</li>
     * <li>apply_auth -> applyAuth</li>
     * <li>emergy_kind -> emergyKind</li>
     * <li>adopt_extent -> adoptExtent</li>
     * <li>inter_stand_num -> interStandNum</li>
     * <li>replaced_stand_num -> replacedStandNum</li>
     * <li>replace_stand_num -> replaceStandNum</li>
     * <li>stand_nature -> standNature</li>
     * <li>stand_state -> standState</li>
     * <li>stand_en_name -> standEnName</li>
     * <li>stand_name -> standName</li>
     * <li>stand_year -> standYear</li>
     * <li>stand_number -> standNumber</li>
     * <li>apply_arctic -> applyArctic</li>
     * <li>stand_sort -> standSort</li>
     * <li>country -> country</li>
     * <li>stand_type -> standType</li>
     * <li>id -> id</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) return null;
        switch (columnName) {
            case "modify_time": return "modifyTime";
            case "creation_time": return "creationTime";
            case "valid_flag": return "validFlag";
            case "creation_user": return "creationUser";
            case "remark": return "remark";
            case "category": return "category";
            case "responsible_unit": return "responsibleUnit";
            case "synopsis": return "synopsis";
            case "tags": return "tags";
            case "relevance_file": return "relevanceFile";
            case "approval_file": return "approvalFile";
            case "sent_screen_file": return "sentScreenFile";
            case "opinion_file": return "opinionFile";
            case "draft_file": return "draftFile";
            case "stand_modify_file": return "standModifyFile";
            case "stand_file": return "standFile";
            case "draft_user": return "draftUser";
            case "drafting_unit": return "draftingUnit";
            case "newproduct_put_time": return "newproductPutTime";
            case "product_put_time": return "productPutTime";
            case "newcar_put_time": return "newcarPutTime";
            case "put_time": return "putTime";
            case "issue_time": return "issueTime";
            case "apply_auth": return "applyAuth";
            case "emergy_kind": return "emergyKind";
            case "adopt_extent": return "adoptExtent";
            case "inter_stand_num": return "interStandNum";
            case "replaced_stand_num": return "replacedStandNum";
            case "replace_stand_num": return "replaceStandNum";
            case "stand_nature": return "standNature";
            case "stand_state": return "standState";
            case "stand_en_name": return "standEnName";
            case "stand_name": return "standName";
            case "stand_year": return "standYear";
            case "stand_number": return "standNumber";
            case "apply_arctic": return "applyArctic";
            case "stand_sort": return "standSort";
            case "country": return "country";
            case "stand_type": return "standType";
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
    public String getRemark() {
        return this.remark;
    }

    /**  **/
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**  **/
    public String getCategory() {
        return this.category;
    }

    /**  **/
    public void setCategory(String category) {
        this.category = category;
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
    public String  getSynopsis() {
        return this.synopsis;
    }

    /**  **/
    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
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
    public String getRelevanceFile() {
        return this.relevanceFile;
    }

    /**  **/
    public void setRelevanceFile(String relevanceFile) {
        this.relevanceFile = relevanceFile;
    }

    /**  **/
    public String getApprovalFile() {
        return this.approvalFile;
    }

    /**  **/
    public void setApprovalFile(String approvalFile) {
        this.approvalFile = approvalFile;
    }

    /**  **/
    public String getSentScreenFile() {
        return this.sentScreenFile;
    }

    /**  **/
    public void setSentScreenFile(String sentScreenFile) {
        this.sentScreenFile = sentScreenFile;
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
    public String getDraftFile() {
        return this.draftFile;
    }

    /**  **/
    public void setDraftFile(String draftFile) {
        this.draftFile = draftFile;
    }

    /**  **/
    public String getStandModifyFile() {
        return this.standModifyFile;
    }

    /**  **/
    public void setStandModifyFile(String standModifyFile) {
        this.standModifyFile = standModifyFile;
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
    public String getDraftUser() {
        return this.draftUser;
    }

    /**  **/
    public void setDraftUser(String draftUser) {
        this.draftUser = draftUser;
    }

    /**  **/
    public String getDraftingUnit() {
        return this.draftingUnit;
    }

    /**  **/
    public void setDraftingUnit(String draftingUnit) {
        this.draftingUnit = draftingUnit;
    }

    /**  **/
    public Date getNewproductPutTime() {
        return this.newproductPutTime;
    }

    /**  **/
    public void setNewproductPutTime(Date newproductPutTime) {
        this.newproductPutTime = newproductPutTime;
    }

    /**  **/
    public Date getProductPutTime() {
        return this.productPutTime;
    }

    /**  **/
    public void setProductPutTime(Date productPutTime) {
        this.productPutTime = productPutTime;
    }

    /**  **/
    public Date getNewcarPutTime() {
        return this.newcarPutTime;
    }

    /**  **/
    public void setNewcarPutTime(Date newcarPutTime) {
        this.newcarPutTime = newcarPutTime;
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
    public String getApplyAuth() {
        return this.applyAuth;
    }

    /**  **/
    public void setApplyAuth(String applyAuth) {
        this.applyAuth = applyAuth;
    }

    /**  **/
    public String getEmergyKind() {
        return this.emergyKind;
    }

    /**  **/
    public void setEmergyKind(String emergyKind) {
        this.emergyKind = emergyKind;
    }

    /**  **/
    public String getAdoptExtent() {
        return this.adoptExtent;
    }

    /**  **/
    public void setAdoptExtent(String adoptExtent) {
        this.adoptExtent = adoptExtent;
    }

    /**  **/
    public String getInterStandNum() {
        return this.interStandNum;
    }

    /**  **/
    public void setInterStandNum(String interStandNum) {
        this.interStandNum = interStandNum;
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
    public String getStandNature() {
        return this.standNature;
    }

    /**  **/
    public void setStandNature(String standNature) {
        this.standNature = standNature;
    }

    /**  **/
    public String getStandState() {
        return this.standState;
    }

    /**  **/
    public void setStandState(String standState) {
        this.standState = standState;
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
    public String getStandYear() {
        return this.standYear;
    }

    /**  **/
    public void setStandYear(String standYear) {
        this.standYear = standYear;
    }

    /**  **/
    public String getStandNumber() {
        return this.standNumber;
    }

    /**  **/
    public void setStandNumber(String standNumber) {
        this.standNumber = standNumber;
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
    public String getStandSort() {
        return this.standSort;
    }

    /**  **/
    public void setStandSort(String standSort) {
        this.standSort = standSort;
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
    public String getStandType() {
        return this.standType;
    }

    /**  **/
    public void setStandType(String standType) {
        this.standType = standType;
    }

    /**  **/
    public String getId() {
        return this.id;
    }

    /**  **/
    public void setId(String id) {
        this.id = id;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getCountryShow() {
        return countryShow;
    }

    public void setCountryShow(String countryShow) {
        this.countryShow = countryShow;
    }

    public String getStandSortShow() {
        return standSortShow;
    }

    public void setStandSortShow(String standSortShow) {
        this.standSortShow = standSortShow;
    }

    public String getApplyArcticShow() {
        return applyArcticShow;
    }

    public void setApplyArcticShow(String applyArcticShow) {
        this.applyArcticShow = applyArcticShow;
    }

    public String getStandStateShow() {
        return standStateShow;
    }

    public void setStandStateShow(String standStateShow) {
        this.standStateShow = standStateShow;
    }

    public String getStandNatureShow() {
        return standNatureShow;
    }

    public void setStandNatureShow(String standNatureShow) {
        this.standNatureShow = standNatureShow;
    }

    public String getAdoptExtentShow() {
        return adoptExtentShow;
    }

    public void setAdoptExtentShow(String adoptExtentShow) {
        this.adoptExtentShow = adoptExtentShow;
    }

    public String getEmergyKindShow() {
        return emergyKindShow;
    }

    public void setEmergyKindShow(String emergyKindShow) {
        this.emergyKindShow = emergyKindShow;
    }

    public String getApplyAuthShow() {
        return applyAuthShow;
    }

    public void setApplyAuthShow(String applyAuthShow) {
        this.applyAuthShow = applyAuthShow;
    }

    public String getCategoryShow() {
        return categoryShow;
    }

    public void setCategoryShow(String categoryShow) {
        this.categoryShow = categoryShow;
    }

    public List<AttFileVo> getStandFileList() {
        return standFileList;
    }

    public void setStandFileList(List<AttFileVo> standFileList) {
        this.standFileList = standFileList;
    }

    public List<AttFileVo> getRelevanceFileList() {
        return relevanceFileList;
    }

    public void setRelevanceFileList(List<AttFileVo> relevanceFileList) {
        this.relevanceFileList = relevanceFileList;
    }

    public List<AttFileVo> getApprovalFileList() {
        return approvalFileList;
    }

    public void setApprovalFileList(List<AttFileVo> approvalFileList) {
        this.approvalFileList = approvalFileList;
    }

    public List<AttFileVo> getSentScreenFileList() {
        return sentScreenFileList;
    }

    public void setSentScreenFileList(List<AttFileVo> sentScreenFileList) {
        this.sentScreenFileList = sentScreenFileList;
    }

    public List<AttFileVo> getOpinionFileList() {
        return opinionFileList;
    }

    public void setOpinionFileList(List<AttFileVo> opinionFileList) {
        this.opinionFileList = opinionFileList;
    }

    public List<AttFileVo> getDraftFileList() {
        return draftFileList;
    }

    public void setDraftFileList(List<AttFileVo> draftFileList) {
        this.draftFileList = draftFileList;
    }

    public List<AttFileVo> getStandModifyFileList() {
        return standModifyFileList;
    }

    public void setStandModifyFileList(List<AttFileVo> standModifyFileList) {
        this.standModifyFileList = standModifyFileList;
    }
}
