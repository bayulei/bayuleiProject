package com.adc.da.activiti.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.activiti.entity.BusExecuProcessEO;
import com.adc.da.base.page.BasePage;

import java.util.List;

/**
 *
 * <br>
 * <b>功能：</b>BUS_EXECU_PROCESS BusExecuProcessEODao<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-04 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public interface BusExecuProcessEODao extends BaseDao<BusExecuProcessEO> {


    List<BusExecuProcessEO> selectByEO(BusExecuProcessEO eo);
}
