package com.adc.da.sys.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

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

/**
 * @Author liwenxuan
 * @Description 新增数据字典，必填字段：字典类型编码、字典类型名称
 * @Date Administrator 2018/9/17
 * @Param [dicTypeVO]
 * @return com.adc.da.util.http.ResponseMessage<com.adc.da.sys.vo.DicTypeVO>
 **/
	@ApiOperation(value = "|DicTypeEO|新增")
	@PostMapping("/create")
//	@RequiresPermissions("sys:dicType:save")
	public ResponseMessage<DicTypeVO> create(@RequestBody DicTypeVO dicTypeVO) throws Exception {

		List<DicTypeEO> dicTypeEOList = dicTypeEOService.getDicTypeEOByDicTypeCode(dicTypeVO.getDicTypeCode());
		List<DicTypeEO> dicTypeEOS = dicTypeEOService.getTypeIdByDicIdAndTypeName(dicTypeVO.getDicId(),dicTypeVO.getDicTypeName());

		if(StringUtils.isBlank(dicTypeVO.getDicTypeCode())){
			return Result.error("字典类型编码不能为空");
		}else if(dicTypeEOList!=null && !dicTypeEOList.isEmpty()){
			return Result.error("字典类型编码已存在");
		}
		if(StringUtils.isBlank(dicTypeVO.getDicTypeName())){
			return Result.error( "字典类型名称不能为空");
		}else if(dicTypeEOS!=null && !dicTypeEOS.isEmpty() ){
			return Result.error("字典类型名称存在");
		}

		DicTypeEO dicTypeEO = dicTypeEOService.saveDictype(beanMapper.map(dicTypeVO,DicTypeEO.class));

		return Result.success("","新增成功",dicTypeVO);
	}


	/**
	 * @Author liwenxuan
	 * @Description 分页查询(根据code也可以进行模糊查找)
	 * @Date Administrator 2018/9/17
	 * @Param [pageNo, pageSize, dicId, dicTypeName, dicTypeCode]
	 * @return com.adc.da.util.http.ResponseMessage<com.adc.da.util.http.PageInfo<com.adc.da.sys.entity.DicTypeEO>>
	 **/
	@ApiOperation(value = "|DicTypeEO|分页列表")
	@GetMapping("/page")
//	@RequiresPermissions("sys:dicType:page")
    public ResponseMessage<PageInfo<DicTypeEO>> pageListByDicId(Integer pageNo, Integer pageSize,String dicId, String dicTypeName,String dicTypeCode) throws Exception{
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
		if (StringUtils.isNotEmpty(dicTypeCode)) {
			page.setDicTypeCode(dicTypeCode);
			page.setDicTypeCodeOperator("LIKE");
		}
		page.setPager(new Pager());
		List<DicTypeEO> rows = dicTypeEOService.queryByPage(page);
		//PageInfo<DicTypeVO> mapPage = beanMapper.mapPage( rows, DicTypeVO.class);
		// getPageInfo(page.getPager(), rows);
		return  Result.success(getPageInfo(page.getPager(), rows));
    }
	

    /**
     * @Author liwenxuan
	 * @Description  修改数据字典编码和名称
	 * 1.如果修改的字典类型编码不改变也可以进行修改   通过用户id获得字段名字和输入的名字一致也可以
	 * 2.原name和新输入的name进行对比,如果一样不算在字典类型编码重复
     * @Date Administrator 2018/9/17
     * @Param [dicTypeVO]
     * @return com.adc.da.util.http.ResponseMessage<com.adc.da.sys.vo.DicTypeVO>
     **/
	@ApiOperation(value = "|DicTypeEO|修改")
	@PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
