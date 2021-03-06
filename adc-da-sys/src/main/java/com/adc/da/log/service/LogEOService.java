package com.adc.da.log.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.base.service.BaseService;
import com.adc.da.log.dao.LogEODao;
import com.adc.da.log.entity.LogEO;


/**
 *
 * <br>
 * <b>功能：</b>TS_LOG LogEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2017-12-24 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("logEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class LogEOService extends BaseService<LogEO, String> {

    private static final Logger logger = LoggerFactory.getLogger(LogEOService.class);

    @Autowired
    private LogEODao dao;

    public LogEODao getDao() {
        return dao;
    }

}
