package com.adc.da.lawss.service;

import com.adc.da.lawss.common.PropertyTypeEnum;
import com.adc.da.lawss.dao.SarProductValEODao;
import com.adc.da.lawss.entity.*;
import com.adc.da.lawss.page.SarProductInfoEOPage;
import com.adc.da.sys.constant.ValueStateEnum;
import com.adc.da.sys.dao.DicTypeEODao;
import com.adc.da.sys.entity.DicTypeEO;
import com.adc.da.sys.util.UUIDUtils;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.utils.StringUtils;
import com.adc.da.util.utils.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.base.service.BaseService;
import com.adc.da.lawss.dao.SarProductInfoEODao;

import java.util.*;


/**
 *
 * <br>
 * <b>功能：</b>SAR_PRODUCT_INFO SarProductInfoEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-03 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("sarProductInfoEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class SarProductInfoEOService extends BaseService<SarProductInfoEO, String> {

    private static final Logger logger = LoggerFactory.getLogger(SarProductInfoEOService.class);

    @Autowired
    private SarProductInfoEODao sarProductInfoEODao;

    @Autowired
    private SarProductValEODao sarProductValEODao;

    @Autowired
    private DicTypeEODao dicTypeEODao;

    public SarProductInfoEODao getDao() {
        return sarProductInfoEODao;
    }

    public List<SarProductInfoEO> querySarProductInfoByPage(SarProductInfoEOPage page){
        Integer rowCount = sarProductInfoEODao.getSarProductInfoCount(page);
        page.getPager().setRowCount(rowCount);
        List<SarProductInfoEO> sarlist = sarProductInfoEODao.getSarProductInfoPage(page);
        return sarlist;
    }

    public ResponseMessage<SarProductInfoEO> createSarProductInfo(SarProductInfoEO sarProductInfoEO){
        //表中插入一条数据
        sarProductInfoEO.setCreationUser("gaoyan");
        sarProductInfoEO.setCreationTime(new Date());
        sarProductInfoEO.setModifyTime(new Date());
        sarProductInfoEO.setId(UUIDUtils.randomUUID20());
        int insertresult = sarProductInfoEODao.insertSelective(sarProductInfoEO);
        if(insertresult>=1){
            //标准关联表中插入数据，对于标准多选属性，标准信息表中存储已逗号隔开，标准关联表中还需要存入相关数据
            //具体多选属性包括( 适用车型:productType 能源种类:emergyKind )
            insertSarProductVal(sarProductInfoEO);
            return  Result.success("00","插入数据成功",sarProductInfoEO);
        }
        else {
            return Result.error("01","插入输入过程中出错");
        }
    }

    public ResponseMessage<SarProductInfoEO> updateSarProductInfo(SarProductInfoEO sarProductInfoEO){
        sarProductInfoEO.setModifyTime(new Date());
        sarProductInfoEODao.updateByPrimaryKeySelective(sarProductInfoEO);
        //先删除标准关联表，然后插入数据
        sarProductValEODao.deleteDataByProductid(sarProductInfoEO.getId());
        insertSarProductVal(sarProductInfoEO);
        return  Result.success("00","修改数据成功",sarProductInfoEO);
    }

    public void insertSarProductVal(SarProductInfoEO sarStandardsInfoEO){
        SarProductValEO sarProductValEO = new SarProductValEO();
        sarProductValEO.setValidFlag(ValueStateEnum.VALUE_TRUE.getValue());
        sarProductValEO.setCreationTime(new Date());
        sarProductValEO.setModifyTime(new Date());
        sarProductValEO.setProductId(sarStandardsInfoEO.getId());
        if(org.apache.commons.lang.StringUtils.isNotEmpty(sarStandardsInfoEO.getProductType())){
            String []applyarr = sarStandardsInfoEO.getProductType().trim().split(",");
            for (String apply:applyarr){
                sarProductValEO.setId(UUID.randomUUID(20));
                sarProductValEO.setPropertyType(PropertyTypeEnum.PRODUCT_TYPE.getValue());
                sarProductValEO.setPropertyValue(apply);
                sarProductValEODao.insertSelective(sarProductValEO);
            }
        }
        if(org.apache.commons.lang.StringUtils.isNotEmpty(sarStandardsInfoEO.getEnergyKind())){
            String []emergyKindarr = sarStandardsInfoEO.getEnergyKind().trim().split(",");
            for (String emergyKind:emergyKindarr){
                sarProductValEO.setId(UUID.randomUUID(20));
                sarProductValEO.setPropertyType(PropertyTypeEnum.ENERGY_KIND.getValue());
                sarProductValEO.setPropertyValue(emergyKind);
                sarProductValEODao.insertSelective(sarProductValEO);
            }
        }
    }

    public Map<String,List<SarProductStandEO>> selectProductLawAndStandByKey(SarProductInfoEOPage sarProductInfoEOPage){
        //查询所有的认证类型
        List<DicTypeEO> dictypelist = dicTypeEODao.getDicTypeByDicCode("PROVETYPE");
        Map<String,List<SarProductStandEO>> map =new HashMap<>();
        for (DicTypeEO dicTypeEO : dictypelist){
            sarProductInfoEOPage.setTypeCode(dicTypeEO.getDicTypeCode());
         List<SarProductStandEO> resultlist = sarProductInfoEODao.selectStandLawsByKey(sarProductInfoEOPage);
         map.put(dicTypeEO.getDicTypeCode(),resultlist);
        }
        return map;
    }

    public List<SarProductStandEO> selectLawAndStandByPro(SarProductInfoEOPage sarProductInfoEOPage){
        String[] protype = sarProductInfoEOPage.getProductType().split(",");
        sarProductInfoEOPage.setProductTypeList(protype);
        String[] energy = sarProductInfoEOPage.getEnergyKind().split(",");
        sarProductInfoEOPage.setEnergyKindList(energy);
        List<SarProductStandEO> resultlist = sarProductInfoEODao.selectLawAndStandByPro(sarProductInfoEOPage);
        return resultlist;
    }
}
