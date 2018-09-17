package com.adc.da.lawss.service;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.utils.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.base.service.BaseService;
import com.adc.da.lawss.dao.SarBussStandPlanEODao;
import com.adc.da.lawss.entity.SarBussStandPlanEO;

import java.util.Date;


/**
 *
 * <br>
 * <b>功能：</b>SAR_BUSS_STAND_PLAN SarBussStandPlanEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-03 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("sarBussStandPlanEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class SarBussStandPlanEOService extends BaseService<SarBussStandPlanEO, String> {

    private static final Logger logger = LoggerFactory.getLogger(SarBussStandPlanEOService.class);

    @Autowired
    private SarBussStandPlanEODao dao;

    public SarBussStandPlanEODao getDao() {
        return dao;
    }


    /**
     * @Author yangxuenan
     * @Description 新增标准年度计划
     * Date 2018/9/17 15:04
     * @Param [sarBussStandPlanEO]
     * @return com.adc.da.util.http.ResponseMessage<com.adc.da.lawss.entity.SarBussStandPlanEO>
     **/
    public ResponseMessage<SarBussStandPlanEO> insertStandPlan(SarBussStandPlanEO sarBussStandPlanEO)  throws Exception{
        sarBussStandPlanEO.setId(UUID.randomUUID(20));
        sarBussStandPlanEO.setValidFlag(0);
        sarBussStandPlanEO.setCreationTime(new Date());
        sarBussStandPlanEO.setModifyTime(new Date());
        int countAdd = dao.insertSelective(sarBussStandPlanEO);
        if(countAdd > 0){
            return Result.success("0","新增成功",sarBussStandPlanEO);
        } else {
            return Result.error("新增失败");
        }
    }

    /**
     * @Author yangxuenan
     * @Description 修改标准年度计划
     * Date 2018/9/17 15:06
     * @Param [sarBussStandPlanEO]
     * @return com.adc.da.util.http.ResponseMessage<com.adc.da.lawss.entity.SarBussStandPlanEO>
     **/
    public ResponseMessage<SarBussStandPlanEO> updateStandPlan(SarBussStandPlanEO sarBussStandPlanEO)  throws Exception{
        sarBussStandPlanEO.setModifyTime(new Date());
        int countUp = dao.updateByPrimaryKeySelective(sarBussStandPlanEO);
        if(countUp > 0){
            return Result.success("0","修改成功",sarBussStandPlanEO);
        } else {
            return Result.error("修改失败");
        }
    }

}
