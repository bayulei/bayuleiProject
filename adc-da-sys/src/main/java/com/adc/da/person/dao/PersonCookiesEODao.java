package com.adc.da.person.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.base.page.BasePage;
import com.adc.da.person.entity.PersonCookiesEO;

import java.util.List;

/**
 *
 * <br>
 * <b>功能：</b>TS_PERSON_COOKIES PersonCookiesEODao<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-03 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public interface PersonCookiesEODao extends BaseDao<PersonCookiesEO> {

    List<PersonCookiesEO> queryByCookieType(String cookieType);

    List<PersonCookiesEO> queryByUserId(String userId);

    int updateByUserId(PersonCookiesEO personCookiesEO);

    public void update(String id);
}
