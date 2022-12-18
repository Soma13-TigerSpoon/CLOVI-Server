package Soma.CLOVI.service;

import Soma.CLOVI.api.response.MessageCode;
import Soma.CLOVI.domain.item.Item;
import Soma.CLOVI.dto.requests.ItemRequestDto;
import Soma.CLOVI.dto.response.ItemResponseDto;
import Soma.CLOVI.repository.Category.CategoryRepository;
import Soma.CLOVI.repository.Item.ItemRepository;
import Soma.CLOVI.repository.Item.ItemRepositoryCustomImpl;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {
  private final ItemRepository itemRepository;
  private final CategoryRepository categoryRepository;
  private final ItemRepositoryCustomImpl itemRepositoryCustomImpl;

  // Used by ShopItem Service (Modification required!)
  public Item getById(Long itemId) {
    Optional<Item> item = itemRepository.findById(itemId);
    String errorMessage = "No item found having id=" + itemId + "!";

    return item.orElseThrow(() -> new IllegalArgumentException(errorMessage));
  }

  // Used by ItemController
  public ItemResponseDto getItemById(Long itemId) {
    Optional<Item> item = itemRepository.findById(itemId);

    return item.map(ItemResponseDto::new).orElse(null);
  }

  // Used by ItemController
  public List<ItemResponseDto> getItemsByIdList(List<Long> itemIdList) {
    List<Item> itemList = itemRepositoryCustomImpl.searchByIdList(itemIdList);

    return itemList.stream().map(ItemResponseDto::new).collect(Collectors.toList());
  }

  @Transactional
  public Long save(ItemRequestDto itemRequestDto){

    //부모 아이템 조회 --> null 이 아닌 경우에
    Item parentItem;
    try{
      parentItem = itemRequestDto.getParentId() < 0 ? null : itemRepository.findById(itemRequestDto.getParentId()).orElseThrow(
          () -> new IllegalArgumentException(MessageCode.ERROR_REQ_PARAM_ITEM_ID.getMessage())
      ) ;
    }catch (Exception e){
      parentItem = null;
      System.out.println(e);
    }


    // 상품은 이름과 색깔, 브랜드로 있는지 파악 후 없으면 카테고리를 조회 해서 새로운 아이템으로 저장
    Item item = itemRepository.findByNameAndColorAndBrand(itemRequestDto.getName(),
        itemRequestDto.getColor(), itemRequestDto.getBrand()).orElse(
        new Item(itemRequestDto,categoryRepository.findById(itemRequestDto.getCategoryId()).orElseThrow(
            () -> new IllegalArgumentException(MessageCode.ERROR_REQ_PARAM_CATEGORY_ID.getMessage())
        ),parentItem)
    );

    itemRepository.save(item);
    return item.getId();
  }
}
