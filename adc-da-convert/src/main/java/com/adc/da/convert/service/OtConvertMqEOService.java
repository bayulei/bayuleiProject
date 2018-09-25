package com.adc.da.convert.service;

import com.adc.da.base.service.BaseService;
import com.adc.da.convert.dao.OtConvertMqEODao;
import com.adc.da.convert.entity.OtConvertMqEO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * <br>
 * <b>功能：</b>OT_CONVERT_MQ OtConvertMqEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-25 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("otConvertMqEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class OtConvertMqEOService extends BaseService<OtConvertMqEO, String> {

    private static final Logger logger = LoggerFactory.getLogger(OtConvertMqEOService.class);

    @Autowired
    private OtConvertMqEODao dao;

    public OtConvertMqEODao getDao() {
        return dao;
    }

}
