package com.adc.da.convert.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.adc.da.convert.MQService.SendMQService;
import com.adc.da.convert.common.DocConverter;
import com.adc.da.file.store.IFileStore;
import com.adc.da.util.utils.*;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.convert.entity.ConvertMqEO;
import com.adc.da.convert.page.ConvertMqEOPage;
import com.adc.da.convert.service.ConvertMqEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author yangxuenan
 * @Description 文件转换
 * Date 2018/8/24 14:07
 * @Param
 * @return
 **/
@RestController
@RequestMapping("/${restPath}/convert/convertMq")
@Api(description = "|ConvertMqEO|")
public class ConvertMqEOController extends BaseController<ConvertMqEO>{

    @Autowired
    private ConvertMqEOService convertMqEOService;

    @Autowired
    private SendMQController sendMQController;

    @Autowired
    private IFileStore iFileStore;

    @Autowired
    BeanMapper beanMapper;

    /**
     * @Author yangxuenan
     * @Description 分页查询
     * Date 2018/8/24 14:07
     * @Param [page]
     * @return com.adc.da.util.http.ResponseMessage<com.adc.da.util.http.PageInfo<com.adc.da.convert.entity.ConvertMqEO>>
     **/
	@ApiOperation(value = "|ConvertMqEO|分页查询")
    @GetMapping("/page")
    /*@RequiresPermissions("convert:convertMq:page")*/
    public ResponseMessage<PageInfo<ConvertMqEO>> page(ConvertMqEOPage page) throws Exception {
        List<ConvertMqEO> rows = convertMqEOService.queryByPage(page);
        return Result.success(beanMapper.mapPage(getPageInfo(page.getPager(), rows), ConvertMqEO.class));
    }

	@ApiOperation(value = "|ConvertMqEO|查询")
    @GetMapping("")
    /*@RequiresPermissions("convert:convertMq:list")*/
    public ResponseMessage<List<ConvertMqEO>> list(ConvertMqEOPage page) throws Exception {
        return Result.success(convertMqEOService.queryByList(page));
	}

    @ApiOperation(value = "|ConvertMqEO|详情")
    @GetMapping("/getConvertDetail")
    /*@RequiresPermissions("convert:convertMq:get")*/
    public ResponseMessage<ConvertMqEO> find(@RequestParam("convertId") String convertId) throws Exception {
        ConvertMqEO convertMqEO = convertMqEOService.selectByPrimaryKey(convertId);
        if(convertMqEO.getMqState() == 0){
            return Result.error("正在转换，请稍后再试！");
        } else if(convertMqEO.getMqState() == 1){
            return Result.success(convertMqEO);
        } else {
            return Result.error("转换失败！");
        }

    }

    @ApiOperation(value = "|ConvertMqEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    /*@RequiresPermissions("convert:convertMq:save")*/
    public ResponseMessage<ConvertMqEO> create(@RequestBody ConvertMqEO convertMqEO) throws Exception {
        convertMqEOService.insertSelective(convertMqEO);
        return Result.success(convertMqEO);
    }

    @ApiOperation(value = "|ConvertMqEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    /*@RequiresPermissions("convert:convertMq:update")*/
    public ResponseMessage<ConvertMqEO> update(@RequestBody ConvertMqEO convertMqEO) throws Exception {
        convertMqEOService.updateByPrimaryKeySelective(convertMqEO);
        return Result.success(convertMqEO);
    }

    @ApiOperation(value = "|ConvertMqEO|删除")
    @DeleteMapping("/{id}")
    /*@RequiresPermissions("convert:convertMq:delete")*/
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        convertMqEOService.deleteByPrimaryKey(id);
        return Result.success();
    }

    /**
     * @Author yangxuenan
     * @Description 上传转换文件
     * Date 2018/8/24 14:07
     * @Param [file]
     * @return com.adc.da.util.http.ResponseMessage
     **/
    @ApiOperation(value = "|ConvertMqEO|上传转换文件")
    @PostMapping("/docUploadConvert")
    public ResponseMessage docUploadConvert(@RequestParam("file") MultipartFile file) throws Exception {
        HttpServletRequest request;
        //文件上传后，保存在upload文件夹
        //获取文件上传路径
        File directory = new File("");
        String courseFile = directory.getCanonicalPath();
        System.out.println("*****上传文件路径*******"+courseFile);
        //保存文件路径
        String saveDirectory =courseFile+"\\adc-da-ui\\src\\main\\uipages\\html\\upload";
        System.out.println("*****上传文件路径*******"+saveDirectory);

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
            String path = this.iFileStore.storeFile(is, fileExtension, saveDirectory);
            if(!path.isEmpty()){
                //String userId = SecurityUtils.getSubject().getSession().getAttribute(RequestUtils.LOGIN_USER_ID).toString();
                ConvertMqEO convert = new ConvertMqEO();
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
    @ApiOperation(value = "|ConvertMqEO|重新转换")
    @PutMapping("/resendMQ")
    public ResponseMessage restartUploadConvert(ConvertMqEO convertMqEO) throws Exception{
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
    public ResponseMessage<ConvertMqEO> deleteConvert(@RequestBody ConvertMqEO convertMqEO) throws Exception {
        convertMqEO.setDelFlag(1);
        convertMqEOService.updateByPrimaryKeySelective(convertMqEO);
        return Result.success(convertMqEO);
    }

}
