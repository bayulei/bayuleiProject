package com.adc.da.person.service;

import com.adc.da.base.page.BasePage;
import com.adc.da.sys.dao.UserEODao;
import com.adc.da.sys.dao.UserRoleEODao;
import com.adc.da.sys.entity.UserEO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.base.service.BaseService;
import com.adc.da.person.dao.UserInfoEODao;
import com.adc.da.person.entity.UserInfoEO;


/**
 *
 * <br>
 * <b>功能：</b>TS_USER_INFO UserInfoEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-03 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("userInfoEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class UserInfoEOService extends BaseService<UserInfoEO, String> {

    private static final Logger logger = LoggerFactory.getLogger(UserInfoEOService.class);

    @Autowired
    private UserInfoEODao dao;

    @Autowired
    private UserEODao userEODao;

    public UserInfoEODao getDao() {
        return dao;
    }

    public UserEODao getUserEODao() {
        return userEODao;
    }

    /**
     * @Author liuyinnan
     * @Description 根据用户ID获取用户信息
     * @Date 19:19 2018/9/26
     * @Param [userId]
     * @return com.adc.da.person.entity.UserInfoEO
     **/
    public UserInfoEO getUserEOAndInfoEOByUserCode(String userId){
        UserInfoEO userInfoEO= dao.getUserInfoByUserId(userId);
        UserEO userEO= userEODao.selectOrgByPrimaryKey(userId);

        if(userInfoEO ==null){
            userInfoEO=new UserInfoEO();
        }
        if(userEO !=null){
            userInfoEO.setAccount(userEO.getAccount());
            userInfoEO.setEmail(userEO.getEmail());
            userInfoEO.setuName(userEO.getUname());
            userInfoEO.setOrgName(userEO.getOrgName());
        }
        return userInfoEO;
    }

    public UserInfoEO getUserInfoEOByUserInfoId(String userId){
        return null;
    }

    public void save(UserInfoEO userInfoEO){

    }


    public UserInfoEO updateById(UserInfoEO userInfoEO){
        return null;
    }

}
