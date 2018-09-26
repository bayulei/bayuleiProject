package com.adc.da.sys.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.adc.da.sys.util.UUIDUtils;
import com.adc.da.util.http.ResponseMessageCodeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.sys.entity.WarnTimeEO;
import com.adc.da.sys.page.WarnTimeEOPage;
import com.adc.da.sys.service.WarnTimeEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/${restPath}/sys/warnTime")
@Api(description = "|WarnTimeEO|")
public class WarnTimeEOController extends BaseController<WarnTimeEO>{

    private static final Logger logger = LoggerFactory.getLogger(WarnTimeEOController.class);

    @Autowired
    private WarnTimeEOService warnTimeEOService;

	@ApiOperation(value = "|WarnTimeEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("sys:warnTime:page")
    public ResponseMessage<PageInfo<WarnTimeEO>> page(WarnTimeEOPage page) throws Exception {
        List<WarnTimeEO> rows = warnTimeEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

	@ApiOperation(value = "|WarnTimeEO|查询")
    @GetMapping("")
//    @RequiresPermissions("sys:warnTime:list")
    public ResponseMessage<List<WarnTimeEO>> list(WarnTimeEOPage page) throws Exception {
        return Result.success(warnTimeEOService.queryByList(page));
	}

    @ApiOperation(value = "|WarnTimeEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("sys:warnTime:get")
    public ResponseMessage<WarnTimeEO> find(@PathVariable String id) throws Exception {
        return Result.success(warnTimeEOService.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "|WarnTimeEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("sys:warnTime:save")
    public ResponseMessage<WarnTimeEO> create(@RequestBody WarnTimeEO warnTimeEO) throws Exception {
        warnTimeEOService.insertSelective(warnTimeEO);
        return Result.success(warnTimeEO);
    }

    @ApiOperation(value = "|WarnTimeEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("sys:warnTime:update")
    public ResponseMessage<WarnTimeEO> update(@RequestBody WarnTimeEO warnTimeEO) throws Exception {
        warnTimeEOService.updateByPrimaryKeySelective(warnTimeEO);
        return Result.success(warnTimeEO);
    }

    @ApiOperation(value = "|WarnTimeEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("sys:warnTime:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        warnTimeEOService.deleteByPrimaryKey(id);
        logger.info("delete from TS_WARN_TIME where id = {}", id);
        return Result.success();
    }


    @ApiOperation(value = "|WarnTimeEO|修改")
    @PutMapping(value = "/updateSource",consumes = APPLICATION_JSON_UTF8_VALUE)
//    @RequiresPermissions("sys:warnTime:update")
    public ResponseMessage<WarnTimeEO> updateSource(@NotNull @RequestBody WarnTimeEO warnTimeEO) throws Exception {
	    WarnTimeEOPage page=new WarnTimeEOPage();
	    List<WarnTimeEO> warnTimeEOList=warnTimeEOService.queryByList(page);
	    if(warnTimeEOList!= null && !warnTimeEOList.isEmpty()){
	        if(warnTimeEOList.size()>1){
                WarnTimeEO eo=warnTimeEOList.get(0);
                eo.setWarnType(warnTimeEO.getWarnType());
                warnTimeEOService.updateByPrimaryKey(eo);
                //此处删除多余数据
                for(int i=1;i<warnTimeEOList.size();i++){
                    WarnTimeEO e= warnTimeEOList.get(i);
                    warnTimeEOService.deleteByPrimaryKey(e.getId());
                }
            }else{
                WarnTimeEO eo=warnTimeEOList.get(0);
                eo.setWarnType(warnTimeEO.getWarnType());
                warnTimeEOService.updateByPrimaryKey(eo);
            }
        }else{
	        WarnTimeEO time=new WarnTimeEO();
	        time.setId(UUIDUtils.randomUUID20());
	        time.setWarnType(warnTimeEO.getWarnType());
	        warnTimeEOService.insert(time);
        }
        return Result.success(ResponseMessageCodeEnum.SUCCESS.getCode(),"设置成功",null);
    }

}
