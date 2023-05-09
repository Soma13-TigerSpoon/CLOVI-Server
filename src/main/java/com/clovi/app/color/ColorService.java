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

    public Long create(ItemColorCreateRequest itemColorCreateRequest, Member member, Long itemInfoId) {
        ItemInfo itemInfo = itemInfoRepository.findByIdAndDeletedIsFalse(itemInfoId).orElseThrow(() -> new ResourceNotFoundException("ItemInfo",itemInfoId));
        String colorName = itemColorCreateRequest.getColor();
        String imgUrl = itemColorCreateRequest.getImgUrl();
        return save(colorName,imgUrl,itemInfo.getId(),member.getId());
    }

    public List<ColorAndImgResponse> findAllColors(Long itemInfoId) {
        return itemColorRepository.findAllByItemInfoId(itemInfoId).stream().map(itemColor -> ColorAndImgResponse.from(itemColor)).collect(Collectors.toList());
    }

    @Transactional
    public Long save(String colorName, String imgUrl, Long itemInfoId, Long userId){
        colorName = colorName.trim();
        // 아래 로직으로 처리해봤는데 color, itemColor 테이블 모두에 중복 데이터가 계속 생김
//        Color color = colorRepository.findByNameAndDeletedIsFalse(colorName).orElse(
//                new Color(colorName)
//        );
//        ItemColor saved = itemColorRepository.findByItemInfoIdAndColorIdAndImgUrlAndDeletedIsFalse(itemInfoId, color.getId(), imgUrl).orElse(
//                itemColorRepository.save(new ItemColor(itemInfoId,color,imgUrl, userId))
//        );
        Color color = colorRepository.existsByNameAndDeletedIsFalse(colorName) ?
                colorRepository.findByNameAndDeletedIsFalse(colorName).get() : colorRepository.save(new Color(colorName));
        ItemColor saved = itemColorRepository.existsByItemInfoIdAndColorIdAndImgUrlAndDeletedIsFalse(itemInfoId, color.getId(),imgUrl) ?
                itemColorRepository.findByItemInfoIdAndColorIdAndImgUrlAndDeletedIsFalse(itemInfoId, color.getId(), imgUrl).get() : itemColorRepository.save(new ItemColor(itemInfoId,color,imgUrl, userId));
        return saved.getId();
    }
}
