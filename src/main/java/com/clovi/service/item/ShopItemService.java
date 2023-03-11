package com.clovi.service.item;

import com.clovi.domain.ManyToMany.ShopItem;
import com.clovi.domain.item.ItemInfo;
import com.clovi.domain.shop.Shop;
import com.clovi.dto.requests.ShopItemCreateRequest;
import com.clovi.dto.requests.ShopItemDeleteRequest;
import com.clovi.exception.ResourceNotFoundException;
import com.clovi.repository.Item.ItemInfoRepository;
import com.clovi.repository.ShopItemRepository;
import com.clovi.repository.ShopRepository;

import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ShopItemService {

  private final ShopRepository shopRepository;
  private final ItemInfoRepository itemInfoRepository;
  private final ShopItemRepository shopItemRepository;

  public boolean isExistUrl(String shopItemUrl){
    Optional<ShopItem> shopItem = shopItemRepository.findByShopItemUrl(shopItemUrl);
    if(shopItem.isPresent()){
      return true;
    }
    return false;
  }
  // 밑의 두 메서드도 마찬가지로 유저가 삭제하거나 생성할 권한이 있는지 체크해줘야함.
  @Transactional
  public Long create(ShopItemCreateRequest shopItemCreateRequest, Long userId){
    String hostname = shopItemCreateRequest.getHostname();
    Long itemId = shopItemCreateRequest.getItemId();

    ItemInfo findItemInfo = itemInfoRepository.findByIdAndDeletedIsFalse(itemId).orElseThrow(() -> new ResourceNotFoundException("Item", itemId));

    Shop findShop = shopRepository.findByHostnameAndDeletedIsFalse(hostname).orElse(
        new Shop(hostname)
    );

    ShopItem newShopItem = new ShopItem(shopItemCreateRequest, findItemInfo,findShop);

    ShopItem saved = shopItemRepository.save(newShopItem);

    return saved.getId();
  }

  @Transactional
  public void delete(ShopItemDeleteRequest shopItemDeleteRequest, Long userId){

    Long shopItemId = shopItemDeleteRequest.getShopItemId();

    ShopItem findShopItem = shopItemRepository.findByIdAndDeletedIsFalse(shopItemId).orElseThrow(() -> new ResourceNotFoundException("shopItem",shopItemId));

    findShopItem.delete();

    shopItemRepository.save(findShopItem);
  }
}
