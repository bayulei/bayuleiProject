package com.adc.da.lawss.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.base.service.BaseService;
import com.adc.da.lawss.dao.UtCarExemptionEODao;
import com.adc.da.lawss.entity.UtCarExemptionEO;


/**
 *
 * <br>
 * <b>功能：</b>UT_CAR_EXEMPTION UtCarExemptionEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-03 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("utCarExemptionEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class UtCarExemptionEOService extends BaseService<UtCarExemptionEO, String> {

    private static final Logger logger = LoggerFactory.getLogger(UtCarExemptionEOService.class);

    @Autowired
    private UtCarExemptionEODao dao;

    public UtCarExemptionEODao getDao() {
        return dao;
    }

}
