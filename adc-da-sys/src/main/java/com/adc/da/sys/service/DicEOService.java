package com.adc.da.sys.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.base.service.BaseService;
import com.adc.da.sys.dao.DicEODao;
import com.adc.da.sys.dao.DicTypeEODao;
import com.adc.da.sys.entity.DictionaryEO;
import com.adc.da.sys.entity.UserEO;
import com.adc.da.util.constant.DeleteFlagEnum;
import com.adc.da.util.utils.UUID;

import java.util.Date;
import java.util.List;


@Service("dicEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class DicEOService extends BaseService<DictionaryEO,String>{
	
	private static final Logger logger = LoggerFactory.getLogger(DictionaryEO.class);

	@Autowired
	private DicEODao dicEODao;
	@Autowired
	private DicTypeEODao dicTypeEODao;

	public DicEODao getDao() {
		return dicEODao;
	}
	
	public DictionaryEO save(DictionaryEO dicEO) {
		dicEO.setId(UUID.randomUUID10());
		dicEO.setValidFlag(DeleteFlagEnum.NORMAL.getValue());
		dicEO.setCreationTime(new Date());
		dicEO.setModifyTime(new Date());
		dicEODao.insert(dicEO);
		return dicEO;
	}
	
	/**
	 * 查询字典详情
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public DictionaryEO getDictionaryById(String id) {
		return dicEODao.getDictionaryEOById(id);
	}
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public DictionaryEO getDictionaryByDicCode(String dictionaryCode) {
		return dicEODao.getDictionaryEOByDicCode(dictionaryCode);
	}

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public DictionaryEO getDicEOAndTypeEoByDicCode(String dictionaryCode) {
		return dicEODao.getDicEOAndTypeEoByDicCode(dictionaryCode);
	}

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public DictionaryEO getDictionaryByDicName(String dictionaryName) {
		return dicEODao.getDictionaryEOByDicName(dictionaryName);
	}
	
	//删除字典
	public void delete(String id) {
		dicEODao.deleteDic(id);
	}
	
	//删除字典
	public void deleteDicAndType(String id) {
		dicEODao.deleteDic(id);
		dicTypeEODao.deleteDicTypeByDicId(id);
	}

	public void updateById(DictionaryEO dictionaryEO) {
		dictionaryEO.setModifyTime(new Date());
		dicEODao.updateByPrimaryKey(dictionaryEO);
	}



}
