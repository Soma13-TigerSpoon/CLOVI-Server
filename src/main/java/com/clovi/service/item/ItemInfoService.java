package com.clovi.service.item;

import com.clovi.domain.category.Category;
import com.clovi.domain.item.Item;
import com.clovi.domain.item.ItemInfo;
import com.clovi.domain.item.ItemColor;
import com.clovi.domain.item.ItemSize;
import com.clovi.dto.requests.item.ItemInfoCreateRequest;
import com.clovi.dto.requests.item.ItemInfoDeleteRequest;
import com.clovi.dto.requests.item.ItemInfoUpdateRequest;
import com.clovi.dto.response.ItemResponseDto;
import com.clovi.exception.ResourceNotFoundException;
import com.clovi.repository.Category.CategoryRepository;
import com.clovi.repository.Item.ItemRepository;
import com.clovi.repository.Item.ItemInfoRepository;
import com.clovi.repository.Item.ItemRepositoryCustomImpl;

import java.util.ArrayList;
import java.util.List;

import com.clovi.repository.ItemColorRepository;
import com.clovi.repository.ItemSizeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemInfoService {
  private final ItemInfoRepository itemInfoRepository;
  private final ItemColorRepository itemColorRepository;
  private final ItemSizeRepository itemSizeRepository;

  private final ItemRepository itemRepository;

  private final ItemRepositoryCustomImpl itemRepositoryCustomImpl;

  private final CategoryRepository categoryRepository;

  // Used by ShopItem Service (Modification required!)
  public ItemInfo getById(Long itemId) {
    ItemInfo itemInfo = itemInfoRepository.findById(itemId).orElseThrow(() -> new ResourceNotFoundException("아이템",itemId));
    return itemInfo;
  }

  // Used by ItemController
  public ItemResponseDto getItemById(Long itemId) {
    ItemInfo itemInfo = itemInfoRepository.findById(itemId).orElseThrow(() -> new ResourceNotFoundException("아이템",itemId));
    List<Item> item = itemRepository.findAllByItemInfoIdAndDeletedIsFalse(itemInfo.getId());
    return new ItemResponseDto(itemInfo,item.get(0));
  }

  // 아래의 세 메소드 모두 유저 찾아서 권한 확인하는 부분 추가해야함.
  @Transactional
  public Long create(ItemInfoCreateRequest itemInfoCreateRequest, Long userId){
    Long categoryId = itemInfoCreateRequest.getCategoryId();

    Category category = categoryRepository.findByIdAndDeletedIsFalse(categoryId).orElseThrow(() -> new ResourceNotFoundException("category",categoryId));

    ItemInfo newItemInfo = new ItemInfo(itemInfoCreateRequest,category,userId);

    ItemInfo saved = itemInfoRepository.save(newItemInfo);

    return saved.getId();
  }
  @Transactional
  public Long update(ItemInfoUpdateRequest itemInfoUpdateRequest, Long userId){
    Long itemId = itemInfoUpdateRequest.getItemId();
    Long categoryId = itemInfoUpdateRequest.getCategoryId();

    ItemInfo findItemInfo = itemInfoRepository.findByIdAndDeletedIsFalse(itemId).orElseThrow(() -> new ResourceNotFoundException("Item", itemId));

    Category category = categoryRepository.findByIdAndDeletedIsFalse(categoryId).orElseThrow(() -> new ResourceNotFoundException("category",categoryId));

    findItemInfo.update(itemInfoUpdateRequest,category,userId);

    ItemInfo saved = itemInfoRepository.save(findItemInfo);

    return saved.getId();
  }

  @Transactional
  public void delete(ItemInfoDeleteRequest itemInfoDeleteRequest, Long userId){
    Long itemId = itemInfoDeleteRequest.getItemId();

    ItemInfo findItemInfo = itemInfoRepository.findByIdAndDeletedIsFalse(itemId).orElseThrow(() -> new ResourceNotFoundException("Item", itemId));

    findItemInfo.delete();

    itemInfoRepository.save(findItemInfo);

  }

  public List<String> getColorListByItemId(Long itemInfoId){
    List<ItemColor> itemColors = itemColorRepository.findAllByItemInfoId(itemInfoId);
    List<String> result = new ArrayList<>();
    for(ItemColor itemColor : itemColors){
      result.add(itemColor.getColor().getName());
    }
    return result;
  }
  public List<String> getSizeListByItemId(Long itemInfoId){
    List<ItemSize> itemSizes = itemSizeRepository.findAllByItemInfoId(itemInfoId);
    List<String> result = new ArrayList<>();
    for(ItemSize itemSize : itemSizes){
      result.add(itemSize.getSize().getName());
    }
    return result;
  }

}
