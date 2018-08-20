package com.adc.da.sys.service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.base.service.BaseService;
import com.adc.da.sys.dao.OrgEODao;
import com.adc.da.sys.dao.SysCorpEODao;
import com.adc.da.sys.dto.SysCorpExcelBusinessDto;
import com.adc.da.sys.dto.SysCorpExcelDto;
import com.adc.da.sys.entity.OrgEO;
import com.adc.da.sys.entity.SysCorpEO;
import com.adc.da.util.constant.DeleteFlagEnum;
import com.adc.da.util.utils.UUID;


/**
 *
 * <br>
 * <b>功能：</b>SYS_CORP SysCorpEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2018-05-05 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("sysCorpEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class SysCorpEOService extends BaseService<SysCorpEO, String> {

    private static final Logger logger = LoggerFactory.getLogger(SysCorpEOService.class);

    @Autowired
    private SysCorpEODao dao;
    @Autowired
	private OrgEODao orgEODao;

    public SysCorpEODao getDao() {
        return dao;
    }
    @Autowired
    private OrgEODao orgDao;
    
    public OrgEODao getOrgDao() {
		return orgDao;
	}

	public void delCorpInfo(String id, String orgId){
    	SysCorpEO corpEO=new SysCorpEO();
    	corpEO.setId(id);
    	corpEO.setActiveFlag(DeleteFlagEnum.DELETE.getValue());
    	corpEO.setDelFlag(DeleteFlagEnum.DELETE.getValue());
    	corpEO.setUpdateTime(new Date());
    	//更新组织机构信息
    	dao.updateByPrimaryKeySelective(corpEO);
    	orgEODao.deleteLogic(orgId);
    	orgEODao.deleteUserOrgByOrgId(orgId);
    };
    
    /**
     * 保存企业信息
     */
    public SysCorpEO saveCorpData(SysCorpEO corpEO){
    	corpEO.setId(UUID.randomUUID10());
    	corpEO.setDelFlag(DeleteFlagEnum.NORMAL.getValue());
    	corpEO.setInsertTime(new Date());
    	corpEO.setUpdateTime(new Date());
    	dao.insertSelective(corpEO);
    	//开始写组织结构表关系
    	OrgEO orgEO=new OrgEO();
    	if(corpEO.getCorpType().equals("0")){//企业
    		orgEO.setId(UUID.randomUUID10());
    		orgEO.setDelFlag(corpEO.getActiveFlag());
    		orgEO.setName(corpEO.getCorpName());
    		orgEO.setParentId("2");
    		orgEO.setParentIds("2");
    		orgEO.setCorpId(corpEO.getId());
    		//TODO 还差激活状态
    		//缺少关联ID写入
    	}else{//使用方
    		orgEO.setId(UUID.randomUUID10());
    		orgEO.setDelFlag(corpEO.getActiveFlag());
    		orgEO.setName(corpEO.getCorpName());
    		orgEO.setParentId("3");
    		orgEO.setParentIds("3");
    		//缺少关联ID写入
    		orgEO.setCorpId(corpEO.getId());
    		//TODO 还差激活状态
    	}
    	orgDao.insert(orgEO);
    	return corpEO;
    }
    
    
    public void updateCorpInfo(SysCorpEO sysCorpEO){
    	sysCorpEO.setUpdateTime(new Date());
    	dao.updateByPrimaryKeySelective(sysCorpEO);
    	//此处更新下企业名称和组织机构名称
    	OrgEO orgEO=new OrgEO();
    	orgEO.setCorpId(sysCorpEO.getId());
    	List<OrgEO> queryByObject = orgDao.queryByObject(orgEO);
    	if(queryByObject!=null && queryByObject.size()>0){
    		OrgEO resultQrgEo = queryByObject.get(0);
    		resultQrgEo.setName(sysCorpEO.getCorpName());
    		resultQrgEo.setDelFlag(sysCorpEO.getActiveFlag());
    		//TODO 激活状态
    		orgDao.updateByPrimaryKeySelective(resultQrgEo);
    	}
    }

	public void addImportCorpDatas(List<SysCorpExcelDto> datas, String corpType) {
		LinkedList<SysCorpEO> sCorpEOs = new LinkedList<SysCorpEO>();
        for (SysCorpExcelDto sysCorpExcelDto : datas) {
        	SysCorpEO corpEO = new SysCorpEO();
        	corpEO.setId(UUID.randomUUID10());
        	corpEO.setCorpName(sysCorpExcelDto.getCorpName());
        	corpEO.setCorpAddress(sysCorpExcelDto.getCorpAddress());
        	corpEO.setCorpUser(sysCorpExcelDto.getCorpUser());
        	corpEO.setCorpPhone(sysCorpExcelDto.getCorpPhone());
        	corpEO.setCorpEmail(sysCorpExcelDto.getCorpEmail());
        	corpEO.setCorpDuty(sysCorpExcelDto.getCorpDuty());
        	corpEO.setInsertTime(new Date());
        	corpEO.setUpdateTime(new Date());
        	corpEO.setDelFlag(0);
        	corpEO.setActiveFlag(0);
        	corpEO.setCorpType(corpType);
        	
        	//开始写组织结构表关系
        	OrgEO orgEO=new OrgEO();
        	//使用方
    		orgEO.setId(UUID.randomUUID10());
    		orgEO.setDelFlag(DeleteFlagEnum.NORMAL.getValue());
    		orgEO.setName(corpEO.getCorpName());
    		orgEO.setParentId("3");
    		orgEO.setParentIds("3");
    		orgEO.setCorpId(corpEO.getId());
        	
        	orgDao.insert(orgEO);
        	sCorpEOs.add(corpEO);
		}
        dao.addCorpeos(sCorpEOs);
	}

	public void addImportCorpBusinessDatas(List<SysCorpExcelBusinessDto> datas, String corpType) {
		LinkedList<SysCorpEO> sCorpEOs = new LinkedList<SysCorpEO>();
        for (SysCorpExcelBusinessDto sysCorpExcelDto : datas) {
        	SysCorpEO corpEO = new SysCorpEO();
        	corpEO.setId(UUID.randomUUID10());
        	corpEO.setCorpName(sysCorpExcelDto.getCorpName());
        	corpEO.setCorpAddress(sysCorpExcelDto.getCorpAddress());
        	corpEO.setCorpUser(sysCorpExcelDto.getCorpUser());
        	corpEO.setCorpPhone(sysCorpExcelDto.getCorpPhone());
        	corpEO.setCorpEmail(sysCorpExcelDto.getCorpEmail());
        	corpEO.setCorpDuty(sysCorpExcelDto.getCorpDuty());
        	corpEO.setInsertTime(new Date());
        	corpEO.setUpdateTime(new Date());
        	corpEO.setDelFlag(0);
        	corpEO.setActiveFlag(0);
        	corpEO.setCorpType(corpType);
        	
        	//开始写组织结构表关系
        	OrgEO orgEO=new OrgEO();
    		orgEO.setId(UUID.randomUUID10());
    		orgEO.setDelFlag(DeleteFlagEnum.NORMAL.getValue());
    		orgEO.setName(corpEO.getCorpName());
    		orgEO.setParentId("2");
    		orgEO.setParentIds("2");
    		orgEO.setCorpId(corpEO.getId());
    		
        	orgDao.insert(orgEO);
        	sCorpEOs.add(corpEO);
		}
        dao.addCorpeos(sCorpEOs);
		
	}

	public List<OrgEO> validateChildsIsEmpty(String id) {
		return orgEODao.queryOrgChildsByCorpId(id);
	}
	

}
