package com.adc.da.lawss.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.lawss.entity.SarStandItemValEO;
/**
 *
 * <br>
 * <b>功能：</b>SAR_STAND_ITEM_VAL SarStandItemValEODao<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-03 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public interface SarStandItemValEODao extends BaseDao<SarStandItemValEO> {
    int  deleteSarStandItemValByItemid(String itemid);
}
