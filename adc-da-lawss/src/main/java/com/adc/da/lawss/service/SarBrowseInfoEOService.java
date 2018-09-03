package com.adc.da.lawss.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.base.service.BaseService;
import com.adc.da.lawss.dao.SarBrowseInfoEODao;
import com.adc.da.lawss.entity.SarBrowseInfoEO;


/**
 *
 * <br>
 * <b>功能：</b>SAR_BROWSE_INFO SarBrowseInfoEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-03 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("sarBrowseInfoEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class SarBrowseInfoEOService extends BaseService<SarBrowseInfoEO, String> {

    private static final Logger logger = LoggerFactory.getLogger(SarBrowseInfoEOService.class);

    @Autowired
    private SarBrowseInfoEODao dao;

    public SarBrowseInfoEODao getDao() {
        return dao;
    }

}
