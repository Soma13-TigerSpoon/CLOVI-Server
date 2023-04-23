package com.clovi.timeShopItem;

import com.clovi.exception.DuplicateResourceException;
import com.clovi.exception.ResourceNotFoundException;
import com.clovi.item.item.Item;
import com.clovi.item.item.repository.ItemRepository;
import com.clovi.item.shopItem.ShopItem;
import com.clovi.item.shopItem.ShopItemRepository;
import com.clovi.member.Member;
import com.clovi.timeframe.TimeFrame;
import com.clovi.timeframe.repository.TimeFrameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TimeShopItemService {

    private final TimeShopItemRepository timeShopItemRepository;

    private final TimeFrameRepository timeFrameRepository;
    private final ItemRepository itemRepository;
    private final ShopItemRepository shopItemRepository;

    @Transactional
    public Long create(Member member, Long timeFrameId, Long itemId, Long shopItemId) {
        if(timeShopItemRepository.existsByTimeIdAndItemIdAndShopItemIdAndDeletedIsFalse(timeFrameId,itemId,shopItemId)){
            throw new DuplicateResourceException("timeShopItem");
        }
        TimeFrame timeFrame = timeFrameRepository.findByIdAndDeletedIsFalse(timeFrameId).orElseThrow(()-> new ResourceNotFoundException("timeframe",timeFrameId));
        Item item = itemRepository.findByIdAndDeletedIsFalse(itemId).orElseThrow(()-> new ResourceNotFoundException("item",itemId));
        ShopItem shopItem = shopItemRepository.findByIdAndDeletedIsFalse(shopItemId).orElseThrow(()-> new ResourceNotFoundException("shopItem",shopItemId));
        TimeShopItem timeShopItem = new TimeShopItem(timeFrame,item,shopItem,member.getId());
        TimeShopItem saved = timeShopItemRepository.save(timeShopItem);
        return saved.getId();
    }
}
