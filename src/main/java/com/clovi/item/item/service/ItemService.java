package com.clovi.item.item.service;

import com.clovi.item.info.ItemInfo;
import com.clovi.item.item.Item;
import com.clovi.member.Member;
import com.clovi.item.item.dto.request.ItemCreateRequest;
import com.clovi.item.item.dto.request.ItemUpdateRequest;
import com.clovi.item.item.dto.response.ItemResponseDto;
import com.clovi.exception.ResourceNotFoundException;
import com.clovi.exception.auth.NoPermissionDeleteException;
import com.clovi.exception.auth.NoPermissionUpdateException;
import com.clovi.item.item.repository.ItemRepository;
import com.clovi.item.info.ItemInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {
  private final ItemInfoRepository itemInfoRepository;

  private final ItemRepository itemRepository;

  public ItemResponseDto findItemById(Long itemId) {

      Item item = itemRepository.findByIdAndDeletedIsFalse(itemId).orElseThrow(() -> new ResourceNotFoundException("item",itemId));

      Long itemInfoId = item.getItemInfo().getId();
      ItemInfo itemInfo = itemInfoRepository.findByIdAndDeletedIsFalse(itemInfoId).orElseThrow(() -> new ResourceNotFoundException("itemInfo",itemInfoId));

      return new ItemResponseDto(itemInfo,item);

  }
  @Transactional
  public Long create(ItemCreateRequest itemCreateRequest, Member member) {
    Long itemId = itemCreateRequest.getItemInfoId();
    Long userId = member.getId();
    ItemInfo findItemInfo = itemInfoRepository.findByIdAndDeletedIsFalse(itemId).orElseThrow(() -> new ResourceNotFoundException("Item", itemId));
    Item newItem = new Item(itemCreateRequest, findItemInfo,userId);
    Item saved = itemRepository.save(newItem);
    return saved.getId();
  }

  @Transactional
  public Long update(ItemUpdateRequest itemUpdateRequest, Long itemId, Member member) {
   Long userId = member.getId();
   Item findItem = itemRepository.findByIdAndDeletedIsFalse(itemId).orElseThrow(() -> new ResourceNotFoundException("item", itemId));
   if(findItem.isNotCreatedBy(userId)){
     throw new NoPermissionUpdateException();
   }
   findItem.update(itemUpdateRequest,userId);
   Item saved = itemRepository.save(findItem);
   return saved.getId();
  }
  @Transactional
  public void delete(Long itemId, Member member) {
    Long userId = member.getId();
    Item findItem = itemRepository.findByIdAndDeletedIsFalse(itemId).orElseThrow(() -> new ResourceNotFoundException("item", itemId));
    if(findItem.isNotCreatedBy(userId)){
      throw new NoPermissionDeleteException();
    }
    findItem.delete();
    itemRepository.save(findItem);
  }

}
