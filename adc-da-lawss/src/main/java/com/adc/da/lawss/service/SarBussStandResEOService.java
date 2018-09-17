package com.adc.da.lawss.service;

import com.adc.da.base.service.BaseService;
import com.adc.da.lawss.dao.SarBussStandResEODao;
import com.adc.da.lawss.entity.SarBussStandResEO;
import com.adc.da.sys.constant.ValueStateEnum;
import com.adc.da.sys.util.UUIDUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


/**
 *
 * <br>
 * <b>功能：</b>SAR_BUSS_STAND_RES SarBussStandResEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-03 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("sarBussStandResEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class SarBussStandResEOService extends BaseService<SarBussStandResEO, String> {

    private static final Logger logger = LoggerFactory.getLogger(SarBussStandResEOService.class);

    @Autowired
    private SarBussStandResEODao dao;

    public SarBussStandResEODao getDao() {
        return dao;
    }

    public SarBussStandResEO insertSarBussStandResEO(SarBussStandResEO sarBussStandResEO){
        sarBussStandResEO.setId(UUIDUtils.randomUUID20());
        sarBussStandResEO.setCreationTime(new Date());
        sarBussStandResEO.setModifyTime(new Date());
        sarBussStandResEO.setValidFlag(ValueStateEnum.VALUE_TRUE.getValue());
        dao.insertSelective(sarBussStandResEO);
        return sarBussStandResEO;
    }

}
