package com.clovi.service;

import com.clovi.domain.item.Item;
import com.clovi.domain.youtube.Video;
import com.clovi.dto.requests.SearchRequestDto;
import com.clovi.dto.response.KeywordResponseDto;
import com.clovi.dto.response.SearchResponseDto;
import com.clovi.repository.Item.ItemRepositoryCustomImpl;
import com.clovi.repository.Video.VideoRepositoryCustomImpl;
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

    public SearchResponseDto getItemsAndVideosByFilter(SearchRequestDto searchRequestDto, Pageable pageable) {
        Page<Item> itemList = itemRepositoryCustomImpl.filterByConditions(searchRequestDto, pageable);
        Page<Video> videoList = videoRepositoryCustomImpl.filterByConditions(searchRequestDto, pageable);

        return new SearchResponseDto(itemList, videoList);
    }

    public KeywordResponseDto getItemsAndVideosByKeyword(String searchKeyword) {
        List<Item> itemList = itemRepositoryCustomImpl.filterByKeyword(searchKeyword);
        List<Video> videoList = videoRepositoryCustomImpl.filterByKeyword(searchKeyword);

        return new KeywordResponseDto(itemList, videoList);
    }
}
