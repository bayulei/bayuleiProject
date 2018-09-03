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

    public DicTypeEO save(DicTypeEO dicTypeEO) {
        dicTypeEO.setId(UUID.randomUUID10());
        dicTypeEO.setValidFlag(DeleteFlagEnum.NORMAL.getValue());

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
    public List<DicTypeEO> getTypeIdByDicIdAndTypeName(String dictionaryCode, String typeName) {
        return dicTypeEODao.getTypeIdByDicIdAndTypeName(dictionaryCode, typeName);
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<DicTypeEO> getDicTypeEOByDicTypeCode(String dicTypeCode) {
        return dicTypeEODao.getDicTypeEOByDicTypeCode(dicTypeCode);
    }

	public void deleteFlagTo1(String id) {
		dicTypeEODao.deleteFlagTo1(id);
	}
}
