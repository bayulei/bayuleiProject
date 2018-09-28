package com.adc.da.person.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.base.service.BaseService;
import com.adc.da.person.dao.PersonNoteEODao;
import com.adc.da.person.entity.PersonNoteEO;

import java.util.Date;


/**
 * <br>
 * <b>功能：</b>TS_PERSON_NOTE PersonNoteEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-03 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("personNoteEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class PersonNoteEOService extends BaseService<PersonNoteEO, String> {

    private static final Logger logger = LoggerFactory.getLogger(PersonNoteEOService.class);

    @Autowired
    private PersonNoteEODao dao;

    public PersonNoteEODao getDao() {
        return dao;
    }

    //刘寅楠
    public PersonNoteEO save(PersonNoteEO personNoteEO) {
        personNoteEO.setCreartionTime(new Date());
        personNoteEO.setModifyTime(new Date());
        dao.insertSelective(personNoteEO);
        return personNoteEO;
    }


    public void updateById(PersonNoteEO personNoteEO) {
        personNoteEO.setCreartionTime(new Date());
        personNoteEO.setModifyTime(new Date());
        dao.updateByPrimaryKeySelective(personNoteEO);
    }


    public void delete(String id) {
        dao.deleteByPrimaryKey(id);
    }


    public Integer updateByCollectId(PersonNoteEO personNoteEO) {
        personNoteEO.setModifyTime(new Date());
        Integer i = dao.updateByCollectId(personNoteEO);
        return i;
    }


    public PersonNoteEO queryByCollectId(String collectId) {
        PersonNoteEO personNoteEO= dao.queryByCollectId(collectId);
        return personNoteEO;
    }


}
