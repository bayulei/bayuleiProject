package com.adc.da.lawss.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.lawss.entity.SarProductInfoEO;
import com.adc.da.lawss.page.SarProductInfoEOPage;

import java.util.List;

/**
 *
 * <br>
 * <b>功能：</b>SAR_PRODUCT_INFO SarProductInfoEODao<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-03 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public interface SarProductInfoEODao extends BaseDao<SarProductInfoEO> {
    int getSarProductInfoCount(SarProductInfoEOPage page);

    List<SarProductInfoEO> getSarProductInfoPage(SarProductInfoEOPage page);

}
