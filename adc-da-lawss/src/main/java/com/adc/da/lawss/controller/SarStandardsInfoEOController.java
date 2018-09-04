package com.adc.da.lawss.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.adc.da.excel.poi.excel.ExcelImportUtil;
import com.adc.da.excel.poi.excel.entity.ImportParams;
import com.adc.da.excel.poi.excel.entity.result.ExcelImportResult;
import com.adc.da.lawss.common.ReadExcel;
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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

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
     * 添加一条标准
     * @param sarStandardsInfoEO  标准信息
     * @return
     * @author gaoyan
     * date 2018-09-04
     */
    @ApiOperation(value = "|SarStandardsInfoEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE,value = "addarStandardsInfo")
    //@RequiresPermissions("lawss:sarStandardsInfo:save")
    public ResponseMessage<SarStandardsInfoEO> create(@RequestBody SarStandardsInfoEO sarStandardsInfoEO,@RequestParam("idPic") MultipartFile[] multipartfiles) throws Exception {
        sarStandardsInfoEOService.createSarStandardsInfo(sarStandardsInfoEO);

        //标准文件资源表，标准文件详情表中插入数据，需要下载文件，并保存数据

        return Result.success(sarStandardsInfoEO);
    }

    /**
     * EXCEL导入标准
     * @param sarStandardsInfoEO  标准信息
     * @return
     * @author gaoyan
     * date 2018-09-04
     */
    @ApiOperation(value = "|SarStandardsInfoEO|导入")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE,value = "importStandardsInfo")
    //@RequiresPermissions("lawss:sarStandardsInfo:save")
    public ResponseMessage<SarStandardsInfoEO> importStandardsInfo(@RequestParam(value = "file", required = false) MultipartFile file) throws Exception {

        //验证文件名是否合格
        ReadExcel readExcel = new ReadExcel();
        if (!readExcel.validateExcel(file.getOriginalFilename())) {
            return Result.error("fail", "请上传excel文件");
        }
        // 获取文件输入流
        InputStream is = file.getInputStream();
        // 导入参数设置，默认即可
        ImportParams params = new ImportParams();
        // 解析excel，并返回校验信息
        ExcelImportResult<SarStandardsInfoEO> result = ExcelImportUtil.importExcelVerify(is, SarStandardsInfoEO.class, params);
        // 如果校验不通过，返回错误信息
        if (result.isVerfiyFail()) {
            return Result.error("fail", "excel文件校验失败");
        }
        //excel读取到的数据
        List<SarStandardsInfoEO> datas = result.getList();
        try {
            return sarStandardsInfoEOService.importSarStandardsInfoData(datas);
        } catch (Exception e) {
            return Result.error("fail", e.getMessage());
        }
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
