package com.adc.da.activiti.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.activiti.entity.BusResAnalyProcessEO;
import org.apache.ibatis.annotations.Delete;

/**
 *
 * <br>
 * <b>功能：</b>BUS_RES_ANALY_PROCESS BusResAnalyProcessEODao<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-26 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public interface BusResAnalyProcessEODao extends BaseDao<BusResAnalyProcessEO> {

    //按照流程Id删除分析结果业务表
    @Delete("delete from BUS_RES_ANALY_PROCESS where PROC_INST_ID = #{processInstanceId}")
    public void deleteAnalyInfo(String processInstanceId);

}
