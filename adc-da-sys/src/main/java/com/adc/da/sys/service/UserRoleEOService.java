package com.adc.da.sys.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.base.service.BaseService;
import com.adc.da.sys.dao.UserRoleEODao;
import com.adc.da.sys.entity.UserRoleEO;


/**
 *
 * <br>
 * <b>功能：</b>TR_USER_ROLE UserRoleEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2017-11-07 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("userRoleEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class UserRoleEOService extends BaseService<UserRoleEO, Integer> {

    private static final Logger logger = LoggerFactory.getLogger(UserRoleEOService.class);

    @Autowired
    private UserRoleEODao dao;

    public UserRoleEODao getDao() {
        return dao;
    }

}
