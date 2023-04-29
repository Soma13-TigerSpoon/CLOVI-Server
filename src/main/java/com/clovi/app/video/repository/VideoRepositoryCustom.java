package com.clovi.app.video.repository;

import com.clovi.app.search.dto.request.SearchRequest;
import com.clovi.app.video.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface VideoRepositoryCustom {
    Page<Video> filterByConditions(SearchRequest searchRequest, Pageable pageable);
    List<Video> filterByKeyword(String searchKeyword);
    List<Video> filterByItemId(Long itemId);

    List<Video> search(String videoUrl);
    Page<Video> SearchPageSimple(Long channelId, Long categoryId, Pageable pageable);
    Page<Video> SearchPageComplex(Long channelId, Long categoryId, Pageable pageable);
}
