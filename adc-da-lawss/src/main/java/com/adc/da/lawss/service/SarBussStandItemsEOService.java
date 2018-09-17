package com.adc.da.lawss.service;

import com.adc.da.lawss.common.PropertyTypeEnum;
import com.adc.da.lawss.dao.SarBussStandItemValEODao;
import com.adc.da.lawss.dto.SarBussStandtemsExcelDto;
import com.adc.da.lawss.dto.SarStandItemsExcelDto;
import com.adc.da.lawss.entity.SarBussStandItemValEO;
import com.adc.da.lawss.entity.SarStandItemValEO;
import com.adc.da.lawss.entity.SarStandItemsEO;
import com.adc.da.lawss.page.SarBussStandItemsEOPage;
import com.adc.da.lawss.vo.SarStandItemsVO;
import com.adc.da.sys.constant.ValueStateEnum;
import com.adc.da.sys.util.UUIDUtils;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.base.service.BaseService;
import com.adc.da.lawss.dao.SarBussStandItemsEODao;
import com.adc.da.lawss.entity.SarBussStandItemsEO;

import java.util.Date;
import java.util.List;


/**
 *
 * <br>
 * <b>功能：</b>SAR_BUSS_STAND_ITEMS SarBussStandItemsEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-03 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("sarBussStandItemsEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class SarBussStandItemsEOService extends BaseService<SarBussStandItemsEO, String> {

    private static final Logger logger = LoggerFactory.getLogger(SarBussStandItemsEOService.class);

    @Autowired
    private SarBussStandItemsEODao dao;

    @Autowired
    private SarBussStandItemValEODao sarBussStandItemValEODao;


    public SarBussStandItemsEODao getDao() {
        return dao;
    }

    public List<SarBussStandItemsEO> querySarBussStandItemsList(SarBussStandItemsEOPage page) {
        List<SarBussStandItemsEO> list = dao.querySarBussStandItemsList(page);
        return list;
    }

    public ResponseMessage<SarBussStandItemsEO> addSarBussStandItems(SarBussStandItemsEO sarBussStandItemsEO) throws Exception {
        sarBussStandItemsEO.setValidFlag(ValueStateEnum.VALUE_TRUE.getValue());
        sarBussStandItemsEO.setCreationTime(new Date());
        sarBussStandItemsEO.setModifyTime(new Date());
        sarBussStandItemsEO.setId(UUIDUtils.randomUUID20());
        int resultint = dao.insertSelective(sarBussStandItemsEO);
        if(resultint==1){
            //标准关联条目表里插入数据
            //涉及到多选的条件： 适用车型：APPLY_ARCTIC， 能源种类：ENERGY_KIND
            SarBussStandItemValEO sarStandItemValEO = new SarBussStandItemValEO();
            sarStandItemValEO.setValidFlag(ValueStateEnum.VALUE_TRUE.getValue());
            sarStandItemValEO.setCreationTime(new Date());
            sarStandItemValEO.setModifyTime(new Date());
            sarStandItemValEO.setItemId(sarBussStandItemsEO.getId());
            if(StringUtils.isNotEmpty(sarBussStandItemsEO.getApplyArctic())){
                String[] applyarr = sarBussStandItemsEO.getApplyArctic().trim().split(",");
                for(String applya:applyarr){
                    sarStandItemValEO.setId(UUIDUtils.randomUUID20());
                    sarStandItemValEO.setPropertyType(PropertyTypeEnum.APPLY_ARCTIC.getValue());
                    sarStandItemValEO.setPropertyValue(applya);
                    sarBussStandItemValEODao.insertSelective(sarStandItemValEO);
                }
            }
            if(StringUtils.isNotEmpty(sarBussStandItemsEO.getEnergyKind())){
                String[] energyarr = sarBussStandItemsEO.getEnergyKind().trim().split(",");
                for(String energy:energyarr){
                    sarStandItemValEO.setId(UUIDUtils.randomUUID20());
                    sarStandItemValEO.setPropertyType(PropertyTypeEnum.ENERGY_KIND.getValue());
                    sarStandItemValEO.setPropertyValue(energy);
                    sarBussStandItemValEODao.insertSelective(sarStandItemValEO);
                }
            }
            return Result.success(sarBussStandItemsEO);
        }
        else {
            return Result.error("01","插入输入过程中出错");
        }
    }

    public ResponseMessage<SarBussStandItemsEO>  updateSarBussStandItemsEO(SarBussStandItemsEO sarBussStandItemsEO){

        sarBussStandItemsEO.setModifyTime(new Date());
        int resultint = dao.updateByPrimaryKeySelective(sarBussStandItemsEO);
        if(resultint==1){
            //标准关联条目表里修改数据 涉及到多选的条件： 适用车型：APPLY_ARCTIC， 能源种类：ENERGY_KIND（先删除后添加）
            //删除过程
            sarBussStandItemValEODao.deleteSarBussStandItemByItemid(sarBussStandItemsEO.getId());

            //添加过程
            SarBussStandItemValEO sarStandItemValEO = new SarBussStandItemValEO();
            sarStandItemValEO.setValidFlag(ValueStateEnum.VALUE_TRUE.getValue());
            sarStandItemValEO.setCreationTime(new Date());
            sarStandItemValEO.setModifyTime(new Date());
            sarStandItemValEO.setItemId(sarBussStandItemsEO.getId());
            if(StringUtils.isNotEmpty(sarBussStandItemsEO.getApplyArctic())){
                String[] applyarr = sarBussStandItemsEO.getApplyArctic().trim().split(",");
                for(String applya:applyarr){
                    sarStandItemValEO.setId(UUIDUtils.randomUUID20());
                    sarStandItemValEO.setPropertyType(PropertyTypeEnum.APPLY_ARCTIC.getValue());
                    sarStandItemValEO.setPropertyValue(applya);
                    sarBussStandItemValEODao.insertSelective(sarStandItemValEO);
                }
            }
            if(StringUtils.isNotEmpty(sarBussStandItemsEO.getEnergyKind())){
                String[] energyarr = sarBussStandItemsEO.getEnergyKind().trim().split(",");
                for(String energy:energyarr){
                    sarStandItemValEO.setId(UUIDUtils.randomUUID20());
                    sarStandItemValEO.setPropertyType(PropertyTypeEnum.ENERGY_KIND.getValue());
                    sarStandItemValEO.setPropertyValue(energy);
                    sarBussStandItemValEODao.insertSelective(sarStandItemValEO);
                }
            }
            return Result.success(sarBussStandItemsEO);
        }
        else {
            return Result.error("01","插入输入过程中出错");
        }
    }

    public ResponseMessage<SarBussStandItemsEO> importSarBussStandItemsData(List<SarBussStandtemsExcelDto> list, String standId){
        //此处需要做各种验证，数据库操作
        //将导入数据循环新增至相应表
        for(SarBussStandtemsExcelDto importDto : list){
            SarBussStandItemsEO sarStandItemsEO = new SarBussStandItemsEO();
            BeanUtils.copyProperties(importDto,sarStandItemsEO);
            try {
                sarStandItemsEO.setStandId(standId);
                addSarBussStandItems(sarStandItemsEO);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return Result.success("0","导入数据成功");
    }
}
