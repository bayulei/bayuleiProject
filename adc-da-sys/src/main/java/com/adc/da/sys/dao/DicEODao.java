package com.adc.da.sys.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.sys.entity.DictionaryEO;

public interface DicEODao extends BaseDao<DictionaryEO>{
	
	public DictionaryEO getDictionaryEOById(String id);
	
	public DictionaryEO getDictionaryEOByDicCode(String dictionaryCode);

	public DictionaryEO getDicEOAndTypeEoByDicCode(String dictionaryCode);

	public DictionaryEO getDictionaryEOByDicName(String dictionaryName);

	public void deleteDic(String id);

}
