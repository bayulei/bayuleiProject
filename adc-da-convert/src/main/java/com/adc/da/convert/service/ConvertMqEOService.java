package com.adc.da.convert.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.base.service.BaseService;
import com.adc.da.convert.dao.ConvertMqEODao;
import com.adc.da.convert.entity.ConvertMqEO;


/**
 *
 * <br>
 * <b>功能：</b>CONVERT_MQ ConvertMqEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-08-16 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("convertMqEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class ConvertMqEOService extends BaseService<ConvertMqEO, String> {

    private static final Logger logger = LoggerFactory.getLogger(ConvertMqEOService.class);

    @Autowired
    private ConvertMqEODao dao;

    public ConvertMqEODao getDao() {
        return dao;
    }

}
