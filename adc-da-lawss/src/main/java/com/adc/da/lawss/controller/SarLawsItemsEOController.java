package com.adc.da.lawss.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.adc.da.excel.poi.excel.ExcelImportUtil;
import com.adc.da.excel.poi.excel.entity.ImportParams;
import com.adc.da.excel.poi.excel.entity.result.ExcelImportResult;
import com.adc.da.lawss.common.ReadExcel;
import com.adc.da.lawss.dto.LawsItemsImportDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.lawss.entity.SarLawsItemsEO;
import com.adc.da.lawss.page.SarLawsItemsEOPage;
import com.adc.da.lawss.service.SarLawsItemsEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/${restPath}/lawss/sarLawsItems")
@Api(description = "|SarLawsItemsEO|")
public class SarLawsItemsEOController extends BaseController<SarLawsItemsEO>{

    private static final Logger logger = LoggerFactory.getLogger(SarLawsItemsEOController.class);

    @Autowired
    private SarLawsItemsEOService sarLawsItemsEOService;

    /**
     * @Author yangxuenan
     * @Description 分页查询
     * Date 2018/9/13 9:08
     * @Param [page]
     * @return com.adc.da.util.http.ResponseMessage<com.adc.da.util.http.PageInfo<com.adc.da.lawss.entity.SarLawsItemsEO>>
     **/
	@ApiOperation(value = "|SarLawsItemsEO|分页查询")
    @GetMapping("/page")
    /*@RequiresPermissions("lawss:sarLawsItems:page")*/
    public ResponseMessage<PageInfo<SarLawsItemsEO>> page(SarLawsItemsEOPage page) throws Exception {
        page.setValidFlag("0");
        page.setOrderBy("modify_time desc");
        List<SarLawsItemsEO> rows = sarLawsItemsEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

	@ApiOperation(value = "|SarLawsItemsEO|查询")
    @GetMapping("")
    /*@RequiresPermissions("lawss:sarLawsItems:list")*/
    public ResponseMessage<List<SarLawsItemsEO>> list(SarLawsItemsEOPage page) throws Exception {
        return Result.success(sarLawsItemsEOService.queryByList(page));
	}

    @ApiOperation(value = "|SarLawsItemsEO|详情")
    @GetMapping("/{id}")
    /*@RequiresPermissions("lawss:sarLawsItems:get")*/
    public ResponseMessage<SarLawsItemsEO> find(@PathVariable String id) throws Exception {
        return Result.success(sarLawsItemsEOService.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "|SarLawsItemsEO|新增")
    @PostMapping("/addLawsItems")
    /*@RequiresPermissions("lawss:sarLawsItems:save")*/
    public ResponseMessage<SarLawsItemsEO> create(SarLawsItemsEO sarLawsItemsEO) throws Exception {
        return sarLawsItemsEOService.createLawsItems(sarLawsItemsEO);
    }

    /**
     * @Author yangxuenan
     * @Description 修改
     * Date 2018/9/13 9:08
     * @Param [sarLawsItemsEO]
     * @return com.adc.da.util.http.ResponseMessage<com.adc.da.lawss.entity.SarLawsItemsEO>
     **/
    @ApiOperation(value = "|SarLawsItemsEO|修改")
    @PutMapping("/updateLawsItems")
    /*@RequiresPermissions("lawss:sarLawsItems:update")*/
    public ResponseMessage<SarLawsItemsEO> update(SarLawsItemsEO sarLawsItemsEO) throws Exception {
        return sarLawsItemsEOService.updateLawsItems(sarLawsItemsEO);
    }

    @ApiOperation(value = "|SarLawsItemsEO|删除")
    @DeleteMapping("/{id}")
    /*@RequiresPermissions("lawss:sarLawsItems:delete")*/
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        sarLawsItemsEOService.deleteByPrimaryKey(id);
        logger.info("delete from SAR_LAWS_ITEMS where id = {}", id);
        return Result.success();
    }

    /**
     * @Author yangxuenan
     * @Description 条目及关联表删除
     * Date 2018/9/14 13:58
     * @Param [id]
     * @return com.adc.da.util.http.ResponseMessage
     **/
    @ApiOperation(value = "|SarLawsItemsEO|条目及关联表删除")
    @PutMapping("/deleteLawsItems")
    /*@RequiresPermissions("lawss:sarLawsInfo:delete")*/
    public ResponseMessage deleteLawsItems(@RequestParam("id") String id) throws Exception {
        return sarLawsItemsEOService.deleteLawsItems(id);
    }

    /**
     * @Author yangxuenan
     * @Description 导入法规条目
     * Date 2018/9/14 13:59
     * @Param [file]
     * @return com.adc.da.util.http.ResponseMessage
     **/
    @ApiOperation(value = "|SarLawsItemsEO|导入法规条目")
    @PostMapping("/importLawsItems")
    public ResponseMessage importLawsItems(@RequestParam("file") MultipartFile file,@RequestParam("lawsId") String lawsId) throws Exception {
    //验证文件名是否合格
        ReadExcel readExcel = new ReadExcel();
        if (!readExcel.validateExcel(file.getOriginalFilename())) {
            return Result.error("fail", "请上传excel文件");
        }

        // 获取文件输入流
        InputStream is = file.getInputStream();
        // 导入参数设置，默认即可
        ImportParams params = new ImportParams();
        params.setSkipBlankKeyCell(false);
        // 解析excel，并返回校验信息
        ExcelImportResult<LawsItemsImportDto> result = ExcelImportUtil.importExcelVerify(is, LawsItemsImportDto.class, params);
        // 如果校验不通过，返回错误信息
        if (result.isVerfiyFail()) {
            return Result.error("fail", "excel文件校验失败");
        }

        //excel读取到的数据
        List<LawsItemsImportDto> datas = result.getList();
        if(datas!=null &&!datas.isEmpty()){
            try {
                return sarLawsItemsEOService.importLawsItemsDatas(datas,lawsId);
            } catch (Exception e) {
                return Result.error("fail", e.getMessage());
            }
        }else{
            return Result.error("fail", "没有可导入的数据,请检查!");
        }
    }


}
