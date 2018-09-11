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
import com.adc.da.lawss.dao.SarMenuEODao;
import com.adc.da.lawss.entity.SarMenuEO;

import java.util.Date;
import java.util.List;


/**
 *
 * <br>
 * <b>功能：</b>SAR_MENU SarMenuEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-03 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("sarMenuEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class SarMenuEOService extends BaseService<SarMenuEO, String> {

    private static final Logger logger = LoggerFactory.getLogger(SarMenuEOService.class);

    @Autowired
    private SarMenuEODao dao;

    public SarMenuEODao getDao() {
        return dao;
    }

    /**
     * @Author yangxuenan
     * @Description 新增标准目录
     * Date 2018/9/11 16:52
     * @Param [sarMenuEO]
     * @return com.adc.da.util.http.ResponseMessage<com.adc.da.lawss.entity.SarMenuEO>
     **/
    public ResponseMessage<SarMenuEO> createSarMenu(SarMenuEO sarMenuEO) throws Exception {
        sarMenuEO.setId(UUID.randomUUID(20));
        sarMenuEO.setValidFlag(0);
        sarMenuEO.setCreationTime(new Date());
        sarMenuEO.setModifyTime(new Date());
        Integer countMenu = dao.insertSelective(sarMenuEO);
        if(countMenu > 0){
            return Result.success("0","新增成功",sarMenuEO);
        } else {
            return Result.error("新增失败！");
        }
    }

    /**
     * @Author yangxuenan
     * @Description 根据父ID查询子节点
     * Date 2018/9/11 17:03
     * @Param [parentId]
     * @return java.util.List<com.adc.da.lawss.entity.SarMenuEO>
     **/
    public List<SarMenuEO> queryMenuByPid(String parentId) throws Exception {
        return dao.queryMenuByPid(parentId);
    }

}
