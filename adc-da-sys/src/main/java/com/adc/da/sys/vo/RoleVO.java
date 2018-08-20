package com.adc.da.sys.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.adc.da.sys.entity.MenuEO;
import com.fasterxml.jackson.annotation.JsonFormat;

public class RoleVO {
	private String rid;
	private String rname;
	private String rdesc;
	private int enabled;
	private int usercount;
	private int belong;
	
    private String remarks;
    private String name;
    //新添字段
    private String oprUser;
    private int useFlag;
	private String operUserName;
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;
	// 扩展字段
	private String extInfo;
	private List<MenuEO> menus = new ArrayList<>();
	private List<String> menusstr = new ArrayList<>();

	public String getRid() {
		return rid;
	}

	public void setRid(String rid) {
		this.rid = rid;
	}

	public String getRname() {
		return rname;
	}

	public void setRname(String rname) {
		this.rname = rname;
	}

	public String getRdesc() {
		return rdesc;
	}

	public void setRdesc(String rdesc) {
		this.rdesc = rdesc;
	}

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	public int getUsercount() {
		return usercount;
	}

	public void setUsercount(int usercount) {
		this.usercount = usercount;
	}

	public int getBelong() {
		return belong;
	}

	public void setBelong(int belong) {
		this.belong = belong;
	}

	public String getExtInfo() {
		return extInfo;
	}

	public void setExtInfo(String extInfo) {
		this.extInfo = extInfo;
	}

	public List<MenuEO> getMenus() {
		return menus;
	}

	public void setMenus(List<MenuEO> menus) {
		this.menus = menus;
	}

	public List<String> getMenusstr() {
		return menusstr;
	}

	public void setMenusstr(List<String> menusstr) {
		this.menusstr = menusstr;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOprUser() {
		return oprUser;
	}

	public void setOprUser(String oprUser) {
		this.oprUser = oprUser;
	}

	public int getUseFlag() {
		return useFlag;
	}

	public void setUseFlag(Integer useFlag) {
		this.useFlag = useFlag;
	}

	public String getOperUserName() {
		return operUserName;
	}

	public void setOperUserName(String operUserName) {
		this.operUserName = operUserName;
	}

	public void setUseFlag(int useFlag) {
		this.useFlag = useFlag;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	
}
