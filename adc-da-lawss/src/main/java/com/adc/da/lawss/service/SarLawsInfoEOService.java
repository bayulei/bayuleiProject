package com.adc.da.lawss.service;

import com.adc.da.lawss.dao.SarLawsMenuEODao;
import com.adc.da.lawss.dao.SarLawsValEODao;
import com.adc.da.lawss.dto.LawsInfoImportDto;
import com.adc.da.lawss.entity.SarLawsMenuEO;
import com.adc.da.lawss.entity.SarLawsValEO;
import com.adc.da.lawss.page.SarLawsMenuEOPage;
import com.adc.da.lawss.page.SarLawsValEOPage;
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
import com.adc.da.lawss.dao.SarLawsInfoEODao;
import com.adc.da.lawss.entity.SarLawsInfoEO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @Author yangxuenan
 * @Description 法规信息
 * Date 2018/9/3 16:36
 * @Param 
 * @return 
 **/
@Service("sarLawsInfoEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class SarLawsInfoEOService extends BaseService<SarLawsInfoEO, String> {

    private static final Logger logger = LoggerFactory.getLogger(SarLawsInfoEOService.class);

    @Autowired
    private SarLawsInfoEODao dao;

    @Autowired
    private SarLawsMenuEODao sarLawsMenuEODao;

    @Autowired
    private SarLawsValEODao sarLawsValEODao;

    public SarLawsInfoEODao getDao() {
        return dao;
    }

    /**
     * @Author yangxuenan
     * @Description 新增法规信息
     * Date 2018/9/3 20:47
     * @Param [sarLawsInfoEO]
     * @return com.adc.da.util.http.ResponseMessage<com.adc.da.lawss.entity.SarLawsInfoEO>
     **/
    public ResponseMessage<SarLawsInfoEO> createLawsInfo(SarLawsInfoEO sarLawsInfoEO) throws Exception {
        String infoId = UUID.randomUUID(20);
        sarLawsInfoEO.setId(infoId);
        sarLawsInfoEO.setValidFlag(0);
        sarLawsInfoEO.setCreationTime(new Date());
        sarLawsInfoEO.setModifyTime(new Date());
        //向法规信息表添加数据
        Integer countInfo = dao.insertSelective(sarLawsInfoEO);
        if(countInfo > 0){
            String menuId = sarLawsInfoEO.getMenuId();
            //判断新增时，是否已选文件夹
            if(StringUtils.isNotEmpty(menuId)){
                SarLawsMenuEO sarLawsMenuEO = new SarLawsMenuEO();
                sarLawsMenuEO.setId(UUID.randomUUID(20));
                sarLawsMenuEO.setLawsId(infoId);
                sarLawsMenuEO.setMenuId(menuId);
                sarLawsMenuEO.setValidFlag(0);
                //向法规目录关联表添加数据
                sarLawsMenuEODao.insertSelective(sarLawsMenuEO);
            }

            SarLawsValEO sarLawsValEO = new SarLawsValEO();
            sarLawsValEO.setId(UUID.randomUUID(20));
            sarLawsValEO.setLawsId(infoId);
            sarLawsValEO.setValidFlag(0);
            sarLawsValEO.setCreationTime(new Date());
            sarLawsValEO.setModifyTime(new Date());
            //向法规关联表添加数据
            sarLawsValEODao.insertSelective(sarLawsValEO);
            return Result.success(sarLawsInfoEO);
        } else {
            return Result.error("新增失败！");
        }
    }

    /**
     * @Author yangxuenan
     * @Description 删除法规信息
     * Date 2018/9/4 9:11
     * @Param [sarLawsInfoEO]
     * @return com.adc.da.util.http.ResponseMessage<com.adc.da.lawss.entity.SarLawsInfoEO>
     **/
    public ResponseMessage<SarLawsInfoEO> deleteLawsInfo(String infoId) throws Exception {
        SarLawsInfoEO sarLawsInfoEO = new SarLawsInfoEO();
        sarLawsInfoEO.setId(infoId);
        sarLawsInfoEO.setValidFlag(1);
        //法规信息表修改有效标识
        Integer countInfo = dao.updateByPrimaryKeySelective(sarLawsInfoEO);
        if(countInfo > 0){
            SarLawsMenuEOPage sarLawsMenuEOpage = new SarLawsMenuEOPage();
            sarLawsMenuEOpage.setLawsId(infoId);
            sarLawsMenuEOpage.setValidFlag("0");
            List<SarLawsMenuEO> listLawsMenu =  sarLawsMenuEODao.selectByLawsInfo(sarLawsMenuEOpage);
            //判断法规目录表是否包含该条法规
            if(!listLawsMenu.isEmpty()){
                for(int j=0;j<listLawsMenu.size();j++){
                    String lawsMenuId = listLawsMenu.get(j).getId();
                    SarLawsMenuEO sarLawsMenuEO = new SarLawsMenuEO();
                    sarLawsMenuEO.setId(lawsMenuId);
                    sarLawsMenuEO.setLawsId(infoId);
                    sarLawsMenuEO.setValidFlag(0);
                    //法规目录关联表修改有效标识
                    sarLawsMenuEODao.updateByPrimaryKeySelective(sarLawsMenuEO);
                }

            }

            SarLawsValEOPage sarLawsValEOPage = new SarLawsValEOPage();
            sarLawsValEOPage.setLawsId(infoId);
            sarLawsValEOPage.setValidFlag("0");
            List<SarLawsValEO> listLawsVal = sarLawsValEODao.selectByLawsVal(sarLawsValEOPage);
            //判断法规关联表是否包含该条法规
            if(!listLawsVal.isEmpty()){
                //法规关联表修改有效标识
                for(int i=0;i<listLawsVal.size();i++){
                    String lawsValId = listLawsVal.get(i).getId();
                    SarLawsValEO sarLawsValEO = new SarLawsValEO();
                    sarLawsValEO.setId(lawsValId);
                    sarLawsValEO.setLawsId(infoId);
                    sarLawsValEO.setValidFlag(1);
                    sarLawsValEODao.updateByPrimaryKeySelective(sarLawsValEO);
                }

            }

            return Result.success(sarLawsInfoEO);
        } else {
            return Result.error("删除失败！");
        }


    }


    /**
     * @Author yangxuenan
     * @Description 导入法规信息
     * Date 2018/9/4 14:28
     * @Param [datas]
     * @return com.adc.da.util.http.ResponseMessage
     **/
    public ResponseMessage importLawsInfoDatas(List<LawsInfoImportDto> datas) throws Exception{

        try{
            //将导入数据循环新增至相应表
            for(LawsInfoImportDto importDto : datas){
                SarLawsInfoEO sarLawsInfoEO = new SarLawsInfoEO();
                sarLawsInfoEO.setCountry(importDto.getCountry());
                sarLawsInfoEO.setLawsProperty(importDto.getLawsProperty());
                sarLawsInfoEO.setLawsNumber(importDto.getLawsNumber());
                sarLawsInfoEO.setLawsName(importDto.getLawsName());
                sarLawsInfoEO.setIssueUnit(importDto.getIssueUnit());
                sarLawsInfoEO.setLawsState(importDto.getLawsStatus());
                sarLawsInfoEO.setIssueTime(importDto.getIssueTime());
                sarLawsInfoEO.setPutTime(importDto.getPutTime());
                sarLawsInfoEO.setReplaceLawsNum(importDto.getReplaceLawsNum());
                sarLawsInfoEO.setApplyArctic(importDto.getApplyArctic());
                sarLawsInfoEO.setEnergyKind(importDto.getEnergyKind());
                sarLawsInfoEO.setApplyAuth(importDto.getApplyAuth());
                sarLawsInfoEO.setResponsibleUnit(importDto.getResponsibleUnit());
                createLawsInfo(sarLawsInfoEO);
            }
            return Result.success();
        } catch (Exception e){
            return Result.error("导入失败");
        }


    }

}
