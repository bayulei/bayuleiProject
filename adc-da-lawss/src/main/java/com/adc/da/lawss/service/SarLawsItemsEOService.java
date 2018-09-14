package com.adc.da.lawss.service;

import com.adc.da.lawss.dao.SarLawsItemValEODao;
import com.adc.da.lawss.dto.LawsItemsImportDto;
import com.adc.da.lawss.entity.SarLawsInfoEO;
import com.adc.da.lawss.entity.SarLawsItemValEO;
import com.adc.da.lawss.page.SarLawsItemValEOPage;
import com.adc.da.lawss.page.SarLawsItemsEOPage;
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
import com.adc.da.lawss.dao.SarLawsItemsEODao;
import com.adc.da.lawss.entity.SarLawsItemsEO;

import java.util.*;


/**
 *
 * <br>
 * <b>功能：</b>SAR_LAWS_ITEMS SarLawsItemsEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-13 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("sarLawsItemsEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class SarLawsItemsEOService extends BaseService<SarLawsItemsEO, String> {

    private static final Logger logger = LoggerFactory.getLogger(SarLawsItemsEOService.class);

    @Autowired
    private SarLawsItemsEODao dao;

    @Autowired
    private SarLawsItemValEODao sarLawsItemValEODao;


    public SarLawsItemsEODao getDao() {
        return dao;
    }


    /**
     * @Author yangxuenan
     * @Description 新增法规条目
     * Date 2018/9/13 11:01
     * @Param [sarLawsItemsEO]
     * @return com.adc.da.util.http.ResponseMessage<com.adc.da.lawss.entity.SarLawsItemsEO>
     **/
    public ResponseMessage<SarLawsItemsEO> createLawsItems(SarLawsItemsEO sarLawsItemsEO) throws Exception {
        String itemId = UUID.randomUUID(20);
        sarLawsItemsEO.setId(itemId);
        sarLawsItemsEO.setCreationTime(new Date());
        sarLawsItemsEO.setModifyTime(new Date());
        sarLawsItemsEO.setValidFlag(0);
        int countAdd = dao.insertSelective(sarLawsItemsEO);
        if(countAdd > 0){
            insertItemsVal(sarLawsItemsEO,itemId);
            return Result.success("0","新增成功",sarLawsItemsEO);
        } else {
            return Result.error("新增失败");
        }
    }

    /**
     * @Author yangxuenan
     * @Description 向关联表中添加数据
     * Date 2018/9/14 10:29
     * @Param [itemEO, itemsId]
     * @return void
     **/
    public void insertItemsVal(SarLawsItemsEO itemEO,String itemsId) {
        //向关联表中添加适用车型数据
        String applyArctics = itemEO.getApplyArctic();
        if(StringUtils.isNotEmpty(applyArctics)) {
            String[] applyArctic = applyArctics.split(",");
            for(int a=0;a<applyArctic.length;a++){
                SarLawsItemValEO sarLawsItemValEO = new SarLawsItemValEO();
                sarLawsItemValEO.setId(UUID.randomUUID(20));
                sarLawsItemValEO.setLawsItemId(itemsId);
                sarLawsItemValEO.setPropertyType("PRODUCTTYPE");
                sarLawsItemValEO.setPropertyVal(applyArctic[a]);
                sarLawsItemValEO.setValidFlag(0);
                sarLawsItemValEO.setCreationTime(new Date());
                sarLawsItemValEO.setModifyTime(new Date());
                sarLawsItemValEODao.insertSelective(sarLawsItemValEO);
            }
        }

        //向关联表中添加能源种类数据
        String energyKinds = itemEO.getEnergyKind();
        if(StringUtils.isNotEmpty(energyKinds)){
            String[] energyKind = energyKinds.split(",");
            for(int e=0;e<energyKind.length;e++){
                SarLawsItemValEO sarLawsItemValEO = new SarLawsItemValEO();
                sarLawsItemValEO.setId(UUID.randomUUID(20));
                sarLawsItemValEO.setLawsItemId(itemsId);
                sarLawsItemValEO.setPropertyType("ENERGYTYPES");
                sarLawsItemValEO.setPropertyVal(energyKind[e]);
                sarLawsItemValEO.setValidFlag(0);
                sarLawsItemValEO.setCreationTime(new Date());
                sarLawsItemValEO.setModifyTime(new Date());
                sarLawsItemValEODao.insertSelective(sarLawsItemValEO);
            }
        }
    }

    /**
     * @Author yangxuenan
     * @Description 修改条目
     * Date 2018/9/14 10:43
     * @Param [sarLawsItemsEO]
     * @return com.adc.da.util.http.ResponseMessage<com.adc.da.lawss.entity.SarLawsItemsEO>
     **/
    public ResponseMessage<SarLawsItemsEO> updateLawsItems(SarLawsItemsEO sarLawsItemsEO) throws Exception {
        //修改info表信息
        int countUp = dao.updateByPrimaryKeySelective(sarLawsItemsEO);
        if(countUp > 0){
            String itemId = sarLawsItemsEO.getId();
            SarLawsItemValEOPage sarLawsItemValEOPage = new SarLawsItemValEOPage();
            sarLawsItemValEOPage.setLawsItemId(itemId);
            sarLawsItemValEOPage.setValidFlag("0");
            //查询条目信息关联表是否包含相关数据
            List<SarLawsItemValEO> getItemVal = sarLawsItemValEODao.queryByList(sarLawsItemValEOPage);
            //判断条目关联表是否包含该条目
            if(getItemVal != null){
                if(!getItemVal.isEmpty()){
                    //法规关联表修改有效标识（删除旧关联表信息）
                    for(int i=0;i<getItemVal.size();i++){
                        String itemsValId = getItemVal.get(i).getId();
                        sarLawsItemValEODao.deleteItemsValById(itemsValId);
                    }
                }
            }
            //添加新的关联表信息
            insertItemsVal(sarLawsItemsEO,itemId);
            return Result.success("0","修改成功",sarLawsItemsEO);
        } else {
            return Result.error("修改失败");
        }
    }

    /**
     * @Author yangxuenan
     * @Description 条目及关联表删除
     * Date 2018/9/13 10:04
     * @Param [itemId]
     * @return com.adc.da.util.http.ResponseMessage<com.adc.da.lawss.entity.SarLawsItemsEO>
     **/
    public ResponseMessage<SarLawsItemsEO> deleteLawsItems(String itemId) throws Exception {
        SarLawsItemsEO sarLawsItemsEO =  new SarLawsItemsEO();
        sarLawsItemsEO.setId(itemId);
        sarLawsItemsEO.setValidFlag(1);
        //删除条目表数据
        int countUp = dao.updateByPrimaryKeySelective(sarLawsItemsEO);
        if(countUp > 0){
            SarLawsItemValEOPage sarLawsItemValEOPage = new SarLawsItemValEOPage();
            sarLawsItemValEOPage.setLawsItemId(itemId);
            sarLawsItemValEOPage.setValidFlag("0");
            //查询条目信息关联表是否包含相关数据
            List<SarLawsItemValEO> getItemVal = sarLawsItemValEODao.queryByList(sarLawsItemValEOPage);
            if(getItemVal.size() > 0){
                for(int i=0;i<getItemVal.size();i++){
                    String valId = getItemVal.get(i).getId();
                    SarLawsItemValEO sarLawsItemValEO = new SarLawsItemValEO();
                    sarLawsItemValEO.setId(valId);
                    sarLawsItemValEO.setValidFlag(1);
                    //删除条目信息关联表数据
                    sarLawsItemValEODao.updateByPrimaryKeySelective(sarLawsItemValEO);
                }
            }
            return Result.success("0","删除成功",sarLawsItemsEO);
        } else {
            return Result.error("删除失败！");
        }
    }


    /**
     * @Author yangxuenan
     * @Description 导入法规条目
     * Date 2018/9/14 14:06
     * @Param [datas]
     * @return com.adc.da.util.http.ResponseMessage
     **/
    public ResponseMessage importLawsItemsDatas(List<LawsItemsImportDto> datas,String lawsId) throws Exception{
        try{
            //验证导入数据是否符合规则
            Map map = validateImportDatas(datas);
            boolean isOk = (boolean) map.get("result");
            if (!isOk) {
                //验证没有通过
                String message = (String) map.get("message");
                return Result.error("fail", message);
            }
            //将导入数据循环新增至相应表
            for(LawsItemsImportDto importDto : datas){
                SarLawsItemsEO sarLawsItemsEO = new SarLawsItemsEO();
                BeanUtils.copyProperties(importDto,sarLawsItemsEO);
                sarLawsItemsEO.setLawsId(lawsId);
                createLawsItems(sarLawsItemsEO);
            }
            return new ResponseMessage("0","导入成功",true);
        } catch (Exception e){
            return Result.error("导入失败");
        }

    }

    /**
     * @Author yangxuenan
     * @Description 验证导入数据
     * Date 2018/9/14 14:13
     * @Param [datas]
     * @return java.util.Map
     **/
    public Map validateImportDatas(List<LawsItemsImportDto> datas){
        //存放数据验证结果信息
        List<String>  stringMessage = new ArrayList<>();
        int i=1;    //记录行号
        //循环验证数据
        for(LawsItemsImportDto dto : datas){
            i++;
            int countError = 0;   //记录失败数据数量
            String errorMsg = "第"+ i +"行：";
            if(StringUtils.isNotEmpty(dto.getItemsNum())){
                if(dto.getItemsNum().length() > 100){
                    errorMsg += "条目号不能超过100个字符";
                    countError++;
                }
            } else {
                errorMsg += "条目号不能为空";
                countError++;
            }
            if(StringUtils.isNotEmpty(dto.getItemsName())){
                if(dto.getItemsName().length() > 500){
                    errorMsg += "条目名称不能超过500个字符";
                    countError++;
                }
            }
            if(StringUtils.isNotEmpty(dto.getParts())){
                if(dto.getParts().length() > 500){
                    errorMsg += "涉及零部件不能超过500个字符";
                    countError++;
                }
            } else {
                errorMsg += "涉及零部件不能为空";
                countError++;
            }
            if(StringUtils.isNotEmpty(dto.getResponsibleUnit())){
                if(dto.getResponsibleUnit().length() > 100){
                    errorMsg += "责任部门不能超过100个字符";
                    countError++;
                }
            } else {
                errorMsg += "责任部门不能为空";
                countError++;
            }
            if(StringUtils.isNotEmpty(dto.getRemarks())){
                if(dto.getRemarks().length() > 5000){
                    errorMsg += "备注不能超过5000个字符";
                    countError++;
                }
            }

            if(countError > 0){
                stringMessage.add(errorMsg);
            }
        }
        Map map = new HashMap();
        if (stringMessage.isEmpty()) {
            map.put("result", true);
            map.put("message", "");
        } else {
            map.put("result", false);
            String html ="";
            for (String message : stringMessage){
                html += message +"</br>";
            }
            map.put("message", html);
        }
        return map;
    }

}
