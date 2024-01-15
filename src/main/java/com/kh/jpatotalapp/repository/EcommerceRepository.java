package com.kh.jpatotalapp.repository;

import com.kh.jpatotalapp.entity.EcommerceData;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface EcommerceRepository extends ElasticsearchRepository<EcommerceData, String> {

}