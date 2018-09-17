package com.adc.da.lawss.service;

import com.adc.da.lawss.common.PropertyTypeEnum;
import com.adc.da.lawss.dao.SarStandItemValEODao;
import com.adc.da.lawss.dto.SarStandExcelDto;
import com.adc.da.lawss.dto.SarStandItemsExcelDto;
import com.adc.da.lawss.entity.SarStandItemValEO;
import com.adc.da.lawss.entity.SarStandardsInfoEO;
import com.adc.da.lawss.page.SarStandItemsEOPage;
import com.adc.da.lawss.page.SarStandardsInfoEOPage;
import com.adc.da.lawss.vo.SarStandItemsVO;
import com.adc.da.sys.constant.ValueStateEnum;
import com.adc.da.sys.util.UUIDUtils;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.utils.UUID;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.base.service.BaseService;
import com.adc.da.lawss.dao.SarStandItemsEODao;
import com.adc.da.lawss.entity.SarStandItemsEO;

import java.util.*;


/**
 *
 * <br>
 * <b>功能：</b>SAR_STAND_ITEMS SarStandItemsEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-03 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("sarStandItemsEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class SarStandItemsEOService extends BaseService<SarStandItemsEO, String> {

    private static final Logger logger = LoggerFactory.getLogger(SarStandItemsEOService.class);

    @Autowired
    private SarStandItemsEODao dao;

    @Autowired
    private SarStandItemValEODao sarStandItemValEODao;

    public SarStandItemsEODao getDao() {
        return dao;
    }


    /**
     * 查询标准分解单
     * @param  page
     * @return
     * @author gaoyan
     * date 2018-09-04
     */
    public List<SarStandItemsVO> querySarStandItemsList(SarStandItemsEOPage page) throws Exception {
        //page正常情况下只有标准ID和责任部门两个查询条件
        List<SarStandItemsVO> list = dao.querySarStandItemsList(page);
        //涉及到多选的条件： 适用车型：APPLY_ARCTIC， 能源种类：ENERGY_KIND
        return  list;
    }

    /**
     * 添加标准分解单
     * @param  sarStandItemsEO
     * @return
     * @author gaoyan
     * date 2018-09-04
     */
    public ResponseMessage<SarStandItemsEO> addSarStandItems(SarStandItemsEO sarStandItemsEO) throws Exception {
        sarStandItemsEO.setValidFlag(ValueStateEnum.VALUE_TRUE.getValue());
        sarStandItemsEO.setCreationTime(new Date());
        sarStandItemsEO.setModifyTime(new Date());
        sarStandItemsEO.setCreationUser("gaoyan");
        sarStandItemsEO.setId(UUIDUtils.randomUUID20());
        int resultint = dao.insertSelective(sarStandItemsEO);
        if(resultint==1){
            //标准关联条目表里插入数据
            //涉及到多选的条件： 适用车型：APPLY_ARCTIC， 能源种类：ENERGY_KIND
            SarStandItemValEO sarStandItemValEO = new SarStandItemValEO();
            sarStandItemValEO.setValidFlag(ValueStateEnum.VALUE_TRUE.getValue());
            sarStandItemValEO.setCreationTime(new Date());
            sarStandItemValEO.setModifyTime(new Date());
            sarStandItemValEO.setItemId(sarStandItemsEO.getId());
            if(StringUtils.isNotEmpty(sarStandItemsEO.getApplyArctic())){
                String[] applyarr = sarStandItemsEO.getApplyArctic().trim().split(",");
                for(String applya:applyarr){
                    sarStandItemValEO.setId(UUIDUtils.randomUUID20());
                    sarStandItemValEO.setPropertyType(PropertyTypeEnum.APPLY_ARCTIC.getValue());
                    sarStandItemValEO.setPropertyVal(applya);
                    sarStandItemValEODao.insertSelective(sarStandItemValEO);
                }
            }
            if(StringUtils.isNotEmpty(sarStandItemsEO.getEnergyKind())){
                String[] energyarr = sarStandItemsEO.getEnergyKind().trim().split(",");
                for(String energy:energyarr){
                    sarStandItemValEO.setId(UUIDUtils.randomUUID20());
                    sarStandItemValEO.setPropertyType(PropertyTypeEnum.ENERGY_KIND.getValue());
                    sarStandItemValEO.setPropertyVal(energy);
                    sarStandItemValEODao.insertSelective(sarStandItemValEO);
                }
            }
            return Result.success(sarStandItemsEO);
        }
        else {
            return Result.error("01","插入输入过程中出错");
        }
    }

    /**
     * EXCEL导入标准分解单
     * @param
     * @return
     * @author gaoyan
     * date 2018-09-04
     */
    public ResponseMessage<SarStandItemsEO> importSarStandItemsData(List<SarStandItemsExcelDto> list,String standId){
        //此处需要做各种验证，数据库操作
        //将导入数据循环新增至相应表
        for(SarStandItemsExcelDto importDto : list){
            SarStandItemsEO sarStandItemsEO = new SarStandItemsEO();
            BeanUtils.copyProperties(importDto,sarStandItemsEO);
            try {
                sarStandItemsEO.setStandId(standId);
                addSarStandItems(sarStandItemsEO);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return Result.success("0","导入数据成功");
    }

    /**
     * 修改标准分解单标准分解单
     * @param
     * @return
     * @author gaoyan
     * date 2018-09-04
     */
    public ResponseMessage<SarStandItemsEO>  updateSarStandItemsEO(SarStandItemsEO sarStandItemsEO){

        sarStandItemsEO.setModifyTime(new Date());
        int resultint = dao.updateByPrimaryKeySelective(sarStandItemsEO);
        if(resultint==1){
            //标准关联条目表里修改数据 涉及到多选的条件： 适用车型：APPLY_ARCTIC， 能源种类：ENERGY_KIND（先删除后添加）
            //删除过程
            sarStandItemValEODao.deleteSarStandItemValByItemid(sarStandItemsEO.getId());
            //添加过程
            SarStandItemValEO sarStandItemValEO = new SarStandItemValEO();
            sarStandItemValEO.setValidFlag(ValueStateEnum.VALUE_TRUE.getValue());
            sarStandItemValEO.setCreationTime(new Date());
            sarStandItemValEO.setModifyTime(new Date());
            sarStandItemValEO.setItemId(sarStandItemsEO.getId());
            if(StringUtils.isNotEmpty(sarStandItemsEO.getApplyArctic())){
                String[] applyarr = sarStandItemsEO.getApplyArctic().trim().split(",");
                for(String applya:applyarr){
                    sarStandItemValEO.setId(UUIDUtils.randomUUID20());
                    sarStandItemValEO.setPropertyType(PropertyTypeEnum.APPLY_ARCTIC.getValue());
                    sarStandItemValEO.setPropertyVal(applya);
                    sarStandItemValEODao.insertSelective(sarStandItemValEO);
                }
            }
            if(StringUtils.isNotEmpty(sarStandItemsEO.getEnergyKind())){
                String[] energyarr = sarStandItemsEO.getEnergyKind().trim().split(",");
                for(String energy:energyarr){
                    sarStandItemValEO.setId(UUIDUtils.randomUUID20());
                    sarStandItemValEO.setPropertyType(PropertyTypeEnum.ENERGY_KIND.getValue());
                    sarStandItemValEO.setPropertyVal(energy);
                    sarStandItemValEODao.insertSelective(sarStandItemValEO);
                }
            }
            return Result.success(sarStandItemsEO);
        }
        else {
            return Result.error("01","插入输入过程中出错");
        }
    }



}
