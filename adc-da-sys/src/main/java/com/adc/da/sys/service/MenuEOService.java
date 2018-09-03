package com.adc.da.sys.service;

import com.adc.da.sys.vo.MenuVO;
import com.adc.da.util.constant.DeleteFlagEnum;
import com.adc.da.util.utils.BeanMapper;
import com.adc.da.util.utils.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.base.entity.TreeEntity;
import com.adc.da.base.service.BaseService;
import com.adc.da.sys.dao.MenuEODao;
import com.adc.da.sys.entity.MenuEO;

import java.util.List;

/**
 *
 * <br>
 * <b>功能：</b>TS_MENU MenuEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2017-11-06 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("menuEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class MenuEOService extends BaseService<MenuEO, String> {

	private static final Logger logger = LoggerFactory.getLogger(MenuEOService.class);

	@Autowired
	private MenuEODao dao;

	@Autowired
	private BeanMapper beanMapper;

	@Override
	public MenuEODao getDao() {
		return dao;
	}

	/**
	 * 添加菜单 构建菜单树
	 */
	public MenuEO insertMenu(MenuEO menuEO) throws Exception {
		menuEO.setId(UUID.randomUUID(10));
		menuEO.setValidFlag(DeleteFlagEnum.NORMAL.getValue());
		TreeEntity parent = menuEO.getParent();
		if (parent != null) {
			menuEO.setParentId(parent.getId());
			menuEO.setParentIds(parent.getParentIds() + parent.getId() + ",");
		}
		//menuEO.setIsShow(1);
		super.insertSelective(menuEO);
		return menuEO;
	}

	/**
	 * 更新菜单
	 * 
	 * @param menuVO
	 *            菜单VO对象
	 */
	public void updateMenu(MenuVO menuVO) throws Exception {
		// 处理当前节点
		// 构建菜单
		String oldParentId = menuVO.getParentId();
		String oldParentIds = menuVO.getParentIds();
		MenuVO parentMenu = menuVO.getParent();
		if (parentMenu != null) {
			menuVO.setParentId(parentMenu.getId());
			menuVO.setParentIds(parentMenu.getParentIds() + parentMenu.getId() + ",");
		}
		// 处理所有的子节点
		// 1 判断父节点是否移动到其他节点
		if (oldParentId != null && !oldParentId.equals(menuVO.getParentId())) {
			// 1 获取要修改的当前节点的所有子节点
			List<MenuEO> childMenus = dao.getChildMenus(menuVO.getId());
			// 2 修改所有子节点的ParentIds为当前节点的parentIds + parentId
			for (MenuEO childMenu : childMenus) {
				childMenu.setParentIds(childMenu.getParentIds().replace(oldParentIds, menuVO.getParentIds()));
				// 更新到数据库
				dao.updateByPrimaryKey(childMenu);
			}
		}
		// 修改当前节点
		dao.updateByPrimaryKeySelective(beanMapper.map(menuVO, MenuEO.class));

	}

	/**
	 * 逻辑删除菜单及其子菜单，物理删除菜单及其子菜单与角色的关联
	 * 
	 * @param ids
	 *            待删除的所有菜单ID
	 */
	public void delete(String[] ids) {
		dao.deleteMenuLogic(ids);
		dao.deleteRoleMenus(ids);
	}

	/**
	 * 获取用户的所有菜单
	 * 
	 * @param userId
	 *            用户ID
	 */
	@Transactional(readOnly = true, rollbackFor = Throwable.class)
	public List<MenuEO> listMenuEOByUserId(String userId) {
		return dao.listMenuEOByUserId(userId);
	}

	/**
	 * 获取所有的菜单
	 * 
	 * @return 所有菜单
	 */
	@Transactional(readOnly = true, rollbackFor = Throwable.class)
	public List<MenuEO> findAll() {
		return dao.findAll();
	}

	/**
	 * 判定菜单是否属于角色
	 * 
	 * @param roleId
	 *            角色ID
	 * @param menuId
	 *            菜单ID
	 * @return true -> 菜单属于角色
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public boolean isBelong(String roleId, String menuId) {
		int count = dao.isBelong(roleId, menuId);
		return count > 0;
	}

	/**
	 * 获取角色对应的所有菜单
	 * 
	 * @param roleId
	 *            角色ID
	 * @return 角色对应的所有菜单
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<MenuEO> listMenuEOByRoleId(String roleId) {
		return dao.listMenuEOByRoleID(roleId);
	}
}
