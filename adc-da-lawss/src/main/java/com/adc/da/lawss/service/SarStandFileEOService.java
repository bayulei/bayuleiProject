package com.adc.da.lawss.service;

import com.adc.da.lawss.entity.SarStandResEO;
import com.adc.da.sys.constant.ValueStateEnum;
import com.adc.da.sys.util.UUIDUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.base.service.BaseService;
import com.adc.da.lawss.dao.SarStandFileEODao;
import com.adc.da.lawss.entity.SarStandFileEO;

import java.util.Date;


/**
 *
 * <br>
 * <b>功能：</b>SAR_STAND_FILE SarStandFileEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-03 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("sarStandFileEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class SarStandFileEOService extends BaseService<SarStandFileEO, String> {

    private static final Logger logger = LoggerFactory.getLogger(SarStandFileEOService.class);

    @Autowired
    private SarStandFileEODao dao;

    public SarStandFileEODao getDao() {
        return dao;
    }

    public SarStandFileEO insertSarStandFileEO(SarStandFileEO sarStandFileEO){
        sarStandFileEO.setId(UUIDUtils.randomUUID20());
        sarStandFileEO.setCreationTime(new Date());
        sarStandFileEO.setModifyTime(new Date());
        sarStandFileEO.setValidFlag(ValueStateEnum.VALUE_TRUE.getValue());
        dao.insertSelective(sarStandFileEO);
        return sarStandFileEO;
    }

}
