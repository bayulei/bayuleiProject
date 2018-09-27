package com.adc.da.lawss.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.Arrays;
import java.util.List;
import com.adc.da.lawss.entity.SarTestItemValEO;
import com.adc.da.lawss.service.SarTestItemValEOService;
import com.adc.da.lawss.vo.SarTestItemVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.lawss.entity.SarTestItemEO;
import com.adc.da.lawss.page.SarTestItemEOPage;
import com.adc.da.lawss.service.SarTestItemEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

@RestController
@RequestMapping("/${restPath}/lawss/sarTestItem")
@Api(description = "|SarTestItemEO|")
public class SarTestItemEOController extends BaseController<SarTestItemEO>{

    private static final Logger logger = LoggerFactory.getLogger(SarTestItemEOController.class);

    @Autowired
    private SarTestItemEOService sarTestItemEOService;

    @Autowired
    private  SarTestItemValEOService sarTestItemValEOService;

    /**
     * @Author liwenxuan
     * @Description
     * 传入字段 page\pageSize\authType\testItemCode\testObj
     * @Date Administrator 2018/9/27
     * @Param [page]
     * @return com.adc.da.util.http.ResponseMessage<com.adc.da.util.http.PageInfo<com.adc.da.lawss.entity.SarTestItemEO>>
     **/
	@ApiOperation(value = "|SarTestItemEO|分页查询")
    @GetMapping("/page")
//    @RequiresPermissions("lawss:sarTestItem:page")
    public ResponseMessage<PageInfo<SarTestItemEO>> page(SarTestItemEOPage page) throws Exception {
        List<SarTestItemEO> rows = sarTestItemEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

	@ApiOperation(value = "|SarTestItemEO|查询")
    @GetMapping("")
//    @RequiresPermissions("lawss:sarTestItem:list")
    public ResponseMessage<List<SarTestItemEO>> list(SarTestItemEOPage page) throws Exception {
        return Result.success(sarTestItemEOService.queryByList(page));
	}

    @ApiOperation(value = "|SarTestItemEO|详情")
    @GetMapping("/{id}")
//    @RequiresPermissions("lawss:sarTestItem:get")
    public ResponseMessage<SarTestItemEO> find(@PathVariable String id) throws Exception {
        return Result.success(sarTestItemEOService.selectByPrimaryKey(id));
    }
/**
 * @Author liwenxuan
 * @Description //TODO
 * @Date Administrator 2018/9/27
 * @Param [sarTestItemEO]
 * @return com.adc.da.util.http.ResponseMessage<com.adc.da.lawss.entity.SarTestItemEO>
 **/
    @ApiOperation(value = "|SarTestItemEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
//    @RequiresPermissions("lawss:sarTestItem:save")
    public ResponseMessage<Integer> create(@RequestBody SarTestItemEO sarTestItemEO) throws Exception {

        SarTestItemValEO sarTestItemValEO = new SarTestItemValEO();
        String[] splitApplyArctic = sarTestItemEO.getApplyArctic().split(",");
        String[] splitEnergyKind = sarTestItemEO.getEnergyKind().split(",");

        //实验项目表新增
        int i = sarTestItemEOService.insertSelective(sarTestItemEO);

//        适用车型多个值维护到实验项目关联表
        if(splitApplyArctic!=null && splitApplyArctic.length>0){
            for(int i1=0;splitApplyArctic.length >=i1;i1++){
                sarTestItemValEO.setTestItemId(sarTestItemEO.getId());
                sarTestItemValEO.setPropertyType("applyArctic");
                sarTestItemValEO.setPropertyValue(splitApplyArctic[0]);
                int i2 = sarTestItemValEOService.insertSelective(sarTestItemValEO);
                if(i2<=0){
                    return  Result.error("参数类型插入操作失败");
                }
            }
        }
        //        能源类型多个值维护到实验项目关联表
        if(splitEnergyKind!=null && splitEnergyKind.length>0){
            for(int i1=0;splitApplyArctic.length >=i1;i1++){
                sarTestItemValEO.setTestItemId(sarTestItemEO.getId());
                sarTestItemValEO.setPropertyType("energyKind");
                sarTestItemValEO.setPropertyValue(splitEnergyKind[0]);
                int i2 = sarTestItemValEOService.insertSelective(sarTestItemValEO);
                if(i2<=0){
                    return  Result.error("参数类型插入操作失败");
                }
            }
        }
        if (i<=0){
            return Result.error("新增失败");
        }
        return Result.success("true","新增成功",1);
    }
/**
 * @Author liwenxuan
 * @Description //需要比新增多传入一个实验项目id
 * @Date Administrator 2018/9/27
 * @Param [sarTestItemEO]
 * @return com.adc.da.util.http.ResponseMessage<com.adc.da.lawss.entity.SarTestItemEO>
 **/
    @ApiOperation(value = "|SarTestItemEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
//    @RequiresPermissions("lawss:sarTestItem:update")
    public ResponseMessage<Integer> update(@RequestBody SarTestItemEO sarTestItemEO) throws Exception {
        int i = sarTestItemEOService.updateByPrimaryKeySelective(sarTestItemEO);
        if (i<=0){
            return Result.error("修改失败");
        }
        return Result.success("true","修改成功",1);
    }
//没用
    @ApiOperation(value = "|SarTestItemEO|删除")
    @DeleteMapping("/{id}")
//    @RequiresPermissions("lawss:sarTestItem:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        sarTestItemEOService.deleteByPrimaryKey(id);
        logger.info("delete from SAR_TEST_ITEM where id = {}", id);
        return Result.success();
    }
/**
 * @Author liwenxuan
 * @Description 删除多条数据
 * @Date Administrator 2018/9/27
 * @Param [ids]
 * @return com.adc.da.util.http.ResponseMessage
 **/
    @ApiOperation(value = "|DicTypeEO|删除多条数据")
    @DeleteMapping("/deleteArr")
//	@RequiresPermissions("lawss:sarTestItem:deleteArr")
    public ResponseMessage deleteArr(String[] ids) throws Exception {

        int i = sarTestItemEOService.deleteByPrimaryKeyList(Arrays.asList(ids));
        if(i<=0){
            return  Result.error("删除失败");
        }
        return Result.success("true", "删除成功", null);
    }


}
