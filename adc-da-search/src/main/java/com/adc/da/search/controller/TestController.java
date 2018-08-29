package com.adc.da.search.controller;

import com.adc.da.search.entity.PartProductEO;
import com.adc.da.search.service.ElasticsearchService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.adc.da.search.util.ElasticsearchConstant.SUCCESS_DELETED;

@RestController
@RequestMapping("/${restPath}")
@Api(description = "|testElasticsearch|")
public class TestController {


    @Autowired
    private TransportClient client;
    @Autowired
    private ElasticsearchService elasticsearchService;



    @PostMapping("add/book/novel")
    @ResponseBody
    public ResponseEntity add(@RequestParam(name ="title") String title,
                              @RequestParam(name ="author") String author,
                              @RequestParam(name ="word_count") String wordCount,
                              @RequestParam(name ="publish_date") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date publishDate){
        try {
            XContentBuilder content =XContentFactory.jsonBuilder()
                    .startObject()
                    .field("title",title)
                    .field("author",author)
                    .field("word_count",wordCount)
                    .field("publish_date",publishDate.getTime())
                    .endObject();
            IndexResponse result = this.client.prepareIndex("book","novel").setSource(content).get();
            return  new ResponseEntity(result.getId(),HttpStatus.OK);
        }
        catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @DeleteMapping("deleteOneDucumentTest")
    @ResponseBody
    public ResponseEntity delete(@RequestParam(name="id") String id) {
        String response = elasticsearchService.deleteDataById("book","novel",id);
        if(response.equals(SUCCESS_DELETED)){
            return new ResponseEntity(response, HttpStatus.OK);
        }
        else {
            return new ResponseEntity(response, HttpStatus.NOT_FOUND);
        }
    }



    @PostMapping("query/book/novel")
    @ResponseBody
    public ResponseEntity query(
            @RequestParam(name="title",required = false) String title,
            @RequestParam(name="author",required = false) String author,
            @RequestParam(name="gt_word_count",defaultValue = "0",required = false) int gtWordCount,
            @RequestParam(name="lt_word_count",required = false) Integer ltWordCount
    ){
        BoolQueryBuilder boolQueryBuilder =QueryBuilders.boolQuery();
        if(author!=null){
            boolQueryBuilder.must(QueryBuilders.matchQuery("author",author));
        }
        if(title!=null){
            boolQueryBuilder.must(QueryBuilders.matchQuery("title",title));
        }
        RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("word_count").from(gtWordCount);

        if(ltWordCount!=null && ltWordCount>0 ){
            rangeQueryBuilder.to(ltWordCount);
        }
        boolQueryBuilder.filter(rangeQueryBuilder);
        SearchRequestBuilder searchRequestBuilder =this.client.prepareSearch("book")
                .setTypes("novel")
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(boolQueryBuilder)
                .setFrom(0)
                .setSize(10);
        System.out.println(searchRequestBuilder);
        SearchResponse response =searchRequestBuilder.get();
        List<Map<String ,Object>> result = new ArrayList<Map<String ,Object>>();
        for (SearchHit hit:response.getHits()){
            result.add(hit.getSourceAsMap());
        }
        return  new ResponseEntity(result,HttpStatus.OK);
    }



    @PostMapping("addIndexSerTest")
    @ResponseBody
    public String searchIndexTest() {
        PartProductEO productEO= new PartProductEO();
        productEO.setCertNumber("er");
        productEO.setCorpId("cid");
        productEO.setCorpModel("cmodel");
        String  indexstate = elasticsearchService.addData((JSONObject) JSON.toJSON(productEO),"testone","two");
        return  indexstate;
    }

    @PostMapping("bulktest")
    @ResponseBody
    public String bulkTest() throws IOException {
        List<JSONObject> list = new ArrayList<JSONObject>();
        for(int i=0;i<100000;i++){
            PartProductEO productEO= new PartProductEO();
            productEO.setCertNumber("er"+i);
            productEO.setCorpId("cid"+i);
            productEO.setCorpModel("cmodel"+i);
            list.add(JSONObject.parseObject(JSONObject.toJSON(productEO).toString()));
        }
        String  indexstate = elasticsearchService.addBulkData("ymq_index","two",list);
        return  indexstate;
    }

    @PostMapping("justtest")
    @ResponseBody
    public List<Map<String, Object>>   justtest() throws IOException {
        String [] index={"bank","book"};
        String [] type={"_doc","_type"};
        String [] fields={"account_number", "balance"};
        List<Map<String, Object>> indexstate = elasticsearchService.searchListDataGroupComplex(index,type);
        return  indexstate;
    }
}