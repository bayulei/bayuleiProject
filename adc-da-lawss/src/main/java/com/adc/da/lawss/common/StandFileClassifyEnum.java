package com.adc.da.lawss.common;

/**
 * 资源库文件分类枚举设置
 * @author gaoyan
 * date 2018/09/03
 */
public enum StandFileClassifyEnum {

	STAND_FILE("STAND_FILE","标准文本/法规文件"),STAND_MODIFY_FILE("STAND_MODIFY_FILE","标准修改单"),
	DRAFT_FILE("DRAFT_FILE","草案文件"),OPINION_FILE("OPINION_FILE","征求意见稿"),
	SENT_SCREEN_FILE("SENT_SCREEN_FILE","送审稿"),APPROVAL_FILE("APPROVAL_FILE","报批稿"),
	RELEVANCE_FILE("RELEVANCE_FILE","关联文件"),LAWS_FILE("LAWS_FILE","法规文件") ;

	private String value;
	private String lable;

	private StandFileClassifyEnum(String value, String lable) {
		this.value = value;
		this.lable = lable;
	}
	public String getValue() {
		return value;
	}
	public String getLable() {
		return lable;
	}
	
	
	
	
}
