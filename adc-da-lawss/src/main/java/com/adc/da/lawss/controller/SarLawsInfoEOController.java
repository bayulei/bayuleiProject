package com.adc.da.lawss.controller;

import static com.adc.da.lawss.common.ReadExcel.encodeFileName;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.adc.da.excel.poi.excel.ExcelExportUtil;
import com.adc.da.excel.poi.excel.ExcelImportUtil;
import com.adc.da.excel.poi.excel.entity.ExportParams;
import com.adc.da.excel.poi.excel.entity.ImportParams;
import com.adc.da.excel.poi.excel.entity.enums.ExcelType;
import com.adc.da.excel.poi.excel.entity.result.ExcelImportResult;
import com.adc.da.lawss.common.ReadExcel;
import com.adc.da.lawss.dto.LawsInfoExportDto;
import com.adc.da.lawss.dto.LawsInfoImportDto;
import com.adc.da.util.exception.AdcDaBaseException;
import com.adc.da.util.utils.IOUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.lawss.entity.SarLawsInfoEO;
import com.adc.da.lawss.page.SarLawsInfoEOPage;
import com.adc.da.lawss.service.SarLawsInfoEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author yangxuenan
 * @Description 法规信息
 * Date 2018/9/3 16:38
 * @Param
 * @return
 **/
@RestController
@RequestMapping("/${restPath}/lawss/sarLawsInfo")
@Api(description = "|SarLawsInfoEO|")
public class SarLawsInfoEOController extends BaseController<SarLawsInfoEO>{

    private static final Logger logger = LoggerFactory.getLogger(SarLawsInfoEOController.class);

    @Autowired
    private SarLawsInfoEOService sarLawsInfoEOService;

