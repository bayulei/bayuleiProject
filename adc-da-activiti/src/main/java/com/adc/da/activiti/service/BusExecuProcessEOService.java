package com.adc.da.activiti.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.base.service.BaseService;
import com.adc.da.activiti.dao.BusExecuProcessEODao;
import com.adc.da.activiti.entity.BusExecuProcessEO;

import java.util.List;


/**
 *
 * <br>
 * <b>功能：</b>BUS_EXECU_PROCESS BusExecuProcessEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-04 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("busExecuProcessEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class BusExecuProcessEOService extends BaseService<BusExecuProcessEO, String> {

    private static final Logger logger = LoggerFactory.getLogger(BusExecuProcessEOService.class);

    @Autowired
    private BusExecuProcessEODao dao;

    public BusExecuProcessEODao getDao() {
        return dao;
    }

    public List<BusExecuProcessEO> selectByEO(BusExecuProcessEO busExecuProcessEO) {
        return  dao.selectByEO(busExecuProcessEO);
    }
}
