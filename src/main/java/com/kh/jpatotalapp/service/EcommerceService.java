package com.kh.jpatotalapp.service;

import com.kh.jpatotalapp.entity.EcommerceData;
import com.kh.jpatotalapp.repository.EcommerceRepository;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EcommerceService {
    private final RestHighLevelClient client;
    private final EcommerceRepository ecommerceDataRepository;
    // 단일 필드 검색
    public SearchResponse searchKeyword(String keyword) throws IOException {
        SearchRequest searchRequest = new SearchRequest("kibana_sample_data_ecommerce");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery("category", keyword)); // category 필드에 keyword가 포함된 문서를 검색
        searchRequest.source(searchSourceBuilder);
        return client.search(searchRequest, RequestOptions.DEFAULT);
    }
    // 다중 필드 검색
    public SearchResponse searchKeyword(String keyword, List<String> fields) throws IOException {
        SearchRequest searchRequest = new SearchRequest("kibana_sample_data_ecommerce");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.multiMatchQuery(keyword, fields.toArray(new String[0])));
        searchRequest.source(searchSourceBuilder);
        return client.search(searchRequest, RequestOptions.DEFAULT);
    }
    // 카테고리 평균 가격 구하는 집계 기능
    public SearchResponse aggregation() throws IOException {
        SearchRequest searchRequest = new SearchRequest("kibana_sample_data_ecommerce");
        return client.search(searchRequest, RequestOptions.DEFAULT);
    }
    // 전체 조회
    public List<EcommerceData> findAll() {
        List<EcommerceData> dataList = new ArrayList<>();
        ecommerceDataRepository.findAll().forEach(dataList::add);
        return dataList;
    }
}