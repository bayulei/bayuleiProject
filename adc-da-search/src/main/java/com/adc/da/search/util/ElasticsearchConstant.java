package com.adc.da.search.util;

/**
 *Elasticsearch接口实现过程中用到的常量定义
 * @author gaoyan
 * date 2018/08/27
 */
public class ElasticsearchConstant {
    //构造方法
    public ElasticsearchConstant() {
    }
    //正确删除后返回结构
    public static final String SUCCESS_DELETED = "DELETED";
    //删除过程中未找到对应数据返回结果
    public static final String HAVE_NOT_FOUND= "NOT_FOUND";
}
