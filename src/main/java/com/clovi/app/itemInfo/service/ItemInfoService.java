package com.clovi.app.itemInfo.service;

import com.clovi.app.category.domain.Category;
import com.clovi.app.category.repository.CategoryRepository;
import com.clovi.app.color.domain.ItemColor;
import com.clovi.app.color.repository.ItemColorRepository;
import com.clovi.app.itemInfo.repository.ItemInfoRepository;
import com.clovi.app.itemInfo.domain.ItemInfo;
import com.clovi.app.itemInfo.dto.request.ItemInfoCreateRequest;
import com.clovi.app.itemInfo.dto.request.ItemInfoUpdateRequest;
import com.clovi.app.item.domain.Item;
import com.clovi.app.item.dto.response.ItemResponse;
import com.clovi.app.item.repository.ItemRepository;
import com.clovi.app.item.repository.ItemRepositoryCustomImpl;
import com.clovi.app.size.domain.ItemSize;
import com.clovi.app.size.repository.ItemSizeRepository;
import com.clovi.app.member.domain.Member;
import com.clovi.exception.ResourceNotFoundException;
import com.clovi.exception.auth.NoPermissionDeleteException;
import com.clovi.exception.auth.NoPermissionUpdateException;

import java.util.ArrayList;
import java.util.List;

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

  // Used by ItemInfoController
  public ItemResponse getItemByIdV1(Long itemInfoId) {
    ItemInfo itemInfo = itemInfoRepository.findByIdAndDeletedIsFalse(itemInfoId).orElseThrow(() -> new ResourceNotFoundException("아이템",itemInfoId));
    List<Item> item = itemRepository.findAllByItemInfoIdAndDeletedIsFalse(itemInfo.getId());
    if(item.size() == 0 ){
      return new ItemResponse(itemInfo);
    }
    return new ItemResponse(itemInfo,item.get(0));
  }
  //Item 페이지에서 color 랑 size 다른것들 보여줄때 사용할 수 도 있을 거 같음 .
  public ItemResponse getItemByIdV2(Long itemInfoId) {
    ItemInfo itemInfo = itemInfoRepository.findById(itemInfoId).orElseThrow(() -> new ResourceNotFoundException("아이템",itemInfoId));
    List<Item> item = itemRepository.findAllByItemInfoIdAndDeletedIsFalse(itemInfo.getId());
    return new ItemResponse(itemInfo,item);
  }
  // 아래의 세 메소드 모두 유저 찾아서 권한 확인하는 부분 추가해야함.
  @Transactional
  public Long create(ItemInfoCreateRequest itemInfoCreateRequest, Member member){
    Long categoryId = itemInfoCreateRequest.getCategoryId();

    Long userId = member.getId();

    Category category = categoryRepository.findByIdAndDeletedIsFalse(categoryId).orElseThrow(() -> new ResourceNotFoundException("category",categoryId));

    ItemInfo newItemInfo = new ItemInfo(itemInfoCreateRequest,category,userId);

    ItemInfo saved = itemInfoRepository.save(newItemInfo);

    return saved.getId();
  }
  @Transactional
  public Long update(ItemInfoUpdateRequest itemInfoUpdateRequest, Long itemInfoId, Member member){
    Long categoryId = itemInfoUpdateRequest.getCategoryId();
    Long userId = member.getId();


    ItemInfo findItemInfo = itemInfoRepository.findByIdAndDeletedIsFalse(itemInfoId).orElseThrow(() -> new ResourceNotFoundException("Item", itemInfoId));

    if(findItemInfo.isNotCreatedBy(userId)){
      throw new NoPermissionUpdateException();
    }

    Category category = categoryRepository.findByIdAndDeletedIsFalse(categoryId).orElseThrow(() -> new ResourceNotFoundException("category",categoryId));

    findItemInfo.update(itemInfoUpdateRequest,category,userId);

    ItemInfo saved = itemInfoRepository.save(findItemInfo);

    return saved.getId();
  }

  @Transactional
  public void delete(Long itemInfoId,  Member member){
    Long userId = member.getId();


    ItemInfo findItemInfo = itemInfoRepository.findByIdAndDeletedIsFalse(itemInfoId).orElseThrow(() -> new ResourceNotFoundException("Item", itemInfoId));

    if(findItemInfo.isNotCreatedBy(userId)){
      throw new NoPermissionDeleteException();
    }

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
