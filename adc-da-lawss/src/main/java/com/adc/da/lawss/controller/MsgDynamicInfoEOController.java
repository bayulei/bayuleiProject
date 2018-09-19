package com.adc.da.lawss.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;


import java.util.Arrays;
import java.util.List;


import com.adc.da.lawss.entity.MsgFileEO;
import com.adc.da.lawss.service.MsgFileEOService;
import com.adc.da.lawss.vo.MsgDynamicInfoVO;

import com.adc.da.sys.constant.ValidFlagEnum;
import com.adc.da.sys.entity.UserEO;
import com.adc.da.sys.service.UserEOService;
import com.adc.da.util.utils.BeanMapper;
import com.adc.da.util.utils.StringUtils;

import com.adc.da.util.utils.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.lawss.entity.MsgDynamicInfoEO;
import com.adc.da.lawss.page.MsgDynamicInfoEOPage;
import com.adc.da.lawss.service.MsgDynamicInfoEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/${restPath}/lawss/msgDynamicInfo")
@Api(description = "|MsgDynamicInfoEO|")
public class MsgDynamicInfoEOController extends BaseController<MsgDynamicInfoEO>{

    private static final Logger logger = LoggerFactory.getLogger(MsgDynamicInfoEOController.class);

    @Autowired
    private MsgDynamicInfoEOService msgDynamicInfoEOService;

    @Autowired
    private UserEOService userEOService;

    @Autowired
    BeanMapper beanMapper;

    @Autowired
    private MsgFileEOService msgFileEOService;
/**
 * @Author liwenxuan
 * @Description  分页查询动态信息表信息
 * @Date Administrator 2018/9/17
 * @Param [pageNo, pageSize]
 * @return com.adc.da.util.http.ResponseMessage<com.adc.da.util.http.PageInfo<com.adc.da.lawss.entity.MsgDynamicInfoEO>>
 **/
	@ApiOperation(value = "|MsgDynamicInfoEO|分页查询")
    @GetMapping("/page")
//    @RequiresPermissions("lawss:msgDynamicInfo:page")
    public ResponseMessage<PageInfo<MsgDynamicInfoEO>> page(Integer pageNo,Integer pageSize,String msgType,String msgTitle) throws Exception {
        MsgDynamicInfoEOPage page = new MsgDynamicInfoEOPage();
        if (pageNo != null) {
            page.setPage(pageNo);
        }
        if (pageSize != null) {
            page.setPageSize(pageSize);
        }
        if(StringUtils.isNotEmpty(msgType)){
            page.setMsgType(msgType);
        }
        if(StringUtils.isNotEmpty(msgTitle)){
            page.setTitle("%"+msgTitle+"%");
            page.setTitleOperator("LIKE");
        }
        page.setValidFlag(ValidFlagEnum.VALID_TRUE.getValue()+"");
        // 此处增加查询创建人代码
        List<MsgDynamicInfoEO> rows = msgDynamicInfoEOService.queryByPage(page);
        if(rows!=null && !rows.isEmpty()){
            for(MsgDynamicInfoEO EO:rows){
                if(StringUtils.isNotEmpty(EO.getPubUser())){
                  UserEO pubUser= userEOService.selectByPrimaryKey(EO.getPubUser());
                  EO.setPubUserName(pubUser!=null?pubUser.getUname():null);
                }
            }
        }
        return Result.success(getPageInfo(page.getPager(), rows));
    }
/**
 * @Author liwenxuan
 * @Description
 * @Date Administrator 2018/9/17
 * @Param [page]
 * @return com.adc.da.util.http.ResponseMessage<java.util.List<com.adc.da.lawss.entity.MsgDynamicInfoEO>>
 **/
	@ApiOperation(value = "|MsgDynamicInfoEO|查询")
    @GetMapping("")
//    @RequiresPermissions("lawss:msgDynamicInfo:list")
    public ResponseMessage<List<MsgDynamicInfoEO>> list(MsgDynamicInfoEOPage page) throws Exception {
        return Result.success(msgDynamicInfoEOService.queryByList(page));
	}

