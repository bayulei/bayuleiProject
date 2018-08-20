package com.adc.da.sys.controller;

import static com.adc.da.sys.common.ReadExcelName.encodeFileName;
import static com.adc.da.sys.util.ValidateDatas.isChineseOrNumberOrLetterOrUnderlineOrChar;
import static com.adc.da.sys.util.ValidateDatas.isLetterOrChineseOrChar1;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

import com.adc.da.sys.dao.SysCorpEODao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.adc.da.base.web.BaseController;
import com.adc.da.excel.poi.excel.ExcelExportUtil;
import com.adc.da.excel.poi.excel.ExcelImportUtil;
import com.adc.da.excel.poi.excel.entity.ExportParams;
import com.adc.da.excel.poi.excel.entity.ImportParams;
import com.adc.da.excel.poi.excel.entity.enums.ExcelType;
import com.adc.da.excel.poi.excel.entity.result.ExcelImportResult;
import com.adc.da.sys.common.LayUiResult;
import com.adc.da.sys.dto.SysCorpExcelBusinessDto;
import com.adc.da.sys.dto.SysCorpExcelDto;
import com.adc.da.sys.entity.OrgEO;
import com.adc.da.sys.entity.SysCorpEO;
import com.adc.da.sys.page.SysCorpEOPage;
import com.adc.da.sys.service.SysCorpEOService;
import com.adc.da.sys.util.ValidateDatas;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.util2.StringUtil;
import com.adc.da.util.utils.Encodes;
import com.adc.da.util.utils.IOUtils;
import com.adc.da.util.constant.DeleteFlagEnum;
import com.adc.da.util.exception.AdcDaBaseException;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;

@RestController
@RequestMapping("/${restPath}/sys/sysCorp")
@Api(description = "|SysCorpEO|")
public class SysCorpEOController extends BaseController<SysCorpEO>{

    private static final Logger logger = LoggerFactory.getLogger(SysCorpEOController.class);
    
    @Autowired
    private SysCorpEOService sysCorpEOService;

	@Autowired
	private SysCorpEODao sysCorpEODao;

