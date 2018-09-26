package com.adc.da.lawss.service;

import com.adc.da.lawss.dao.MsgFileEODao;
import com.adc.da.lawss.entity.MsgFileEO;
import com.adc.da.lawss.vo.MsgDynamicInfoVO;
import com.adc.da.sys.constant.ValidFlagEnum;
import com.adc.da.sys.util.UUIDUtils;
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

import java.util.ArrayList;
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

    @Autowired
    private MsgFileEODao msgFileEODao;

    public MsgDynamicInfoEODao getDao() {
        return dao;
    }

    @Transactional(rollbackFor = Exception.class)
    public String saveMsgInfo(MsgDynamicInfoVO msgDynamicInfoVO) throws Exception {
        Date nowDate=new Date();
        String msgId=UUIDUtils.randomUUID20();
        MsgDynamicInfoEO msgDynamicInfoEO=new MsgDynamicInfoEO();
        msgDynamicInfoEO.setId(msgId);
        msgDynamicInfoEO.setValidFlag(ValidFlagEnum.VALID_TRUE.getValue());
        msgDynamicInfoEO.setCreationTime(nowDate);
        msgDynamicInfoEO.setModifyTime(nowDate);
        msgDynamicInfoEO.setPubUser(msgDynamicInfoVO.getPubUser());
        logger.debug("打印输入的富文本信息框："+msgDynamicInfoVO.getContent());
        msgDynamicInfoEO.setContent(msgDynamicInfoVO.getContent());
        msgDynamicInfoEO.setIsPicMsg(msgDynamicInfoVO.getIsPicMsg());
        msgDynamicInfoEO.setLinkUri(msgDynamicInfoVO.getLinkUri());
        msgDynamicInfoEO.setMsgMode(msgDynamicInfoVO.getMsgMode());
        msgDynamicInfoEO.setMsgType(msgDynamicInfoVO.getMsgType());
        msgDynamicInfoEO.setPubOrg(msgDynamicInfoVO.getPubOrg());
        msgDynamicInfoEO.setPubTime(msgDynamicInfoVO.getPubTime());
        msgDynamicInfoEO.setTitle(msgDynamicInfoVO.getTitle());
        dao.insertSelective(msgDynamicInfoEO);
        List<MsgFileEO> msgFileEOList = msgDynamicInfoVO.getMsgFileEOList();
        if(msgFileEOList != null && !msgFileEOList.isEmpty()){
            for( MsgFileEO  msgFileEO :   msgFileEOList){
                msgFileEO.setId(UUIDUtils.randomUUID20());
                msgFileEO.setMsgId(msgId);
                msgFileEO.setCreationTime(nowDate);
                msgFileEO.setModifyTime(nowDate);
                msgFileEO.setValidFlag(ValidFlagEnum.VALID_TRUE.getValue());
                msgFileEO.setFileType("FILE");
                msgFileEODao.insert(msgFileEO);
            }
        }
        // 此处将新闻图片写入数据库
        if(msgDynamicInfoVO.getIsPicMsg()==ValidFlagEnum.VALID_TRUE.getValue()){
            MsgFileEO picFile=  msgDynamicInfoVO.getPicFileEO();
            picFile.setId(UUIDUtils.randomUUID20());
            picFile.setMsgId(msgId);
            picFile.setFileType("PIC");
            picFile.setValidFlag(ValidFlagEnum.VALID_TRUE.getValue());
            picFile.setCreationTime(nowDate);
            picFile.setModifyTime(nowDate);
            msgFileEODao.insert(picFile);
        }
        return msgId;
    }
//修改
    public int updateByPrimaryKeySelective(MsgDynamicInfoEO msgDynamicInfoEO) throws Exception {

        msgDynamicInfoEO.setModifyTime(new Date());
        return dao.updateByPrimaryKeySelective(msgDynamicInfoEO);
    }


    @Transactional(rollbackFor = Exception.class)
    public void updateMsgInfo(MsgDynamicInfoVO msgDynamicInfoVO)throws Exception{
        Date nowDate=new Date();
        MsgDynamicInfoEO msgDynamicInfoEO=new MsgDynamicInfoEO();
        msgDynamicInfoEO.setId(msgDynamicInfoVO.getId());
        msgDynamicInfoEO.setValidFlag(ValidFlagEnum.VALID_TRUE.getValue());
        msgDynamicInfoEO.setCreationTime(msgDynamicInfoVO.getCreationTime());
        msgDynamicInfoEO.setModifyTime(nowDate);
        msgDynamicInfoEO.setPubUser(msgDynamicInfoVO.getPubUser());
        logger.debug("打印输入的富文本信息框："+msgDynamicInfoVO.getContent());
        msgDynamicInfoEO.setContent(msgDynamicInfoVO.getContent());
        msgDynamicInfoEO.setIsPicMsg(msgDynamicInfoVO.getIsPicMsg());
        msgDynamicInfoEO.setLinkUri(msgDynamicInfoVO.getLinkUri());
        msgDynamicInfoEO.setMsgMode(msgDynamicInfoVO.getMsgMode());
        msgDynamicInfoEO.setMsgType(msgDynamicInfoVO.getMsgType());
        msgDynamicInfoEO.setPubOrg(msgDynamicInfoVO.getPubOrg());
        msgDynamicInfoEO.setPubTime(msgDynamicInfoVO.getPubTime());
        msgDynamicInfoEO.setTitle(msgDynamicInfoVO.getTitle());
        dao.updateByPrimaryKeySelective(msgDynamicInfoEO);

        //开始更新附件信息
        // 首先将附件表数据全部清空
        List<String> msgIds=new ArrayList<String>();
        msgIds.add(msgDynamicInfoVO.getId());
        msgFileEODao.deleteLogicInBatch(new ArrayList<String>(msgIds));
        //重新将文件添加到附件表中
        List<MsgFileEO> msgFileEOList = msgDynamicInfoVO.getMsgFileEOList();
        if(msgFileEOList != null && !msgFileEOList.isEmpty()){
            for( MsgFileEO  msgFileEO :   msgFileEOList){
                msgFileEO.setId(UUIDUtils.randomUUID20());
                msgFileEO.setMsgId(msgDynamicInfoVO.getId());
                msgFileEO.setCreationTime(nowDate);
                msgFileEO.setModifyTime(nowDate);
                msgFileEO.setValidFlag(ValidFlagEnum.VALID_TRUE.getValue());
                msgFileEO.setFileType("FILE");
                msgFileEODao.insert(msgFileEO);
            }
        }
        // 此处将新闻图片写入数据库
        if(msgDynamicInfoVO.getIsPicMsg()==ValidFlagEnum.VALID_TRUE.getValue()){
            MsgFileEO picFile=  msgDynamicInfoVO.getPicFileEO();
            picFile.setId(UUIDUtils.randomUUID20());
            picFile.setMsgId(msgDynamicInfoVO.getId());
            picFile.setFileType("PIC");
            picFile.setValidFlag(ValidFlagEnum.VALID_TRUE.getValue());
            picFile.setCreationTime(nowDate);
            picFile.setModifyTime(nowDate);
            msgFileEODao.insert(picFile);
        }

    }



//  调用附件存入动态信息id
    public  Integer updateIdOfMsgFile(List<MsgFileEO> msgFileEOS){
        return   dao.updateIdOfMsgFile(msgFileEOS);
    }

    public void deleteLogicInBatch(List<String> ids){
        dao.deleteLogicInBatch(ids);
        // 删除消息附件内容
        msgFileEODao.deleteLogicInBatch(ids);
    }

}
