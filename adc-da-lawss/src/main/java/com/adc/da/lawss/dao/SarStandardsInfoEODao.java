package com.adc.da.lawss.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.lawss.entity.SarStandardsInfoEO;
import com.adc.da.lawss.page.SarStandardsInfoEOPage;
import com.adc.da.lawss.vo.SarStandExcelDto;
import java.util.List;

/**
 *
 * <br>
 * <b>功能：</b>SAR_STANDARDS_INFO SarStandardsInfoEODao<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-03 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public interface SarStandardsInfoEODao extends BaseDao<SarStandardsInfoEO> {
    List<SarStandardsInfoEO>  getSarStandardsInfoPage(SarStandardsInfoEOPage page);
    int getSarStandardsInfoCount(SarStandardsInfoEOPage page);

    List<SarStandExcelDto> getSarStandardsInfo(SarStandardsInfoEOPage page);
}
