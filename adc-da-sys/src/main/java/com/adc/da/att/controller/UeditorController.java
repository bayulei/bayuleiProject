package com.adc.da.att.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.adc.da.att.common.ueditor.ActionEnter;
import com.adc.da.att.entity.UeditorImage;
import com.adc.da.att.service.AttFileEOService;
import com.adc.da.att.vo.AttFileVo;
import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;


/**
 * 用于处理关于ueditor插件相关的请求
 * @author zhangyanduan
 * @date 2018年9月25日
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/${restPath}/ueditor")
public class UeditorController {
	@Value("classpath:ueditor/config.json")
	private Resource ueditorConfig;
	@Autowired
	private AttFileEOService attFileEOService;

	private static final Logger logger =Logger.getLogger(UeditorController.class);

	@RequestMapping(value = "/exec")
	@ResponseBody
	public String exec(HttpServletRequest request) throws UnsupportedEncodingException{ 
		request.setCharacterEncoding("utf-8");
		String rootPath = request.getRealPath("/");
		return new ActionEnter( request, rootPath ).exec();
	}

	@RequestMapping(value = "/getConfig")
	@ResponseBody
	public String getUeditorConfig(HttpServletRequest request) throws Exception{
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		logger.info(path);
		logger.info(basePath);
		String ueditorData =  IOUtils.toString(ueditorConfig.getInputStream(), Charset.forName("UTF-8"));
		return ueditorData;
	}

	@RequestMapping("/uploadImageData")
	@ResponseBody
	public String uploadImageData(HttpServletRequest request) {
		Gson gson = new Gson();
		UeditorImage msg = uploadFile(request);
		return gson.toJson(msg);
	}

	private UeditorImage uploadFile(HttpServletRequest request) {
		UeditorImage image = new UeditorImage();
		try{
			List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("upfile");
			String referer = request.getHeader("referer");
			if(files!=null && !files.isEmpty()){
				MultipartFile uploadFile=files.get(0);
				AttFileVo attFileVo= attFileEOService.saveFileInfo(uploadFile);
				String picUrlPath ="";
				if(StringUtils.isNotEmpty(referer)){
					picUrlPath=referer+"uploadPath"+ attFileVo.getFilePath()+attFileVo.getFileName();
				}else{
					picUrlPath="uploadPath"+attFileVo.getFilePath()+attFileVo.getFileName();
				}
				logger.info("Ueditor 上传图片返回路径："+picUrlPath);
				image.setState("SUCCESS");
				image.setUrl(picUrlPath);
				image.setTitle(attFileVo.getOldFileName());
				image.setState(attFileVo.getOldFileName());
			}else{
				image.setState("FAIL");
			}
		}catch (Exception  e){
			image.setState("FAIL");
			logger.error(e.getMessage());
		}


/*			image.setUrl(serverPath + path);
			image.setState("SUCCESS");
			image.setOriginal(fileName);
			image.setTitle(fileName);*/


		return image;
	}

	
}
