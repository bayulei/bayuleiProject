package com.adc.da.sys.dto;

import com.adc.da.excel.annotation.Excel;

public class SysCorpExcelDto {
	
	@Excel(name = "使用方名称")
	private String corpName;
	@Excel(name = "地址")
    private String corpAddress;
	@Excel(name = "联系人")
    private String corpUser;
	@Excel(name = "职务")
	private String corpDuty;
	@Excel(name = "联系电话")
    private String corpPhone;
	@Excel(name = "邮箱")
    private String corpEmail;
	public String getCorpName() {
		return corpName;
	}
	public void setCorpName(String corpName) {
		this.corpName = (corpName!=null?corpName.trim():null);
	}
	public String getCorpAddress() {
		return corpAddress;
	}
	public void setCorpAddress(String corpAddress) {
		this.corpAddress = (corpAddress!=null?corpAddress.trim():null);;
	}
	public String getCorpUser() {
		return corpUser;
	}
	public void setCorpUser(String corpUser) {
		this.corpUser = (corpUser!=null?corpUser.trim():null);;
	}
	public String getCorpPhone() {
		return corpPhone;
	}
	public void setCorpPhone(String corpPhone) {
		this.corpPhone = (corpPhone!=null?corpPhone.trim():null);;
	}
	public String getCorpEmail() {
		return corpEmail;
	}
	public void setCorpEmail(String corpEmail) {
		this.corpEmail = (corpEmail!=null?corpEmail.trim():null);;
	}
	public String getCorpDuty() {
		return corpDuty;
	}
	public void setCorpDuty(String corpDuty) {
		this.corpDuty = (corpDuty!=null?corpDuty.trim():null);;
	}
    
}
