package com.adc.da.lawss.service;

import com.adc.da.base.service.BaseService;
import com.adc.da.lawss.common.PropertyTypeEnum;
import com.adc.da.lawss.dao.SarBussStandMenuEODao;
import com.adc.da.lawss.dao.SarBussStandValEODao;
import com.adc.da.lawss.dao.SarBussionessStandEODao;
import com.adc.da.lawss.dto.SarBussionessStandExcelDto;
import com.adc.da.lawss.entity.SarBussStandMenuEO;
import com.adc.da.lawss.entity.SarBussStandValEO;
import com.adc.da.lawss.entity.SarBussionessStandEO;
import com.adc.da.lawss.page.SarBussionessStandEOPage;
import com.adc.da.lawss.vo.TimeStartEndVO;
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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 *
 * <br>
 * <b>功能：</b>SAR_BUSSIONESS_STAND SarBussionessStandEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-09-03 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("sarBussionessStandEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class SarBussionessStandEOService extends BaseService<SarBussionessStandEO, String> {

    private static final Logger logger = LoggerFactory.getLogger(SarBussionessStandEOService.class);

    @Autowired
    private SarBussionessStandEODao sarBussionessStandEODao;

    @Autowired
    private SarBussStandMenuEODao sarBussStandMenuEODao;

    @Autowired
    private SarBussStandValEODao sarBussStandValEODao;

    public SarBussionessStandEODao getDao() {
        return sarBussionessStandEODao;
    }

    public List<SarBussionessStandEO> getSarBussionStandPage(SarBussionessStandEOPage page){
        if(StringUtils.isNotEmpty(page.getIssueTime())) {
            TimeStartEndVO timevo = timeconvert(page.getIssueTime());
            if(StringUtils.isNotEmpty(timevo.getStart())){
                page.setIssueTime1(timevo.getStart());
            }
            else {
                page.setIssueTime2(timevo.getEnd());
            }
        }
        if(StringUtils.isNotEmpty(page.getPutTime())) {
            TimeStartEndVO timevo = timeconvert(page.getPutTime());
            if(StringUtils.isNotEmpty(timevo.getStart())){
                page.setPutTime1(timevo.getStart());
            }
            else {
                page.setPutTime2(timevo.getEnd());
            }
        }
        Integer rowCount = sarBussionessStandEODao.getBussionessStandInfoCount(page);
        page.getPager().setRowCount(rowCount);
        List<SarBussionessStandEO> sarlist = sarBussionessStandEODao.getBussionessStandInfoPage(page);
        return sarlist;
    }

    public ResponseMessage<SarBussionessStandEO> createSarBussionessStand(SarBussionessStandEO sarBussionessStandEO){
        //标准信息表中插入一条数据
        sarBussionessStandEO.setPutUser("gaoyan");  //发布人
        sarBussionessStandEO.setValidTime(ValueStateEnum.VALUE_TRUE.getValue());
        sarBussionessStandEO.setCreationTime(new Date());
        sarBussionessStandEO.setModifyTime(new Date());
        sarBussionessStandEO.setId(UUIDUtils.randomUUID20());
        int insertresult = sarBussionessStandEODao.insertSelective(sarBussionessStandEO);
        if(insertresult>=1){
            //根据新建标准所在目录，确定此处是否需要在标准目录关联表中插入数据
            if(StringUtils.isNotEmpty(sarBussionessStandEO.getMenuId())){
                SarBussStandMenuEO sarStandMenuEO = new SarBussStandMenuEO();
                sarStandMenuEO.setBussStandId(sarBussionessStandEO.getId());
                sarStandMenuEO.setMenuId(sarBussionessStandEO.getMenuId());
                sarStandMenuEO.setValidFlag(ValueStateEnum.VALUE_TRUE.getValue());
                sarStandMenuEO.setId(UUID.randomUUID(20));
                sarBussStandMenuEODao.insertSelective(sarStandMenuEO);
            }

            //标准关联表中插入数据，对于标准多选属性，标准信息表中存储已逗号隔开，标准关联表中还需要存入相关数据
            //具体多选属性包括( 适用车型:applyArctic 能源种类:emergyKind   代替标准号前台页面要求是多选，但常量EXCEL中没有提及需要确认)
            insertSarStandVal(sarBussionessStandEO);
            return  Result.success("00","插入数据成功",sarBussionessStandEO);
        }
        else {
            return Result.error("01","插入输入过程中出错");
        }
    }

    public void insertSarStandVal(SarBussionessStandEO sarBussionessStandEO){
        SarBussStandValEO sarStandValEO = new SarBussStandValEO();
        sarStandValEO.setValidFlag(ValueStateEnum.VALUE_TRUE.getValue());
        sarStandValEO.setCreationTime(new Date());
        sarStandValEO.setModifyTime(new Date());
        sarStandValEO.setStandId(sarBussionessStandEO.getId());
        if(StringUtils.isNotEmpty(sarBussionessStandEO.getApplyArctic())){
            String []applyarr = sarBussionessStandEO.getApplyArctic().trim().split(",");
            for (String apply:applyarr){
                sarStandValEO.setId(UUID.randomUUID(20));
                sarStandValEO.setPropertyType(PropertyTypeEnum.APPLY_ARCTIC.getValue());
                sarStandValEO.setPropertyValue(apply);
                sarBussStandValEODao.insertSelective(sarStandValEO);
            }
        }
        if(StringUtils.isNotEmpty(sarBussionessStandEO.getEnergyKind())){
            String []emergyKindarr = sarBussionessStandEO.getEnergyKind().trim().split(",");
            for (String emergyKind:emergyKindarr){
                sarStandValEO.setId(UUID.randomUUID(20));
                sarStandValEO.setPropertyType(PropertyTypeEnum.ENERGY_KIND.getValue());
                sarStandValEO.setPropertyValue(emergyKind);
                sarBussStandValEODao.insertSelective(sarStandValEO);
            }
        }
    }

    public ResponseMessage<SarBussionessStandEO> importSarBussionessStand(List<SarBussionessStandExcelDto> list){
        //此处需要做各种验证，数据库操作
        try{
            //验证导入数据是否符合规则
            //Map map = validateImportDatas(datas);
            //boolean isOk = (boolean) map.get("result");
            /*if (!isOk) {
                //验证没有通过
                String message = (String) map.get("message");
                return Result.error("fail", message);
            }*/
            //将导入数据循环新增至相应表getProductInfoPage
            for(SarBussionessStandExcelDto importDto : list){
                SarBussionessStandEO sarBussionessStandEO = new SarBussionessStandEO();
                BeanUtils.copyProperties(importDto,sarBussionessStandEO);
                createSarBussionessStand(sarBussionessStandEO);
            }
            return Result.success("0","导入数据成功");
        } catch (Exception e){
            return Result.error("导入失败");
        }
    }

    public TimeStartEndVO timeconvert(String state){
        //发布时间条件设置
        TimeStartEndVO timeStartEndVO = new TimeStartEndVO();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        Long datetime = date.getTime();
        String issuetime = formatter.format(date);
        switch (state) {
            //本月
            case "1":
                timeStartEndVO.setStart(issuetime.substring(0, 8) + "00");
                timeStartEndVO.setEnd(issuetime.substring(0, 8) + "31");
                break;
            //近三个月
            case "2":
                timeStartEndVO.setStart(formatter.format(new Date(datetime - (91 * 24 * 60 * 60 * 1000L))));
                timeStartEndVO.setEnd(issuetime);
                break;
            //近一年
            case "3":
                timeStartEndVO.setStart(formatter.format(new Date(datetime - (91 * 24 * 60 * 60 * 1000L))));
                timeStartEndVO.setEnd(issuetime);
                break;
            //近三年
            case "4":
                timeStartEndVO.setStart(formatter.format(new Date(datetime - (3 * 365 * 24 * 60 * 60 * 1000L))));
                timeStartEndVO.setEnd(issuetime);
                break;
            //三年以上
            case "5":
                timeStartEndVO.setEnd(formatter.format(new Date(datetime - (3 * 365 * 24 * 60 * 60 * 1000L))));
        }
        return timeStartEndVO;
    }

    public SarBussionessStandEO updateSarBussionessStand(SarBussionessStandEO sarBussionessStandEO) {
        sarBussionessStandEODao.updateByPrimaryKeySelective(sarBussionessStandEO);
        //先删除标准关联表，然后插入数据
        sarBussStandValEODao.deleteDataByStandid(sarBussionessStandEO.getId());
        insertSarStandVal(sarBussionessStandEO);
        return sarBussionessStandEO;
    }

    public List<SarBussionessStandExcelDto> getSarBussionessStand(SarBussionessStandEOPage page){
        return  sarBussionessStandEODao.getSarBussionessStand(page);
    }
}
