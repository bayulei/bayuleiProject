package com.adc.da.sys.entity;

import java.io.Serializable;

import com.adc.da.base.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DictionaryEO extends BaseEntity implements Serializable{

	private static final long serialVersionUID = 3658632939727891047L;
	private String id;
    private String dictionaryCode;
    private String dictionaryName;
	private Integer delFlag;
    private String dicTypeName;
    private String dicId;
    private List<DicTypeEO> dicTypeEOList = new ArrayList<>();
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date insertTime;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

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

    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>id -> id</li>
     * <li>dictionaryCode -> dictionarycode</li>
     * <li>dictionaryName -> dictionaryname</li>
     * <li>delFlag -> del</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) return null;
        switch (fieldName) {
            case "id": return "id";
            case "dictionaryCode": return "dictionary_code";
            case "dictionaryName": return "dictionary_name";
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
            case "dictionary_code": return "dictionaryCode";
            case "dictionary_name": return "dictionaryName";
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
    

    public String getDictionaryCode() {
		return dictionaryCode;
	}

	public void setDictionaryCode(String dictionaryCode) {
		this.dictionaryCode = dictionaryCode;
	}

	public String getDictionaryName() {
		return dictionaryName;
	}

	public void setDictionaryName(String dictionaryName) {
		this.dictionaryName = dictionaryName;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	public List<DicTypeEO> getDicTypeEOList() {
		return dicTypeEOList;
	}

	public void setDicTypeEOList(List<DicTypeEO> dicTypeEOList) {
		this.dicTypeEOList = dicTypeEOList;
	}

	public Date getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
