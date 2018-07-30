package com.bestcode.esrent.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.bestcode.esrent.entity.House;
import com.bestcode.esrent.entity.HouseDetail;
import com.bestcode.esrent.entity.HouseTag;
import com.bestcode.esrent.repository.HouseDetailRepository;
import com.bestcode.esrent.repository.HouseRepository;
import com.bestcode.esrent.repository.HouseTagRepository;
import com.bestcode.esrent.service.search.HouseIndexKey;
import com.bestcode.esrent.service.search.HouseIndexTemplate;
import com.bestcode.esrent.service.search.SearchService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryAction;
import org.elasticsearch.index.reindex.DeleteByQueryRequestBuilder;
import org.elasticsearch.rest.RestStatus;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * @author xch
 * @create 2018-07-30 22:41
 **/
@Slf4j
@Service
public class SearchServiceImpl implements SearchService {

    private static final String INDEX_NAME = "xunwu";
    private static final String INDEX_TYPE = "house";

    @Autowired
    private HouseRepository houseRepository;

    @Autowired
    private HouseDetailRepository houseDetailRepository;

    @Autowired
    private HouseTagRepository houseTagRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TransportClient esClient;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public boolean index(Long houseId) {
        House house = houseRepository.findOne(houseId);
        if (house == null) {
            log.error("Index house {} dose not exist", houseId);
            return false;
        }
        HouseIndexTemplate houseIndexTemplate = new HouseIndexTemplate();
        modelMapper.map(house, houseIndexTemplate);

        HouseDetail detail = houseDetailRepository.findByHouseId(houseId);
        if (detail == null) {
            // todo 异常处理
        }
        modelMapper.map(detail, houseIndexTemplate);

        List<HouseTag> houseTags = houseTagRepository.findAllByHouseId(houseId);
        if (!CollectionUtils.isEmpty(houseTags)) {
            List<String> tags = houseTags.stream().map(HouseTag::getName).collect(Collectors.toList());
            houseIndexTemplate.setTags(tags);
        }
        SearchRequestBuilder requestBuilder = esClient.prepareSearch(INDEX_NAME).setTypes(INDEX_TYPE)
                .setQuery(QueryBuilders.termQuery(HouseIndexKey.HOUSE_ID, houseId));
        log.debug("requestBuilder :{}", requestBuilder);
        SearchResponse response = requestBuilder.get();
        long totalHit = response.getHits().getTotalHits();
        boolean success;
        if (totalHit == 0) {
            success = create(houseIndexTemplate);
        } else if (totalHit == 1) {
            String esId = response.getHits().getAt(0).getId();
            success = update(esId, houseIndexTemplate);
        } else {
            success = deleteAndCreate(totalHit, houseIndexTemplate);
        }
        if (success) {
            log.debug("Index success with house {}", houseId);
        }
        return success;

    }

    public boolean create(HouseIndexTemplate houseIndexTemplate) {
        try {
            IndexResponse response = esClient.prepareIndex(INDEX_NAME, INDEX_TYPE)
                    .setSource(objectMapper.writeValueAsBytes(houseIndexTemplate), XContentType.JSON)
                    .get();
            log.debug("Create index with house: {}", houseIndexTemplate.getHouseId());
            if (response.status() == RestStatus.CREATED) {
                return true;
            } else {
                return false;
            }
        } catch (JsonProcessingException e) {
            log.error("Error to index house: {}, exception:{}", houseIndexTemplate.getHouseId(), e);
            return false;
        }
    }

    public boolean update(String esId, HouseIndexTemplate houseIndexTemplate) {
        try {
            UpdateResponse response = esClient.prepareUpdate(INDEX_NAME, INDEX_TYPE, esId)
                    .setDoc(objectMapper.writeValueAsBytes(houseIndexTemplate), XContentType.JSON)
                    .get();
            log.debug("Update index with house: {}", houseIndexTemplate.getHouseId());
            if (response.status() == RestStatus.OK) {
                return true;
            } else {
                return false;
            }
        } catch (JsonProcessingException e) {
            log.error("Error to index house: {}, exception:{}", houseIndexTemplate.getHouseId(), e);
            return false;
        }

    }

    public boolean deleteAndCreate(long totalHit, HouseIndexTemplate houseIndexTemplate) {
        DeleteByQueryRequestBuilder builder = DeleteByQueryAction.INSTANCE
                .newRequestBuilder(esClient)
                .filter(QueryBuilders.termQuery(HouseIndexKey.HOUSE_ID, houseIndexTemplate.getHouseId()))
                .source(INDEX_NAME);
        log.debug("Delete by query for house:{}", builder);
        BulkByScrollResponse response = builder.get();
        long deleted = response.getDeleted();
        if (totalHit != deleted) {
            log.warn("Need delete {}, but {} was delete", totalHit, deleted);
            return false;
        } else {
            return create(houseIndexTemplate);
        }

    }

    @Override
    public void remove(Long houseId) {

    }
}
