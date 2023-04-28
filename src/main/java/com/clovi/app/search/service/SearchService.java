package com.clovi.app.search.service;

import com.clovi.app.item.domain.Item;
import com.clovi.app.video.domain.Video;
import com.clovi.app.video.repository.VideoRepositoryCustomImpl;
import com.clovi.app.search.dto.request.SearchRequest;
import com.clovi.app.search.dto.response.KeywordResponse;
import com.clovi.app.search.dto.response.SearchResponse;
import com.clovi.app.item.repository.ItemRepositoryCustomImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SearchService {
    private final ItemRepositoryCustomImpl itemRepositoryCustomImpl;
    private final VideoRepositoryCustomImpl videoRepositoryCustomImpl;

    public SearchResponse getItemsAndVideosByFilter(SearchRequest searchRequest, Pageable pageable) {
        Page<Item> itemList = itemRepositoryCustomImpl.filterByConditions(searchRequest, pageable);
        Page<Video> videoList = videoRepositoryCustomImpl.filterByConditions(searchRequest, pageable);

        return new SearchResponse(itemList, videoList);
    }

    public KeywordResponse getItemsAndVideosByKeyword(String searchKeyword) {
        List<Item> itemList = itemRepositoryCustomImpl.filterByKeyword(searchKeyword);
        List<Video> videoList = videoRepositoryCustomImpl.filterByKeyword(searchKeyword);

        return new KeywordResponse(itemList, videoList);
    }
}