    @ApiOperation(value = "|MsgDynamicInfoEO|详情")
    @GetMapping("/{id}")
//    @RequiresPermissions("lawss:msgDynamicInfo:get")
    public ResponseMessage<MsgDynamicInfoEO> find(@PathVariable String id) throws Exception {
        return Result.success(msgDynamicInfoEOService.selectByPrimaryKey(id));
    }
/**
 * @Author liwenxuan
 * @Description  新增动态信息表和新增动态信息附件表
 * 先根据需求判断一些字段不能为空，先新增动态信息表然后生成动态信息数据id，把此id作为新增动态信息附件表中消息id字段新增到动态信息附件表
 *对象里面的发布日期不能为空
 * 调用附件存入动态信息id/文件名称/文件后缀/文件ID
 * @Date Administrator 2018/9/17
 * @Param [msgDynamicInfoVO]
 * @return com.adc.da.util.http.ResponseMessage<com.adc.da.lawss.entity.MsgDynamicInfoEO>
 **/
    @ApiOperation(value = "|MsgDynamicInfoEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
//    @RequiresPermissions("lawss:msgDynamicInfo:save")
    public ResponseMessage<MsgDynamicInfoEO> create(@RequestBody MsgDynamicInfoVO msgDynamicInfoVO) throws Exception {

        if(StringUtils.isBlank(msgDynamicInfoVO.getTitle())){
            return Result.error("r0014", "动态标题不能为空");
        }
        if(msgDynamicInfoVO.getPubTime()==null){
            return  Result.error("r0015", "发布时间不能为空");
        }

        if(StringUtils.isBlank(msgDynamicInfoVO.getMsgType())){
            return  Result.error("r0016","消息类型不能为空");
        }

        if(StringUtils.isBlank(msgDynamicInfoVO.getMsgMode())){
            return  Result.error("r0016", "消息消息所属模块不能为空");
        }
        if(StringUtils.isBlank(msgDynamicInfoVO.getContent())){
            return  Result.error("r0016", "动态内容不能为空");
        }

        MsgDynamicInfoEO  msgDynamicInfoEO=   beanMapper.map(msgDynamicInfoVO, MsgDynamicInfoEO.class);
        msgDynamicInfoEOService.insertSelective( msgDynamicInfoEO);
        List<MsgFileEO> msgFileEOList = msgDynamicInfoVO.getMsgFileEOList();
        for( MsgFileEO  msgFileEO :   msgFileEOList){
            msgFileEO.setId(UUID.randomUUID10());
            msgFileEOService.insert(msgFileEO);
        }
        return Result.success(msgDynamicInfoEO);
    }
    /**
     * @Author liwenxuan
     * @Description
     * 1.修改动态信息表
     * 2.修改动态信息附件表（不可以在这里修改只可以插入附件）
     *
     * @Date Administrator 2018/9/17
     * @Param [msgDynamicInfoVO]
     * @return com.adc.da.util.http.ResponseMessage<com.adc.da.lawss.entity.MsgDynamicInfoEO>
     **/
    @ApiOperation(value = "|MsgDynamicInfoEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
//    @RequiresPermissions("lawss:msgDynamicInfo:update")
    public ResponseMessage<MsgDynamicInfoEO> update(@RequestBody MsgDynamicInfoVO msgDynamicInfoVO) throws Exception {
        MsgDynamicInfoEO msgDynamicInfoEO = beanMapper.map(msgDynamicInfoVO, MsgDynamicInfoEO.class);
        msgDynamicInfoEOService.updateByPrimaryKeySelective(msgDynamicInfoEO);
       List<MsgFileEO> msgFileEOList = msgDynamicInfoVO.getMsgFileEOList();
        for( MsgFileEO  msgFileEO :   msgFileEOList){
            msgFileEOService.updateByPrimaryKey(msgFileEO);
        }
        return Result.success(msgDynamicInfoEO);
    }
/**
 * @Author liwenxuan
 * @Description  删除动态信息表信息（设置valid_flag=1）
 * @Date Administrator 2018/9/17
 * @Param [id]
 * @return com.adc.da.util.http.ResponseMessage
 **/
/*    @ApiOperation(value = "|MsgDynamicInfoEO|删除")
    @DeleteMapping("/{id}")
//    @RequiresPermissions("lawss:msgDynamicInfo:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        msgDynamicInfoEOService.deleteByPrimaryKey(id);
        logger.info("delete from MSG_DYNAMIC_INFO where id = {}", id);
        return Result.success();
    }*/


    @ApiOperation(value = "|MsgDynamicInfoEO|删除")
    @DeleteMapping("/{ids}")
//    @RequiresPermissions("lawss:msgDynamicInfo:delete")
    public ResponseMessage deleteMsgList(@NotNull @PathVariable("ids") String[] ids) throws Exception {
        msgDynamicInfoEOService.deleteLogicInBatch(Arrays.asList(ids));
//        msgDynamicInfoEOService.deleteByPrimaryKey(id);
//        logger.info("delete from MSG_DYNAMIC_INFO where id = {}", id);
        return Result.success();
    }

}
