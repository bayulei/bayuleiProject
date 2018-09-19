package com.adc.da.sys.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.sys.entity.DicTypeEO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DicTypeEODao extends BaseDao<DicTypeEO>{

	public DicTypeEO getDicTypeEOById(String id);

	public void deleteDicTypeByIdInBatch(List<String> ids);

	List<DicTypeEO> getTypeIdByDicIdAndTypeName(@Param("dicId") String dicId,@Param("id") String id, @Param("typeName") String typeName);


	List<DicTypeEO> getDicTypeEOByDicTypeCode( @Param("dicId") String dicId,@Param("id") String id,@Param("dicTypeCode")String dicTypeCode);

    void batchInsertTypeEo(List<DicTypeEO> ModelType);

    public void deleteDicTypeByDicId(String id);
    
	public void deleteFlagTo1(String id);

	/**
	 * @Author yangxuenan
	 * @Description 根据数据字典编码查询字典类型
	 * Date 2018/9/11 15:09
	 * @Param [dictionaryCode]
	 * @return java.util.List<com.adc.da.sys.entity.DicTypeEO>
	 **/
	public List<DicTypeEO> getDicTypeByDicCode(String dictionaryCode);
}
