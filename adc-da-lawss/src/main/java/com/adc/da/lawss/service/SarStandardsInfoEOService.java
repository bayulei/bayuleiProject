package com.adc.da.lawss.service;

import com.adc.da.lawss.dao.SarStandMenuEODao;
import com.adc.da.lawss.entity.SarStandMenuEO;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.base.service.BaseService;
import com.adc.da.lawss.dao.SarStandardsInfoEODao;
import com.adc.da.lawss.entity.SarStandardsInfoEO;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;


/**
 *
 * <br>
 * <b>功能：</b>SAR_STANDARDS_INFO SarStandardsInfoEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-03 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("sarStandardsInfoEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class SarStandardsInfoEOService extends BaseService<SarStandardsInfoEO, String> {

    private static final Logger logger = LoggerFactory.getLogger(SarStandardsInfoEOService.class);

    //标准信息表DAO
    @Autowired
    private SarStandardsInfoEODao sarStandardsInfoEOdao;

    //标准目录关联表DAO
    @Autowired
    private SarStandMenuEODao sarStandMenuEODao;

    public SarStandardsInfoEODao getDao() {
        return sarStandardsInfoEOdao;
    }



    public ResponseMessage<SarStandardsInfoEO> createSarStandardsInfo(@RequestBody SarStandardsInfoEO sarStandardsInfoEO) throws Exception {
        //标准信息表中插入一条数据
        sarStandardsInfoEO.setCreationUser("gaoyan");
        sarStandardsInfoEO.setValidFlag(1);
        sarStandardsInfoEO.setCreationTime(new Date());
        sarStandardsInfoEO.setModifyTime(new Date());
        int insertresult = sarStandardsInfoEOdao.insertSelective(sarStandardsInfoEO);

        if(insertresult>=1){
            //根据新建标准所在目录，确定此处是否需要在标准目录关联表中插入数据
            if(!sarStandardsInfoEO.getMenuId().isEmpty()){
                SarStandMenuEO sarStandMenuEO = new SarStandMenuEO();
                sarStandMenuEO.setStandId(sarStandardsInfoEO.getId());
                sarStandMenuEO.setMenuId(sarStandardsInfoEO.getMenuId());
                sarStandardsInfoEO.setValidFlag(1);
            }

            //标准关联表中插入数据，对于标准多选属性，标准信息表中存储已逗号隔开，标准关联表中还需要存入相关数据
            //具体多选属性包括( 适用车型:applyArctic 能源种类:emergyKind 适用认证:applyAuth 所属类别所属类别:category; 代替标准号)


            //标准文件资源表，标准文件详情表中插入数据


                return  Result.success("00","插入数据成功");
        }
        else {
            return Result.error("01","插入输入过程中出错");
        }
    }

}
