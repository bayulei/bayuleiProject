package com.adc.da.sys.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.base.service.BaseService;
import com.adc.da.sys.dao.WarnTimeEODao;
import com.adc.da.sys.entity.WarnTimeEO;


/**
 *
 * <br>
 * <b>功能：</b>TS_WARN_TIME WarnTimeEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-17 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("warnTimeEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class WarnTimeEOService extends BaseService<WarnTimeEO, String> {

    private static final Logger logger = LoggerFactory.getLogger(WarnTimeEOService.class);

    @Autowired
    private WarnTimeEODao dao;

    public WarnTimeEODao getDao() {
        return dao;
    }

}
