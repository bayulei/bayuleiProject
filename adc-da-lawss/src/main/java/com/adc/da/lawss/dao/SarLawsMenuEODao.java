package com.adc.da.lawss.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.lawss.entity.SarLawsMenuEO;
import com.adc.da.lawss.page.SarLawsMenuEOPage;

import java.util.List;

/**
 *
 * <br>
 * <b>功能：</b>SAR_LAWS_MENU SarLawsMenuEODao<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-03 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public interface SarLawsMenuEODao extends BaseDao<SarLawsMenuEO> {

    /**
     * @Author yangxuenan
     * @Description 根据法规信息查询数据
     * Date 2018/9/4 9:19
     * @Param [sarLawsMenuEO]
     * @return java.util.List<com.adc.da.lawss.entity.SarLawsMenuEO>
     **/
    List<SarLawsMenuEO> selectByLawsInfo(SarLawsMenuEOPage sarLawsMenuEO);

}
