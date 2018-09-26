package com.adc.da.lawss.service;

import com.adc.da.att.vo.AttFileVo;
import com.adc.da.base.service.BaseService;
import com.adc.da.lawss.common.PropertyTypeEnum;
import com.adc.da.lawss.common.StandFileClassifyEnum;
import com.adc.da.lawss.dao.*;
import com.adc.da.lawss.dto.LawsInfoImportDto;
import com.adc.da.lawss.entity.*;
import com.adc.da.lawss.page.SarStandardsInfoEOPage;
import com.adc.da.lawss.dto.SarStandExcelDto;
import com.adc.da.sys.constant.ValueStateEnum;
import com.adc.da.sys.dao.DicTypeEODao;
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
import java.util.Map;


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

    //标准关联表DAO
    @Autowired
    private SarStandValEODao sarStandValEODao;

    @Autowired
    private DicTypeEODao dicTypeEODao;

    @Autowired
    private SarStandResEODao sarStandResEODao;

    @Autowired
    private SarStandFileEODao sarStandFileEODao;

    public SarStandardsInfoEODao getDao() {
        return sarStandardsInfoEOdao;
    }

    public ResponseMessage<SarStandardsInfoEO> createSarStandardsInfo(SarStandardsInfoEO sarStandardsInfoEO) throws Exception {
        //标准信息表中插入一条数据
        sarStandardsInfoEO.setCreationUser("gaoyan");
        sarStandardsInfoEO.setValidFlag(ValueStateEnum.VALUE_TRUE.getValue());
        sarStandardsInfoEO.setCreationTime(new Date());
        sarStandardsInfoEO.setModifyTime(new Date());
        sarStandardsInfoEO.setId(UUID.randomUUID(20));
        int insertresult = sarStandardsInfoEOdao.insertSelective(sarStandardsInfoEO);
        if(insertresult>=1){
            //根据新建标准所在目录，确定此处是否需要在标准目录关联表中插入数据
            if(StringUtils.isNotEmpty(sarStandardsInfoEO.getMenuId())){
                SarStandMenuEO sarStandMenuEO = new SarStandMenuEO();
                sarStandMenuEO.setStandId(sarStandardsInfoEO.getId());
                sarStandMenuEO.setMenuId(sarStandardsInfoEO.getMenuId());
                sarStandMenuEO.setValidFlag(ValueStateEnum.VALUE_TRUE.getValue());
                sarStandMenuEO.setId(UUID.randomUUID(20));
                sarStandMenuEODao.insertSelective(sarStandMenuEO);
            }
            //标准关联表中插入数据，对于标准多选属性，标准信息表中存储已逗号隔开，标准关联表中还需要存入相关数据
            //具体多选属性包括( 适用车型:applyArctic 能源种类:emergyKind 适用认证:applyAuth 所属类别:category;  代替标准号前台页面要求是多选，但常量EXCEL中没有提及需要确认)
            insertSarStandVal(sarStandardsInfoEO);
            //标准文件资源表，标准文件详情表中插入数据，并保存数据
            if(sarStandardsInfoEO.getStandFileList() != null) {
                insertSarStandFile(sarStandardsInfoEO.getId(), sarStandardsInfoEO.getStandFileList(), StandFileClassifyEnum.STAND_FILE.getValue());
            }

            return  Result.success("00","插入数据成功",sarStandardsInfoEO);
        }
        else {
            return Result.error("01","插入输入过程中出错");
        }
    }

    public ResponseMessage<SarStandardsInfoEO> importSarStandardsInfoData(List<SarStandExcelDto> list,String  standType){
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
            //将导入数据循环新增至相应表
            for(SarStandExcelDto importDto : list){
                SarStandardsInfoEO sarStandardsInfoEO = new SarStandardsInfoEO();
                BeanUtils.copyProperties(importDto,sarStandardsInfoEO);
                sarStandardsInfoEO.setStandType(standType);
                createSarStandardsInfo(sarStandardsInfoEO);
            }
            return Result.success("0","导入数据成功");
        } catch (Exception e){
            return Result.error("导入失败");
        }
    }

    /**
     * 修改页面
     * @param sarStandardsInfoEO  标准信息
     * @return
     * @author gaoyan
     * date 2018-09-14
     */
    public ResponseMessage<SarStandardsInfoEO> updateSarStandardsInfo(SarStandardsInfoEO sarStandardsInfoEO) throws Exception {
        sarStandardsInfoEO.setModifyTime(new Date());
        sarStandardsInfoEOdao.updateByPrimaryKeySelective(sarStandardsInfoEO);
        //先删除标准关联表，然后插入数据
        sarStandValEODao.deleteDataByStandid(sarStandardsInfoEO.getId());
        insertSarStandVal(sarStandardsInfoEO);
        return  Result.success("00","修改数据成功",sarStandardsInfoEO);
    }

    /**
     * 自定义分页查询
     * @param page  标准信息
     * @return
     * @author gaoyan
     * date 2018-09-04
     */
    public List<SarStandardsInfoEO> getSarStandardsInfoPage(SarStandardsInfoEOPage page){
        if(StringUtils.isNotEmpty(page.getIssueTime())) {
            //发布时间条件设置
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            Long datetime = date.getTime();
            String issuetime = formatter.format(date);
            switch (page.getIssueTime()) {
                //本月
                case "1":
                    page.setIssueTime1(issuetime.substring(0, 8) + "00");
                    page.setIssueTime2(issuetime.substring(0, 8) + "31");
                    break;
                    //近三个月
                case "2":
                    page.setIssueTime1(formatter.format(new Date(datetime - (91 * 24 * 60 * 60 * 1000L))));
                    page.setIssueTime2(issuetime);
                    break;
                    //近一年
                case "3":
                    page.setIssueTime1(formatter.format(new Date(datetime - (91 * 24 * 60 * 60 * 1000L))));
                    page.setIssueTime2(issuetime);
                    break;
                    //近三年
                case "4":
                    page.setIssueTime1(formatter.format(new Date(datetime - (3 * 365 * 24 * 60 * 60 * 1000L))));
                    page.setIssueTime2(issuetime);
                    break;
                    //三年以上
                case "5":
                    page.setIssueTime2(formatter.format(new Date(datetime - (3 * 365 * 24 * 60 * 60 * 1000L))));
            }
        }
        Integer rowCount = sarStandardsInfoEOdao.getSarStandardsInfoCount(page);
        page.getPager().setRowCount(rowCount);
        List<SarStandardsInfoEO> sarlist = sarStandardsInfoEOdao.getSarStandardsInfoPage(page);
       /* dicTypeEODao.getDicTypeEOById()
         countryShow;
         standSortShow; // 标准类别下拉框
         applyArcticShow; // 适用车型下拉框
         standStateShow; // 标准状态下拉框
         standNatureShow; // 标准性质下拉框
         adoptExtentShow; // 采标程度下拉框
         emergyKindShow; // 能源种类下拉框
         applyAuthShow; // 适用认证下拉框
         categoryShow; // 所属类别下拉框*/

        return sarlist;
    }

    public List<SarStandExcelDto> getSarStandardsInfo(SarStandardsInfoEOPage page){
        return  sarStandardsInfoEOdao.getSarStandardsInfo(page);
    }

    public List<SarStandardsInfoEO> selectStandardsByStandnumber(String replaceStandNum,String standType){
        SarStandardsInfoEO sarStandardsInfoEO = new SarStandardsInfoEO();
        sarStandardsInfoEO.setReplaceStandNum(replaceStandNum);
        sarStandardsInfoEO.setStandType(standType);
        return  sarStandardsInfoEOdao.selectStandardsByStandnumber(sarStandardsInfoEO);
    }

    public List<SarStandardsInfoEO> getSarStandardsInfoByMenu(SarStandardsInfoEOPage page){
        return  sarStandardsInfoEOdao.getSarStandardsInfoByMenu(page);
    }

    public void insertSarStandVal(SarStandardsInfoEO sarStandardsInfoEO){
        SarStandValEO sarStandValEO = new SarStandValEO();
        sarStandValEO.setValidFlag(ValueStateEnum.VALUE_TRUE.getValue());
        sarStandValEO.setCreationTime(new Date());
        sarStandValEO.setModifyTime(new Date());
        sarStandValEO.setStandId(sarStandardsInfoEO.getId());
        if(StringUtils.isNotEmpty(sarStandardsInfoEO.getApplyArctic())){
            String []applyarr = sarStandardsInfoEO.getApplyArctic().trim().split(",");
            for (String apply:applyarr){
                sarStandValEO.setId(UUID.randomUUID(20));
                sarStandValEO.setPropertyType(PropertyTypeEnum.APPLY_ARCTIC.getValue());
                sarStandValEO.setPropertyVal(apply);
                sarStandValEODao.insertSelective(sarStandValEO);
            }
        }
        if(StringUtils.isNotEmpty(sarStandardsInfoEO.getEmergyKind())){
            String []emergyKindarr = sarStandardsInfoEO.getEmergyKind().trim().split(",");
            for (String emergyKind:emergyKindarr){
                sarStandValEO.setId(UUID.randomUUID(20));
                sarStandValEO.setPropertyType(PropertyTypeEnum.ENERGY_KIND.getValue());
                sarStandValEO.setPropertyVal(emergyKind);
                sarStandValEODao.insertSelective(sarStandValEO);
            }
        }
        if(StringUtils.isNotEmpty(sarStandardsInfoEO.getApplyAuth())){
            String []applyAutharr = sarStandardsInfoEO.getApplyAuth().trim().split(",");
            for (String applyAuth:applyAutharr){
                sarStandValEO.setId(UUID.randomUUID(20));
                sarStandValEO.setPropertyType(PropertyTypeEnum.APPLY_AUTH.getValue());
                sarStandValEO.setPropertyVal(applyAuth);
                sarStandValEODao.insertSelective(sarStandValEO);
            }
        }
        if(StringUtils.isNotEmpty(sarStandardsInfoEO.getCategory())){
            String []categoryarr = sarStandardsInfoEO.getCategory().trim().split(",");
            for (String category:categoryarr){
                sarStandValEO.setId(UUID.randomUUID(20));
                sarStandValEO.setPropertyType(PropertyTypeEnum.CATEGORY.getValue());
                sarStandValEO.setPropertyVal(category);
                sarStandValEODao.insertSelective(sarStandValEO);
            }
        }
    }

    public void insertSarStandFile(String standId ,List<AttFileVo> list,String standFileClassify){
        if (list != null) {
            SarStandResEO sarStandResEO = new SarStandResEO();
            SarStandFileEO sarStandFileEO = new SarStandFileEO();
            for (AttFileVo fileInfo  : list) {
                //标准文件资源表存数据库
                sarStandResEO.setStandId(standId);            //标准ID
                sarStandResEO.setStandFileClassify(standFileClassify);  //文件分类
                sarStandResEO.setFileName(fileInfo.getOldFileName());           //文件名称@@@@@@@需要确定取name 还是 oldname
                sarStandResEO.setFileSuffix(fileInfo.getFileSuffix());         //文件类型
                sarStandResEO.setId(UUIDUtils.randomUUID20());
                sarStandResEO.setCreationTime(new Date());
                sarStandResEO.setModifyTime(new Date());
                sarStandResEO.setValidFlag(ValueStateEnum.VALUE_TRUE.getValue());
                int i = sarStandResEODao.insertSelective(sarStandResEO);
                //标准文件详情表存数据库
                sarStandFileEO.setId(UUIDUtils.randomUUID20());
                sarStandFileEO.setCreationTime(new Date());
                sarStandFileEO.setModifyTime(new Date());
                sarStandFileEO.setValidFlag(ValueStateEnum.VALUE_TRUE.getValue());
                sarStandFileEO.setStandId(standId);     //标准ID
                sarStandFileEO.setResId(sarStandResEO.getId());       //资源ID
                sarStandFileEO.setAttId(fileInfo.getId());       //文件ID
                sarStandFileEO.setUseModel("");  //文件使用模式        //文件名称@@@@@@@需要修改
                sarStandFileEODao.insertSelective(sarStandFileEO);
            }
        }
    }
}
