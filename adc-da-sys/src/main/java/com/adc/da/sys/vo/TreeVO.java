package com.adc.da.sys.vo;

import java.util.List;

public abstract class TreeVO<T extends TreeVO> {

	protected String id;
	protected String name;
	protected String parentId; // 所有父级编号
	protected String parentIds; // 所有父级编号
	protected T parent; // 父级编号
	protected List<T> childList;
	protected Integer delFlag;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	public T getParent() {
		return parent;
	}

	public void setParent(T parent) {
		this.parent = parent;
	}

	public List<T> getChildList() {
		return childList;
	}

	public void setChildList(List<T> childList) {
		this.childList = childList;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
}
