package com.adc.da.sys.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import com.adc.da.sys.entity.DictionaryEO;
import com.adc.da.sys.vo.DictionaryVO;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.page.Pager;
import com.adc.da.base.web.BaseController;
import com.adc.da.sys.common.LayUiResult;
import com.adc.da.sys.entity.DicTypeEO;
import com.adc.da.sys.page.DicTypeEOPage;
import com.adc.da.sys.service.DicTypeEOService;
import com.adc.da.sys.vo.DicTypeVO;
import com.adc.da.util.http.PageInfo;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.utils.BeanMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/${restPath}/sys/dictype")
@Api(description = "字典类型明细管理")
public class DicTypeEORestController extends BaseController<DicTypeEO>{
	private static final Logger logger = LoggerFactory.getLogger(DicTypeEO.class);
	@Autowired
	private DicTypeEOService dicTypeEOService;

	@Autowired
	BeanMapper beanMapper;
	
	@ApiOperation(value = "|DicTypeEO|新增")
	@PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
//	@RequiresPermissions("sys:dicType:save")
	public ResponseMessage<DicTypeVO> create(@RequestBody DicTypeVO dicTypeVO) throws Exception {

		List<DicTypeEO> dicTypeEOList = dicTypeEOService.getDicTypeEOByDicTypeCode(dicTypeVO.getDicTypeCode());
		List<DicTypeEO> dicTypeEOS = dicTypeEOService.getTypeIdByDicIdAndTypeName(dicTypeVO.getDicId(),dicTypeVO.getDicTypeName());

		if(StringUtils.isBlank(dicTypeVO.getDicTypeCode())){
			return Result.error("r0014", "字典类型编码不能为空");
		}else if(dicTypeEOList!=null && !dicTypeEOList.isEmpty()){
			return Result.error("r0015","字典类型编码已存在");
		}
		if(StringUtils.isBlank(dicTypeVO.getDicTypeName())){
			return Result.error("r0016", "字典类型名称不能为空");
		}else if(dicTypeEOS!=null && !dicTypeEOS.isEmpty() ){
			return Result.error("r0017","字典类型名称存在");
		}

//		DicTypeEO dicTypeEO = dicTypeEOService.save(beanMapper.map(dicTypeVO, DicTypeEO.class));
		DicTypeEO dicTypeEO = dicTypeEOService.saveDictype(beanMapper.map(dicTypeVO,DicTypeEO.class));

//		dicTypeVO.setId(dicTypeEO.getId());
		return Result.success(dicTypeVO);
	}

	
	@ApiOperation(value = "|DicTypeEO|分页列表")
	@GetMapping("/page")
//	@RequiresPermissions("sys:dicType:page")
    public ResponseMessage<PageInfo<DicTypeEO>> pageListByDicId(Integer pageNo, Integer pageSize,String dicId, String dicTypeName) throws Exception{
		DicTypeEOPage page = new DicTypeEOPage();
		if (pageNo != null) {
			page.setPage(pageNo);
		}
		if (pageSize != null) {
			page.setPageSize(pageSize);
		}
		if (StringUtils.isNotEmpty(dicId)) {
			page.setDicId(dicId);
		}
		if (StringUtils.isNotEmpty(dicTypeName)) {
			page.setDicTypeName(dicTypeName);
			page.setDicTypeNameOperator("LIKE");
		}
		page.setPager(new Pager());
		List<DicTypeEO> rows = dicTypeEOService.queryByPage(page);
		//PageInfo<DicTypeVO> mapPage = beanMapper.mapPage( rows, DicTypeVO.class);
		// getPageInfo(page.getPager(), rows);
		return  Result.success(getPageInfo(page.getPager(), rows));
    }
	
