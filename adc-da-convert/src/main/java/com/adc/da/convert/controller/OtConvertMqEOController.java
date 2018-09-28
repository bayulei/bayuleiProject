package com.adc.da.convert.controller;

import com.adc.da.att.service.AttFileEOService;
import com.adc.da.att.vo.AttFileVo;
import com.adc.da.base.web.BaseController;
import com.adc.da.convert.entity.OtConvertMqEO;
import com.adc.da.convert.page.OtConvertMqEOPage;
import com.adc.da.convert.service.OtConvertMqEOService;
import com.adc.da.file.store.IFileStore;
import com.adc.da.util.http.PageInfo;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.utils.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping("/${restPath}/convert/otConvertMq")
@Api(description = "|OtConvertMqEO|")
public class OtConvertMqEOController extends BaseController<OtConvertMqEO>{

    private static final Logger logger = LoggerFactory.getLogger(OtConvertMqEOController.class);

    @Autowired
    private OtConvertMqEOService otConvertMqEOService;

    @Autowired
    private SendMQController sendMQController;

    @Autowired
    private AttFileEOService attFileEOService;

    @Autowired
    BeanMapper beanMapper;

	@ApiOperation(value = "|OtConvertMqEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("convert:otConvertMq:page")
    public ResponseMessage<PageInfo<OtConvertMqEO>> page(OtConvertMqEOPage page) throws Exception {
        List<OtConvertMqEO> rows = otConvertMqEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

	@ApiOperation(value = "|OtConvertMqEO|查询")
    @GetMapping("")
    @RequiresPermissions("convert:otConvertMq:list")
    public ResponseMessage<List<OtConvertMqEO>> list(OtConvertMqEOPage page) throws Exception {
        return Result.success(otConvertMqEOService.queryByList(page));
	}

    @ApiOperation(value = "|OtConvertMqEO|详情")
    @GetMapping("/getConvertDetail")
    /*@RequiresPermissions("convert:otConvertMq:get")*/
    public ResponseMessage<OtConvertMqEO> find(@RequestParam("convertId") String convertId) throws Exception {
        OtConvertMqEO convertMqEO = otConvertMqEOService.selectByPrimaryKey(convertId);
        if(convertMqEO.getMqState() == 0){
            return Result.error("正在转换，请稍后再试！");
        } else if(convertMqEO.getMqState() == 1){
            return Result.success(convertMqEO);
        } else {
            return Result.error("转换失败！");
        }
    }

    @ApiOperation(value = "|OtConvertMqEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("convert:otConvertMq:save")
    public ResponseMessage<OtConvertMqEO> create(@RequestBody OtConvertMqEO otConvertMqEO) throws Exception {
        otConvertMqEOService.insertSelective(otConvertMqEO);
        return Result.success(otConvertMqEO);
    }

    @ApiOperation(value = "|OtConvertMqEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("convert:otConvertMq:update")
    public ResponseMessage<OtConvertMqEO> update(@RequestBody OtConvertMqEO otConvertMqEO) throws Exception {
        otConvertMqEOService.updateByPrimaryKeySelective(otConvertMqEO);
        return Result.success(otConvertMqEO);
    }

    @ApiOperation(value = "|OtConvertMqEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("convert:otConvertMq:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        otConvertMqEOService.deleteByPrimaryKey(id);
        logger.info("delete from OT_CONVERT_MQ where id = {}", id);
        return Result.success();
    }
    /**
     * @Author yangxuenan
     * @Description 上传转换文件
     * Date 2018/8/24 14:07
     * @Param [file]
     * @return com.adc.da.util.http.ResponseMessage
     **/
    @ApiOperation(value = "|OtConvertMqEO|上传转换文件")
    @PostMapping("/docUploadConvert")
    public ResponseMessage docUploadConvert(@RequestParam("file") MultipartFile file) throws Exception {
        HttpServletRequest request;
        //文件上传后，保存在upload文件夹
        //获取文件上传路径
        //File directory = new File("");
        //String courseFile = directory.getCanonicalPath();
        //System.out.println("*****上传文件路径*******"+courseFile);
        //保存文件路径
        //String saveDirectory =courseFile+"\\adc-da-ui\\src\\main\\uipages\\html\\upload";
        //System.out.println("*****上传文件路径*******"+saveDirectory);

        InputStream is = null;
        try{
            //判断文件类型
            String fileExtension = FileUtil.getFileExtension(file.getOriginalFilename());
            List<String> whiteUrls = new ArrayList<>();
            whiteUrls.add("doc");
            whiteUrls.add("docx");
            whiteUrls.add("pdf");
            if (!whiteUrls.contains(fileExtension)) {
                return Result.error("r0071", "上传文件类型不允许，请重试");
            }

            is = file.getInputStream();
            //保存文件
            //String path = this.iFileStore.storeFile(is, fileExtension, saveDirectory);
            AttFileVo attVo = attFileEOService.saveFileInfo(file);
            if(StringUtils.isNotEmpty(attVo.getFilePath())){
                String path = attVo.getFilePath()+attVo.getFileName();
                //String userId = SecurityUtils.getSubject().getSession().getAttribute(RequestUtils.LOGIN_USER_ID).toString();
                OtConvertMqEO convert = new OtConvertMqEO();
                String convertId = UUID.randomUUID(20);
                convert.setId(convertId);
                convert.setFilePath(path);
                /*convert.setUserId(userId);*/
                //创建消息队列
                int countAdd = sendMQController.sendMQ(convert,0);
                if(countAdd > 0){
                    return Result.success(convertId);
                } else {
                    return Result.error("文件加入数据库失败，请重试");
                }

            } else {
                return Result.error("文件上传失败，请重试");
            }
        } catch (Exception e) {
            return Result.error("r0072", "文件转换失败，请重试");
        } finally {
            IOUtils.closeQuietly(is);
        }
    }

    /**
     * @Author yangxuenan
     * @Description 将文件重新加入队列
     * Date 2018/8/24 14:11
     * @Param [convertMqEO]
     * @return com.adc.da.util.http.ResponseMessage
     **/
    @ApiOperation(value = "|OtConvertMqEO|重新转换")
    @PutMapping("/resendMQ")
    public ResponseMessage restartUploadConvert(OtConvertMqEO convertMqEO) throws Exception{
        int countAdd = sendMQController.sendMQ(convertMqEO,1);
        if(countAdd > 0){
            return Result.success();
        } else {
            return Result.error("文件加入数据库失败，请重试");
        }
    }

    /**
     * @Author yangxuenan
     * @Description 删除转换队列数据
     * Date 2018/8/28 8:56
     * @Param [convertMqEO]
     * @return com.adc.da.util.http.ResponseMessage<com.adc.da.convert.entity.ConvertMqEO>
     **/
    @ApiOperation(value = "|ConvertMqEO|删除")
    @PutMapping("/deleteConvert")
    /*@RequiresPermissions("convert:convertMq:deleteConvert")*/
    public ResponseMessage<OtConvertMqEO> deleteConvert(@RequestBody OtConvertMqEO convertMqEO) throws Exception {
        convertMqEO.setValidFlag(1);
        otConvertMqEOService.updateByPrimaryKeySelective(convertMqEO);
        return Result.success(convertMqEO);
    }

}
