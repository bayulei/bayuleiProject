package com.adc.da.activiti.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.activiti.entity.BusNoticecheckProcessEO;
import org.apache.ibatis.annotations.Delete;

/**
 *
 * <br>
 * <b>功能：</b>BUS_NOTICECHECK_PROCESS BusNoticecheckProcessEODao<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-18 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public interface BusNoticecheckProcessEODao extends BaseDao<BusNoticecheckProcessEO> {

    //按照流程Id删除通知排查业务表
    @Delete("delete from BUS_NOTICECHECK_PROCESS where TASK_ID = #{taskId}")
    public void deleteNoticeCheckInfo(String taskId);
}
