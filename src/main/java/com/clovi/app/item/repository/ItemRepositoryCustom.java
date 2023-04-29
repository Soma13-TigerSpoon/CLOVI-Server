package com.clovi.app.item.repository;

import com.clovi.app.item.Item;
import com.clovi.app.itemInfo.ItemInfo;
import com.clovi.app.search.dto.request.SearchRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ItemRepositoryCustom {
    Page<Item> filterByConditions(SearchRequest searchRequest, Pageable pageable);
    List<Item> filterByKeyword(String searchKeyword);

    List<ItemInfo> searchByIdList(List<Long> ItemIdList);
    Page<ItemInfo> SearchPageSimple(Long postId, Pageable pageable);
    Page<ItemInfo> SearchPageComplex(Long postId, Pageable pageable);
}
