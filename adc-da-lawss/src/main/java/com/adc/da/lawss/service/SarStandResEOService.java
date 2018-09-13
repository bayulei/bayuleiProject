package com.adc.da.lawss.service;

import com.adc.da.sys.constant.ValueStateEnum;
import com.adc.da.sys.util.UUIDUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.base.service.BaseService;
import com.adc.da.lawss.dao.SarStandResEODao;
import com.adc.da.lawss.entity.SarStandResEO;

import java.util.Date;
import java.util.Random;
import java.util.RandomAccess;


/**
 *
 * <br>
 * <b>功能：</b>SAR_STAND_RES SarStandResEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-03 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("sarStandResEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class SarStandResEOService extends BaseService<SarStandResEO, String> {

    private static final Logger logger = LoggerFactory.getLogger(SarStandResEOService.class);

    @Autowired
    private SarStandResEODao dao;

    public SarStandResEODao getDao() {
        return dao;
    }

    public SarStandResEO insertSarStandResEO(SarStandResEO sarStandResEO){
        sarStandResEO.setId(UUIDUtils.randomUUID20());
        sarStandResEO.setCreationTime(new Date());
        sarStandResEO.setModifyTime(new Date());
        sarStandResEO.setValidFlag(ValueStateEnum.VALUE_TRUE.getValue());
        dao.insertSelective(sarStandResEO);
        return sarStandResEO;
    }

}
