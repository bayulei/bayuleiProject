package com.adc.da.sys.controller;

import com.adc.da.base.web.BaseController;
import com.adc.da.sys.constant.IsBelongEnum;
import com.adc.da.sys.entity.MenuEO;
import com.adc.da.sys.service.MenuEOService;
import com.adc.da.sys.vo.MenuVO;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.utils.BeanMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping("/${restPath}/sys/menu")
@Api(description = "菜单管理")
public class MenuEOController extends BaseController<MenuEO> {

	private static final Logger logger = LoggerFactory.getLogger(MenuEOController.class);

	@Autowired
	private MenuEOService menuEOService;

	@Autowired
	private BeanMapper beanMapper;

	@ApiOperation(value = "|MenuEO|详情")
	@GetMapping("/{id}")
	@RequiresPermissions("sys:menu:get")
	public ResponseMessage<MenuVO> find(@NotNull @PathVariable("id") String id) throws Exception {
		return Result.success(beanMapper.map(menuEOService.selectByPrimaryKey(id), MenuVO.class));
	}

	@ApiOperation(value = "|MenuEO|新增")
	@PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
	@RequiresPermissions("sys:menu:save")
	public ResponseMessage<MenuVO> create(@RequestBody MenuVO menuVO) throws Exception {
		MenuEO menuEO = menuEOService.insertMenu(beanMapper.map(menuVO, MenuEO.class));
		return Result.success(beanMapper.map(menuEO, MenuVO.class));
	}

	@ApiOperation(value = "|MenuEO|列表 --> 角色对应的菜单")
	@GetMapping
	/*@RequiresPermissions("sys:menu:list")*/
	public ResponseMessage<List<MenuVO>> list(String roleId) {
		List<MenuVO> menuVOs = beanMapper.mapList(menuEOService.findAll(), MenuVO.class);
		if(roleId != null && !"".equals(roleId)){
            for (MenuVO menuVO: menuVOs) {
                if(menuEOService.isBelong(roleId, menuVO.getId())){
//                	这个字段已经修改
//                    menuVO.setBelong(IsBelongEnum.BELONG.getValue());
                }
            }
        }
		return Result.success(menuVOs);
	}

	@ApiOperation(value = "|MenuEO|修改")
	@PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
	@RequiresPermissions("sys:menu:update")
	public ResponseMessage<MenuVO> update(@RequestBody MenuVO menuVO) throws Exception {
		menuEOService.updateMenu(menuVO);
		return Result.success(menuVO);
	}

	@ApiOperation(value = "|MenuEO|删除")
	@DeleteMapping("/{ids}")
	@RequiresPermissions("sys:menu:delete")
	public ResponseMessage delete(@NotNull @PathVariable("ids") String[] ids) throws Exception {
		menuEOService.delete(ids);
		logger.info("log===>delete from TS_MENU where ids = {}", ids);
		return Result.success();
	}

	@ApiOperation(value = "用户菜单列表|MenuEO|")
	@GetMapping("/listMenuByUserId/{userId}")
//	@RequiresPermissions("sys:menu:listMenuByUserId")
	public ResponseMessage<List<MenuVO>> listMenuByUserId(@NotNull @PathVariable("userId") String userId) {
		return Result.success(beanMapper.mapList(menuEOService.listMenuEOByUserId(userId), MenuVO.class));
	}
}
