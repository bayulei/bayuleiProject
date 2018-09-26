package com.adc.da.person.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.person.entity.PersonConfEO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * <br>
 * <b>功能：</b>TS_PERSON_CONF PersonConfEODao<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-03 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public interface PersonConfEODao extends BaseDao<PersonConfEO> {

//    List<PersonConfEO> updateById();
//    List<PersonConfEO> deleteByIdList(String ids);

//    List<PersonConfEO> insertByList(PersonConfEO personConfEO);
//    public void insertSelective(@Param("Id"));

      List<PersonConfEO> insert1(PersonConfEO personConfEO);
}
