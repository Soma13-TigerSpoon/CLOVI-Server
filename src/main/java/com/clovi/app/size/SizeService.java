package com.clovi.app.size;

import com.clovi.app.size.domain.ItemSize;
import com.clovi.app.size.domain.Size;
import com.clovi.app.size.dto.request.ItemSizeCreateRequest;
import com.clovi.app.size.repository.ItemSizeRepository;
import com.clovi.app.size.repository.SizeRepository;
import com.clovi.app.member.Member;
import com.clovi.app.itemInfo.ItemInfo;
import com.clovi.exception.ResourceNotFoundException;
import com.clovi.app.itemInfo.ItemInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SizeService {
    private final ItemInfoRepository itemInfoRepository;
    private final SizeRepository sizeRepository;
    private final ItemSizeRepository itemSizeRepository;

    public Long create(ItemSizeCreateRequest itemSizeCreateRequest, Member member, Long itemInfoId) {
        ItemInfo itemInfo = itemInfoRepository.findByIdAndDeletedIsFalse(itemInfoId).orElseThrow(() -> new ResourceNotFoundException("ItemInfo",itemInfoId));
        String sizeName = itemSizeCreateRequest.getSize();
        return save(sizeName,itemInfo.getId(),member.getId());
    }

    public List<String> findAllSize(Long itemInfoId) {
        return itemSizeRepository.findAllByItemInfoId(itemInfoId).stream().map(ItemSize::getSizeName).collect(Collectors.toList());
    }

    @Transactional
    public Long save(String sizeName,Long itemInfoId,Long userId){
        sizeName = sizeName.trim();
        Size size = sizeRepository.existsByNameAndDeletedIsFalse(sizeName) ?
                sizeRepository.findByNameAndDeletedIsFalse(sizeName).get():sizeRepository.save(new Size(sizeName));
        ItemSize saved = itemSizeRepository.existsByItemInfoIdAndSizeIdAndDeletedIsFalse(itemInfoId,size.getId()) ?
                itemSizeRepository.findByItemInfoIdAndSizeIdAndDeletedIsFalse(itemInfoId, size.getId()).get() : itemSizeRepository.save(new ItemSize(size,itemInfoId,userId));
        return saved.getId();
    }

}
