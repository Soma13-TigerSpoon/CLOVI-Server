package com.clovi.service.item;

import com.clovi.domain.item.*;
import com.clovi.domain.user.Member;
import com.clovi.dto.requests.item.ItemColorCreateRequest;
import com.clovi.dto.requests.item.ItemSizeCreateRequest;
import com.clovi.dto.response.ColorAndImgResponseDto;
import com.clovi.exception.ResourceNotFoundException;
import com.clovi.repository.Item.*;
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
        String sizeName = itemSizeCreateRequest.getSize().trim();
        Size size = sizeRepository.findByName(sizeName).orElse(
                sizeRepository.save(new Size(sizeName))
        );
        ItemSize itemSize = new ItemSize(size,itemInfo,member.getId());
        ItemSize saved = itemSizeRepository.save(itemSize);
        return saved.getId();
    }

    public List<String> findAllColors(Long itemInfoId) {
        return itemSizeRepository.findAllByItemInfoId(itemInfoId).stream().map(ItemSize::getSizeName).collect(Collectors.toList());
    }
}
