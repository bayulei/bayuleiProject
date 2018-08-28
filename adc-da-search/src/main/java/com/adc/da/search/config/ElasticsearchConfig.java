package com.adc.da.search.config;


import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * gaoyan
 * 2018-08-23
 * elasticsearch配置config
 */
@Configuration
public class ElasticsearchConfig {

    @Autowired
    private  ElasticsearchProperties elasticsearchProperties;

    @Bean
    public TransportClient client() throws UnknownHostException {
        TransportClient client = null;
        try {
            Settings settings = Settings.builder()
                    .put("client.transport.sniff", true)//增加嗅探机制，找到ES集群
                    .put("thread_pool.search.size", elasticsearchProperties.getPoolSize())//增加线程池个数，暂时设为5
                    .put("cluster.name",elasticsearchProperties.getClustername())
                    .build();
            client = new PreBuiltTransportClient(settings)
                      .addTransportAddress(new TransportAddress(InetAddress.getByName(elasticsearchProperties.getIp()),elasticsearchProperties.getPort()));
        } catch (java.net.UnknownHostException e) {
            e.printStackTrace();
        }
      return  client;
    }

}
