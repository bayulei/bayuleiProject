package com.adc.da.search.service;

import com.adc.da.search.entity.SearchInfoEO;
import org.elasticsearch.action.search.SearchResponse;

import java.util.Map;
import java.util.List;

public interface SearchCenterService {

    List<Map<String, Object>> searchSarBykey(SearchInfoEO searchInfoEO);
    /**
     * 查询结果集searchResponse装换成List形式
     * @param searchResponse  需要转换的数据
     * @return
     * @author gaoyan
     * date 2018-08-28
     */
    List<Map<String, Object>> SearchResponseToList(SearchResponse searchResponse);

}
