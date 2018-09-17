package com.adc.da.lawss.service;

import com.adc.da.util.utils.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.base.service.BaseService;
import com.adc.da.lawss.dao.MsgBrowseInfoEODao;
import com.adc.da.lawss.entity.MsgBrowseInfoEO;

import java.util.Date;


/**
 *
 * <br>
 * <b>功能：</b>MSG_BROWSE_INFO MsgBrowseInfoEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-03 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("msgBrowseInfoEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class MsgBrowseInfoEOService extends BaseService<MsgBrowseInfoEO, String> {

    private static final Logger logger = LoggerFactory.getLogger(MsgBrowseInfoEOService.class);

    @Autowired
    private MsgBrowseInfoEODao dao;

    public MsgBrowseInfoEODao getDao() {
        return dao;
    }
//新增
    public int insertSelective(MsgBrowseInfoEO msgBrowseInfoEO) throws Exception {
        msgBrowseInfoEO.setId(UUID.randomUUID10());
        msgBrowseInfoEO.setCreationTime(new Date());
        msgBrowseInfoEO.setModifyTime(new Date());
//        浏览了多长时间
//        msgBrowseInfoEO.setBrowseTime(new Date());
        return dao.insertSelective(msgBrowseInfoEO);
    }

//修改
public int updateByPrimaryKeySelective(MsgBrowseInfoEO msgBrowseInfoEO) throws Exception {
    msgBrowseInfoEO.setModifyTime(new Date());
    return dao.updateByPrimaryKeySelective(msgBrowseInfoEO);
}
}
