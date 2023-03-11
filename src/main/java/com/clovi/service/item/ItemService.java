package com.clovi.service.item;

import com.clovi.domain.item.Item;
import com.clovi.domain.item.ItemInfo;
import com.clovi.dto.requests.item.detail.ItemCreateRequest;
import com.clovi.dto.requests.item.detail.ItemDeleteRequest;
import com.clovi.dto.requests.item.detail.ItemUpdateRequest;
import com.clovi.exception.ResourceNotFoundException;
import com.clovi.exception.auth.NoPermissionDeleteException;
import com.clovi.exception.auth.NoPermissionUpdateException;
import com.clovi.repository.Item.ItemRepository;
import com.clovi.repository.Item.ItemInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {
  private final ItemInfoRepository itemInfoRepository;

  private final ItemRepository itemRepository;

  @Transactional
  public Long create(ItemCreateRequest itemCreateRequest, Long userId) {
    Long itemId = itemCreateRequest.getItemId();
    ItemInfo findItemInfo = itemInfoRepository.findByIdAndDeletedIsFalse(itemId).orElseThrow(() -> new ResourceNotFoundException("Item", itemId));
    Item newItem = new Item(itemCreateRequest, findItemInfo,userId);
    Item saved = itemRepository.save(newItem);
    return saved.getId();
  }

  @Transactional
  public Long update(ItemUpdateRequest itemUpdateRequest, Long userId) {
   Long itemId = itemUpdateRequest.getItemId();
   Item findItem = itemRepository.findByIdAndDeletedIsFalse(itemId).orElseThrow(() -> new ResourceNotFoundException("item", itemId));
   if(findItem.getCreateBy() != userId){
     throw new NoPermissionUpdateException();
   }
   findItem.update(itemUpdateRequest,userId);
   Item saved = itemRepository.save(findItem);
   return saved.getId();
  }
  @Transactional
  public void delete(ItemDeleteRequest itemDeleteRequest, Long userId) {
    Long itemId = itemDeleteRequest.getItemId();
    Item findItem = itemRepository.findByIdAndDeletedIsFalse(itemId).orElseThrow(() -> new ResourceNotFoundException("item", itemId));
    if(findItem.getCreateBy() != userId){
      throw new NoPermissionDeleteException();
    }
    findItem.delete();
    itemRepository.save(findItem);
  }

}
