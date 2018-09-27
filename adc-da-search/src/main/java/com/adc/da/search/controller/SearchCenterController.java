package com.adc.da.search.controller;

import com.adc.da.base.web.BaseController;
import com.adc.da.search.entity.SearchInfoEO;
import com.adc.da.search.service.SearchCenterService;
import com.adc.da.util.http.PageInfo;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.client.transport.TransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/${restPath}/search/searchCenter")
@Api(description = "|SearchCenter|")
public class SearchCenterController extends BaseController<Map<String, Object>> {

    @Autowired
    private TransportClient client;
    @Autowired
    private SearchCenterService searchCenterService;

    /**
     *
     * @param searchInfoEO
     * @return
     */
    @ApiOperation(value = "|SearchCenter|查询")
    @PostMapping(value="/searchSarBykey")
    public ResponseMessage<PageInfo<Map<String, Object>>>  searchSarBykey(SearchInfoEO searchInfoEO){
      List<Map<String, Object>> result =  new ArrayList<>();
        result = searchCenterService.searchSarBykey(searchInfoEO);
      return Result.success(getPageInfo(searchInfoEO.getPager(), result));
    }

}
