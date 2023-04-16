package com.clovi.service.item;

import com.clovi.domain.item.Color;
import com.clovi.domain.item.ItemColor;
import com.clovi.domain.item.ItemInfo;
import com.clovi.domain.user.Member;
import com.clovi.dto.requests.item.ItemColorCreateRequest;
import com.clovi.dto.response.ColorAndImgResponseDto;
import com.clovi.exception.ResourceNotFoundException;
import com.clovi.repository.Item.ColorRepository;
import com.clovi.repository.Item.ItemInfoRepository;
import com.clovi.repository.Item.ItemColorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ColorService {

    private final ItemInfoRepository itemInfoRepository;
    private final ColorRepository colorRepository;
    private final ItemColorRepository itemColorRepository;

    public Long create(ItemColorCreateRequest itemInfoCreateRequest, Member member, Long itemInfoId) {
        ItemInfo itemInfo = itemInfoRepository.findByIdAndDeletedIsFalse(itemInfoId).orElseThrow(() -> new ResourceNotFoundException("ItemInfo",itemInfoId));
        String colorName = itemInfoCreateRequest.getColor().trim();
        Color color = colorRepository.findByName(colorName).orElse(
                colorRepository.save(new Color(colorName))
        );
        ItemColor itemColor = new ItemColor(itemInfo,color,itemInfoCreateRequest.getImgUrl());
        ItemColor saved = itemColorRepository.save(itemColor);
        return saved.getId();
    }

    public List<ColorAndImgResponseDto> findAllColors(Long itemInfoId) {
        return itemColorRepository.findAllByItemInfoId(itemInfoId).stream().map(ColorAndImgResponseDto::new).collect(Collectors.toList());
    }
}
