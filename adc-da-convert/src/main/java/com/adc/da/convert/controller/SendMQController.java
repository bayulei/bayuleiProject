package com.adc.da.convert.controller;

import com.adc.da.convert.entity.ConvertMqEO;
import com.adc.da.convert.service.ConvertMqEOService;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adc.da.util.utils.UUID;

import javax.xml.ws.Response;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author yangxuenan
 * @Description 创建发送消息队列
 * Date 2018/8/24 14:02
 * @Param
 * @return
 **/
@RestController
@RequestMapping("/convert/sendMQ")
@Api(description = "|消息队列|")
public class SendMQController{

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Autowired
    private ConvertMqEOService convertMqEOService;

    /**
     * @Author yangxuenan
     * @Description 创建发送消息队列
     * Date 2018/8/24 13:59
     * @Param [convert, convertType]
     * @return int
     **/
    public int sendMQ(ConvertMqEO convert,int convertType) throws Exception{
            Map<String,Object> convertInfo = new HashMap<String,Object>();
            convertInfo.put("batIndex", convert.getId());
            convertInfo.put("path", convert.getFilePath());
            //发送消息队列
            this.rabbitTemplate.convertAndSend("convert-exchange", "convert-key", convertInfo);

            //判断用户是初次发送还是重新发送,初次将添加到数据库，重新发送将修改原数据信息
            if(convertType == 0){
                convert.setMqState(0);
                convert.setInsertTime(new Date());
                convert.setUpdateTime(new Date());
                convert.setDelFlag(0);
                return convertMqEOService.insertSelective(convert);
            } else {
                convert.setMqState(0);
                convert.setUpdateTime(new Date());
                return convertMqEOService.updateByPrimaryKeySelective(convert);
            }


    }


}
