package com.clovi.service.item;

import com.clovi.domain.item.Item;
import com.clovi.domain.item.ItemDetail;
import com.clovi.dto.requests.item.detail.ItemDetailCreateRequest;
import com.clovi.dto.requests.item.detail.ItemDetailDeleteRequest;
import com.clovi.dto.requests.item.detail.ItemDetailUpdateRequest;
import com.clovi.exception.ResourceNotFoundException;
import com.clovi.exception.auth.NoPermissionDeleteException;
import com.clovi.exception.auth.NoPermissionUpdateException;
import com.clovi.repository.Item.ItemDetailRepository;
import com.clovi.repository.Item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemDetailService {
  private final ItemRepository itemRepository;

  private ItemDetailRepository itemDetailRepository;

  @Transactional
  public Long create(ItemDetailCreateRequest itemDetailCreateRequest, Long userId) {
    Long itemId = itemDetailCreateRequest.getItemId();
    Item findItem = itemRepository.findByIdAndIsDeletedIsFalse(itemId).orElseThrow(() -> new ResourceNotFoundException("item", itemId));
    ItemDetail newItemDetail = new ItemDetail(itemDetailCreateRequest,findItem,userId);
    ItemDetail saved = itemDetailRepository.save(newItemDetail);
    return saved.getId();
  }

  @Transactional
  public Long update(ItemDetailUpdateRequest itemDetailUpdateRequest, Long userId) {
   Long itemDetailId = itemDetailUpdateRequest.getItemDetailId();
   ItemDetail findItemDetail = itemDetailRepository.findByIdAndIsDeletedIsFalse(itemDetailId).orElseThrow(() -> new ResourceNotFoundException("itemDetail", itemDetailId));
   if(findItemDetail.getCreateBy() != userId){
     throw new NoPermissionUpdateException();
   }
   findItemDetail.update(itemDetailUpdateRequest,userId);
   ItemDetail saved = itemDetailRepository.save(findItemDetail);
   return saved.getId();
  }
  @Transactional
  public void delete(ItemDetailDeleteRequest itemDetailDeleteRequest, Long userId) {
    Long itemDetailId = itemDetailDeleteRequest.getItemDetailId();
    ItemDetail findItemDetail = itemDetailRepository.findByIdAndIsDeletedIsFalse(itemDetailId).orElseThrow(() -> new ResourceNotFoundException("itemDetail", itemDetailId));
    if(findItemDetail.getCreateBy() != userId){
      throw new NoPermissionDeleteException();
    }
    findItemDetail.delete();
    itemDetailRepository.save(findItemDetail);
  }

}
