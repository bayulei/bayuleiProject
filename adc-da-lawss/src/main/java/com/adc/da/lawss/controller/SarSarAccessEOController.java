package com.adc.da.lawss.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.adc.da.sys.util.LoginUserUtil;
import com.adc.da.sys.util.UUIDUtils;
import io.swagger.models.auth.In;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.lawss.entity.SarSarAccessEO;
import com.adc.da.lawss.page.SarSarAccessEOPage;
import com.adc.da.lawss.service.SarSarAccessEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

@RestController
@RequestMapping("/${restPath}/lawss/sarSarAccess")
@Api(description = "|SarSarAccessEO|")
public class SarSarAccessEOController extends BaseController<SarSarAccessEO>{

    private static final Logger logger = LoggerFactory.getLogger(SarSarAccessEOController.class);

    @Autowired
    private SarSarAccessEOService sarSarAccessEOService;



    /*
     * @Author liuyinnan
     * @Description //对数据进行分页查询
     * @Date 16:15 2018/9/27
     * @Param [page]
     * @return com.adc.da.util.http.ResponseMessage<com.adc.da.util.http.PageInfo<com.adc.da.lawss.entity.SarSarAccessEO>>
     **/
	@ApiOperation(value = "|SarSarAccessEO|分页查询")
    @GetMapping("/page")
    //@RequiresPermissions("lawss:sarSarAccess:page")
    public ResponseMessage<PageInfo<SarSarAccessEO>> page(SarSarAccessEOPage page) throws Exception {
        String userId=LoginUserUtil.getUserId();
        List<SarSarAccessEO> rows = sarSarAccessEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

	@ApiOperation(value = "|SarSarAccessEO|查询")
    @GetMapping("")
    //@RequiresPermissions("lawss:sarSarAccess:list")
    public ResponseMessage<List<SarSarAccessEO>> list(SarSarAccessEOPage page) throws Exception {
        return Result.success(sarSarAccessEOService.queryByList(page));
	}

    @ApiOperation(value = "|SarSarAccessEO|详情")
    @GetMapping("/{id}")
    //@RequiresPermissions("lawss:sarSarAccess:get")
    public ResponseMessage<SarSarAccessEO> find(@PathVariable String id) throws Exception {
        return Result.success(sarSarAccessEOService.selectByPrimaryKey(id));
    }


    /*
     * @Author liuyinnan
     * @Description //新增国家地区和准入信息
     * @Date 16:14 2018/9/27
     * @Param [sarSarAccessEO]
     * @return com.adc.da.util.http.ResponseMessage<java.lang.Integer>
     **/
    @ApiOperation(value = "|SarSarAccessEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    //@RequiresPermissions("lawss:sarSarAccess:save")
    public ResponseMessage<Integer> create(@RequestBody SarSarAccessEO sarSarAccessEO) throws Exception {
	    sarSarAccessEO.setId(UUIDUtils.randomUUID20());
	    sarSarAccessEO.setCreationTime(new Date());
	    sarSarAccessEO.setModifyTime(new Date());
	    sarSarAccessEO.setValidFlag(0);
	    sarSarAccessEO.setCreationUser("zhangsan");
        int sarSarAccessEO1=sarSarAccessEOService.insertSelective(sarSarAccessEO);
        if(sarSarAccessEO1 == 0){
            return Result.error("新增失败");
        }
        return Result.success();
    }
    

    @ApiOperation(value = "|SarSarAccessEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    //@RequiresPermissions("lawss:sarSarAccess:update")
    public ResponseMessage<SarSarAccessEO> update(@RequestBody SarSarAccessEO sarSarAccessEO) throws Exception {
        sarSarAccessEOService.updateByPrimaryKeySelective(sarSarAccessEO);
        return Result.success(sarSarAccessEO);
    }

    @ApiOperation(value = "|SarSarAccessEO|删除")
    @DeleteMapping("/{id}")
    //@RequiresPermissions("lawss:sarSarAccess:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        sarSarAccessEOService.deleteByPrimaryKey(id);
        logger.info("delete from SAR_SAR_ACCESS where id = {}", id);
        return Result.success();
    }



    /*
     * @Author liuyinnan
     * @Description //通过用户id进行删除
     * @Date 16:16 2018/9/27
     * @Param [sarSarAccessEO]
     * @return com.adc.da.util.http.ResponseMessage<java.lang.Integer>
     **/
    @ApiOperation(value = "删除")
    @PutMapping("/deleteByUserId")
    //@RequiresPermissions("lawss:sarSarAccess:update")
    public ResponseMessage<Integer> updateByUserId(@RequestBody SarSarAccessEO sarSarAccessEO) throws Exception {
        SarSarAccessEO sarSarAccessEO1=new SarSarAccessEO();
        sarSarAccessEO1.setId(UUIDUtils.randomUUID20());
        sarSarAccessEO1.setValidFlag(1);
        sarSarAccessEO1.setModifyTime(new Date());
        int sarSarAccessEO2=sarSarAccessEOService.updateByPrimaryKeySelective(sarSarAccessEO);
        return Result.success(sarSarAccessEO2);
    }
}
