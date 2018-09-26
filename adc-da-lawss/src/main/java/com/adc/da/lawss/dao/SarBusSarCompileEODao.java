package com.adc.da.lawss.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.lawss.entity.SarBusSarCompileEO;
import com.adc.da.lawss.page.SarBusSarCompileEOPage;

import java.util.List;

/**
 *
 * <br>
 * <b>功能：</b>SAR_BUS_SAR_COMPILE SarBusSarCompileEODao<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-03 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public interface SarBusSarCompileEODao extends BaseDao<SarBusSarCompileEO> {

    /**
     * @Author yangxuenan
     * @Description 根据标准编号查询数据
     * Date 2018/9/17 9:00
     * @Param [sarBusSarCompileEO]
     * @return java.util.List<com.adc.da.lawss.entity.SarBusSarCompileEO>
     **/
    List<SarBusSarCompileEO> queryByStandCode(SarBusSarCompileEOPage sarBusSarCompileEO);
}
