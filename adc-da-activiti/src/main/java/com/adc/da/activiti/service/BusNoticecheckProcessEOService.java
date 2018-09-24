package com.adc.da.activiti.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.base.service.BaseService;
import com.adc.da.activiti.dao.BusNoticecheckProcessEODao;
import com.adc.da.activiti.entity.BusNoticecheckProcessEO;


/**
 *
 * <br>
 * <b>功能：</b>BUS_NOTICECHECK_PROCESS BusNoticecheckProcessEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-18 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("busNoticecheckProcessEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class BusNoticecheckProcessEOService extends BaseService<BusNoticecheckProcessEO, String> {

    private static final Logger logger = LoggerFactory.getLogger(BusNoticecheckProcessEOService.class);

    @Autowired
    private BusNoticecheckProcessEODao dao;

    public BusNoticecheckProcessEODao getDao() {
        return dao;
    }

    //按照流程Id删除通知排查业务表
    public void deleteNoticeCheckInfo(String taskId){
        dao.deleteNoticeCheckInfo(taskId);
    }

}
