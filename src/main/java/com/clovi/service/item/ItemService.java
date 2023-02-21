package com.clovi.service.item;

import com.clovi.domain.category.Category;
import com.clovi.domain.item.Item;
import com.clovi.domain.item.ItemColor;
import com.clovi.domain.item.ItemSize;
import com.clovi.dto.requests.item.ItemCreateRequest;
import com.clovi.dto.requests.item.ItemDeleteRequest;
import com.clovi.dto.requests.item.ItemUpdateRequest;
import com.clovi.dto.response.ItemResponseDto;
import com.clovi.exception.ResourceNotFoundException;
import com.clovi.repository.Category.CategoryRepository;
import com.clovi.repository.Item.ItemRepository;
import com.clovi.repository.Item.ItemRepositoryCustomImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.clovi.repository.ItemColorRepository;
import com.clovi.repository.ItemSizeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {
  private final ItemRepository itemRepository;
  private final ItemColorRepository itemColorRepository;
  private final ItemSizeRepository itemSizeRepository;
  private final ItemRepositoryCustomImpl itemRepositoryCustomImpl;

  private final CategoryRepository categoryRepository;

  // Used by ShopItem Service (Modification required!)
  public Item getById(Long itemId) {
    Item item = itemRepository.findById(itemId).orElseThrow(() -> new ResourceNotFoundException("아이템",itemId));
    return item;
  }

  // Used by ItemController
  public ItemResponseDto getItemById(Long itemId) {
    Item item = itemRepository.findById(itemId).orElseThrow(() -> new ResourceNotFoundException("아이템",itemId));
    return new ItemResponseDto(item,getColorListByItemId(itemId),getSizeListByItemId(itemId));
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

  public List<String> getColorListByItemId(Long itemId){
    List<ItemColor> itemColors = itemColorRepository.findAllByItemId(itemId);
    List<String> result = new ArrayList<>();
    for(ItemColor itemColor : itemColors){
      result.add(itemColor.getColor().getName());
    }
    return result;
  }
  public List<String> getSizeListByItemId(Long itemId){
    List<ItemSize> itemSizes = itemSizeRepository.findAllByItemId(itemId);
    List<String> result = new ArrayList<>();
    for(ItemSize itemSize : itemSizes){
      result.add(itemSize.getSize().getName());
    }
    return result;
  }

}