    /**
     * @Author yangxuenan
     * @Description 法规信息分页查询
     * Date 2018/9/3 16:38
     * @Param [page]
     * @return com.adc.da.util.http.ResponseMessage<com.adc.da.util.http.PageInfo<com.adc.da.lawss.entity.SarLawsInfoEO>>
     **/
	@ApiOperation(value = "|SarLawsInfoEO|分页查询")
    @GetMapping("/page")
    /*@RequiresPermissions("lawss:sarLawsInfo:page")*/
    public ResponseMessage<PageInfo<SarLawsInfoEO>> page(SarLawsInfoEOPage page) throws Exception {
        page.setOrderBy("SAR_LAWS_INFO.modify_time desc");
        List<SarLawsInfoEO> rows = sarLawsInfoEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

    /**
     * @Author yangxuenan
     * @Description 法规信息列表查询
     * Date 2018/9/3 16:39
     * @Param [page]
     * @return com.adc.da.util.http.ResponseMessage<java.util.List<com.adc.da.lawss.entity.SarLawsInfoEO>>
     **/
	@ApiOperation(value = "|SarLawsInfoEO|查询")
    @GetMapping("")
    @RequiresPermissions("lawss:sarLawsInfo:list")
    public ResponseMessage<List<SarLawsInfoEO>> list(SarLawsInfoEOPage page) throws Exception {
        return Result.success(sarLawsInfoEOService.queryByList(page));
	}

	/**
	 * @Author yangxuenan
	 * @Description 法规信息详情查询
	 * Date 2018/9/3 16:39
	 * @Param [id]
	 * @return com.adc.da.util.http.ResponseMessage<com.adc.da.lawss.entity.SarLawsInfoEO>
	 **/
    @ApiOperation(value = "|SarLawsInfoEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("lawss:sarLawsInfo:get")
    public ResponseMessage<SarLawsInfoEO> find(@PathVariable String id) throws Exception {
        return Result.success(sarLawsInfoEOService.selectByPrimaryKey(id));
    }

    /**
     * @Author yangxuenan
     * @Description 法规信息新增
     * Date 2018/9/3 16:39
     * @Param [sarLawsInfoEO]
     * @return com.adc.da.util.http.ResponseMessage<com.adc.da.lawss.entity.SarLawsInfoEO>
     **/
    @ApiOperation(value = "|SarLawsInfoEO|新增")
    @PostMapping("/createLawsInfo")
    /*@RequiresPermissions("lawss:sarLawsInfo:save")*/
    public ResponseMessage<SarLawsInfoEO> createLawsInfo(SarLawsInfoEO sarLawsInfoEO) throws Exception {
        return sarLawsInfoEOService.createLawsInfo(sarLawsInfoEO);
    }

    /**
     * @Author yangxuenan
     * @Description 法规信息修改
     * Date 2018/9/3 16:40
     * @Param [sarLawsInfoEO]
     * @return com.adc.da.util.http.ResponseMessage<com.adc.da.lawss.entity.SarLawsInfoEO>
     **/
    @ApiOperation(value = "|SarLawsInfoEO|修改")
    @PutMapping("/updateLawsInfo")
    /*@RequiresPermissions("lawss:sarLawsInfo:update")*/
    public ResponseMessage<SarLawsInfoEO> update(SarLawsInfoEO sarLawsInfoEO) throws Exception {
        sarLawsInfoEO.setModifyTime(new Date());
        return sarLawsInfoEOService.updateLawsInfo(sarLawsInfoEO);
    }

    /**
     * @Author yangxuenan
     * @Description 法规信息删除
     * Date 2018/9/3 16:40
     * @Param [id]
     * @return com.adc.da.util.http.ResponseMessage
     **/
    @ApiOperation(value = "|SarLawsInfoEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("lawss:sarLawsInfo:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        sarLawsInfoEOService.deleteByPrimaryKey(id);
        logger.info("delete from SAR_LAWS_INFO where id = {}", id);
        return Result.success();
    }

    /**
     * @Author yangxuenan
     * @Description 信息及关联表删除
     * Date 2018/9/4 14:20
     * @Param [id]
     * @return com.adc.da.util.http.ResponseMessage
     **/
    @ApiOperation(value = "|SarLawsInfoEO|信息及关联表删除")
    @PutMapping("/deleteLawsInfos")
    /*@RequiresPermissions("lawss:sarLawsInfo:delete")*/
    public ResponseMessage deleteLawsInfos(@RequestParam("id") String id) throws Exception {
        return sarLawsInfoEOService.deleteLawsInfo(id);
    }

    /**
     * @Author yangxuenan
     * @Description
     * Date 2018/9/4 14:20
     * @Param [id]
     * @return com.adc.da.util.http.ResponseMessage
     **/
    @ApiOperation(value = "|SarLawsInfoEO|导入法规信息")
    @PostMapping("/importLawsInfos")
    /*@RequiresPermissions("lawss:sarLawsInfo:importLawsInfos")*/
    public ResponseMessage importLawsInfos(@RequestParam("file") MultipartFile file,String pageType) throws Exception {
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
        ExcelImportResult<LawsInfoImportDto> result = ExcelImportUtil.importExcelVerify(is, LawsInfoImportDto.class, params);
        // 如果校验不通过，返回错误信息
        if (result.isVerfiyFail()) {
            return Result.error("fail", "excel文件校验失败");
        }

        //excel读取到的数据
        List<LawsInfoImportDto> datas = result.getList();
        if(datas!=null &&!datas.isEmpty()){
            try {
                return sarLawsInfoEOService.importLawsInfoDatas(datas,pageType);
            } catch (Exception e) {
                return Result.error("fail", e.getMessage());
            }
        }else{
            return Result.error("fail", "没有可导入的数据,请检查!");
        }
    }

    /**
     * @Author yangxuenan
     * @Description 导出法规信息
     * Date 2018/9/5 11:09
     * @Param [response, request]
     * @return void
     **/
    @ApiOperation(value = "|SarLawsInfoEO|导出法规信息")
    @GetMapping("/exportLawsInfos")
    public void exportLawsInfos(HttpServletResponse response, HttpServletRequest request) throws Exception{
        OutputStream os = null;
        Workbook workbook = null;
        try{
            response.setHeader("Content-Disposition",
                    "attachment; filename=" + encodeFileName("导出法规信息.xlsx",request));
            response.setContentType("application/force-download");
            ExportParams exportParams = new ExportParams();
            exportParams.setType(ExcelType.XSSF);

            //存放需要导出的数据
            SarLawsInfoEO sarLawsInfoEO = new SarLawsInfoEO();
            sarLawsInfoEO.setLawsName("11111");
            //将导出对象与dto对应
            List<LawsInfoExportDto> dto = new ArrayList<>();
            BeanUtils.copyProperties(sarLawsInfoEO, dto);

            //导出数据到Excel
            workbook = ExcelExportUtil.exportExcel(exportParams, LawsInfoExportDto.class, dto);
            os = response.getOutputStream();
            workbook.write(os);
            os.flush();
        } catch (Exception e) {
            throw new AdcDaBaseException("导出法规信息文件失败，请重试");
        } finally {
            IOUtils.closeQuietly(os);
        }
    }

}
