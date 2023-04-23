package com.clovi.service.item;

import com.clovi.domain.category.Category;
import com.clovi.domain.item.Item;
import com.clovi.domain.item.ItemInfo;
import com.clovi.domain.item.ItemColor;
import com.clovi.domain.item.ItemSize;
import com.clovi.domain.user.Member;
import com.clovi.dto.requests.item.ItemInfoCreateRequest;
import com.clovi.dto.requests.item.ItemInfoUpdateRequest;
import com.clovi.dto.response.ItemResponseDto;
import com.clovi.exception.ResourceNotFoundException;
import com.clovi.exception.auth.NoPermissionDeleteException;
import com.clovi.exception.auth.NoPermissionUpdateException;
import com.clovi.repository.Category.CategoryRepository;
import com.clovi.repository.Item.ItemRepository;
import com.clovi.repository.Item.ItemInfoRepository;
import com.clovi.repository.Item.ItemRepositoryCustomImpl;

import java.util.ArrayList;
import java.util.List;

import com.clovi.repository.Item.ItemColorRepository;
import com.clovi.repository.Item.ItemSizeRepository;
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
  public ItemResponseDto getItemByIdV1(Long itemInfoId) {
    ItemInfo itemInfo = itemInfoRepository.findByIdAndDeletedIsFalse(itemInfoId).orElseThrow(() -> new ResourceNotFoundException("아이템",itemInfoId));
    List<Item> item = itemRepository.findAllByItemInfoIdAndDeletedIsFalse(itemInfo.getId());
    if(item.size() == 0 ){
      return new ItemResponseDto(itemInfo);
    }
    return new ItemResponseDto(itemInfo,item.get(0));
  }
  //Item 페이지에서 color 랑 size 다른것들 보여줄때 사용할 수 도 있을 거 같음 .
  public ItemResponseDto getItemByIdV2(Long itemInfoId) {
    ItemInfo itemInfo = itemInfoRepository.findById(itemInfoId).orElseThrow(() -> new ResourceNotFoundException("아이템",itemInfoId));
    List<Item> item = itemRepository.findAllByItemInfoIdAndDeletedIsFalse(itemInfo.getId());
    return new ItemResponseDto(itemInfo,item);
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
  public Long update(ItemInfoUpdateRequest itemInfoUpdateRequest, Long itemInfoId,  Member member){
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
