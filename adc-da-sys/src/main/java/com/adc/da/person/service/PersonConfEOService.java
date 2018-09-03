package com.adc.da.person.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.base.service.BaseService;
import com.adc.da.person.dao.PersonConfEODao;
import com.adc.da.person.entity.PersonConfEO;


/**
 *
 * <br>
 * <b>功能：</b>TS_PERSON_CONF PersonConfEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-03 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("personConfEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class PersonConfEOService extends BaseService<PersonConfEO, String> {

    private static final Logger logger = LoggerFactory.getLogger(PersonConfEOService.class);

    @Autowired
    private PersonConfEODao dao;

    public PersonConfEODao getDao() {
        return dao;
    }

}
