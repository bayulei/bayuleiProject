package com.adc.da.lawss.service;

import com.adc.da.lawss.dto.SarStandExcelDto;
import com.adc.da.lawss.dto.SarTestItemExportDto;
import com.adc.da.lawss.page.SarStandardsInfoEOPage;
import com.adc.da.lawss.page.SarTestItemEOPage;
import com.adc.da.sys.util.UUIDUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.base.service.BaseService;
import com.adc.da.lawss.dao.SarTestItemEODao;
import com.adc.da.lawss.entity.SarTestItemEO;

import java.util.Date;
import java.util.List;


/**
 *
 * <br>
 * <b>功能：</b>SAR_TEST_ITEM SarTestItemEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-03 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("sarTestItemEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class SarTestItemEOService extends BaseService<SarTestItemEO, String> {

    private static final Logger logger = LoggerFactory.getLogger(SarTestItemEOService.class);

    @Autowired
    private SarTestItemEODao dao;

    public SarTestItemEODao getDao() {
        return dao;
    }
//liwenxuan:新增
    public int insertSelective(SarTestItemEO sarTestItemEO) throws Exception {
        //todo 创建人需要从session中获取
        sarTestItemEO.setCreationUser("从session中获取");
        sarTestItemEO.setModifyTime(new Date());
        sarTestItemEO.setCreationTime(new Date());
        sarTestItemEO.setId(UUIDUtils.randomUUID20());
        sarTestItemEO.setValidFlag(0);
        return dao.insertSelective(sarTestItemEO);
    }
    //liwenxuan:修改
    public int updateByPrimaryKeySelective(SarTestItemEO sarTestItemEO) throws Exception {
        sarTestItemEO.setCreationUser("从session中获取");
        sarTestItemEO.setModifyTime(new Date());
        return dao.updateByPrimaryKeySelective(sarTestItemEO);
    }
    //liwenxuan:删除多条记录
    public int deleteByPrimaryKeyList(List<String> ids){
        return dao.deleteByPrimaryKeyList(ids);
    }


    public List<SarTestItemExportDto> getSarStandardsInfo(SarTestItemEOPage page){
        return  dao.getSarStandardsInfo(page);
    }
}
