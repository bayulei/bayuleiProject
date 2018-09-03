package com.adc.da.lawss.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.base.service.BaseService;
import com.adc.da.lawss.dao.SarLawsValEODao;
import com.adc.da.lawss.entity.SarLawsValEO;


/**
 *
 * <br>
 * <b>功能：</b>SAR_LAWS_VAL SarLawsValEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-03 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("sarLawsValEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class SarLawsValEOService extends BaseService<SarLawsValEO, String> {

    private static final Logger logger = LoggerFactory.getLogger(SarLawsValEOService.class);

    @Autowired
    private SarLawsValEODao dao;

    public SarLawsValEODao getDao() {
        return dao;
    }

}
