package com.adc.da.lawss.service;

import com.adc.da.lawss.entity.MsgFileEO;
import com.adc.da.util.utils.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.base.service.BaseService;
import com.adc.da.lawss.dao.MsgDynamicInfoEODao;
import com.adc.da.lawss.entity.MsgDynamicInfoEO;

import java.util.Date;
import java.util.List;


/**
 *
 * <br>
 * <b>功能：</b>MSG_DYNAMIC_INFO MsgDynamicInfoEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-03 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("msgDynamicInfoEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class MsgDynamicInfoEOService extends BaseService<MsgDynamicInfoEO, String> {

    private static final Logger logger = LoggerFactory.getLogger(MsgDynamicInfoEOService.class);

    @Autowired
    private MsgDynamicInfoEODao dao;

    public MsgDynamicInfoEODao getDao() {
        return dao;
    }

    public int insertSelective(MsgDynamicInfoEO msgDynamicInfoEO) throws Exception {

        msgDynamicInfoEO.setId(UUID.randomUUID10());
        msgDynamicInfoEO.setValidFlag(0);
        msgDynamicInfoEO.setCreationTime(new Date());
        msgDynamicInfoEO.setModifyTime(new Date());

        return dao.insertSelective(msgDynamicInfoEO);
    }
//修改
    public int updateByPrimaryKeySelective(MsgDynamicInfoEO msgDynamicInfoEO) throws Exception {

        msgDynamicInfoEO.setModifyTime(new Date());
        return dao.updateByPrimaryKeySelective(msgDynamicInfoEO);
    }

//  调用附件存入动态信息id
    public  Integer updateIdOfMsgFile(List<MsgFileEO> msgFileEOS){


     return   dao.updateIdOfMsgFile(msgFileEOS);

}
}
