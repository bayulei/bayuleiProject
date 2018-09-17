package com.adc.da.lawss.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.lawss.dto.SarBussionessStandExcelDto;
import com.adc.da.lawss.entity.SarBussionessStandEO;
import com.adc.da.lawss.page.SarBussionessStandEOPage;

import java.util.List;

/**
 *
 * <br>
 * <b>功能：</b>SAR_BUSSIONESS_STAND SarBussionessStandEODao<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-03 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public interface SarBussionessStandEODao extends BaseDao<SarBussionessStandEO> {

    List<SarBussionessStandEO> getBussionessStandInfoPage(SarBussionessStandEOPage page);

    int getBussionessStandInfoCount(SarBussionessStandEOPage page);

    List<SarBussionessStandExcelDto> getSarBussionessStand(SarBussionessStandEOPage page);

}
