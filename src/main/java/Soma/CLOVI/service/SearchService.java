package Soma.CLOVI.service;

import Soma.CLOVI.domain.item.Item;
import Soma.CLOVI.domain.youtube.Video;
import Soma.CLOVI.dto.requests.SearchRequestDto;
import Soma.CLOVI.dto.response.KeywordResponseDto;
import Soma.CLOVI.dto.response.SearchResponseDto;
import Soma.CLOVI.repository.Item.ItemRepositoryCustomImpl;
import Soma.CLOVI.repository.Video.VideoRepositoryCustomImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

        SearchResponseDto result = new SearchResponseDto(itemList, videoList);
        return result;
    }

    public KeywordResponseDto getItemsAndVideosByKeyword(String searchKeyword) {
        List<Item> itemList = itemRepositoryCustomImpl.filterByKeyword(searchKeyword);
        List<Video> videoList = videoRepositoryCustomImpl.filterByKeyword(searchKeyword);

        KeywordResponseDto result = new KeywordResponseDto(itemList, videoList);
        return result;
    }
}
