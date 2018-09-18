package com.adc.da.lawss.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.lawss.entity.SarProductValEO;
/**
 *
 * <br>
 * <b>功能：</b>SAR_PRODUCT_VAL SarProductValEODao<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-03 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public interface SarProductValEODao extends BaseDao<SarProductValEO> {

    //通过企业id删除表中数据
    int deleteDataByProductid(String productId);
}
