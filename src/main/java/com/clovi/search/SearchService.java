package com.clovi.search;

import com.clovi.item.item.Item;
import com.clovi.video.Video;
import com.clovi.search.dto.request.SearchRequestDto;
import com.clovi.search.dto.response.KeywordResponseDto;
import com.clovi.search.dto.response.SearchResponseDto;
import com.clovi.item.item.repository.ItemRepositoryCustomImpl;
import com.clovi.video.repository.VideoRepositoryCustomImpl;
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
