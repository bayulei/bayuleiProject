package com.adc.da.sys.controller;

import com.adc.da.base.page.Pager;
import com.adc.da.base.web.BaseController;
import com.adc.da.sys.common.LayUiResult;
import com.adc.da.sys.entity.DicTypeEO;
import com.adc.da.sys.entity.DictionaryEO;
import com.adc.da.sys.page.DicTypeEOPage;
import com.adc.da.sys.page.DictionaryEOPage;
import com.adc.da.sys.service.DicEOService;
import com.adc.da.sys.service.DicTypeEOService;
import com.adc.da.sys.vo.DictionaryVO;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.utils.BeanMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

/**
 *
 */
@RestController
@RequestMapping("/${restPath}/sys/dictionary")
@Api(description = "数据字典管理")
public class DicEORestController extends BaseController<DictionaryEO>{
	private static final Logger logger = LoggerFactory.getLogger(DictionaryEO.class);
	
	@Autowired
	private DicEOService dicEOService;
	
	@Autowired
	private DicTypeEOService dicTypeEOService;

	@Autowired
	BeanMapper beanMapper;

	@SuppressWarnings("unchecked")
	@ApiOperation(value = "|DictionaryEO|新增数据字典")
	@PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
	//@RequiresPermissions("sys:dic:save")
	public ResponseMessage<DictionaryVO> create(@RequestBody DictionaryVO dicVO) throws Exception {
		if (StringUtils.isBlank(dicVO.getDictionaryCode())) {
			return Result.error("r0014", "字典编码不能为空");
		}
		else if (dicEOService.getDictionaryByDicCode(dicVO.getDictionaryCode()) != null) {
			return Result.error("r0015", "字典编号已存在");
		}
		if (StringUtils.isBlank(dicVO.getDictionaryName())) {
			return Result.error("r0016", "字典名称不能为空");
		}else if (dicEOService.getDictionaryByDicName(dicVO.getDictionaryName())!=null) {
			return Result.error("r0015", "字典名称已经存在");
		}

		DictionaryEO dicEO = dicEOService.save(beanMapper.map(dicVO, DictionaryEO.class));
		return Result.success(beanMapper.map(dicEO, DictionaryVO.class));
	}






	/**
	 * 周鑫
	 * @param page
	 * @return
	 * @throws Exception
	 */
///李文轩：数据字典没有分页
	/*@ApiOperation(value = "|DictionaryEO|分页查询")
	@GetMapping("/page")
//	@RequiresPermissions("sys:dic:page")
	public LayUiResult page(DictionaryEOPage page) throws Exception {
		page.setOrderBy("u0.modify_time desc");
		List<DictionaryEO> rows = dicEOService.queryByPage(page);
		return new LayUiResult(getPageInfo(page.getPager(), rows));
	}*/
	
	@ApiOperation(value = "|DictionaryEO|修改")
	@PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
//	 @RequiresPermissions("sys:dic:update")
	public ResponseMessage<DictionaryVO> update(@RequestBody DictionaryVO dictionaryVO) throws Exception {

		DictionaryEO dictionaryByDicCode = dicEOService.getDictionaryByDicCode(dictionaryVO.getDictionaryCode());
		DictionaryEO dictionaryByDicName = dicEOService.getDictionaryByDicName(dictionaryVO.getDictionaryName());

		/*if (StringUtils.isBlank(dictionaryVO.getDictionaryCode())) {
			return Result.error("r0014", "字典编码不能为空");
		} else if (getDic != null && !(dictionaryVO.getId().equals(getDic.getId()))) {
			return Result.error("r0015", "字典编号已存在");
		}
		if (StringUtils.isBlank(dictionaryVO.getDictionaryName())) {
			return Result.error("r0016", "字典名称不能为空");
		} else if (getDic != null && !(dictionaryVO.getId().equals(getDic.getId()))) {
			return Result.error("r0015", "字典名称已存在");
		}*/
//李文轩
		if (StringUtils.isBlank(dictionaryVO.getDictionaryCode())) {
			return Result.error("r0014", "字典编码不能为空");
		} else if (dictionaryByDicCode != null && !(dictionaryVO.getId().equals(dictionaryByDicCode.getId()))) {
			return Result.error("r0015", "字典编号已存在");
		}
		if (StringUtils.isBlank(dictionaryVO.getDictionaryName())) {
			return Result.error("r0016", "字典名称不能为空");
		} else if (dictionaryByDicName != null && !(dictionaryVO.getId().equals(dictionaryByDicName.getId()))) {
			return Result.error("r0015", "字典名称已存在");
		}



		dicEOService.updateById(beanMapper.map(dictionaryVO, DictionaryEO.class));
		return Result.success(dictionaryVO);
	}
	
	@ApiOperation(value = "|DictionaryEO|详情")
	@GetMapping("/{id}")
//	 @RequiresPermissions("sys:dic:get")
	public ResponseMessage<DictionaryVO> getById(@NotNull @PathVariable("id") String id) throws Exception {
		DictionaryVO dictionaryVO = beanMapper.map(dicEOService.getDictionaryById(id), DictionaryVO.class);
		return Result.success(dictionaryVO);
	}
	
	@ApiOperation(value = "|DictionaryEO|删除")
	@DeleteMapping("/{id}")
//	 @RequiresPermissions("sys:dic:delete")
	public ResponseMessage delete(@NotNull @PathVariable(value = "id") String id) throws Exception {
		DicTypeEOPage page = new DicTypeEOPage();
		if (StringUtils.isNotEmpty(id)) {
			page.setDicId(id);
		}
		page.setPager(new Pager());
		List<DicTypeEO> rows = dicTypeEOService.queryByPage(page);
		if(rows!=null && !rows.isEmpty()){
			return Result.error("r0031", "该字典下存在类型明细，确定删除吗？");
		}
		dicEOService.delete(id);
		return Result.success();
	}

	@ApiOperation(value = "|DictionaryEO|删除字典和相关明细")
	@DeleteMapping("/deleteDicAndType/{id}")
//	 @RequiresPermissions("sys:dic:delete")
	public ResponseMessage deleteDicAndType(@NotNull @PathVariable(value = "id") String id) throws Exception {
		dicEOService.deleteDicAndType(id);
		return Result.success();
	}

	/*
	 *  数据字典下拉框接口（通用）
	 * @MethodName:getByDicCode
	 * @author: DuYunbao
	 * @date: 2018/5/3 10:36
	 */
	@ApiOperation(value = "数据字典下拉框接口")
	@GetMapping("/dicCode/{dicCode}")
//	 @RequiresPermissions("sys:dic:getByDicCode")
	public ResponseMessage<DictionaryVO> getByDicCode(@NotNull @PathVariable("dicCode") String dicCode) throws Exception {
		DictionaryVO dictionaryVO = beanMapper.map(dicEOService.getDicEOAndTypeEoByDicCode(dicCode), DictionaryVO.class);
		return Result.success(dictionaryVO);
	}

}
