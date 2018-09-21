package com.adc.da.lawss.service;

import com.adc.da.lawss.dao.SarBussStandMenuEODao;
import com.adc.da.lawss.dao.SarLawsMenuEODao;
import com.adc.da.lawss.dao.SarStandMenuEODao;
import com.adc.da.lawss.entity.SarBussStandMenuEO;
import com.adc.da.lawss.entity.SarLawsMenuEO;
import com.adc.da.lawss.entity.SarStandMenuEO;
import com.adc.da.lawss.page.SarLawsMenuEOPage;
import com.adc.da.lawss.vo.NewmenuOldmenuVO;
import com.adc.da.sys.constant.ValueStateEnum;
import com.adc.da.sys.util.UUIDUtils;
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

    @Autowired
    private SarStandMenuEODao sarStandMenuEODao;

    @Autowired
    private SarLawsMenuEODao sarLawsMenuEODao;

    @Autowired
    private SarBussStandMenuEODao sarBussStandMenuEODao;



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
     * @Description 根据父ID查询子节点及
     * Date 2018/9/11 17:03
     * @Param [parentId]
     * @return java.util.List<com.adc.da.lawss.entity.SarMenuEO>
     **/
    public List<SarMenuEO>  queryMenuByPid(SarMenuEO sarMenuEO) throws Exception {
        //先判断目录下是否有子节点
        List<SarMenuEO> list = dao.queryMenuByPid(sarMenuEO);
        return list;
    }

    /**
     * @Author gaoyan
     * @Description 根据父ID查询子节点及
     * Date 2018/9/20 17:03
     * @Param [parentId]
     * @return java.util.List<com.adc.da.lawss.entity.SarMenuEO>
     **/

    public boolean  judgequeryMenuByPid(SarMenuEO sarMenuEO) throws Exception {
        boolean result = false;   //true 表示有记录，false表示无记录
        //先判断目录下是否有子节点
        List<SarMenuEO> list = dao.queryByPidExcpetSelf(sarMenuEO);
        if(list.size()<=0){
            //再查节点下是否有标准，法规，企业标准记录
            switch (sarMenuEO.getSorDivide()){
                case "INLAND_STAND":case "FOREIGN_STAND":
                    SarStandMenuEO sarStandMenuEO = new SarStandMenuEO();
                    sarStandMenuEO.setMenuId(sarMenuEO.getId());
                    List<SarStandMenuEO> listresult = sarStandMenuEODao.selectByMenuId(sarStandMenuEO);
                    if(listresult !=null && listresult.size()>0){
                        result=true;
                    }
                    break;
                case "BUSINESS_STAND":
                    SarBussStandMenuEO sarBussStandMenuEO = new SarBussStandMenuEO();
                    sarBussStandMenuEO.setMenuId(sarMenuEO.getId());
                    List<SarBussStandMenuEO> listresult1 = sarBussStandMenuEODao.selectByMenuId(sarBussStandMenuEO);
                    if(listresult1 !=null && listresult1.size()>0){
                        result=true;
                    }
                    break;
                case "INLAND_LAWS":case "FOREIGN_LAWS":
                    SarLawsMenuEOPage sarLawsMenuEO = new SarLawsMenuEOPage();
                    sarLawsMenuEO.setMenuId(sarMenuEO.getId());
                    List<SarLawsMenuEO> listresult2 = sarLawsMenuEODao.selectByLawsInfo(sarLawsMenuEO);
                    if(listresult2 !=null && listresult2.size()>0){
                        result=true;
                    }
                    break;
            }
        }
        else{
            result=true;
        }
        return result;
    }

    public String updateStandLawsBymenuid (SarMenuEO upMenu){
        NewmenuOldmenuVO newmenuOldmenuVO = new NewmenuOldmenuVO();
        newmenuOldmenuVO.setOldMenuid(upMenu.getId());
        List<SarMenuEO> list = dao.queryMenuByDis(upMenu);
        if(list !=null && list.size()>0){
            newmenuOldmenuVO.setNewMenuid(list.get(0).getId());
        }
        switch (upMenu.getSorDivide()){
            case "INLAND_STAND":case "FOREIGN_STAND":
                sarStandMenuEODao.updateMenuidByMenuid(newmenuOldmenuVO);
                break;
            case "BUSINESS_STAND":
                sarBussStandMenuEODao.updateMenuidByMenuid(newmenuOldmenuVO);
                break;
            case "INLAND_LAWS":case "FOREIGN_LAWS":
                sarLawsMenuEODao.updateMenuidByMenuid(newmenuOldmenuVO);
                break;
        }
        return  "success";
    }
}
