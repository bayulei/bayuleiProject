package com.adc.da.lawss.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;


import java.util.List;


import com.adc.da.lawss.entity.MsgFileEO;
import com.adc.da.lawss.vo.MsgDynamicInfoVO;

import com.adc.da.util.utils.BeanMapper;
import com.adc.da.util.utils.StringUtils;

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

@RestController
@RequestMapping("/${restPath}/lawss/msgDynamicInfo")
@Api(description = "|MsgDynamicInfoEO|")
public class MsgDynamicInfoEOController extends BaseController<MsgDynamicInfoEO>{

    private static final Logger logger = LoggerFactory.getLogger(MsgDynamicInfoEOController.class);

    @Autowired
    private MsgDynamicInfoEOService msgDynamicInfoEOService;

    @Autowired
    BeanMapper beanMapper;

	@ApiOperation(value = "|MsgDynamicInfoEO|分页查询")
    @GetMapping("/page")
//    @RequiresPermissions("lawss:msgDynamicInfo:page")
    public ResponseMessage<PageInfo<MsgDynamicInfoEO>> page(Integer pageNo,Integer pageSize) throws Exception {
        MsgDynamicInfoEOPage page = new MsgDynamicInfoEOPage();
        if (pageNo != null) {
            page.setPage(pageNo);
        }
        if (pageSize != null) {
            page.setPageSize(pageSize);
        }
        List<MsgDynamicInfoEO> rows = msgDynamicInfoEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

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

    @ApiOperation(value = "|MsgDynamicInfoEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
//    @RequiresPermissions("lawss:msgDynamicInfo:save")
    public ResponseMessage<MsgDynamicInfoEO> create(@RequestBody MsgDynamicInfoVO msgDynamicInfoVO) throws Exception {

        if(StringUtils.isBlank(msgDynamicInfoVO.getTitle())){
            return Result.error("r0014", "动态标题不能为空");
        }
//        对象里面的发布日期不能为空
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




//  李文轩：对新增的动态标题进行重复判断
/*        if(StringUtils.isBlank(msgDynamicInfoEO.getTitle())){
            return Result.error("r0014", "动态标题不能为空");
        }else if(dicTypeEOList!=null && !dicTypeEOList.isEmpty()){
            return Result.error("r0015","字典类型编码已存在");
        }*/

        MsgDynamicInfoEO  msgDynamicInfoEO=   beanMapper.map(msgDynamicInfoVO, MsgDynamicInfoEO.class);
        msgDynamicInfoEOService.insertSelective( msgDynamicInfoEO);


//        调用附件存入动态信息id/文件名称/文件后缀/文件ID
        List<MsgFileEO> msgFileEOList = msgDynamicInfoVO.getMsgFileEOList();
        msgDynamicInfoEOService.updateIdOfMsgFile(msgFileEOList);

        return Result.success(msgDynamicInfoEO);
    }

    @ApiOperation(value = "|MsgDynamicInfoEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
//    @RequiresPermissions("lawss:msgDynamicInfo:update")
    public ResponseMessage<MsgDynamicInfoEO> update(@RequestBody MsgDynamicInfoEO msgDynamicInfoEO) throws Exception {
        msgDynamicInfoEOService.updateByPrimaryKeySelective(msgDynamicInfoEO);
        return Result.success(msgDynamicInfoEO);
    }

    @ApiOperation(value = "|MsgDynamicInfoEO|删除")
    @DeleteMapping("/{id}")
//    @RequiresPermissions("lawss:msgDynamicInfo:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        msgDynamicInfoEOService.deleteByPrimaryKey(id);
        logger.info("delete from MSG_DYNAMIC_INFO where id = {}", id);
        return Result.success();
    }

}
