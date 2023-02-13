package Soma.CLOVI.service.item;

import Soma.CLOVI.domain.category.Category;
import Soma.CLOVI.domain.item.Item;
import Soma.CLOVI.dto.requests.ItemCreateRequest;
import Soma.CLOVI.dto.requests.ItemDeleteRequest;
import Soma.CLOVI.dto.requests.ItemUpdateRequest;
import Soma.CLOVI.dto.response.ItemResponseDto;
import Soma.CLOVI.exception.ResourceNotFoundException;
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
  private final ItemRepositoryCustomImpl itemRepositoryCustomImpl;

  private final CategoryRepository categoryRepository;

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

  // 아래의 세 메소드 모두 유저 찾아서 권한 확인하는 부분 추가해야함.
  @Transactional
  public Long create(ItemCreateRequest itemCreateRequest, Long userId){
    Long categoryId = itemCreateRequest.getCategoryId();

    Category category = categoryRepository.findByIdAndIsDeletedIsFalse(categoryId).orElseThrow(() -> new ResourceNotFoundException("category",categoryId));

    Item newItem = new Item(itemCreateRequest,category,userId);

    Item saved = itemRepository.save(newItem);

    return saved.getId();
  }
  @Transactional
  public Long update(ItemUpdateRequest itemUpdateRequest, Long userId){
    Long itemId = itemUpdateRequest.getItemId();
    Long categoryId = itemUpdateRequest.getCategoryId();

    Item findItem = itemRepository.findByIdAndIsDeletedIsFalse(itemId).orElseThrow(() -> new ResourceNotFoundException("item", itemId));

    Category category = categoryRepository.findByIdAndIsDeletedIsFalse(categoryId).orElseThrow(() -> new ResourceNotFoundException("category",categoryId));

    findItem.update(itemUpdateRequest,category,userId);

    Item saved = itemRepository.save(findItem);

    return saved.getId();
  }

  @Transactional
  public void delete(ItemDeleteRequest itemDeleteRequest, Long userId){
    Long itemId = itemDeleteRequest.getItemId();

    Item findItem = itemRepository.findByIdAndIsDeletedIsFalse(itemId).orElseThrow(() -> new ResourceNotFoundException("item", itemId));

    findItem.delete();

    itemRepository.save(findItem);

  }

}
