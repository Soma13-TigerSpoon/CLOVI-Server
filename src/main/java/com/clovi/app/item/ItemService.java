package com.clovi.app.item;

import com.clovi.exception.ResourceNotFoundException;
import com.clovi.exception.auth.NoPermissionDeleteException;
import com.clovi.exception.auth.NoPermissionUpdateException;
import com.clovi.app.item.dto.request.ItemCreateRequest;
import com.clovi.app.item.dto.request.ItemUpdateRequest;
import com.clovi.app.item.dto.response.ItemResponse;
import com.clovi.app.itemInfo.ItemInfo;
import com.clovi.app.member.Member;
import com.clovi.app.item.repository.ItemRepository;
import com.clovi.app.itemInfo.ItemInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {
  private final ItemInfoRepository itemInfoRepository;

  private final ItemRepository itemRepository;

  public ItemResponse findItemById(Long itemId) {

      Item item = itemRepository.findByIdAndDeletedIsFalse(itemId).orElseThrow(() -> new ResourceNotFoundException("item",itemId));
      return ItemResponse.from(item);

  }
  @Transactional
  public Long create(ItemCreateRequest itemCreateRequest, Member member) {
    Long itemInfoId = itemCreateRequest.getItemInfoId();
    Long userId = member.getId();
    ItemInfo findItemInfo = itemInfoRepository.findByIdAndDeletedIsFalse(itemInfoId).orElseThrow(() -> new ResourceNotFoundException("ItemInfo", itemInfoId));
    Item newItem = itemCreateRequest.toEntity(findItemInfo,userId);
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
