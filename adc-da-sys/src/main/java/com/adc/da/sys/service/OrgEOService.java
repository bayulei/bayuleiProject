package com.adc.da.sys.service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.base.service.BaseService;
import com.adc.da.sys.dao.OrgEODao;
import com.adc.da.sys.dao.SysCorpEODao;
import com.adc.da.sys.entity.OrgEO;
import com.adc.da.sys.entity.SysCorpEO;
import com.adc.da.sys.entity.UserOrgEO;
import com.adc.da.util.constant.DeleteFlagEnum;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.utils.UUID;
import com.alibaba.fastjson.JSON;

@Service("orgEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class OrgEOService extends BaseService<OrgEO, String> {

	private static final Logger logger = LoggerFactory.getLogger(OrgEOService.class);
	
	@Autowired
	private SysCorpEODao sCorpEODao;
	@Autowired
	private OrgEODao dao;
	
	@Override
	public OrgEODao getDao() {
		return dao;
	}
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<OrgEO> listOrgEOByOrgName(String orgName) {
		return dao.listOrgEOByOrgName(orgName);
	}
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public OrgEO getOrgEOByNameAndPid(String orgName,String parentId) {
		return dao.getOrgEOByNameAndPid(orgName,parentId);
	}
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<OrgEO> getOrgEOByPid(String parentId) {
		return dao.getOrgEOByPid(parentId);
	}
	
	public ResponseMessage save(OrgEO orgEO) {
		if("2".equals(orgEO.getParentId()) || "3".equals(orgEO.getParentId())) {
			int corpType = Integer.valueOf(orgEO.getParentId());
			SysCorpEO corpEO = new SysCorpEO();
			corpEO.setId(UUID.randomUUID10());
			corpEO.setCorpName(orgEO.getName());
			corpEO.setCorpType((corpType-2)+"");
			corpEO.setDelFlag(0);
			corpEO.setActiveFlag(0);
			sCorpEODao.insert(corpEO);
			orgEO.setCorpId(corpEO.getId());
		}
		orgEO.setId(UUID.randomUUID10());
		orgEO.setDelFlag(DeleteFlagEnum.NORMAL.getValue());
		orgEO.setIsShow(0);
		orgEO.setInsertTime(new Date());
		orgEO.setUpdateTime(new Date());
		int line = dao.insert(orgEO);
		if(line > 0) {
			return Result.success();
		} else {
			return Result.error();
		}
	}
	
	/**
	 * 组织机构详情
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public OrgEO getOrgEOById(String id) {
		return dao.getOrgEOById(id);
	}
	
	/**
	 * 删除组织机构
	 * @return 
	 */
	public ResponseMessage delete(String idMerge) {
		String[] ids = idMerge.split(",");
		String id = ids[0];
		List<OrgEO> list = dao.getOrgEOByPid(id);
		// 如果该组织机构下有子机构，则不允许删除
		if (list != null && !list.isEmpty()) {
			return Result.error("r0031", "该组织机构下有子机构，不能删除");
		}
		int line = dao.deleteLogic(id);
		dao.deleteUserOrgByOrgId(id);
		if(ids.length == 2) {
			String corpId = ids[1];
			if(StringUtils.isNotBlank(corpId)) {
				SysCorpEO corpEO=new SysCorpEO();
				corpEO.setId(corpId);
				corpEO.setActiveFlag(DeleteFlagEnum.DELETE.getValue());
				corpEO.setDelFlag(DeleteFlagEnum.DELETE.getValue());
				corpEO.setUpdateTime(new Date());
				//更新组织机构信息
				sCorpEODao.updateByPrimaryKeySelective(corpEO);
			}
		}
		if(line > 0) {
			return Result.success();
		} else {
			return Result.error();
		}
	}
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public LinkedList<OrgEO> selectOrgAllNode(OrgEO orgEO) {
		LinkedList<OrgEO> rootNodes = dao.selectOrgAllNode(orgEO);
		return rootNodes;
	}



	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public LinkedList<OrgEO> getTree() {
		LinkedList<OrgEO> rootNodes = dao.selectRootNode();
		addNodes(rootNodes, 0);
		return rootNodes;
	}

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	private void addNodes(LinkedList<OrgEO> rootNodes, int i) {
		if(i < rootNodes.size() || rootNodes.size() == 1) {
			OrgEO orgEO = rootNodes.get(i);
			LinkedList<OrgEO> nodes = dao.selectNodeByPid(orgEO.getId());
			if(nodes != null) {
				rootNodes.addAll(nodes);
			}
			if(rootNodes.size() > 1) {
				addNodes(rootNodes, ++i);
			}
		}
	}

	public ResponseMessage updateById(OrgEO orgEO) {
		if("1".equals(orgEO.getLevel()) && StringUtils.isNotEmpty(orgEO.getCorpId())) {
			SysCorpEO corpEO = new SysCorpEO();
			corpEO.setId(orgEO.getCorpId());
			corpEO.setCorpName(orgEO.getName());
			sCorpEODao.updateByPrimaryKeySelective(corpEO);
		}
		orgEO.setUpdateTime(new Date());
		int line = dao.updateByPrimaryKeySelective(orgEO);
		if(line > 0) {
			return Result.success();
		} else {
			return Result.error();
		}
	}

	public ResponseMessage<Integer> delOrgRelatedUser(String userId, String orgId) {
		UserOrgEO userOrgEO = new UserOrgEO();
		userOrgEO.setUserId(userId);
		userOrgEO.setOrgId(orgId);
		dao.delOrgRelatedUser(userOrgEO);
		return Result.success();
	}

	public ResponseMessage<Integer> addOrgRelatedUser(String userOrgs) {
		List<UserOrgEO> userOrgEOs = JSON.parseArray(userOrgs, UserOrgEO.class);
		dao.addOrgRelatedUsers(userOrgEOs);
		return Result.success();
	}

	public int delOrgRelatedUserByUserId(String usId) {
		return dao.delOrgRelatedUserByUserId(usId);
	}

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<OrgEO> findById(String id, String userCorpName) {
		LinkedList<OrgEO> rootNodes = new LinkedList<OrgEO>();
		OrgEO orgEO = new OrgEO();
		orgEO.setId(id);
		orgEO.setName(userCorpName);
		rootNodes.add(orgEO);
		addNodes(rootNodes, 0);
		return rootNodes;
	}

}
