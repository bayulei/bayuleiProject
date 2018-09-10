package com.adc.da.att.dao;

import com.adc.da.base.dao.BaseDao;

import org.apache.ibatis.annotations.Param;

import com.adc.da.att.entity.AttFileEO;
/**
 *
 * <br>
 * <b>功能：</b>ATT_FILE AttFileEODao<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-07 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public interface AttFileEODao extends BaseDao<AttFileEO> {

	public void creatTableInfo(@Param("tableName")String tableName);
	
	public int existTable(@Param("tableName")String tableName);
	
	public AttFileEO selectFileInfoById(AttFileEO attFileEO);
	
}
