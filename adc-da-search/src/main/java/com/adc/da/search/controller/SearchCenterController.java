package com.adc.da.search.controller;

import com.adc.da.search.service.ElasticsearchService;
import io.swagger.annotations.Api;
import org.elasticsearch.client.transport.TransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/${restPath}/search/searchCenter")
@Api(description = "|SearchCenter|")
public class SearchCenterController {

    @Autowired
    private TransportClient client;
    @Autowired
    private ElasticsearchService elasticsearchService;



}
