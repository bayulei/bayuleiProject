package com.adc.da.lawss.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.lawss.entity.SarStandardsInfoEO;
import com.adc.da.lawss.page.SarStandardsInfoEOPage;
import com.adc.da.lawss.service.SarStandardsInfoEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

/**
 * 用户管理模块相关接口
 * 1.详情
 * 2.分页查询
 * 3.新增
 * 4.修改
 * 5.删除
 * 6.配置角色信息
 *
 * @author gaoyan
 * date 2018/09/03
 * @see UserEOService
 * @see UserEO
 * @see UserVO
 * @see UserEOPage
 * @see com.adc.da.sys.dao.UserEODao
 * @see mybatis.mapper.sys
 */
@RestController
@RequestMapping("/${restPath}/lawss/sarStandardsInfo")
@Api(description = "|标准信息管理|")
public class SarStandardsInfoEOController extends BaseController<SarStandardsInfoEO>{

    private static final Logger logger = LoggerFactory.getLogger(SarStandardsInfoEOController.class);

    @Autowired
    private SarStandardsInfoEOService sarStandardsInfoEOService;

	@ApiOperation(value = "|SarStandardsInfoEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("lawss:sarStandardsInfo:page")
    public ResponseMessage<PageInfo<SarStandardsInfoEO>> page(SarStandardsInfoEOPage page) throws Exception {
        List<SarStandardsInfoEO> rows = sarStandardsInfoEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

	@ApiOperation(value = "|SarStandardsInfoEO|查询")
    @GetMapping("")
    @RequiresPermissions("lawss:sarStandardsInfo:list")
    public ResponseMessage<List<SarStandardsInfoEO>> list(SarStandardsInfoEOPage page) throws Exception {
        return Result.success(sarStandardsInfoEOService.queryByList(page));
	}

    @ApiOperation(value = "|SarStandardsInfoEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("lawss:sarStandardsInfo:get")
    public ResponseMessage<SarStandardsInfoEO> find(@PathVariable String id) throws Exception {
        return Result.success(sarStandardsInfoEOService.selectByPrimaryKey(id));
    }

    /**
     *
     * @param sarStandardsInfoEO
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "|SarStandardsInfoEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE,value = "addarStandardsInfo")
    //@RequiresPermissions("lawss:sarStandardsInfo:save")
    public ResponseMessage<SarStandardsInfoEO> create(@RequestBody SarStandardsInfoEO sarStandardsInfoEO) throws Exception {
        sarStandardsInfoEOService.createSarStandardsInfo(sarStandardsInfoEO);
        return Result.success(sarStandardsInfoEO);
    }

    @ApiOperation(value = "|SarStandardsInfoEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("lawss:sarStandardsInfo:update")
    public ResponseMessage<SarStandardsInfoEO> update(@RequestBody SarStandardsInfoEO sarStandardsInfoEO) throws Exception {
        sarStandardsInfoEOService.updateByPrimaryKeySelective(sarStandardsInfoEO);
        return Result.success(sarStandardsInfoEO);
    }

    @ApiOperation(value = "|SarStandardsInfoEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("lawss:sarStandardsInfo:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        sarStandardsInfoEOService.deleteByPrimaryKey(id);
        logger.info("delete from SAR_STANDARDS_INFO where id = {}", id);
        return Result.success();
    }

}
