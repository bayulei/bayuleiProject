package com.adc.da.activiti.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.base.service.BaseService;
import com.adc.da.activiti.dao.BusResAnalyProcessEODao;
import com.adc.da.activiti.entity.BusResAnalyProcessEO;


/**
 *
 * <br>
 * <b>功能：</b>BUS_RES_ANALY_PROCESS BusResAnalyProcessEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-26 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("busResAnalyProcessEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class BusResAnalyProcessEOService extends BaseService<BusResAnalyProcessEO, String> {

    private static final Logger logger = LoggerFactory.getLogger(BusResAnalyProcessEOService.class);

    @Autowired
    private BusResAnalyProcessEODao dao;

    public BusResAnalyProcessEODao getDao() {
        return dao;
    }

    //按照流程Id删除分析结果业务表
    public void deleteAnalyInfo(String processInstanceId){
        dao.deleteAnalyInfo(processInstanceId);
    }

}
