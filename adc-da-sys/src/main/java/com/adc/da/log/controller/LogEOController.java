package com.adc.da.log.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;

import com.adc.da.base.page.Pager;
import com.adc.da.util.utils.BeanMapper;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adc.da.base.web.BaseController;
import com.adc.da.log.entity.LogEO;
import com.adc.da.log.vo.LogVO;
import com.adc.da.log.page.LogEOPage;
import com.adc.da.log.service.LogEOService;
import com.adc.da.util.http.PageInfo;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/${restPath}/log/log")
@Api(description = "日志管理")
public class LogEOController extends BaseController<LogEO>{

    private static final Logger logger = LoggerFactory.getLogger(LogEOController.class);

    @Autowired
    private LogEOService logEOService;

    @Autowired
    BeanMapper beanMapper;
    /**
     * new paging query, using GetMapping.
     * total 5 variable: pageNo,pageSize,account,startTime,endTime
     * 2018/03/26
     */

    @ApiOperation(value = "|LogEO|分页查询")
    @GetMapping("")
    // @RequiresPermissions("sys:user:list")

    public ResponseMessage<PageInfo<LogVO>> page(Integer pageNo, Integer pageSize, String account, String startTime,
                                                 String endTime) throws Exception {
        LogEOPage page = new LogEOPage();
        if (pageNo != null) {
            page.setPage(pageNo);
        }
        if (pageSize != null) {
            page.setPageSize(pageSize);
        }
        if (StringUtils.isNotEmpty(account)) {
            page.setAccount(account);
            page.setAccountOperator("LIKE");
        }
        if (StringUtils.isNotEmpty(startTime)) {
            page.setStartTime1(startTime);
            //page.setStartTime1("LIKE");
        }
        if (StringUtils.isNotEmpty(endTime)) {
            page.setStartTime2(endTime);

        }
        page.setPager(new Pager());
        List<LogEO> rows = logEOService.queryByPage(page);
        return Result.success(beanMapper.mapPage(getPageInfo(page.getPager(), rows), LogVO.class));

    }

    /**
     *  end
     */

    @ApiOperation(value = "|LogEO|详情")
    @GetMapping("/{id}")
    public ResponseMessage<LogVO> find(@PathVariable String id) throws Exception {
        LogVO logVO = beanMapper.map(logEOService.selectByPrimaryKey(id), LogVO.class);
        return Result.success(logVO);
    }


//	@ApiOperation(value = "|LogEO|查询")
//    @GetMapping("")
//    public ResponseMessage<List<LogEO>> list(LogEOPage page) throws Exception {
//        return Result.success(logEOService.queryByList(page));
//	}

//    @ApiOperation(value = "|LogEO|新增")
//    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
//    public ResponseMessage<LogEO> create(@RequestBody LogEO logEO) throws Exception {
//        logEOService.insertSelective(logEO);
//        return Result.success(logEO);
//    }
//
//    @ApiOperation(value = "|LogEO|修改")
//    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
//    public ResponseMessage<LogEO> update(@RequestBody LogEO logEO) throws Exception {
//        logEOService.updateByPrimaryKeySelective(logEO);
//        return Result.success(logEO);
//    }
//
//    @ApiOperation(value = "|LogEO|删除")
//    @DeleteMapping("/{id}")
//    public ResponseMessage delete(@PathVariable String id) throws Exception {
//        logEOService.deleteByPrimaryKey(id);
//        logger.info("delete from TS_LOG where id = {}", id);
//        return Result.success();
//    }

}
