package Soma.CLOVI.service.item;

import Soma.CLOVI.domain.ManyToMany.ShopItem;
import Soma.CLOVI.domain.item.Item;
import Soma.CLOVI.domain.shop.Shop;
import Soma.CLOVI.dto.requests.ShopItemCreateRequest;
import Soma.CLOVI.dto.requests.ShopItemDeleteRequest;
import Soma.CLOVI.dto.requests.ShopItemRequestDto;
import Soma.CLOVI.exception.ResourceNotFoundException;
import Soma.CLOVI.repository.Item.ItemRepository;
import Soma.CLOVI.repository.ShopItemRepository;
import Soma.CLOVI.repository.ShopRepository;
import Soma.CLOVI.service.ShopService;
import Soma.CLOVI.service.item.ItemService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ShopItemService {

  private final ShopRepository shopRepository;
  private final ItemRepository itemRepository;
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

    Item findItem = itemRepository.findByIdAndIsDeletedIsFalse(itemId).orElseThrow(() -> new ResourceNotFoundException("item", itemId));

    Shop findShop = shopRepository.findByHostnameAndIsDeletedIsFalse(hostname).orElse(
        new Shop(hostname)
    );

    ShopItem newShopItem = new ShopItem(shopItemCreateRequest,findItem,findShop);

    ShopItem saved = shopItemRepository.save(newShopItem);

    return saved.getId();
  }

  @Transactional
  public void delete(ShopItemDeleteRequest shopItemDeleteRequest, Long userId){

    Long shopItemId = shopItemDeleteRequest.getShopItemId();

    ShopItem findShopItem = shopItemRepository.findByIdAndIsDeletedIsFalse(shopItemId).orElseThrow(() -> new ResourceNotFoundException("shopItem",shopItemId));

    findShopItem.delete();

    shopItemRepository.save(findShopItem);
  }
}
