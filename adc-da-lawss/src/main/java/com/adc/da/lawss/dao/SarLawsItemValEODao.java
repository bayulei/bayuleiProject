package com.adc.da.lawss.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.lawss.entity.SarLawsItemValEO;
/**
 *
 * <br>
 * <b>功能：</b>SAR_LAWS_ITEM_VAL SarLawsItemValEODao<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-03 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public interface SarLawsItemValEODao extends BaseDao<SarLawsItemValEO> {

    /**
     * @Author yangxuenan
     * @Description 根据id删除条目信息
     * Date 2018/9/14 13:56
     * @Param [id]
     * @return int
     **/
    int deleteItemsValById(String id);
}
