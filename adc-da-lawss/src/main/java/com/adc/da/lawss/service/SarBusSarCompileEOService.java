package com.adc.da.lawss.service;

import com.adc.da.lawss.page.SarBusSarCompileEOPage;
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
import com.adc.da.lawss.dao.SarBusSarCompileEODao;
import com.adc.da.lawss.entity.SarBusSarCompileEO;

import java.util.Date;
import java.util.List;


/**
 *
 * <br>
 * <b>功能：</b>SAR_BUS_SAR_COMPILE SarBusSarCompileEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-03 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("sarBusSarCompileEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class SarBusSarCompileEOService extends BaseService<SarBusSarCompileEO, String> {

    private static final Logger logger = LoggerFactory.getLogger(SarBusSarCompileEOService.class);

    @Autowired
    private SarBusSarCompileEODao dao;

    public SarBusSarCompileEODao getDao() {
        return dao;
    }

    /**
     * @Author yangxuenan
     * @Description 新增标准台账
     * Date 2018/9/17 9:07
     * @Param [sarBusSarCompileEO]
     * @return com.adc.da.util.http.ResponseMessage<com.adc.da.lawss.entity.SarBusSarCompileEO>
     **/
    public ResponseMessage<SarBusSarCompileEO> insertSarBusSarCompile(SarBusSarCompileEO sarBusSarCompileEO) throws Exception {
        SarBusSarCompileEOPage sarBussPage = new SarBusSarCompileEOPage();
        sarBussPage.setStandNumber(sarBusSarCompileEO.getStandNumber());
        sarBussPage.setValidFlag("0");
        List<SarBusSarCompileEO> getList = dao.queryByStandCode(sarBussPage);
        if(getList != null){
            if(getList.size() > 0){
                return Result.error("新增失败,企业标准编码已存在！");
            } else {
                sarBusSarCompileEO.setId(UUID.randomUUID(20));
                sarBusSarCompileEO.setValidFlag(0);
                sarBusSarCompileEO.setCreationTime(new Date());
                sarBusSarCompileEO.setModifyTime(new Date());
                int countAdd = dao.insertSelective(sarBusSarCompileEO);

                if(countAdd > 0){
                    return Result.success("0","新增成功",sarBusSarCompileEO);
                } else {
                    return Result.error("新增失败");
                }
            }
        } else {
            sarBusSarCompileEO.setId(UUID.randomUUID(20));
            sarBusSarCompileEO.setValidFlag(0);
            sarBusSarCompileEO.setCreationTime(new Date());
            sarBusSarCompileEO.setModifyTime(new Date());
            int countAdd = dao.insertSelective(sarBusSarCompileEO);

            if(countAdd > 0){
                return Result.success("0","新增成功",sarBusSarCompileEO);
            } else {
                return Result.error("新增失败");
            }
        }
    }

    /**
     * @Author yangxuenan
     * @Description  修改标准台账
     * Date 2018/9/17 9:45
     * @Param [sarBusSarCompileEO]
     * @return com.adc.da.util.http.ResponseMessage<com.adc.da.lawss.entity.SarBusSarCompileEO>
     **/
    public ResponseMessage<SarBusSarCompileEO> updateSarBusSarCompile(SarBusSarCompileEO sarBusSarCompileEO) throws Exception{
        SarBusSarCompileEOPage sarBussPage = new SarBusSarCompileEOPage();
        sarBussPage.setId(sarBusSarCompileEO.getId());
        sarBussPage.setStandNumber(sarBusSarCompileEO.getStandNumber());
        sarBussPage.setValidFlag("0");
        List<SarBusSarCompileEO> getList = dao.queryByStandCode(sarBussPage);
        if(getList != null){
            if(getList.size() > 0){
                return Result.error("修改失败,企业标准编码已存在！");
            } else {
                sarBusSarCompileEO.setId(sarBusSarCompileEO.getId());
                sarBusSarCompileEO.setModifyTime(new Date());
                int countAdd = dao.updateByPrimaryKeySelective(sarBusSarCompileEO);

                if(countAdd > 0){
                    return Result.success("0","修改成功",sarBusSarCompileEO);
                } else {
                    return Result.error("修改失败");
                }
            }
        } else {
            sarBusSarCompileEO.setId(sarBusSarCompileEO.getId());
            sarBusSarCompileEO.setModifyTime(new Date());
            int countAdd = dao.updateByPrimaryKeySelective(sarBusSarCompileEO);

            if(countAdd > 0){
                return Result.success("0","修改成功",sarBusSarCompileEO);
            } else {
                return Result.error("修改失败");
            }
        }
    }

}
