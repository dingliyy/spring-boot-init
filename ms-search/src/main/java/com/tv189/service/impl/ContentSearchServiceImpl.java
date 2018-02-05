package com.tv189.service.impl;

import static org.elasticsearch.index.query.QueryBuilders.boolQuery;
import static org.elasticsearch.index.query.QueryBuilders.matchQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import com.tv189.common.SearchParam;
import com.tv189.domain.Content;
import com.tv189.domain.Video;
import com.tv189.service.ContentSearchService;

@Service
public class ContentSearchServiceImpl implements ContentSearchService{
	@Autowired
	private ElasticsearchOperations elasticsearchOperations;
	
	
	@Override
	public List<Content> search(SearchParam searchParam) {
		String wd = searchParam.getWd();
        SearchQuery queryBuilder = new NativeSearchQueryBuilder()
                .withQuery(
                        boolQuery().should(matchQuery("name", wd).boost(.9f))
                ).build();

        return elasticsearchOperations.query(queryBuilder,(t) -> getContents(t));
	}
	
	private List<Content> getContents(SearchResponse response){
        List<Content> result = new ArrayList<>();
        long count = response.getHits().getTotalHits();
        if(count > 0){
            for(SearchHit hit : response.getHits().getHits()){
                result.add(Content.builder()
                        .id((long) hit.getSource().get("id"))
                        .title((String)hit.getSource().get("title"))
                        .videos(getVideos((ArrayList<Map<String, Object>>) hit.getSource().get("videos")))
                        .build());
            }
        }
        return result;
    }
	
	private List<Video> getVideos(ArrayList<Map<String, Object>> listOfMap){
        List<Video> videos = new ArrayList<>();
        for(Map<String, Object> map : listOfMap){
            Video video = Video.builder()
                    .videoQuarlityId((int) map.get("videoQuarlityId"))
                    .path((String) map.get("path"))
                    .hlsPath((String) map.get("hlsPath"))
                    .build();
            videos.add(video);
        }
        return videos;
    }

}
