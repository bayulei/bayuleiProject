package com.adc.da.sys.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import com.adc.da.sys.util.UUIDUtils;
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
public class DicTypeEORestController extends BaseController<DicTypeEO> {
    private static final Logger logger = LoggerFactory.getLogger(DicTypeEO.class);
    @Autowired
    private DicTypeEOService dicTypeEOService;
    @Autowired
    BeanMapper beanMapper;

    /**
     * @return com.adc.da.util.http.ResponseMessage<com.adc.da.sys.vo.DicTypeVO>
     * @Author liwenxuan
     * @Description 新增数据字典，必填字段：字典类型编码、字典类型名称
     * @Date Administrator 2018/9/17
     * @Param [dicTypeVO]
     **/
    @ApiOperation(value = "|DicTypeEO|新增")
    @PostMapping("/create")
//	@RequiresPermissions("sys:dicType:save")
    public ResponseMessage<Integer> create(@RequestBody DicTypeVO dicTypeVO) throws Exception {

        //通过传入的code获取对象（此时id为空，sql做了判断）
        List<DicTypeEO> dicTypeEOList = dicTypeEOService.getDicTypeEOByDicTypeCode(dicTypeVO.getDicId(),dicTypeVO.getId(),dicTypeVO.getDicTypeCode());
        //通过传入的name和数据字典id获取对象
        List<DicTypeEO> dicTypeEOS = dicTypeEOService.getTypeIdByDicIdAndTypeName(dicTypeVO.getDicId(),dicTypeVO.getId(), dicTypeVO.getDicTypeName());

        if (dicTypeVO.getDicTypeCode().isEmpty()) {
            return Result.error("字典类型编码不能为空");
        } else if (dicTypeEOList != null && !dicTypeEOList.isEmpty()) {
            return Result.error("字典类型编码已存在");
        }
        if (dicTypeVO.getDicTypeName().isEmpty()) {
            return Result.error("字典类型名称不能为空");
        } else if (dicTypeEOS != null && !dicTypeEOS.isEmpty()) {
            return Result.error("字典类型名称存在");
        }

        Integer dicTypeEO = dicTypeEOService.saveDictype(beanMapper.map(dicTypeVO, DicTypeEO.class));
        if(dicTypeEO>0){
          return   Result.success("true","新增成功");
        }

        return Result.error("新增失败");
    }


    /**
     * @return com.adc.da.util.http.ResponseMessage<com.adc.da.util.http.PageInfo   <   com.adc.da.sys.entity.DicTypeEO>>
     * @Author liwenxuan
     * @Description 分页查询(根据code也可以进行模糊查找)
     * @Date Administrator 2018/9/17
     * @Param [pageNo, pageSize, dicId, dicTypeName, dicTypeCode]
     **/
    @ApiOperation(value = "|DicTypeEO|分页列表")
    @GetMapping("/page")
//	@RequiresPermissions("sys:dicType:page")
    public ResponseMessage<PageInfo<DicTypeEO>> pageListByDicId(DicTypeEOPage page) throws Exception {
        List<DicTypeEO> rows = dicTypeEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }


