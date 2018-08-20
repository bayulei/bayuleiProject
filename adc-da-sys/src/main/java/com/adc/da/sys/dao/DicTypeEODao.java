package com.adc.da.sys.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.sys.entity.DicTypeEO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DicTypeEODao extends BaseDao<DicTypeEO>{

	public DicTypeEO getDicTypeEOById(String id);

	public void deleteDicTypeByIdInBatch(List<String> ids);

	List<DicTypeEO> getTypeIdByDicIdAndTypeName(@Param("dicId") String dicId, @Param("typeName") String typeName);


	List<DicTypeEO> getDicTypeEOByDicTypeCode(String dicTypeCode);

    void batchInsertTypeEo(List<DicTypeEO> ModelType);

    public void deleteDicTypeByDicId(String id);
    
	public void deleteFlagTo1(String id);
}
