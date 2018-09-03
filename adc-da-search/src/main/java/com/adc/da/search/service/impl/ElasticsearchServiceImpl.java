package com.adc.da.search.service.impl;

import com.adc.da.search.config.EsPage;
import com.adc.da.search.service.ElasticsearchService;
import com.adc.da.util.utils.StringUtils;
import com.alibaba.fastjson.JSONObject;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequestBuilder;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.mapper.RangeFieldMapper;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryAction;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.BucketOrder;
import org.elasticsearch.search.aggregations.bucket.range.Range;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregator;
import org.elasticsearch.search.aggregations.metrics.avg.Avg;
import org.elasticsearch.search.aggregations.metrics.max.Max;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.mail.search.StringTerm;
import java.util.*;

@Component
public class ElasticsearchServiceImpl implements ElasticsearchService {
    private  final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ElasticsearchServiceImpl.class);

    @Autowired
    private TransportClient transportClient;

    private  TransportClient client;

    @PostConstruct
    public void init() {
        client = this.transportClient;
    }

    /**
     * Elasticsearch 创建索引实现
     * @param index  要创建的索引
     * @return true表示创建成功，false表示创建失败
     * @author gaoyan
     * date 2018-08-27
     */
    @Override
    public   boolean createIndex(String index){
        if (!isIndexExist(index)) {
            LOGGER.info("Index is not exits!");
        }
        CreateIndexResponse indexresponse = client.admin().indices().prepareCreate(index).get();
        LOGGER.info("执行建立成功？" + indexresponse.isAcknowledged());
        return indexresponse.isAcknowledged();
    }

    /**
     * Elasticsearch 删除索引实现
     * @param index  要删除的索引
     * @return true表示删除成功，false表示删除失败
     * @author gaoyan
     * date 2018-08-27
     */
    @Override
    public  boolean deleteIndex(String index) {
        if (!isIndexExist(index)) {
            LOGGER.info("Index is not exits!");
        }
        DeleteIndexResponse dResponse = client.admin().indices().prepareDelete(index).get();
        if (dResponse.isAcknowledged()) {
            LOGGER.info("delete index " + index + "  successfully!");
        } else {
            LOGGER.info("Fail to delete index " + index);
        }
        return dResponse.isAcknowledged();
    }

    /**
     * Elasticsearch 判断索引是否存在实现
     * @param index  要判断的索引名称
     * @return true表示该索引存在，false表示该索引不存在
     * @author gaoyan
     * date 2018-08-28
     */
    @Override
    public  boolean isIndexExist(String index) {
        IndicesExistsResponse inExistsResponse = client.admin().indices().exists(new IndicesExistsRequest(index)).actionGet();
        if (inExistsResponse.isExists()) {
            LOGGER.info("Index [" + index + "] is exist!");
        } else {
            LOGGER.info("Index [" + index + "] is not exist!");
        }
        return inExistsResponse.isExists();
    }

    /**
     * 指定ID数据添加接口实现，
     * @param jsonObject 要增加的数据
     * @param index      索引，类似数据库
     * @param type       类型，类似表 (自6.0改版以后，一个索引里只允许有一种类型，
     *                    如果索引中已经有类型，增加数据过程中，添加其他类型会报错)
     * @param id         数据ID
     * @return  返回创建的文档的id
     * @author gaoyan
     * date 2018-08-28
     */
    @Override
    public  String addData(JSONObject jsonObject, String index, String type, String id) {
        IndexResponse response = client.prepareIndex(index, type, id).setSource(jsonObject).get();
        LOGGER.info("addData response status:{},id:{}", response.status().getStatus(), response.getId());
        return response.getId();
    }

    /**
     * 不指定ID数据添加接口实现，
     * @param jsonObject 要增加的数据
     * @param index      索引，类似数据库
     * @param type       类型，类似表 (自6.0改版以后，一个索引里只允许有一种类型，
     *                    如果索引中已经有类型，增加数据过程中，添加其他类型会报错)
     * @return  返回创建的文档的id
     * @author gaoyan
     * date 2018-08-28
     */
    @Override
    public  String addData(JSONObject jsonObject, String index, String type) {
        IndexResponse response = client.prepareIndex(index, type).setSource(jsonObject).get();
        LOGGER.info("addData response status:{},id:{}", response.status().getStatus(), response.getId());
        return response.getId();
    }

    /**
     * 通过ID删除数据实现
     *
     * @param index 索引，类似数据库
     * @param type  类型，类似表
     * @param id    数据ID
     * @return  正确删除返回DELETED
     * @author gaoyan
     * date 2018-08-28
     */
    @Override
    public  String deleteDataById(String index, String type, String id) {
        DeleteResponse response = client.prepareDelete(index, type, id).get();
        LOGGER.info("deleteDataById response status:{},id:{}", response.status().getStatus(), response.getId());
        return response.getResult().toString();
    }

    /**
     * Elasticsearch 删除某个index所有的文档接口实现
     * @param index  要插入的索引
     * @return 删除返回的结果，一共删除多少条
     * @author gaoyan
     * date 2018-08-27
     */
    @Override
    public long deleteAllDataOfIndex(String index) {
        BulkByScrollResponse response = DeleteByQueryAction.INSTANCE.newRequestBuilder(client)
                .filter(QueryBuilders.matchAllQuery())
                .source(index)
                .get();
        //一共删除掉多少条
        long deleted = response.getDeleted();
        return deleted;
    }

    /**
     * 通过ID 更新数据实现
     *
     * @param jsonObject 要更新的数据
     * @param index      索引，类似数据库
     * @param type       类型，类似表
     * @param id         数据ID
     * @return  如果正确删除返回 UPDATED
     * @author gaoyan
     * date 2018-08-28
     */
    @Override
    public  String updateDataById(JSONObject jsonObject, String index, String type, String id) {
        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.index(index).type(type).id(id).doc(jsonObject);
        UpdateResponse result;
        try {
             result = this.client.update(updateRequest).get();
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return result.getResult().toString();
    }

    /**
     * 通过ID获取数据实现
     *
     * @param index  索引，类似数据库
     * @param type   类型，类似表
     * @param id     数据ID
     * @param fields 需要显示的字段，逗号分隔（缺省为全部字段）
     * @return  Map结构的数据集
     * @author gaoyan
     * date 2018-08-28
     */
    @Override
    public  Map<String, Object> searchDataById(String index, String type, String id, String fields) {

        GetRequestBuilder getRequestBuilder = client.prepareGet(index, type, id);
        if (StringUtils.isNotEmpty(fields)) {
            getRequestBuilder.setFetchSource(fields.split(","), null);
        }
        GetResponse getResponse =  getRequestBuilder.get();
        return getResponse.getSource();
    }


    /**
     * 使用分词查询
     *
     * @param index    索引名称
     * @param type     类型名称
     * @param fields   需要显示的字段，逗号分隔（缺省为全部字段）
     * @param matchStr 过滤条件（xxx=111,aaa=222）
     * @return
     * @author gaoyan
     * date 2018-08-28
     */
    @Override
    public  List<Map<String, Object>> searchListData(String index, String type, String fields, String matchStr) {
        return searchListData(index, type, 0, 0, null, fields, null, false, null, matchStr);
    }

    /**
     * 使用分词查询
     *
     * @param index       索引名称
     * @param type        类型名称
     * @param fields      需要显示的字段，逗号分隔（缺省为全部字段）
     * @param sortField   排序字段
     * @param matchPhrase true 使用，短语精准匹配
     * @param matchStr    过滤条件（xxx=111,aaa=222）
     * @return
     * @author gaoyan
     * date 2018-08-28
     */
    @Override
    public  List<Map<String, Object>> searchListData(String index, String type, String fields, String sortField, boolean matchPhrase, String matchStr) {
        return searchListData(index, type, 0, 0, null, fields, sortField, matchPhrase, null, matchStr);
    }


    /**
     * 使用分词查询
     *
     * @param index          索引名称
     * @param type           类型名称
     * @param size           文档大小限制
     * @param fields         需要显示的字段，逗号分隔（缺省为全部字段）
     * @param sortField      排序字段
     * @param matchPhrase    true 使用，短语精准匹配
     * @param highlightField 高亮字段
     * @param matchStr       过滤条件（xxx=111,aaa=222）
     * @return
     */
    @Override
    public  List<Map<String, Object>> searchListData(String index, String type, Integer size, String fields, String sortField, boolean matchPhrase, String highlightField, String matchStr) {
        return searchListData(index, type, 0, 0, size, fields, sortField, matchPhrase, highlightField, matchStr);
    }


    /**
     * 使用分词查询
     *
     * @param index          索引名称
     * @param type           类型名称
     * @param startTime      开始时间
     * @param endTime        结束时间
     * @param size           文档大小限制
     * @param fields         需要显示的字段，逗号分隔（缺省为全部字段）
     * @param sortField      排序字段
     * @param matchPhrase    true 使用，短语精准匹配
     * @param highlightField 高亮字段
     * @param matchStr       过滤条件（xxx=111,aaa=222）
     * @return
     */
    @Override
    public  List<Map<String, Object>> searchListData(String index, String type, long startTime, long endTime, Integer size, String fields, String sortField, boolean matchPhrase, String highlightField, String matchStr) {

        SearchRequestBuilder searchRequestBuilder = client.prepareSearch(index);
        if (StringUtils.isNotEmpty(type)) {
            searchRequestBuilder.setTypes(type.split(","));
        }
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();

        if (startTime > 0 && endTime > 0) {
            boolQuery.must(QueryBuilders.rangeQuery("processTime")
                    .format("epoch_millis")
                    .from(startTime)
                    .to(endTime)
                    .includeLower(true)
                    .includeUpper(true));
        }

        //搜索的的字段
        if (StringUtils.isNotEmpty(matchStr)) {
            for (String s : matchStr.split(",")) {
                String[] ss = s.split("=");
                if (ss.length > 1) {
                    if (matchPhrase == Boolean.TRUE) {
                        boolQuery.must(QueryBuilders.matchPhraseQuery(s.split("=")[0], s.split("=")[1]));
                    } else {
                        boolQuery.must(QueryBuilders.matchQuery(s.split("=")[0], s.split("=")[1]));
                    }
                }

            }
        }

        // 高亮（xxx=111,aaa=222）
        if (StringUtils.isNotEmpty(highlightField)) {
            HighlightBuilder highlightBuilder = new HighlightBuilder();

            //highlightBuilder.preTags("<span style='color:red' >");//设置前缀
            //highlightBuilder.postTags("</span>");//设置后缀

            // 设置高亮字段
            highlightBuilder.field(highlightField);
            searchRequestBuilder.highlighter(highlightBuilder);
        }


        searchRequestBuilder.setQuery(boolQuery);

        if (StringUtils.isNotEmpty(fields)) {
            searchRequestBuilder.setFetchSource(fields.split(","), null);
        }
        searchRequestBuilder.setFetchSource(true);

        if (StringUtils.isNotEmpty(sortField)) {
            searchRequestBuilder.addSort(sortField, SortOrder.DESC);
        }

        if (size != null && size > 0) {
            searchRequestBuilder.setSize(size);
        }

        //打印的内容 可以在 Elasticsearch head 和 Kibana  上执行查询
        LOGGER.info("\n{}", searchRequestBuilder);

        SearchResponse searchResponse = searchRequestBuilder.execute().actionGet();

        long totalHits = searchResponse.getHits().totalHits;
        long length = searchResponse.getHits().getHits().length;

        LOGGER.info("共查询到[{}]条数据,处理数据条数[{}]", totalHits, length);

        if (searchResponse.status().getStatus() == 200) {
            // 解析对象
            return setSearchResponse(searchResponse, highlightField);
        }

        return null;

    }

    /**
     * 使用分词查询,并分页
     *
     * @param index          索引名称
     * @param type           类型名称,可传入多个type逗号分隔
     * @param currentPage    当前页
     * @param pageSize       每页显示条数
     * @param startTime      开始时间
     * @param endTime        结束时间
     * @param fields         需要显示的字段，逗号分隔（缺省为全部字段）
     * @param sortField      排序字段
     * @param matchPhrase    true 使用，短语精准匹配
     * @param highlightField 高亮字段
     * @param matchStr       过滤条件（xxx=111,aaa=222）
     * @return
     */
    @Override
    public  EsPage searchDataPage(String index, String type, int currentPage, int pageSize, long startTime, long endTime, String fields, String sortField, boolean matchPhrase, String highlightField, String matchStr) {
        SearchRequestBuilder searchRequestBuilder = client.prepareSearch(index);
        if (StringUtils.isNotEmpty(type)) {
            searchRequestBuilder.setTypes(type.split(","));
        }
        searchRequestBuilder.setSearchType(SearchType.QUERY_THEN_FETCH);

        // 需要显示的字段，逗号分隔（缺省为全部字段）
        if (StringUtils.isNotEmpty(fields)) {
            searchRequestBuilder.setFetchSource(fields.split(","), null);
        }

        //排序字段
        if (StringUtils.isNotEmpty(sortField)) {
            searchRequestBuilder.addSort(sortField, SortOrder.DESC);
        }

        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();

        if (startTime > 0 && endTime > 0) {
            boolQuery.must(QueryBuilders.rangeQuery("processTime")
                    .format("epoch_millis")
                    .from(startTime)
                    .to(endTime)
                    .includeLower(true)
                    .includeUpper(true));
        }

        // 查询字段
        if (StringUtils.isNotEmpty(matchStr)) {
            for (String s : matchStr.split(",")) {
                String[] ss = s.split("=");
                if (matchPhrase == Boolean.TRUE) {
                    boolQuery.must(QueryBuilders.matchPhraseQuery(s.split("=")[0], s.split("=")[1]));
                } else {
                    boolQuery.must(QueryBuilders.matchQuery(s.split("=")[0], s.split("=")[1]));
                }
            }
        }

        // 高亮（xxx=111,aaa=222）
        if (StringUtils.isNotEmpty(highlightField)) {
            HighlightBuilder highlightBuilder = new HighlightBuilder();

            //highlightBuilder.preTags("<span style='color:red' >");//设置前缀
            //highlightBuilder.postTags("</span>");//设置后缀

            // 设置高亮字段
            highlightBuilder.field(highlightField);
            searchRequestBuilder.highlighter(highlightBuilder);
        }

        searchRequestBuilder.setQuery(QueryBuilders.matchAllQuery());
        searchRequestBuilder.setQuery(boolQuery);

        // 分页应用
        searchRequestBuilder.setFrom(currentPage).setSize(pageSize);

        // 设置是否按查询匹配度排序
        searchRequestBuilder.setExplain(true);

        //打印的内容 可以在 Elasticsearch head 和 Kibana  上执行查询
        LOGGER.info("\n{}", searchRequestBuilder);

        // 执行搜索,返回搜索响应信息
        SearchResponse searchResponse = searchRequestBuilder.execute().actionGet();

        long totalHits = searchResponse.getHits().totalHits;
        long length = searchResponse.getHits().getHits().length;

        LOGGER.debug("共查询到[{}]条数据,处理数据条数[{}]", totalHits, length);

        if (searchResponse.status().getStatus() == 200) {
            // 解析对象
            List<Map<String, Object>> sourceList = setSearchResponse(searchResponse, highlightField);

            return new EsPage(currentPage, pageSize, (int) totalHits, sourceList);
        }

        return null;

    }

    /**
     * 高亮结果集 特殊处理
     *
     * @param searchResponse
     * @param highlightField
     */
    @Override
    public  List<Map<String, Object>> setSearchResponse(SearchResponse searchResponse, String highlightField) {
        List<Map<String, Object>> sourceList = new ArrayList<Map<String, Object>>();
        StringBuffer stringBuffer = new StringBuffer();

        for (SearchHit searchHit : searchResponse.getHits().getHits()) {
            searchHit.getSourceAsMap().put("id", searchHit.getId());

            if (StringUtils.isNotEmpty(highlightField)) {

                System.out.println("遍历 高亮结果集，覆盖 正常结果集" + searchHit.getSourceAsMap());
                Text[] text = searchHit.getHighlightFields().get(highlightField).getFragments();

                if (text != null) {
                    for (Text str : text) {
                        stringBuffer.append(str.string());
                    }
                    //遍历 高亮结果集，覆盖 正常结果集
                    searchHit.getSourceAsMap().put(highlightField, stringBuffer.toString());
                }
            }
            sourceList.add(searchHit.getSourceAsMap());
        }
        return sourceList;
    }

    /**
     * Elasticsearch 批量插入接口实现
     * @param index
     * @param type
     * @param list
     * @return
     * @author gaoyan
     * date 2018-08-27
     */
    @Override
    public String addBulkData(String index, String type, List<JSONObject> list) {
            BulkRequestBuilder bulkRequestBuilder = client.prepareBulk();
            for(int i=0;i<list.size();i++){
                IndexRequestBuilder indexRequestBuilder = client.prepareIndex(index, type, UUID.randomUUID().toString()).setSource(list.get(i));
                bulkRequestBuilder.add(indexRequestBuilder);
            }
            BulkResponse response = bulkRequestBuilder.get();
           /* for (BulkItemResponse respons : response) {
                respons.getResponse().getResult().equals("created");
            }*/
            return "success";
    }

    /**
     * 默认查询全部数据，并按照对应的字段排序,方法内写了部分基础查询，具体业务查询可参考方法体中注释代码
     * @param index  index名称，可以从多个index中查询，所以这里定义成String数组
     * @param type   type名称，可以从多种type中查询，所以这里定义成String数组
     * @param sortfield  需要排序的字段，
     * @return
     * @author gaoyan
     * date 2018-08-28
     */
    @Override
    public List<Map<String, Object>> searchListDataAndSort( String[] index,String[] type,String sortfield,String[] fields) {
        SearchRequestBuilder searchRequestBuilder = client.prepareSearch(index)
                .setTypes(type)
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                //设置全文搜索
                //.setQuery(QueryBuilders.matchAllQuery())
                //设置某个field的搜索条件项，此处是模糊搜索；text:mill ,搜索address中含有关键字 mill;text:mill lane,搜索address中含有关键字mill或者lane的
                //.setQuery(QueryBuilders.matchQuery("address","mill lane"))
                //使用matchPhraseQuery过程中，当text:mill lane ,搜索address中即含有关键字mill lane,mill lane 词组不会被拆分，作为一个整体当做查询条件。
                .setQuery(QueryBuilders.matchPhraseQuery("address","lane mill"));
                //如果需要按照多个字段排序，直接依次添加addSort
                //.addSort(sortfield,SortOrder.DESC)
                //设置查询数量
                //.setSize(60)
                //设置从第几个开始查
                //.setFrom(0)
                //设置要查询的字段，fields
                //.setFetchSource(fields,null)
                /*.setPostFilter(QueryBuilders.rangeQuery("age").from(12).to(18))     // Filter
                .setExplain(true);*/
        SearchResponse response = searchRequestBuilder.get();
        List<Map<String, Object>> result = SearchResponseToList(response);
        return result;
    }

    @Override
    public List<Map<String, Object>> searchListDataUnit(String[] index, String[] type, String sortfield, String[] fields) {
        SearchRequestBuilder searchRequestBuilder = client.prepareSearch(index)
                .setTypes(type)
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH);

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

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

        searchRequestBuilder.setQuery(boolQueryBuilder);
        SearchResponse response = searchRequestBuilder.get();
        List<Map<String, Object>> result = SearchResponseToList(response);
        return result;
    }

    /**
     * 涉及组合查询条件主要涉及到聚合函数的使用，主要是分组求平均值，依靠平均值排序
     * @param index  index名称，可以从多个index中查询，所以这里定义成String数组
     * @param type   type名称，可以从多种type中查询，所以这里定义成String数组
     * @return
     * @author gaoyan
     * date 2018-08-29
     */
    @Override
    public List<Map<String, Object>> searchListDataGroup(String[] index, String[] type) {
        SearchResponse sr = client.prepareSearch(index)
                                  .setTypes(type)
                                  .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                                  .addAggregation(
                                      //分组依靠关键字terms
                                      AggregationBuilders.terms("by_state")
                                                         .field("state.keyword")
                                                         .subAggregation(
                                                                 //分组后求平均值依靠关键字avg,其他具体聚合函数可以参考官网
                                                                 // https://www.elastic.co/guide/en/elasticsearch/client/java-api/current/_metrics_aggregations.html
                                                                 AggregationBuilders.avg("average_balance")
                                                                                    .field("balance")
                                                         )
                                                         //分组后的排序方法,分别是聚合函数平均值，分组后统计数据，分组后关键字key,根据需求选择合适的排序
                                                         .order(BucketOrder.aggregation("average_balance",false))
                                                         .order(BucketOrder.count(true))
                                                         .order(BucketOrder.key(true))
                                      )
                                  .execute()
                                  .actionGet();
        //分组后得到的数据整合
        StringTerms result = sr.getAggregations().get("by_state");
        List<Map<String, Object>> sourceList = new ArrayList<Map<String, Object>>();
        for (StringTerms.Bucket bucket : result.getBuckets()) {
            Map<String, Object> map = new HashMap<>();
            map.put("key",bucket.getKeyAsString());
            map.put("count",bucket.getDocCount());
            Avg avg = bucket.getAggregations().get("average_balance");
            map.put("averagebalance",avg.getValue());
            sourceList.add(map);
        }
        return sourceList;
    }

    /**
     * 涉及组合查询条件主要涉及到聚合函数的使用，较为复杂，按照年龄阶段分组后，再安装性别分组，求balance平均值  ranges
     * @param index  index名称，可以从多个index中查询，所以这里定义成String数组
     * @param type   type名称，可以从多种type中查询，所以这里定义成String数组
     * @return
     * @author gaoyan
     * date 2018-08-29
     */
    @Override
    public List<Map<String, Object>> searchListDataGroupComplex(String[] index, String[] type) {
        SearchResponse sr = client.prepareSearch(index)
                .setTypes(type)
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .addAggregation(
                        //分组依靠关键字range
                        AggregationBuilders.range("by_age")
                                .field("age")
                                .addRange(20,30)
                                .addRange(30,40)
                                .addRange(40,50)
                                .subAggregation(
                                        //分组依靠关键字terms
                                        AggregationBuilders.terms("by_gender")
                                                .field("gender.keyword")
                                                .subAggregation(
                                                        //分组后求依靠关键字avg,其他具体聚合函数可以参考官网
                                                        // https://www.elastic.co/guide/en/elasticsearch/client/java-api/current/_metrics_aggregations.html
                                                        AggregationBuilders.max("max_balance")
                                                                .field("balance")
                                                )
                                )
                )
                .execute()
                .actionGet();
        //分组后得到的数据整合
        Range result = sr.getAggregations().get("by_age");
        List<Map<String, Object>> sourceList = new ArrayList<Map<String, Object>>();
        for (Range.Bucket bucket : result.getBuckets()) {
            Map<String, Object> map = new HashMap<>();
            map.put("key",bucket.getKeyAsString());
            map.put("count",bucket.getDocCount());

            List<Map<String, Object>> sourcechildList = new ArrayList<Map<String, Object>>();
            StringTerms resultchild = bucket.getAggregations().get("by_gender");
            for (StringTerms.Bucket bucketchild : resultchild.getBuckets()) {
                Map<String, Object> childmap = new HashMap<>();
                childmap.put("key",bucketchild.getKeyAsString());
                childmap.put("count",bucketchild.getDocCount());
                Max max = bucketchild.getAggregations().get("max_balance");
                map.put("maxbalance",max.getValue());
                sourcechildList.add(childmap);
            }

            map.put("gender",sourcechildList);
            sourceList.add(map);
        }
        return sourceList;
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
