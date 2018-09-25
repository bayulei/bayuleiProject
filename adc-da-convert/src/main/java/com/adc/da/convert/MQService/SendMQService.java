package com.adc.da.convert.MQService;

import com.adc.da.convert.common.DocConverter;
import com.adc.da.convert.entity.OtConvertMqEO;
import com.adc.da.convert.service.OtConvertMqEOService;
import com.adc.da.file.store.IFileStore;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

/**
 * @Author yangxuenan
 * @Description 接受消息队列并转换
 * Date 2018/8/24 14:03
 * @Param
 * @return
 **/
@Component
public class SendMQService {

    @Autowired
    private IFileStore iFileStore;

    @Autowired
    private OtConvertMqEOService convertMqEOService;

    /**
     * @Author yangxuenan
     * @Description 创建消费者
     * Date 2018/8/24 14:03
     * @Param [convertInfo]
     * @return void
     **/
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "createConvertMQ", durable = "true"),
            exchange = @Exchange(value = "convert-exchange", ignoreDeclarationExceptions = "true"),
            key = "convert-key"))
    public void createMQ(Map<String,Object> convertInfo) throws Exception{
        try{
            docUploadConvert(convertInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * @Author yangxuenan
     * @Description 文件转换
     * Date 2018/8/21 15:51
     * @Param [path]
     * @return com.adc.da.util.http.ResponseMessage
     **/
    public ResponseMessage docUploadConvert(Map<String,Object> convertInfo) throws Exception{
        try {
            //文件上传后的路径
            String path = convertInfo.get("path").toString();
            //保存在数据库中的ID
            String convertId = convertInfo.get("batIndex").toString();
            //转换结果路径
            String swfpath = "";
            if(!"".equals(path)){
                String converfilename = path.replaceAll("\\\\", "/");
                System.out.println("*****上传路径替换，加入文件名*******"+converfilename);
                //截取文件类型
                int index = converfilename.indexOf(".");
                String fileType = converfilename.substring(index,converfilename.length());
                //调用转换类DocConverter,并将需要转换的文件传递给该类的构造方法
                DocConverter d = new DocConverter(converfilename,fileType);

                //调用conver方法开始转换，先执行doc2pdf()将office文件转换为pdf;再执行pdf2swf()将pdf转换为swf;
                d.conver(fileType);
                //调用getswfPath()方法，打印转换后的swf文件路径
                swfpath = d.getswfPath();
                System.out.println("*****转换后的swf文件路径*******"+swfpath);
                //生成swf相对路径，以便传递给flexpaper播放器
                //int subIndex = dPath.indexOf("/upload");
                //swfpath = dPath.substring(subIndex,dPath.length());
                //System.out.println("*****转换后的文件相对路径*******"+swfpath);
                //转换成功后修改数据状态
                OtConvertMqEO convertMqEO = new OtConvertMqEO();
                if(!swfpath.isEmpty()){
                    convertMqEO.setId(convertId);
                    convertMqEO.setModifyTime(new Date());
                    convertMqEO.setFilePath(swfpath);
                    convertMqEO.setMqState(1);
                    convertMqEOService.updateByPrimaryKeySelective(convertMqEO);
                } else {
                    convertMqEO.setId(convertId);
                    convertMqEO.setModifyTime(new Date());
                    convertMqEO.setMqState(2);
                    convertMqEOService.updateByPrimaryKeySelective(convertMqEO);
                }
                return Result.success(swfpath);
            } else {
                return Result.error("文件存储失败，请重试");
            }
        } catch (Exception e) {
            return Result.error("r0072", "文件转换失败，请重试");
        }
    }

}
