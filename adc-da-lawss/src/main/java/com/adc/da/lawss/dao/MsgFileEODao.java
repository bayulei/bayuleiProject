package com.adc.da.lawss.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.lawss.entity.MsgFileEO;

import java.util.List;

/**
 *
 * <br>
 * <b>功能：</b>MSG_FILE MsgFileEODao<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-03 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public interface MsgFileEODao extends BaseDao<MsgFileEO> {

//修改动态信息附件表
    int updateByPrimaryKeySelective(List<MsgFileEO> msgFileEO);

    /****
     *  批量删除 根据消息ID
     * @MethodName:deleteLogicInBatch
     * @author: zhangyanduan
     * @param:[ids]
     * @return:void
     * date: 2018/9/18 17:05
     */
    public void deleteLogicInBatch(List<String> ids );
}
