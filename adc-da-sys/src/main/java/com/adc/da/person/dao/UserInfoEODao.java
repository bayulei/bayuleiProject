package com.adc.da.person.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.person.entity.UserInfoEO;
import org.apache.catalina.User;

/**
 *
 * <br>
 * <b>功能：</b>TS_USER_INFO UserInfoEODao<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-03 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public interface UserInfoEODao extends BaseDao<UserInfoEO> {


    public UserInfoEO getUserInfoByUserId(String userId);

}
