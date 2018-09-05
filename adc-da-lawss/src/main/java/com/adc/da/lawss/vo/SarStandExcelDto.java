package com.adc.da.lawss.vo;

import com.adc.da.excel.annotation.Excel;


/**
 * 条件查询符合标准数据导出Excel模板
 * @author gaoyan
 * date 2018/09/04
 */
public class SarStandExcelDto {

	//创建人
	@Excel(name = "创建人")
	private String creationUser;

	public String getCreationUser() {
		return creationUser;
	}

	public void setCreationUser(String creationUser) {
		this.creationUser = (creationUser!=null?creationUser.trim():null);
	}

}
