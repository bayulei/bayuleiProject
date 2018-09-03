package com.adc.da.person.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.base.service.BaseService;
import com.adc.da.person.dao.PersonCollectEODao;
import com.adc.da.person.entity.PersonCollectEO;


/**
 *
 * <br>
 * <b>功能：</b>TS_PERSON_COLLECT PersonCollectEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-03 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("personCollectEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class PersonCollectEOService extends BaseService<PersonCollectEO, String> {

    private static final Logger logger = LoggerFactory.getLogger(PersonCollectEOService.class);

    @Autowired
    private PersonCollectEODao dao;

    public PersonCollectEODao getDao() {
        return dao;
    }

}
