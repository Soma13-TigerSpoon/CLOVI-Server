package Soma.CLOVI.service;

import Soma.CLOVI.domain.item.Item;
import Soma.CLOVI.dto.response.ItemResponseDto;
import Soma.CLOVI.repository.Item.ItemRepository;
import Soma.CLOVI.repository.Item.ItemRepositoryCustomImpl;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {
  private final ItemRepository itemRepository;
  private final ItemRepositoryCustomImpl itemRepositoryCustomImpl;

  public List<ItemResponseDto> getItems(List<Long> itemIdList) {
    return itemRepositoryCustomImpl.searchByIdList(itemIdList).stream().
        map(item -> new ItemResponseDto(item)).collect(Collectors.toList());
  }

  public Item getById(Long itemId) {
    return itemRepository.findById(itemId).orElseThrow(
        () -> new IllegalArgumentException("해당 Id를 가진 아이템이 없습니다. id=" + itemId));
  }
}
