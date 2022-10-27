package Soma.CLOVI.service;

import Soma.CLOVI.domain.item.Item;
import Soma.CLOVI.dto.response.ItemResponseDto;
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
}
