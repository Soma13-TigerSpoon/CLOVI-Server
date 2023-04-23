package com.clovi.item.color;

import com.clovi.item.color.domain.Color;
import com.clovi.item.color.domain.ItemColor;
import com.clovi.item.color.repository.ColorRepository;
import com.clovi.item.color.repository.ItemColorRepository;
import com.clovi.item.info.ItemInfo;
import com.clovi.member.Member;
import com.clovi.item.color.dto.request.ItemColorCreateRequest;
import com.clovi.item.color.dto.response.ColorAndImgResponseDto;
import com.clovi.exception.ResourceNotFoundException;
import com.clovi.item.info.ItemInfoRepository;
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
        ItemColor itemColor = new ItemColor(itemInfo,color,itemInfoCreateRequest.getImgUrl(), member.getId());
        ItemColor saved = itemColorRepository.save(itemColor);
        return saved.getId();
    }

    public List<ColorAndImgResponseDto> findAllColors(Long itemInfoId) {
        return itemColorRepository.findAllByItemInfoId(itemInfoId).stream().map(ColorAndImgResponseDto::new).collect(Collectors.toList());
    }
}
