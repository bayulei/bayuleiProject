package com.adc.da.sys.entity;

import java.io.Serializable;

import com.adc.da.base.entity.BaseEntity;

public class DicTypeEO extends BaseEntity implements Serializable{

	private static final long serialVersionUID = 3658632939727891047L;
	private String id;
    private String dicTypeCode;
    private String dicTypeName;
    private String dicId;
    private Integer delFlag;
    
    
    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>id -> id</li>
     * <li>dicTypeCode -> dtypecode</li>
     * <li>dicTypeName -> dtypename</li>
     *  <li>dicId -> dictionaryid</li>
     * <li>delFlag -> del</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) return null;
        switch (fieldName) {
            case "id": return "id";
            case "dicTypeCode": return "dic_type_code";
            case "dicTypeName": return "dic_type_name";
            case "dicId": return "dic_id";
            case "delFlag": return "del_flag";
            default: return null;
        }
    }

    /**
     * 原始数据库列名转换为java字段名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>id -> id</li>
     * <li>dictionarycode -> dictionaryCode</li>
     * <li>dictionaryname -> dictionaryName</li>
     * <li>del -> delFlag</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) return null;
        switch (columnName) {
            case "id": return "id";
            case "dic_type_code": return "dicTypeCode";
            case "dic_type_name": return "dicTypeName";
            case "dic_id": return "dicId";
            case "del_flag": return "delFlag";
            default: return null;
        }
    }
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    
    
    public String getDicTypeCode() {
		return dicTypeCode;
	}
	public void setDicTypeCode(String dicTypeCode) {
		this.dicTypeCode = dicTypeCode;
	}

	public String getDicTypeName() {
		return dicTypeName;
	}	
	public void setDicTypeName(String dicTypeName) {
		this.dicTypeName = dicTypeName;
	}

	public String getDicId() {
		return dicId;
	}

	public void setDicId(String dicId) {
		this.dicId = dicId;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
}
