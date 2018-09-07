package com.adc.da.lawss.service;

import com.adc.da.base.service.BaseService;
import com.adc.da.lawss.common.PropertyTypeEnum;
import com.adc.da.lawss.dao.SarStandMenuEODao;
import com.adc.da.lawss.dao.SarStandValEODao;
import com.adc.da.lawss.dao.SarStandardsInfoEODao;
import com.adc.da.lawss.entity.SarStandMenuEO;
import com.adc.da.lawss.entity.SarStandValEO;
import com.adc.da.lawss.entity.SarStandardsInfoEO;
import com.adc.da.lawss.page.SarStandardsInfoEOPage;
import com.adc.da.lawss.vo.SarStandExcelDto;
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

import java.util.Date;
import java.util.List;


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

            //标准文件资源表，标准文件详情表中插入数据




            return  Result.success("00","插入数据成功");
        }
        else {
            return Result.error("01","插入输入过程中出错");
        }
    }

    public ResponseMessage<SarStandardsInfoEO> importSarStandardsInfoData(List<SarStandardsInfoEO> list){
        //此处需要做各种验证，数据库操作
        SarStandardsInfoEO sarStandardsInfoEO = new SarStandardsInfoEO();
        return Result.success(sarStandardsInfoEO);
    }

    /**
     * 自定义分页查询
     * @param page  标准信息
     * @return
     * @author gaoyan
     * date 2018-09-04
     */
    public List<SarStandardsInfoEO> getSarStandardsInfoPage(SarStandardsInfoEOPage page){
        Integer rowCount = sarStandardsInfoEOdao.getSarStandardsInfoCount(page);
        page.getPager().setRowCount(rowCount);
        return  sarStandardsInfoEOdao.getSarStandardsInfoPage(page);
    }

    public List<SarStandExcelDto> getSarStandardsInfo(SarStandardsInfoEOPage page){
        return  sarStandardsInfoEOdao.getSarStandardsInfo(page);
    }
}
