package com.adc.da.lawss.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.lawss.entity.SarLawsInfoEO;

/**
 * @Author yangxuenan
 * @Description 法规信息
 * Date 2018/9/3 16:37
 * @Param
 * @return
 **/
public interface SarLawsInfoEODao extends BaseDao<SarLawsInfoEO> {
    /**
     * @Author yangxuenan
     * @Description 通过id查询详细信息
     * Date 2018/9/21 16:42
     * @Param [id]
     * @return com.adc.da.lawss.entity.SarLawsInfoEO
     **/
    SarLawsInfoEO selectInfoById(String id);

}
