package com.adc.da.lawss.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.adc.da.lawss.entity.SarBussStandMenuEO;
import com.adc.da.lawss.entity.SarLawsMenuEO;
import com.adc.da.lawss.entity.SarStandMenuEO;
import com.adc.da.lawss.page.SarLawsMenuEOPage;
import com.adc.da.lawss.vo.NewmenuOldmenuVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.lawss.entity.SarMenuEO;
import com.adc.da.lawss.page.SarMenuEOPage;
import com.adc.da.lawss.service.SarMenuEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

@RestController
@RequestMapping("/${restPath}/lawss/sarMenu")
@Api(description = "|SarMenuEO|")
public class SarMenuEOController extends BaseController<SarMenuEO>{

    private static final Logger logger = LoggerFactory.getLogger(SarMenuEOController.class);

    @Autowired
    private SarMenuEOService sarMenuEOService;

	@ApiOperation(value = "|SarMenuEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("lawss:sarMenu:page")
    public ResponseMessage<PageInfo<SarMenuEO>> page(SarMenuEOPage page) throws Exception {
        List<SarMenuEO> rows = sarMenuEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

	@ApiOperation(value = "|SarMenuEO|查询")
    @GetMapping("/selectmenu")
    /*@RequiresPermissions("lawss:sarMenu:list")*/
    public ResponseMessage<List<SarMenuEO>> list(SarMenuEOPage page) throws Exception {
        page.setValidFlag("0");
        List<SarMenuEO> list = sarMenuEOService.queryByList(page);
        return Result.success(list);
	}

    @ApiOperation(value = "|SarMenuEO|详情")
    @GetMapping("/{id}")
    /*@RequiresPermissions("lawss:sarMenu:get")*/
    public ResponseMessage<SarMenuEO> find(@PathVariable String id) throws Exception {
        return Result.success(sarMenuEOService.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "|SarMenuEO|新增")
    @PostMapping("/addSarMenu")
    /*@RequiresPermissions("lawss:sarMenu:save")*/
    public ResponseMessage<SarMenuEO> addSarMenu(SarMenuEO sarMenuEO) throws Exception {
        return sarMenuEOService.createSarMenu(sarMenuEO);
    }

    @ApiOperation(value = "|SarMenuEO|修改")
    @PostMapping("/updateSarMenu")
    /*@RequiresPermissions("lawss:sarMenu:update")*/
    public ResponseMessage<SarMenuEO> updateSarMenu(SarMenuEO sarMenuEO) throws Exception {
        sarMenuEO.setModifyTime(new Date());
        int countUpdate = sarMenuEOService.updateByPrimaryKeySelective(sarMenuEO);
        if(countUpdate > 0){
            return Result.success("0","修改成功",sarMenuEO);
        } else {
            return Result.error("修改失败");
        }
    }

    @ApiOperation(value = "|SarMenuEO|删除")
    @DeleteMapping("/{id}")
    /*@RequiresPermissions("lawss:sarMenu:delete")*/
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        sarMenuEOService.deleteByPrimaryKey(id);
        logger.info("delete from SAR_MENU where id = {}", id);
        return Result.success();
    }

    /**
     * @Author yangxuenan
     * @Description 根据父ID查询子节点下是否有记录
     * Date 2018/9/11 17:06
     * @Param [parentId]
     * @return com.adc.da.util.http.ResponseMessage<java.util.List<com.adc.da.lawss.entity.SarMenuEO>>
     **/
    @ApiOperation(value = "|SarMenuEO|查询子节点")
    @GetMapping("/judgequeryMenuByPid")
    /*@RequiresPermissions("lawss:sarMenu:list")*/
    public ResponseMessage<Boolean> judgequeryMenuByPid(SarMenuEO sarMenuEO) throws Exception {
        return Result.success(sarMenuEOService.judgequeryMenuByPid(sarMenuEO));
    }

    /**
     * @Author yangxuenan
     * @Description 删除目录及子目录
     * Date 2018/9/11 17:30
     * @Param [sarMenuEO]
     * @return com.adc.da.util.http.ResponseMessage
     **/
    @ApiOperation(value = "|SarMenuEO|删除目录及子目录")
    @PostMapping("/deleteMenuAndChildren")
    /*@RequiresPermissions("lawss:sarMenu:update")*/
    public ResponseMessage deleteMenuAndChildren(SarMenuEO sarMenuEO) throws Exception {
        sarMenuEO.setModifyTime(new Date());
        sarMenuEO.setValidFlag(1);
        int countUpdate = sarMenuEOService.updateByPrimaryKeySelective(sarMenuEO);
        if(countUpdate > 0){
            //查询是否包含子目录
            List<SarMenuEO> getChildrenMenu = sarMenuEOService.queryMenuByPid(sarMenuEO);
            if(getChildrenMenu.size() > 0) {
                for(int i=0;i<getChildrenMenu.size();i++){
                    SarMenuEO upMenu = new SarMenuEO();
                    upMenu.setId(getChildrenMenu.get(i).getId());
                    upMenu.setValidFlag(1);
                    upMenu.setSorDivide(sarMenuEO.getSorDivide());
                    //删除子目录
                    sarMenuEOService.updateByPrimaryKeySelective(upMenu);
                    //删除对应标准，法规，
                    sarMenuEOService.updateStandLawsBymenuid(upMenu);

                }
            }
            return Result.success("0","删除成功",sarMenuEO);
        } else {
            return Result.error("删除失败");
        }
    }

}
