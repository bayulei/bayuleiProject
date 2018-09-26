package com.adc.da.person.entity;

import com.adc.da.base.entity.BaseEntity;

import java.util.Date;

/**
 * <b>功能：</b>TS_USER_INFO UserInfoEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-03 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class UserInfoEO extends BaseEntity {

    @org.springframework.format.annotation.DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;
    @org.springframework.format.annotation.DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date creationTime;
    private Integer validFlag;
    private Object signature;
    private String faxAddress;
    private String address;
    private Integer gender;
    private String partPost;
    private String assistantPost;
    private String mainPost;
    private String mobilePhone;
    private String officePhone;
    private String userPic;
    private String duty;
    private String userId;
    private String id;
//    追加字段
    private String account;
    private String uName;
    private String email;

    //刘寅楠
//    protected List<UserEO> userEOList=new ArrayList<>();
//    public UserEO userEO = new UserEO();

    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>modifyTime -> modify_time</li>
     * <li>creationTime -> creation_time</li>
     * <li>validFlag -> valid_flag</li>
     * <li>signature -> signature</li>
     * <li>faxAddress -> fax_address</li>
     * <li>address -> address</li>
     * <li>gender -> gender</li>
     * <li>partPost -> part_post</li>
     * <li>assistantPost -> assistant_post</li>
     * <li>mainPost -> main_post</li>
     * <li>mobilePhone -> mobile_phone</li>
     * <li>officePhone -> office_phone</li>
     * <li>userPic -> user_pic</li>
     * <li>duty -> duty</li>
     * <li>userId -> user_id</li>
     * <li>id -> id</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) return null;
        switch (fieldName) {
            case "modifyTime": return "modify_time";
            case "creationTime": return "creation_time";
            case "validFlag": return "valid_flag";
            case "signature": return "signature";
            case "faxAddress": return "fax_address";
            case "address": return "address";
            case "gender": return "gender";
            case "partPost": return "part_post";
            case "assistantPost": return "assistant_post";
            case "mainPost": return "main_post";
            case "mobilePhone": return "mobile_phone";
            case "officePhone": return "office_phone";
            case "userPic": return "user_pic";
            case "duty": return "duty";
            case "userId": return "user_id";
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
     * <li>signature -> signature</li>
     * <li>fax_address -> faxAddress</li>
     * <li>address -> address</li>
     * <li>gender -> gender</li>
     * <li>part_post -> partPost</li>
     * <li>assistant_post -> assistantPost</li>
     * <li>main_post -> mainPost</li>
     * <li>mobile_phone -> mobilePhone</li>
     * <li>office_phone -> officePhone</li>
     * <li>user_pic -> userPic</li>
     * <li>duty -> duty</li>
     * <li>user_id -> userId</li>
     * <li>id -> id</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) return null;
        switch (columnName) {
            case "modify_time": return "modifyTime";
            case "creation_time": return "creationTime";
            case "valid_flag": return "validFlag";
            case "signature": return "signature";
            case "fax_address": return "faxAddress";
            case "address": return "address";
            case "gender": return "gender";
            case "part_post": return "partPost";
            case "assistant_post": return "assistantPost";
            case "main_post": return "mainPost";
            case "mobile_phone": return "mobilePhone";
            case "office_phone": return "officePhone";
            case "user_pic": return "userPic";
            case "duty": return "duty";
            case "user_id": return "userId";
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
    public Object getSignature() {
        return this.signature;
    }

    /**  **/
    public void setSignature(Object signature) {
        this.signature = signature;
    }

    /**  **/
    public String getFaxAddress() {
        return this.faxAddress;
    }

    /**  **/
    public void setFaxAddress(String faxAddress) {
        this.faxAddress = faxAddress;
    }

    /**  **/
    public String getAddress() {
        return this.address;
    }

    /**  **/
    public void setAddress(String address) {
        this.address = address;
    }

    /**  **/
    public Integer getGender() {
        return this.gender;
    }

    /**  **/
    public void setGender(Integer gender) {
        this.gender = gender;
    }

    /**  **/
    public String getPartPost() {
        return this.partPost;
    }

    /**  **/
    public void setPartPost(String partPost) {
        this.partPost = partPost;
    }

    /**  **/
    public String getAssistantPost() {
        return this.assistantPost;
    }

    /**  **/
    public void setAssistantPost(String assistantPost) {
        this.assistantPost = assistantPost;
    }

    /**  **/
    public String getMainPost() {
        return this.mainPost;
    }

    /**  **/
    public void setMainPost(String mainPost) {
        this.mainPost = mainPost;
    }

    /**  **/
    public String getMobilePhone() {
        return this.mobilePhone;
    }

    /**  **/
    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    /**  **/
    public String getOfficePhone() {
        return this.officePhone;
    }

    /**  **/
    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }

    /**  **/
    public String getUserPic() {
        return this.userPic;
    }

    /**  **/
    public void setUserPic(String userPic) {
        this.userPic = userPic;
    }

    /**  **/
    public String getDuty() {
        return this.duty;
    }

    /**  **/
    public void setDuty(String duty) {
        this.duty = duty;
    }

    /**  **/
    public String getUserId() {
        return this.userId;
    }

    /**  **/
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**  **/
    public String getId() {
        return this.id;
    }

    /**  **/
    public void setId(String id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public List<UserEO> getUserEOList() {
//        return userEOList;
//    }
//
//    public void setUserEOList(List<UserEO> userEOList) {
//        this.userEOList = userEOList;
//    }

//    public UserEO getUserEO() {
//        return userEO;
//    }
//
//    public void setUserEO(UserEO userEO) {
//        this.userEO = userEO;
//    }
}
