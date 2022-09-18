package Soma.CLOVI.service;

import Soma.CLOVI.domain.ManyToMany.ShopItem;
import Soma.CLOVI.domain.item.Item;
import Soma.CLOVI.domain.shop.Shop;
import Soma.CLOVI.dto.requests.ShopItemRequestDto;
import Soma.CLOVI.repository.Item.ItemRepository;
import Soma.CLOVI.repository.ShopItemRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ShopItemService {

  private final ShopService shopService;
  private final ItemService itemService;
  private final ItemRepository itemRepository;
  private final ShopItemRepository shopItemRepository;
  @Transactional
  public Long save(ShopItemRequestDto shopItemRequestDto) {
//    if(isExistUrl(shopItemRequestDto.getShopUrl())){
//      throw new IllegalArgumentException("이미 존재하는 판매처 입니다.");
//    }
    System.out.println(shopItemRequestDto);

    Item item = itemService.getById(shopItemRequestDto.getItemId());
    Shop shop = shopService.getByHostname(shopItemRequestDto.getHostname());

    ShopItem shopItem = new ShopItem(shopItemRequestDto,item,shop);
    shopItemRepository.save(shopItem);

    return shopItem.getId();
  }

  public boolean isExistUrl(String shopUrl){
    Optional<ShopItem> shopItem = shopItemRepository.findByShopUrl(shopUrl);
    if(shopItem.isPresent()){
      return true;
    }
    return false;
  }
}
