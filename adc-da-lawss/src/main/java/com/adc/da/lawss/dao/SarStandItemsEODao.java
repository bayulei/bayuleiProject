package com.adc.da.lawss.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.base.page.BasePage;
import com.adc.da.lawss.entity.SarStandItemsEO;
import com.adc.da.lawss.page.SarStandItemsEOPage;
import com.adc.da.lawss.vo.SarStandItemsVO;

import java.util.List;

/**
 *
 * <br>
 * <b>功能：</b>SAR_STAND_ITEMS SarStandItemsEODao<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-03 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public interface SarStandItemsEODao extends BaseDao<SarStandItemsEO> {

    List<SarStandItemsVO> querySarStandItemsList(SarStandItemsEOPage page);

}
