package com.adc.da.lawss.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.lawss.entity.SarLawsValEO;
import com.adc.da.lawss.page.SarLawsValEOPage;

import java.util.List;

/**
 *
 * <br>
 * <b>功能：</b>SAR_LAWS_VAL SarLawsValEODao<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-03 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public interface SarLawsValEODao extends BaseDao<SarLawsValEO> {

    /**
     * @Author yangxuenan
     * @Description 根据法规信息查询关联表数据
     * Date 2018/9/4 9:29
     * @Param [sarLawsValEO]
     * @return java.util.List<com.adc.da.lawss.entity.SarLawsValEO>
     **/
    List<SarLawsValEO> selectByLawsVal(SarLawsValEOPage sarLawsValEO);

    /**
     * @Author yangxuenan
     * @Description 删除
     * Date 2018/9/13 16:56
     * @Param [id]
     * @return int
     **/
    int deleteByValId(String id);
}