	@ApiOperation(value = "|SysCorpEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("sys:sysCorp:page")
    public LayUiResult<SysCorpEO> page(SysCorpEOPage page) throws Exception {
		if(StringUtil.isNotEmpty(page.getCorpAddress()) && !page.getCorpAddress().trim().equals("")){
			page.setCorpAddressOperator("LIKE");
			String corpAddress = page.getCorpAddress();
			page.setCorpAddress("%"+corpAddress+"%");
		}else{
			page.setCorpAddress(null);
		}
		if(StringUtil.isNotEmpty(page.getCorpDuty())&& !page.getCorpDuty().trim().equals("")){
			page.setCorpDutyOperator("LIKE");
			String corpDuty = page.getCorpDuty();
			page.setCorpDuty("%"+corpDuty+"%");
		}else{
			page.setCorpDuty(null);
		}
		if(StringUtil.isNotEmpty(page.getCorpName())&& !page.getCorpName().trim().equals("")){
			page.setCorpNameOperator("LIKE");
			String corpName = page.getCorpName();
			page.setCorpName("%"+corpName+"%");
		}else{
			page.setCorpName(null);
		}
		if(StringUtil.isNotEmpty(page.getCorpUser())&& !page.getCorpUser().trim().equals("")){
			page.setCorpUserOperator("LIKE");
			String corpUser = page.getCorpUser();
			page.setCorpUser("%"+corpUser+"%");
		}else{
			page.setCorpUser(null);
		}
		page.setDelFlag(DeleteFlagEnum.NORMAL.getValue()+"");
		page.setOrderBy("update_time desc");
        List<SysCorpEO> rows = sysCorpEOService.queryByPage(page);
        PageInfo<SysCorpEO> mapPage = getPageInfo(page.getPager(), rows);
        return new LayUiResult<SysCorpEO>(mapPage);
    }

	@ApiOperation(value = "|SysCorpEO|查询")
    @GetMapping("")
    @RequiresPermissions("sys:sysCorp:list")
    public ResponseMessage<List<SysCorpEO>> list(SysCorpEOPage page) throws Exception {
		page.setOrderBy("update_time");
        return Result.success(sysCorpEOService.queryByList(page));
	}

    @ApiOperation(value = "|SysCorpEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("sys:sysCorp:get")
    public ResponseMessage<SysCorpEO> find(@PathVariable String id) throws Exception {
        return Result.success(sysCorpEOService.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "|SysCorpEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("sys:sysCorp:save")
    public ResponseMessage<SysCorpEO> create(@RequestBody SysCorpEO sysCorpEO) throws Exception {
		if("0".equals(sysCorpEO.getCorpType())){
			SysCorpEO getCorpCode = sysCorpEODao.selectByCorpCode(sysCorpEO.getCorpCode());
			if(getCorpCode != null) {
				return Result.error("企业唯一辨识码已存在！");
			}
		}
		sysCorpEOService.saveCorpData(sysCorpEO);
		return Result.success(sysCorpEO);
    }

    @ApiOperation(value = "|SysCorpEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("sys:sysCorp:update")
    public ResponseMessage<SysCorpEO> update(@RequestBody SysCorpEO sysCorpEO) throws Exception {
		if("0".equals(sysCorpEO.getCorpType())){
			SysCorpEO getCorpCode = sysCorpEODao.selectByCorpCode(sysCorpEO.getCorpCode());
			if(getCorpCode != null && !(sysCorpEO.getId()).equals(getCorpCode.getId())){
				return Result.error("企业唯一辨识码已存在！");
			}
		}
		sysCorpEOService.updateCorpInfo(sysCorpEO);
		return Result.success(sysCorpEO);
    }

    @ApiOperation(value = "|SysCorpEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("sys:sysCorp:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        sysCorpEOService.deleteByPrimaryKey(id);
        logger.info("delete from SYS_CORP where id = {}", id);
        return Result.success();
    }
    
    @ApiOperation(value = "|SysCorpEO|批量删除")
	@DeleteMapping("/deleteList/{idList}")
	@RequiresPermissions("sys:role:deleteList")
	public ResponseMessage deleteList(@NotNull @PathVariable("idList") String ids) throws Exception {
    	String resultIds = "";
		String[] idList=ids.split(",");
		if(idList!=null && idList.length>0){
			for(String id:idList){
				List<OrgEO> eos = sysCorpEOService.validateChildsIsEmpty(id);
				if(eos.size() == 1) {//只有自己
					sysCorpEOService.delCorpInfo(id, eos.get(0).getId());
				}else {//有子部门，不可删除
					resultIds += id+",";
				}
			}
			if(!resultIds.isEmpty()) {
				resultIds = resultIds.substring(0, resultIds.length()-1);
				return Result.error("4869", resultIds);
			}
		}
		return Result.success(resultIds);
	}
    
    @ApiOperation(value = "|SysCorpEO|导出使用方名录excel")
    @GetMapping(value = "/exportCorpExcel")
    public void exportCorpExcel(String ids, HttpServletResponse response,HttpServletRequest request) {
    	OutputStream os = null;
        Workbook workbook = null;
        try {
            response.setHeader("Content-Disposition",
                    "attachment; filename=" + encodeFileName("使用方清单.xlsx",request));
            response.setContentType("application/force-download");
            ExportParams exportParams = new ExportParams();
            exportParams.setType(ExcelType.XSSF);

            List<SysCorpEO> datas = new ArrayList<SysCorpEO>();
            String[] idArr = ids.split(",");
            for (String id : idArr) {
				try {
					SysCorpEO sysCorpEO = sysCorpEOService.selectByPrimaryKey(id);
					datas.add(sysCorpEO);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

            List<SysCorpExcelDto> datasDto = new ArrayList<SysCorpExcelDto>();
            if (!datas.isEmpty()) {
                for (SysCorpEO eo : datas) {
                	SysCorpExcelDto dto = new SysCorpExcelDto();
                    BeanUtils.copyProperties(eo, dto);
                    datasDto.add(dto);
                }
            }

            workbook = ExcelExportUtil.exportExcel(exportParams, SysCorpExcelDto.class, datasDto);
            os = response.getOutputStream();
            workbook.write(os);
            os.flush();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            throw new AdcDaBaseException("下载文件失败，请重试");
        } finally {
            IOUtils.closeQuietly(os);
        }
    }
    
    @SuppressWarnings("finally")
	@ApiOperation(value = "|SysCorpEO|导入企业名录excel")
    @PostMapping("/importCorp")
    public ResponseMessage importCorp(@RequestParam("file") MultipartFile file, String corpType) {
    	boolean returnResult = true;
    	InputStream is = null;
    	String resultMsg = null;
    	int excelDataCount = 0;		//excel 数据总数
    	int excelLegalCount = 0;	//合法数据总数
    	try {
			// 获取文件输入流
			is = file.getInputStream();
			// 导入参数设置，默认即可
			ImportParams params = new ImportParams();
			// 解析excel，并返回校验信息
			ExcelImportResult<SysCorpExcelDto> result = ExcelImportUtil.importExcelVerify(is, SysCorpExcelDto.class, params);
			// 如果校验不通过，返回错误信息
			if (result.isVerfiyFail()) {
				returnResult = false;
			}
			//excel读取到的数据
	        List<SysCorpExcelDto> datas = result.getList();
	        
	        excelDataCount = datas.size();
	        
	        resultMsg = validateData(datas);//验证数据是否合法, 接收返回消息
	        
	        excelLegalCount = datas.size();
	        
	        if(returnResult) {
	        	if(!datas.isEmpty())
	        		sysCorpEOService.addImportCorpDatas(datas, corpType);
	        }
		} catch (IOException e) {
			e.printStackTrace();
			returnResult = false;
		} catch (Exception e) {
			e.printStackTrace();
			returnResult = false;
		}finally {
			try {
				if(is!=null){
					is.close();
				}
				if(returnResult) {
					if(excelDataCount == excelLegalCount)
						return Result.success();
					else
						return Result.error("4869", resultMsg);
				}
				else 
					return Result.error();
			} catch (IOException e) {
				e.printStackTrace();
				return Result.error();
			}
		}
    }

    @ApiOperation(value = "|SysCorpEO|导出企业excel")
    @GetMapping(value = "/exportCorpExcelBusiness")
    public void exportCorpExcelBusiness(String ids, HttpServletResponse response,HttpServletRequest request) {
    	OutputStream os = null;
        Workbook workbook = null;
        try {
            response.setHeader("Content-Disposition",
                    "attachment; filename=" + encodeFileName("企业清单.xlsx",request));
            response.setContentType("application/force-download");
            ExportParams exportParams = new ExportParams();
            exportParams.setType(ExcelType.XSSF);

            List<SysCorpEO> datas = new ArrayList<SysCorpEO>();
            String[] idArr = ids.split(",");
            for (String id : idArr) {
				try {
					SysCorpEO sysCorpEO = sysCorpEOService.selectByPrimaryKey(id);
					datas.add(sysCorpEO);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

            List<SysCorpExcelBusinessDto> datasDto = new ArrayList<SysCorpExcelBusinessDto>();
            if (datas != null && !datas.isEmpty()) {
                for (SysCorpEO eo : datas) {
                	SysCorpExcelBusinessDto dto = new SysCorpExcelBusinessDto();
                    BeanUtils.copyProperties(eo, dto);
                    datasDto.add(dto);
                }
            }

            workbook = ExcelExportUtil.exportExcel(exportParams, SysCorpExcelBusinessDto.class, datasDto);
            os = response.getOutputStream();
            workbook.write(os);
            os.flush();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            throw new AdcDaBaseException("下载文件失败，请重试");
        } finally {
            IOUtils.closeQuietly(os);
        }
    }
    
    @SuppressWarnings("finally")
	@ApiOperation(value = "|SysCorpEO|导入企业excel")
    @PostMapping("/importCorpBusiness")
    public ResponseMessage importCorpBusiness(@RequestParam("file") MultipartFile file, String corpType) {
    	boolean returnResult = true;
    	InputStream is = null;
    	String resultMsg = null;
    	int excelDataCount = 0;		//excel 数据总数
    	int excelLegalCount = 0;	//合法数据总数
    	try {
			// 获取文件输入流
			is = file.getInputStream();
			// 导入参数设置，默认即可
			ImportParams params = new ImportParams();
			// 解析excel，并返回校验信息
			ExcelImportResult<SysCorpExcelBusinessDto> result = ExcelImportUtil.importExcelVerify(is, SysCorpExcelBusinessDto.class, params);
			// 如果校验不通过，返回错误信息
			if (result.isVerfiyFail()) {
				returnResult = false;
			}
			//excel读取到的数据
	        List<SysCorpExcelBusinessDto> datas = result.getList();
	        excelDataCount = datas.size();
	        resultMsg = validateDataBusiness(datas);//验证数据是否合法, 接收返回消息
	        excelLegalCount = datas.size();
	        if(returnResult) {
	        	if(!datas.isEmpty())
	        		sysCorpEOService.addImportCorpBusinessDatas(datas, corpType);
	        }
		} catch (IOException e) {
			e.printStackTrace();
			returnResult = false;
		} catch (Exception e) {
			e.printStackTrace();
			returnResult = false;
		}finally {
			try {
				is.close();
				if(returnResult) {
					if(excelDataCount == excelLegalCount)
						return Result.success();
					else
						return Result.error("4869", resultMsg);
				}
				else 
					return Result.error();
			} catch (IOException e) {
				e.printStackTrace();
				return Result.error();
			}
		}
    }
    
	private String validateDataBusiness(List<SysCorpExcelBusinessDto> datas) {
		StringBuffer resultMsg = new StringBuffer();
		int rowCount = 2;
		for (int i = 0; i < datas.size(); i++) {
			String msg = null;
			SysCorpExcelBusinessDto sysCorpExcelDto = datas.get(i);
			if (StringUtils.isBlank(sysCorpExcelDto.getCorpName())){
				msg = "第"+rowCount+"行的生产企业名称不能为空。"+"\n";
			}else if(StringUtils.isBlank(sysCorpExcelDto.getCorpAddress())) {
				msg = "第"+rowCount+"行的地址不能为空。"+"\n";
			}else if (StringUtils.isBlank(sysCorpExcelDto.getCorpUser())){
				msg = "第"+rowCount+"行的联系人不能为空。"+"\n";
			}else if (StringUtils.isBlank(sysCorpExcelDto.getCorpDuty())){
				msg = "第"+rowCount+"行的职务不能为空。"+"\n";
			}else if (StringUtils.isBlank(sysCorpExcelDto.getCorpPhone())){
				msg = "第"+rowCount+"行的联系电话不能为空。"+"\n";
			}else {
				if(sysCorpExcelDto.getCorpName().length() > 30 || isLetterOrChineseOrChar1(sysCorpExcelDto.getCorpName())){//生产企业名称验证
					msg = "第"+rowCount+"行的生产企业名称格式错误，长度30字符之内，只可填写汉字、字母、（）"+"\n";
				}else if(sysCorpExcelDto.getCorpAddress().length() > 50 || isChineseOrNumberOrLetterOrUnderlineOrChar(sysCorpExcelDto.getCorpAddress())) {//地址验证
					msg = "第"+rowCount+"行的地址格式错误，长度50字符之内，只可填写汉字、数字、字母、（）、-、下划线"+"\n";
				}else if(sysCorpExcelDto.getCorpUser().length() > 10 || !ValidateDatas.isenOrch(sysCorpExcelDto.getCorpUser())) {//联系人验证
					msg = "第"+rowCount+"行的联系人格式错误，长度10字符之内，只可填写汉字和字母。"+"\n";
				}else if(sysCorpExcelDto.getCorpDuty().length() > 30 || !ValidateDatas.isenOrch(sysCorpExcelDto.getCorpDuty())) {//职务验证
					msg = "第"+rowCount+"行的职务格式错误，长度30字符之内，只可填写汉字和字母。"+"\n";
				}else if(!ValidateDatas.isPhone(sysCorpExcelDto.getCorpPhone())) {//联系电话验证
					msg = "第"+rowCount+"行的联系电话格式错误，请填写正确的电话格式。"+"\n";
				}else if(StringUtils.isNotBlank(sysCorpExcelDto.getCorpEmail())) {//邮箱，如果不是 空，则验证
					if(sysCorpExcelDto.getCorpEmail().length() > 30 || !ValidateDatas.isEmail(sysCorpExcelDto.getCorpEmail())) {//邮箱验证
						msg = "第"+rowCount+"行的邮箱格式错误，长度30字符之内，且正确的邮箱地址。"+"\n";
					}
				}
			}
			if(msg != null && !msg.isEmpty()) {
				resultMsg.append(msg).append("</br>");
				datas.remove(i);
				i--;
			}
			rowCount++;
		}
		return resultMsg.toString();
	}
	private String validateData(List<SysCorpExcelDto> datas) {
		StringBuffer resultMsg = new StringBuffer();
		int rowCount = 2;
		for (int i = 0; i < datas.size(); i++) {
			String msg = null;
			SysCorpExcelDto sysCorpExcelDto = datas.get(i);
			if (StringUtils.isBlank(sysCorpExcelDto.getCorpName())){
				msg = "第"+rowCount+"行的使用方名称不能为空。"+"\n";
			}else if(StringUtils.isBlank(sysCorpExcelDto.getCorpAddress())) {
				msg = "第"+rowCount+"行的地址不能为空。"+"\n";
			}else if (StringUtils.isBlank(sysCorpExcelDto.getCorpUser())){
				msg = "第"+rowCount+"行的联系人不能为空。"+"\n";
			}else if (StringUtils.isBlank(sysCorpExcelDto.getCorpDuty())){
				msg = "第"+rowCount+"行的职务不能为空。"+"\n";
			}else if (StringUtils.isBlank(sysCorpExcelDto.getCorpPhone())){
				msg = "第"+rowCount+"行的联系电话不能为空。"+"\n";
			}else {
				if(sysCorpExcelDto.getCorpName().length() > 30 || isLetterOrChineseOrChar1(sysCorpExcelDto.getCorpName())){//使用方名称验证
					msg = "第"+rowCount+"行的使用方名称格式错误，长度30字符之内，只可填写汉字、字母、（）"+"\n";
				}else if(sysCorpExcelDto.getCorpAddress().length() > 50 ||isChineseOrNumberOrLetterOrUnderlineOrChar(sysCorpExcelDto.getCorpAddress())) {//地址验证
					msg = "第"+rowCount+"行的地址格式错误，长度50字符之内，只可填写汉字、数字、字母、（）、-、下划线"+"\n";
				}else if(sysCorpExcelDto.getCorpUser().length() > 10 || !ValidateDatas.isenOrch(sysCorpExcelDto.getCorpUser())) {//联系人验证
					msg = "第"+rowCount+"行的联系人格式错误，长度10字符之内，只可填写汉字和字母。"+"\n";
				}else if(sysCorpExcelDto.getCorpDuty().length() > 30 || !ValidateDatas.isenOrch(sysCorpExcelDto.getCorpDuty())) {//职务验证
					msg = "第"+rowCount+"行的职务格式错误，长度30字符之内，只可填写汉字和字母。"+"\n";
				}else if(!ValidateDatas.isPhone(sysCorpExcelDto.getCorpPhone())) {//联系电话验证
					msg = "第"+rowCount+"行的联系电话格式错误，请填写正确的电话格式。"+"\n";
				}else if(StringUtils.isNotBlank(sysCorpExcelDto.getCorpEmail())) {//邮箱，如果不是 空，则验证
					if(sysCorpExcelDto.getCorpEmail().length() > 30 || !ValidateDatas.isEmail(sysCorpExcelDto.getCorpEmail())) {//邮箱验证
						msg = "第"+rowCount+"行的邮箱格式错误，长度30字符之内，且正确的邮箱地址。"+"\n";
					}
				}
			}
			if(msg != null && !msg.isEmpty()) {
				resultMsg.append(msg).append("</br>");
				datas.remove(i);
				i--;
			}
			rowCount++;
		}
		return resultMsg.toString();
	}
}
