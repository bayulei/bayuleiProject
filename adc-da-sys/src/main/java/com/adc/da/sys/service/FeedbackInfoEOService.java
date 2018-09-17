package com.adc.da.sys.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.base.service.BaseService;
import com.adc.da.sys.dao.FeedbackInfoEODao;
import com.adc.da.sys.entity.FeedbackInfoEO;


/**
 *
 * <br>
 * <b>功能：</b>TS_FEEDBACK_INFO FeedbackInfoEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-17 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("feedbackInfoEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class FeedbackInfoEOService extends BaseService<FeedbackInfoEO, String> {

    private static final Logger logger = LoggerFactory.getLogger(FeedbackInfoEOService.class);

    @Autowired
    private FeedbackInfoEODao dao;

    public FeedbackInfoEODao getDao() {
        return dao;
    }

}
