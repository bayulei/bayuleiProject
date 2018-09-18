package com.adc.da.lawss.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;
import java.util.Map;

import com.adc.da.lawss.entity.SarProductStandEO;
import com.adc.da.lawss.entity.SarStandardsInfoEO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.lawss.entity.SarProductInfoEO;
import com.adc.da.lawss.page.SarProductInfoEOPage;
import com.adc.da.lawss.service.SarProductInfoEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

@RestController
@RequestMapping("/${restPath}/lawss/sarProductInfo")
@Api(description = "|SarProductInfoEO|")
public class SarProductInfoEOController extends BaseController<SarProductInfoEO>{

    private static final Logger logger = LoggerFactory.getLogger(SarProductInfoEOController.class);

    @Autowired
    private SarProductInfoEOService sarProductInfoEOService;

    /**
     * 分页查询
     * @param page  分页信息
     * @return
     * @author gaoyan
     * date 2018-09-17
     */
	@ApiOperation(value = "|SarProductInfoEO|分页查询")
    @GetMapping("/getProductInfoPage")
    //@RequiresPermissions("lawss:sarProductInfo:page")
    public ResponseMessage<PageInfo<SarProductInfoEO>> page(SarProductInfoEOPage page) throws Exception {
        List<SarProductInfoEO> rows = sarProductInfoEOService.querySarProductInfoByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

	@ApiOperation(value = "|SarProductInfoEO|查询")
    @GetMapping("")
    @RequiresPermissions("lawss:sarProductInfo:list")
    public ResponseMessage<List<SarProductInfoEO>> list(SarProductInfoEOPage page) throws Exception {
        return Result.success(sarProductInfoEOService.queryByList(page));
	}

    @ApiOperation(value = "|SarProductInfoEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("lawss:sarProductInfo:get")
    public ResponseMessage<SarProductInfoEO> find(@PathVariable String id) throws Exception {
        return Result.success(sarProductInfoEOService.selectByPrimaryKey(id));
    }

    /**
     * 新增
     * @param sarProductInfoEO
     * @return
     * @author gaoyan
     * date 2018-09-17
     */
    @ApiOperation(value = "|SarProductInfoEO|新增")
    @PostMapping(value = "/addSarProductInfo")
    //@RequiresPermissions("lawss:sarProductInfo:save")
    public ResponseMessage<SarProductInfoEO> create(SarProductInfoEO sarProductInfoEO) throws Exception {
        ResponseMessage<SarProductInfoEO> result = sarProductInfoEOService.createSarProductInfo(sarProductInfoEO);
        return Result.success("","添加成功",sarProductInfoEO);
    }

    /**
     * 修改
     * @param sarProductInfoEO
     * @return
     * @author gaoyan
     * date 2018-09-17
     */
    @ApiOperation(value = "|SarProductInfoEO|修改")
    @PostMapping(value = "/updateSarProductInfo")
    //@RequiresPermissions("lawss:sarProductInfo:update")
    public ResponseMessage<SarProductInfoEO> updateSarProductInfo(SarProductInfoEO sarProductInfoEO) throws Exception {
        ResponseMessage<SarProductInfoEO> result = sarProductInfoEOService.updateSarProductInfo(sarProductInfoEO);
        return Result.success("","修改成功",sarProductInfoEO);
    }

    @ApiOperation(value = "|SarProductInfoEO|删除")
    @DeleteMapping("/{id}")
   // @RequiresPermissions("lawss:sarProductInfo:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        sarProductInfoEOService.deleteByPrimaryKey(id);
        logger.info("delete from SAR_PRODUCT_INFO where id = {}", id);
        return Result.success();
    }

    @ApiOperation(value = "|SarProductInfoEO|查询企业产品关联标准法规")
    @PostMapping("/selectProductLawAndStand")
    // @RequiresPermissions("lawss:sarProductInfo:delete")
    public ResponseMessage<Map<String,List<SarProductStandEO>>> selectProductLawAndStand(SarProductInfoEOPage sarProductInfoEOPage) throws Exception {
        Map<String,List<SarProductStandEO>> resutl = sarProductInfoEOService.selectProductLawAndStandByKey(sarProductInfoEOPage);
        logger.info("delete from SAR_PRODUCT_INFO where id = {}");
        return Result.success(resutl);
    }

    @ApiOperation(value = "|SarProductInfoEO|根据产品属性（产品种类，能源种类），匹配标准中的属性，筛选出符合属性的标准")
    @PostMapping("/selectLawAndStandByPro")
    // @RequiresPermissions("lawss:sarProductInfo:delete")
    public ResponseMessage<List<SarProductStandEO>> selectLawAndStandByPro(SarProductInfoEOPage sarProductInfoEOPage) throws Exception {
        List<SarProductStandEO> resutl = sarProductInfoEOService.selectLawAndStandByPro(sarProductInfoEOPage);
        logger.info("delete from SAR_PRODUCT_INFO where id = {}");
        return Result.success(resutl);
    }

}
