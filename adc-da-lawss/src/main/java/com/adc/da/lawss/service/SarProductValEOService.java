package com.adc.da.lawss.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.base.service.BaseService;
import com.adc.da.lawss.dao.SarProductValEODao;
import com.adc.da.lawss.entity.SarProductValEO;


/**
 *
 * <br>
 * <b>功能：</b>SAR_PRODUCT_VAL SarProductValEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-03 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("sarProductValEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class SarProductValEOService extends BaseService<SarProductValEO, String> {

    private static final Logger logger = LoggerFactory.getLogger(SarProductValEOService.class);

    @Autowired
    private SarProductValEODao dao;

    public SarProductValEODao getDao() {
        return dao;
    }

}