//	@RequiresPermissions("sys:dicType:update")
	public ResponseMessage<DicTypeVO> update(@RequestBody DicTypeVO dicTypeVO) throws Exception {
		List<DicTypeEO> dicTypeEOList = dicTypeEOService.getDicTypeEOByDicTypeCode(dicTypeVO.getDicTypeCode());
		List<DicTypeEO> dicTypeEOS = dicTypeEOService.getTypeIdByDicIdAndTypeName(dicTypeVO.getDicId(),dicTypeVO.getDicTypeName());
		DicTypeEO   dicTypeEO  =   dicTypeEOService.getDicTypeById(dicTypeVO.getId());

		if(StringUtils.isBlank(dicTypeVO.getDicTypeCode())){
			return Result.error( "字典类型编码不能为空");
		}else if(dicTypeEO.getDicTypeCode().equals(dicTypeVO.getDicTypeCode())){
					if(dicTypeEOList!=null && !dicTypeEOList.isEmpty()  ){
				return Result.error("字典类型编码已存在");
			        }
			}

		if(StringUtils.isBlank(dicTypeVO.getDicTypeName())){
			return Result.error("字典类型名称不能为空");
		}else	if(dicTypeEO.getDicTypeName().equals(dicTypeVO.getDicTypeName())){
			if(dicTypeEOS!=null && !dicTypeEOS.isEmpty() ){
				return Result.error("字典类型名称存在");
			}
		}
		dicTypeEOService.updateByPrimaryKeySelective(beanMapper.map(dicTypeVO, DicTypeEO.class));
		return Result.success("","修改成功",dicTypeVO);
	}
	/**
	 * @Author liwenxuan
	 * @Description   根据数据字典参数表id获取数据字典参数表信息
	 * @Date Administrator 2018/9/17
	 * @Param [id]
	 * @return com.adc.da.util.http.ResponseMessage<com.adc.da.sys.vo.DicTypeVO>
	 **/
	@ApiOperation(value = "|DicTypeEO|详情")
	@GetMapping("/{id}")
//	@RequiresPermissions("sys:dicType:get")
	public ResponseMessage<DicTypeVO> getById(@NotNull @PathVariable("id") String id) throws Exception {
		DicTypeVO dicTypeVO = beanMapper.map(dicTypeEOService.getDicTypeById(id), DicTypeVO.class);
		return Result.success(dicTypeVO);
	}


	/**
	 * @Author liwenxuan
	 * @Description  删除多条数据  ,实际上设置vilid_flag=1
	 * @Date Administrator 2018/9/17
	 * @Param [ids]
	 * @return com.adc.da.util.http.ResponseMessage
	 **/
	@ApiOperation(value = "|DicTypeEO|删除多条数据")
	//@DeleteMapping("/deleteArr/{ids}")
	@DeleteMapping("/deleteArr")
//	@RequiresPermissions("sys:dicType:delete")
	public ResponseMessage delete(String[] ids) throws Exception {
		dicTypeEOService.delete(Arrays.asList(ids));
		return Result.success("","删除成功",null);
	}



	/**
	 * @Author liwenxuan
	 * @Description  删除一条数据
	 * @Date Administrator 2018/9/17
	 * @Param [id]
	 * @return com.adc.da.util.http.ResponseMessage
	 **/
	@ApiOperation(value = "|DicTypeEO|删除一条数据")
	@DeleteMapping("/delete/{ids}")
//	@RequiresPermissions("sys:dicType:deleteArr")
	public ResponseMessage deleteArr(@NotNull @PathVariable("ids") String id) throws Exception {
		dicTypeEOService.deleteDicTypeByDicId(id);
		return Result.success("","删除成功",null);
	}

	/**
	 * @Author yangxuenan
	 * @Description 根据数据字典编码查询字典类型
	 * Date 2018/9/12 10:12
	 * @Param [dicCode]
	 * @return com.adc.da.util.http.ResponseMessage<java.util.Map<java.lang.String,java.lang.String>>
	 **/
	@ApiOperation(value = "|DicTypeEO|查询字典类型")
	@GetMapping("/getDicTypeByDicCode")
	public ResponseMessage<List<Map<String,String>>> getDicTypeByDicCode(@RequestParam String dicCode) throws Exception {
		List<Map<String,String>> dicTypeEO = dicTypeEOService.getDicTypeByDicCode(dicCode);
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
