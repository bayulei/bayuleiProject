package com.adc.da.lawss.service;

import com.adc.da.lawss.common.PropertyTypeEnum;
import com.adc.da.lawss.dao.SarStandItemValEODao;
import com.adc.da.lawss.entity.SarStandItemValEO;
import com.adc.da.lawss.page.SarStandItemsEOPage;
import com.adc.da.lawss.vo.SarStandItemsVO;
import com.adc.da.sys.constant.ValueStateEnum;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.utils.UUID;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
        for(SarStandItemsVO sarStandItemsVO: list){
            if(StringUtils.isNotEmpty(sarStandItemsVO.getApplyArctic())){
                String[] applyarr = sarStandItemsVO.getApplyArctic().trim().split(",");
                List<Map<String,Object>>  applylist = new ArrayList<>();
                for(String applya:applyarr){
                    Map<String,Object> applyamap = new HashMap<>();
                    applyamap.put("id",applya);
                    //此处空的双引号中需要添加查询语句中查到的数据
                    applyamap.put("text","");
                    applylist.add(applyamap);
                }
                sarStandItemsVO.setApplyArcticlist(applylist);
            }
            if(StringUtils.isNotEmpty(sarStandItemsVO.getEnergyKind())){
                String[] energyarr = sarStandItemsVO.getEnergyKind().trim().split(",");
                List<Map<String,Object>>  energylist = new ArrayList<>();
                for(String energy:energyarr){
                    Map<String,Object> energymap = new HashMap<>();
                    energymap.put("id",energy);
                    //此处空的双引号中需要添加查询语句中查到的数据
                    energymap.put("text","");
                    energylist.add(energymap);
                }
                sarStandItemsVO.setEnergyKindlist(energylist);
            }
        }
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
                    sarStandItemValEO.setId(UUID.randomUUID(20));
                    sarStandItemValEO.setPropertyType(PropertyTypeEnum.APPLY_ARCTIC.getValue());
                    sarStandItemValEO.setPropertyVal(applya);
                    sarStandItemValEODao.insertSelective(sarStandItemValEO);
                }
            }
            if(StringUtils.isNotEmpty(sarStandItemsEO.getEnergyKind())){
                String[] energyarr = sarStandItemsEO.getEnergyKind().trim().split(",");
                for(String energy:energyarr){
                    sarStandItemValEO.setId(UUID.randomUUID(20));
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
    public ResponseMessage<SarStandItemsEO> importSarStandItemsData(List<SarStandItemsEO> list){
        //此处需要做各种验证，数据库操作
        SarStandItemsEO sarStandItemsEO = new SarStandItemsEO();
        return Result.success(sarStandItemsEO);
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
                    sarStandItemValEO.setId(UUID.randomUUID(20));
                    sarStandItemValEO.setPropertyType(PropertyTypeEnum.APPLY_ARCTIC.getValue());
                    sarStandItemValEO.setPropertyVal(applya);
                    sarStandItemValEODao.insertSelective(sarStandItemValEO);
                }
            }
            if(StringUtils.isNotEmpty(sarStandItemsEO.getEnergyKind())){
                String[] energyarr = sarStandItemsEO.getEnergyKind().trim().split(",");
                for(String energy:energyarr){
                    sarStandItemValEO.setId(UUID.randomUUID(20));
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
