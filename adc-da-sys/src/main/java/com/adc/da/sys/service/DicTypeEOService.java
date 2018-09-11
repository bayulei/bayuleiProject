package com.adc.da.sys.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.base.service.BaseService;
import com.adc.da.sys.dao.DicTypeEODao;
import com.adc.da.sys.entity.DicTypeEO;
import com.adc.da.util.constant.DeleteFlagEnum;
import com.adc.da.util.utils.UUID;

@Service("dicTypeEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class DicTypeEOService extends BaseService<DicTypeEO, String> {
    private static final Logger logger = LoggerFactory.getLogger(DicTypeEO.class);

    @Autowired
    private DicTypeEODao dicTypeEODao;


    public DicTypeEODao getDao() {
        return dicTypeEODao;
    }
//李文轩：此方法对应的sql语句必须有parentId，不传入parentId就会报错
    public DicTypeEO save(DicTypeEO dicTypeEO) {

        String parentId = dicTypeEO.getParentId();

        dicTypeEO.setId(UUID.randomUUID10());
        dicTypeEO.setValidFlag(DeleteFlagEnum.NORMAL.getValue());

		if(parentId!=null){
			dicTypeEO.setParentId(parentId);
		}

        dicTypeEODao.insert(dicTypeEO);
        return dicTypeEO;
    }

    /**
     * 查询字典详情
     */
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public DicTypeEO getDicTypeById(String id) {
        return dicTypeEODao.getDicTypeEOById(id);
    }

    public void delete(List<String> ids) {
        dicTypeEODao.deleteDicTypeByIdInBatch(ids);
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<DicTypeEO> getTypeIdByDicIdAndTypeName(String dicId, String typeName) {
        return dicTypeEODao.getTypeIdByDicIdAndTypeName(dicId, typeName);
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<DicTypeEO> getDicTypeEOByDicTypeCode(String dicTypeCode) {
        return dicTypeEODao.getDicTypeEOByDicTypeCode(dicTypeCode);
    }

	public void deleteDicTypeByDicId(String id) {
		dicTypeEODao.deleteDicTypeByDicId(id);
	}

    /**
     * 新增数据字典参数表
     * */
    public DicTypeEO saveDictype(DicTypeEO dicTypeEO){
        dicTypeEO.setId(UUID.randomUUID10());
        dicTypeEO.setValidFlag(DeleteFlagEnum.NORMAL.getValue());
        dicTypeEODao.insertSelective(dicTypeEO);
        return dicTypeEO;
    }

    /**
     * @Author yangxuenan
     * @Description 根据数据字典编码查询字典类型
     * Date 2018/9/11 15:09
     * @Param [dictionaryCode]
     * @return java.util.List<com.adc.da.sys.entity.DicTypeEO>
     **/
    public List<DicTypeEO> getDicTypeByDicCode(String dictionaryCode){
        return dicTypeEODao.getDicTypeByDicCode(dictionaryCode);
    }
}
