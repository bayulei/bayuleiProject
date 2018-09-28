package com.adc.da.search.service.impl;

import com.adc.da.search.entity.SearchInfoEO;
import com.adc.da.search.service.SearchCenterService;
import org.eclipse.jetty.util.StringUtil;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class SearchCenterServiceImpl implements SearchCenterService {
    private  final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(SearchCenterServiceImpl.class);

    @Autowired
    private TransportClient transportClient;

    private  TransportClient client;

    @PostConstruct
    public void init() {
        client = this.transportClient;
    }


    @Override
    public List<Map<String, Object>> searchSarBykey(SearchInfoEO searchInfoEO) {

        String[] index = {"stand","laws","bussstand"};
        List<Map<String, Object>> result = new ArrayList<>();
        if(StringUtil.isNotBlank(searchInfoEO.getSelectKey()) &&  StringUtil.isNotBlank(searchInfoEO.getSelectValue())){
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            switch (searchInfoEO.getSelectKey()){
                case "standNumber":
                    boolQueryBuilder.should(QueryBuilders.matchPhraseQuery("standnumbershow",searchInfoEO.getSelectValue()));
                    boolQueryBuilder.should(QueryBuilders.matchQuery("laws_number",searchInfoEO.getSelectValue()));
                    boolQueryBuilder.should(QueryBuilders.matchQuery("stand_code",searchInfoEO.getSelectValue()));
                    break;
                case "standName":
                    boolQueryBuilder.should(QueryBuilders.matchQuery("stand_name",searchInfoEO.getSelectValue()));
                    break;
                case "standState":
                    // 需要修改
                    boolQueryBuilder.should(QueryBuilders.matchQuery("standstateshow",searchInfoEO.getSelectValue()));
                    boolQueryBuilder.should(QueryBuilders.matchQuery("lawsstateshow",searchInfoEO.getSelectValue()));
                    boolQueryBuilder.should(QueryBuilders.matchQuery("standstateshow",searchInfoEO.getSelectValue()));
                    break;
                case "standFile":
                    //标准正文  暂缺
                    break;
                case "applyArctic":
                    boolQueryBuilder.should(QueryBuilders.matchQuery("applyarcticshow",searchInfoEO.getSelectValue()));
                    break;
                case "issueTime":
                    RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("balance").format("epoch_millis").from(20000).to(30000).includeLower(true).includeUpper(true);
                    boolQueryBuilder.filter(rangeQueryBuilder);
                    //boolQueryBuilder.should(QueryBuilders.matchQuery("issue_time",searchInfoEO.getSelectValue()));
                    break;
                case "putTime":
                    boolQueryBuilder.should(QueryBuilders.matchQuery("put_time",searchInfoEO.getSelectValue()));
                    break;
                case "draftUser":
                    boolQueryBuilder.should(QueryBuilders.matchQuery("draft_user",searchInfoEO.getSelectValue()));
                    break;
                case "draftingUnit":
                    boolQueryBuilder.should(QueryBuilders.matchQuery("drafting_unit",searchInfoEO.getSelectValue()));
                    break;
                case "replaceStandNum":
                    boolQueryBuilder.should(QueryBuilders.matchQuery("replace_stand_num",searchInfoEO.getSelectValue()));
                    boolQueryBuilder.should(QueryBuilders.matchQuery("replace_laws_num",searchInfoEO.getSelectValue()));
                    break;
                case "replacedStandNum":
                    boolQueryBuilder.should(QueryBuilders.matchQuery("replaced_stand_num",searchInfoEO.getSelectValue()));
                    break;

            }
            SearchRequestBuilder searchRequestBuilder = client.prepareSearch(index)
                    .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                    .setFrom((searchInfoEO.getPage()-1)*searchInfoEO.getPageSize())
                    .setSize(searchInfoEO.getPageSize());
            searchRequestBuilder.setQuery(boolQueryBuilder);
            SearchResponse response = searchRequestBuilder.get();
            result = SearchResponseToList(response);
            searchInfoEO.getPager().setRowCount((int)response.getHits().getTotalHits());
        }
        else{
            index[0]="stand";
            index[1]="laws";
            index[2]="bussstand";
            SearchRequestBuilder searchRequestBuilder = client.prepareSearch(index)
                    .setQuery(QueryBuilders.matchAllQuery())
                    .setFrom((searchInfoEO.getPage()-1)*searchInfoEO.getPageSize())
                    .setSize(searchInfoEO.getPageSize());
            SearchResponse response = searchRequestBuilder.get();
            result = SearchResponseToList(response);
        }
        return result;


        //boolQueryBuilder两次.must ,如下，表示address中必须包含lane和mill
        //boolQueryBuilder.must(QueryBuilders.matchQuery("address","lane"));
        //boolQueryBuilder.must(QueryBuilders.matchQuery("address","mill"));

        //boolQueryBuilder两次.should ,如下，表示address中必须包含lane或者mill
        //boolQueryBuilder.should(QueryBuilders.matchQuery("address","lane"));
        //boolQueryBuilder.should(QueryBuilders.matchQuery("address","mill"));

        //boolQueryBuilder两次.mustNot ,如下，表示address中必须不能包含lane和mill
        //boolQueryBuilder.mustNot(QueryBuilders.matchQuery("address","lane"));
        //boolQueryBuilder.mustNot(QueryBuilders.matchQuery("address","mill"));

        //BoolQuerBuilder 可以使用must,should,mustnot 组合使用
        //boolQueryBuilder.mustNot(QueryBuilders.matchQuery("state","ID"));
        //boolQueryBuilder.must(QueryBuilders.matchQuery("age",40));
        //boolQueryBuilder.must(QueryBuilders.matchQuery("balance",28969));

        //boolQueryBuilder.filter中可以添加RangeQueryBuilder，下面注释中两种定义的RangeQueryBuilder是一样的，不同的两种方式
        //RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("balance").from(20000).to(30000).includeLower(true).includeUpper(true);
        //RangeQueryBuilder rangeQueryBuilder2 = QueryBuilders.rangeQuery("age").gte(20).lte(23);
        //boolQueryBuilder.filter(rangeQueryBuilder);
        //boolQueryBuilder.filter(rangeQueryBuilder2);



    }

    @Override
    public  List<Map<String, Object>> SearchResponseToList(SearchResponse searchResponse) {
        List<Map<String, Object>> sourceList = new ArrayList<Map<String, Object>>();
        for (SearchHit searchHit : searchResponse.getHits().getHits()) {
            searchHit.getSourceAsMap().put("id", searchHit.getId());
            sourceList.add(searchHit.getSourceAsMap());
        }
        return sourceList;
    }
}
