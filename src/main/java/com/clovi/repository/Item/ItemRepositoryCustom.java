package com.clovi.repository.Item;

import com.clovi.domain.item.Item;
import com.clovi.domain.item.ItemInfo;
import com.clovi.dto.requests.SearchRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ItemRepositoryCustom {
    Page<Item> filterByConditions(SearchRequestDto searchRequestDto, Pageable pageable);
    List<Item> filterByKeyword(String searchKeyword);

    List<ItemInfo> searchByIdList(List<Long> ItemIdList);
    Page<ItemInfo> SearchPageSimple(Long postId, Pageable pageable);
    Page<ItemInfo> SearchPageComplex(Long postId, Pageable pageable);
}
