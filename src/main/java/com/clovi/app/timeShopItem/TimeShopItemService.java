package com.clovi.app.timeShopItem;

import com.clovi.exception.DuplicateResourceException;
import com.clovi.exception.ResourceNotFoundException;
import com.clovi.app.item.Item;
import com.clovi.app.item.repository.ItemRepository;
import com.clovi.app.shopItem.ShopItem;
import com.clovi.app.shopItem.ShopItemRepository;
import com.clovi.app.timeframe.Timeframe;
import com.clovi.app.member.Member;
import com.clovi.app.timeframe.repository.TimeframeRepository;
import com.clovi.exception.auth.NoPermissionDeleteException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TimeShopItemService {
    private final TimeShopItemRepository timeShopItemRepository;

    private final TimeframeRepository timeframeRepository;
    private final ItemRepository itemRepository;
    private final ShopItemRepository shopItemRepository;

    @Transactional
    public Long createTimeShopItem(Long timeframeId, Long itemId, Long shopItemId, Member member) {
        if(timeShopItemRepository.existsByTimeIdAndItemIdAndShopItemIdAndDeletedIsFalse(timeframeId, itemId, shopItemId)) {
            throw new DuplicateResourceException("timeShopItem");
        }

        Timeframe timeframe = timeframeRepository.findByIdAndDeletedIsFalse(timeframeId)
                .orElseThrow(() -> new ResourceNotFoundException("timeframe", timeframeId));
        Item item = itemRepository.findByIdAndDeletedIsFalse(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("item", itemId));
        ShopItem shopItem = shopItemRepository.findByIdAndDeletedIsFalse(shopItemId)
                .orElseThrow(() -> new ResourceNotFoundException("shopItem", shopItemId));

        TimeShopItem saved = timeShopItemRepository.save(
                new TimeShopItem(timeframe, item, shopItem, member.getId())
        );
        return saved.getId();
    }

    @Transactional
    public void deleteTimeShopItem(Long timeframeId, Long itemId, Long shopItemId, Member member) {
        timeframeRepository.findByIdAndDeletedIsFalse(timeframeId)
                .orElseThrow(() -> new ResourceNotFoundException("timeframe", timeframeId));
        itemRepository.findByIdAndDeletedIsFalse(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("item", itemId));
        shopItemRepository.findByIdAndDeletedIsFalse(shopItemId)
                .orElseThrow(() -> new ResourceNotFoundException("shopItem", shopItemId));

        TimeShopItem timeShopItem = timeShopItemRepository.findByTimeIdAndItemIdAndShopItemIdAndDeletedIsFalse(timeframeId, itemId, shopItemId)
                .orElseThrow(() -> new ResourceNotFoundException("timeShopItem", ""));

        if(timeShopItem.isNotCreatedBy(member.getId())) {
            throw new NoPermissionDeleteException();
        }

        timeShopItem.delete();
        timeShopItemRepository.save(timeShopItem);
    }
}