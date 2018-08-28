package com.adc.da.search.service;

import com.adc.da.search.config.EsPage;
import com.alibaba.fastjson.JSONObject;
import org.elasticsearch.action.search.SearchResponse;

import java.util.List;
import java.util.Map;

public interface ElasticsearchService {

    /**
     * Elasticsearch 创建索引定义
     * @param index  要创建的索引
     * @return true表示创建成功，false表示创建失败
     * @author gaoyan
     * date 2018-08-27
     */
      boolean createIndex(String index);

    /**
     * Elasticsearch 删除索引定义
     * @param index  要删除的索引
     * @return true表示删除成功，false表示删除失败
     * @author gaoyan
     * date 2018-08-27
     */
      boolean deleteIndex(String index);

    /**
     * Elasticsearch 判断索引是否存在定义
     * @param index  要判断的索引名称
     * @return true表示该索引存在，false表示该索引不存在
     * @author gaoyan
     * date 2018-08-28
     */
      boolean isIndexExist(String index);

    /**
     * 指定ID数据添加接口定义，
     * @param jsonObject 要增加的数据
     * @param index      索引，类似数据库
     * @param type       类型，类似表 (自6.0改版以后，一个索引里只允许有一种类型，
     *                    如果索引中已经有类型，增加数据过程中，添加其他类型会报错)
     * @param id         数据ID
     * @return  返回创建的文档的id
     * @author gaoyan
     * date 2018-08-28
     */
      String addData(JSONObject jsonObject, String index, String type, String id);

    /**
     * 不指定ID数据添加接口定义，
     * @param jsonObject 要增加的数据
     * @param index      索引，类似数据库
     * @param type       类型，类似表 (自6.0改版以后，一个索引里只允许有一种类型，
     *                    如果索引中已经有类型，增加数据过程中，添加其他类型会报错)
     * @return  返回创建的文档的id
     * @author gaoyan
     * date 2018-08-28
     */
      String addData(JSONObject jsonObject, String index, String type);

    /**
     * 通过ID删除数据
     *
     * @param index 索引，类似数据库
     * @param type  类型，类似表
     * @param id    数据ID
     */
    String  deleteDataById(String index, String type, String id);

    /**
     * Elasticsearch 删除某个index所有的文档接口定义
     * @param index  要插入的索引
     * @return 删除返回的结果
     * @author gaoyan
     * date 2018-08-27
     */
    long deleteAllDataOfIndex(String index);

    /**
     * 通过ID 更新数据
     *
     * @param jsonObject 要增加的数据
     * @param index      索引，类似数据库
     * @param type       类型，类似表
     * @param id         数据ID
     * @return
     */
      void updateDataById(JSONObject jsonObject, String index, String type, String id);

    /**
     * 通过ID获取数据
     *
     * @param index  索引，类似数据库
     * @param type   类型，类似表
     * @param id     数据ID
     * @param fields 需要显示的字段，逗号分隔（缺省为全部字段）
     * @return
     */
      Map<String, Object> searchDataById(String index, String type, String id, String fields);


    /**
     * 使用分词查询
     *
     * @param index    索引名称
     * @param type     类型名称,可传入多个type逗号分隔
     * @param fields   需要显示的字段，逗号分隔（缺省为全部字段）
     * @param matchStr 过滤条件（xxx=111,aaa=222）
     * @return
     */
      List<Map<String, Object>> searchListData(String index, String type, String fields, String matchStr);

    /**
     * 使用分词查询
     *
     * @param index       索引名称
     * @param type        类型名称,可传入多个type逗号分隔
     * @param fields      需要显示的字段，逗号分隔（缺省为全部字段）
     * @param sortField   排序字段
     * @param matchPhrase true 使用，短语精准匹配
     * @param matchStr    过滤条件（xxx=111,aaa=222）
     * @return
     */
      List<Map<String, Object>> searchListData(String index, String type, String fields, String sortField, boolean matchPhrase, String matchStr);


    /**
     * 使用分词查询
     *
     * @param index          索引名称
     * @param type           类型名称,可传入多个type逗号分隔
     * @param size           文档大小限制
     * @param fields         需要显示的字段，逗号分隔（缺省为全部字段）
     * @param sortField      排序字段
     * @param matchPhrase    true 使用，短语精准匹配
     * @param highlightField 高亮字段
     * @param matchStr       过滤条件（xxx=111,aaa=222）
     * @return
     */
      List<Map<String, Object>> searchListData(String index, String type, Integer size, String fields, String sortField, boolean matchPhrase, String highlightField, String matchStr);


    /**
     * 使用分词查询
     *
     * @param index          索引名称
     * @param type           类型名称,可传入多个type逗号分隔
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
      List<Map<String, Object>> searchListData(String index, String type, long startTime, long endTime, Integer size, String fields, String sortField, boolean matchPhrase, String highlightField, String matchStr);

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
      EsPage searchDataPage(String index, String type, int currentPage, int pageSize, long startTime, long endTime, String fields, String sortField, boolean matchPhrase, String highlightField, String matchStr);

    /**
     * 高亮结果集 特殊处理
     *
     * @param searchResponse
     * @param highlightField
     */
    List<Map<String, Object>> setSearchResponse(SearchResponse searchResponse, String highlightField);

    /**
     * Elasticsearch 批量插入接口定义
     * @param index  要插入的索引
     * @param type   要插入数据对应的type
     * @param list   插入数据的list
     * @return
     * @author gaoyan
     * date 2018-08-27
     */
    String addBulkData(String index, String type,List<JSONObject> list);

}
