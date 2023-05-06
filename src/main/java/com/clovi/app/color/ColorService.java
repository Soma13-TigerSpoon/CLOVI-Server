package com.clovi.app.color;

import com.clovi.exception.ResourceNotFoundException;
import com.clovi.app.color.domain.Color;
import com.clovi.app.color.domain.ItemColor;
import com.clovi.app.color.dto.request.ItemColorCreateRequest;
import com.clovi.app.color.dto.response.ColorAndImgResponse;
import com.clovi.app.color.repository.ColorRepository;
import com.clovi.app.color.repository.ItemColorRepository;
import com.clovi.app.itemInfo.ItemInfo;
import com.clovi.app.itemInfo.ItemInfoRepository;
import com.clovi.app.member.Member;
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

    public List<ColorAndImgResponse> findAllColors(Long itemInfoId) {
        return itemColorRepository.findAllByItemInfoId(itemInfoId).stream().map(itemColor -> ColorAndImgResponse.from(itemColor)).collect(Collectors.toList());
    }
}
