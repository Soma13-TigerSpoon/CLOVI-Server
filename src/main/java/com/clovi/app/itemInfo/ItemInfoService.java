package com.clovi.app.itemInfo;

import com.clovi.app.category.Category;
import com.clovi.app.category.repository.CategoryRepository;
import com.clovi.app.itemInfo.dto.response.ItemInfoResponse;
import com.clovi.exception.ResourceNotFoundException;
import com.clovi.exception.auth.NoPermissionDeleteException;
import com.clovi.exception.auth.NoPermissionUpdateException;
import com.clovi.app.itemInfo.dto.request.ItemInfoCreateRequest;
import com.clovi.app.itemInfo.dto.request.ItemInfoUpdateRequest;
import com.clovi.app.member.Member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemInfoService {
  private final ItemInfoRepository itemInfoRepository;
  private final CategoryRepository categoryRepository;


  public ItemInfoResponse getItemInfoById(Long itemInfoId) {
    ItemInfo itemInfo = itemInfoRepository.findByIdAndDeletedIsFalse(itemInfoId).orElseThrow(() -> new ResourceNotFoundException("아이템",itemInfoId));
    return ItemInfoResponse.from(itemInfo);
  }
  // 아래의 세 메소드 모두 유저 찾아서 권한 확인하는 부분 추가해야함.
  @Transactional
  public Long create(ItemInfoCreateRequest itemInfoCreateRequest, Member member){
    Long categoryId = itemInfoCreateRequest.getCategoryId();

    Long userId = member.getId();

    Category category = categoryRepository.findByIdAndDeletedIsFalse(categoryId).orElseThrow(() -> new ResourceNotFoundException("category",categoryId));

    ItemInfo newItemInfo = itemInfoCreateRequest.toEntity(category,userId);

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

}