	@ApiOperation(value = "|DicTypeEO|修改")
	@PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
//	@RequiresPermissions("sys:dicType:update")
	public ResponseMessage<DicTypeVO> update(@RequestBody DicTypeVO dicTypeVO) throws Exception {
		List<DicTypeEO> dicTypeEOList = dicTypeEOService.getDicTypeEOByDicTypeCode(dicTypeVO.getDicTypeCode());
		List<DicTypeEO> dicTypeEOS = dicTypeEOService.getTypeIdByDicIdAndTypeName(dicTypeVO.getDicId(),dicTypeVO.getDicTypeName());

		if(StringUtils.isBlank(dicTypeVO.getDicTypeCode())){
			return Result.error("r0014", "字典类型编码不能为空");
		}else if(dicTypeEOList!=null && !dicTypeEOList.isEmpty()){
			return Result.error("r0015","字典类型编码已存在");
		}
		if(StringUtils.isBlank(dicTypeVO.getDicTypeName())){
			return Result.error("r0016", "字典类型名称不能为空");
		}else if(dicTypeEOS!=null && !dicTypeEOS.isEmpty() ){
			return Result.error("r0017","字典类型名称存在");
		}

		dicTypeEOService.updateByPrimaryKeySelective(beanMapper.map(dicTypeVO, DicTypeEO.class));
		return Result.success(dicTypeVO);
	}
	
	@ApiOperation(value = "|DicTypeEO|详情")
	@GetMapping("/{id}")
//	@RequiresPermissions("sys:dicType:get")
	public ResponseMessage<DicTypeVO> getById(@NotNull @PathVariable("id") String id) throws Exception {
		DicTypeVO dicTypeVO = beanMapper.map(dicTypeEOService.getDicTypeById(id), DicTypeVO.class);
		return Result.success(dicTypeVO);
	}
//	删除多条数据
	@ApiOperation(value = "|DicTypeEO|删除多条数据")
	@DeleteMapping("/deleteArr/{ids}")
//	@RequiresPermissions("sys:dicType:delete")
	public ResponseMessage delete(@NotNull @PathVariable("ids") String[] ids) throws Exception {
		dicTypeEOService.delete(Arrays.asList(ids));
		return Result.success();
	}

//	删除一条数据
	@ApiOperation(value = "|DicTypeEO|删除一条数据")
	@DeleteMapping("/delete/{ids}")
//	@RequiresPermissions("sys:dicType:deleteArr")
	public ResponseMessage deleteArr(@NotNull @PathVariable("ids") String id) throws Exception {

//		删除一条数据
	/*	String[] idList = ids.split(",");
		if(idList!=null && idList.length>0){
			for(String id:idList){
				dicTypeEOService.deleteDicTypeByDicId(id);
			}
		}*/
		dicTypeEOService.deleteDicTypeByDicId(id);
		return Result.success();
	}

	/**
	 * @Author yangxuenan
	 * @Description 根据数据字典编码查询字典类型
	 * Date 2018/9/11 15:16
	 * @Param [dicCode]
	 * @return com.adc.da.util.http.ResponseMessage<java.util.List<com.adc.da.sys.entity.DicTypeEO>>
	 **/
	@ApiOperation(value = "|DicTypeEO|查询字典类型")
	@GetMapping("/getDicTypeByDicCode")
	public ResponseMessage<List<DicTypeEO>> getDicTypeByDicCode(@RequestParam String dicCode) throws Exception {
		List<DicTypeEO> dicTypeEO = dicTypeEOService.getDicTypeByDicCode(dicCode);
		return Result.success(dicTypeEO);
	}

	/**
	 * @Author gaoyan
	 * @Description 分组查询全部字典类型
	 * Date 2018/9/11 19:09
	 * @Param [dicCode]
	 * @return com.adc.da.util.http.ResponseMessage<java.util.List<com.adc.da.sys.entity.DicTypeEO>>
	 **/
	@ApiOperation(value = "|DicTypeEO|查询字典类型")
	@GetMapping("/getDicTypeListCode")
	public ResponseMessage<Map<String,Object>> getDicTypeListCode() throws Exception {
		Map<String,Object> dicTypeEO = dicTypeEOService.getDicTypeListCode();
		return Result.success(dicTypeEO);
	}
}