    /**
     * @return com.adc.da.util.http.ResponseMessage<com.adc.da.sys.vo.DicTypeVO>
     * @Author liwenxuan
     * @Description 修改数据字典编码和名称
     * 1.如果修改的字典类型编码不改变也可以进行修改   通过用户id获得字段名字和输入的名字一致也可以
     * 2.原name和新输入的name进行对比,如果一样不算在字典类型编码重复
     * @Date Administrator 2018/9/17
     * @Param [dicTypeVO]
     **/
    @ApiOperation(value = "|DicTypeEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
//	@RequiresPermissions("sys:dicType:update")
    public ResponseMessage<Integer> update(@RequestBody DicTypeVO dicTypeVO) throws Exception {
        //通过传入的code和id获取对象
        List<DicTypeEO> dicTypeEOList = dicTypeEOService.getDicTypeEOByDicTypeCode(dicTypeVO.getDicId(),dicTypeVO.getId(),dicTypeVO.getDicTypeCode());
        //通过传入的name获取对象
        List<DicTypeEO> dicTypeEOS = dicTypeEOService.getTypeIdByDicIdAndTypeName(dicTypeVO.getDicId(),dicTypeVO.getId(), dicTypeVO.getDicTypeName());

        if (dicTypeVO.getDicTypeCode().isEmpty()) {
            return Result.error("字典类型编码不能为空");
        }else if (!dicTypeEOList.isEmpty()) {
                return Result.error("字典类型编码已存在");
        }

        if (dicTypeVO.getDicTypeName().isEmpty()) {
            return Result.error("字典类型名称不能为空");
        } else if (!dicTypeEOS.isEmpty()) {
                return Result.error("字典类型名称存在");
        }
        int i = dicTypeEOService.updateByPrimaryKeySelective(beanMapper.map(dicTypeVO, DicTypeEO.class));
        if(i==0){
            return Result.error("修改失败");
        }
        return Result.success("true", "修改成功");
    }

    /**
     * @return com.adc.da.util.http.ResponseMessage<com.adc.da.sys.vo.DicTypeVO>
     * @Author liwenxuan
     * @Description 根据数据字典参数表id获取数据字典参数表信息
     * @Date Administrator 2018/9/17
     * @Param [id]
     **/
    @ApiOperation(value = "|DicTypeEO|详情")
    @GetMapping("/{id}")
//	@RequiresPermissions("sys:dicType:get")
    public ResponseMessage<DicTypeVO> getById(@NotNull @PathVariable("id") String id) throws Exception {
        DicTypeVO dicTypeVO = beanMapper.map(dicTypeEOService.getDicTypeById(id), DicTypeVO.class);
        return Result.success(dicTypeVO);
    }


    /**
     * @return com.adc.da.util.http.ResponseMessage
     * @Author liwenxuan
     * @Description 删除多条数据  ,实际上设置vilid_flag=1
     * @Date Administrator 2018/9/17
     * @Param [ids]
     **/
    @ApiOperation(value = "|DicTypeEO|删除多条数据")
    //@DeleteMapping("/deleteArr/{ids}")
    @DeleteMapping("/deleteArr")
//	@RequiresPermissions("sys:dicType:delete")
    public ResponseMessage deleteArr(String[] ids) throws Exception {
        dicTypeEOService.delete(Arrays.asList(ids));
        return Result.success("", "删除成功", null);
    }


    /**
     * @return com.adc.da.util.http.ResponseMessage
     * @Author liwenxuan
     * @Description 删除一条数据
     * @Date Administrator 2018/9/17
     * @Param [id]
     **/
    @ApiOperation(value = "|DicTypeEO|删除一条数据")
    @DeleteMapping("/delete")
//	@RequiresPermissions("sys:dicType:deleteArr")
    public ResponseMessage delete(String dicTypeEOId) throws Exception {
        dicTypeEOService.deleteDicTypeByDicId(dicTypeEOId);
        return Result.success("", "删除成功", null);
    }

    /**
     * @return com.adc.da.util.http.ResponseMessage<java.util.Map   <   java.lang.String   ,   java.lang.String>>
     * @Author yangxuenan
     * @Description 根据数据字典编码查询字典类型
     * Date 2018/9/12 10:12
     * @Param [dicCode]
     **/
    @ApiOperation(value = "|DicTypeEO|查询字典类型")
    @GetMapping("/getDicTypeByDicCode")
    public ResponseMessage<List<Map<String, String>>> getDicTypeByDicCode(@RequestParam String dicCode) throws Exception {
        List<Map<String, String>> dicTypeEO = dicTypeEOService.getDicTypeByDicCode(dicCode);
        return Result.success(dicTypeEO);
    }

    /**
     * @return com.adc.da.util.http.ResponseMessage<java.util.List   <   com.adc.da.sys.entity.DicTypeEO>>
     * @Author gaoyan
     * @Description 分组查询全部字典类型
     * Date 2018/9/11 19:09
     * @Param [dicCode]
     **/
    @ApiOperation(value = "|DicTypeEO|查询字典类型")
    @GetMapping("/getDicTypeListCode")
    public ResponseMessage<Map<String, Object>> getDicTypeListCode() throws Exception {
        Map<String, Object> dicTypeEO = dicTypeEOService.getDicTypeListCode();
        return Result.success(dicTypeEO);
    }
}
