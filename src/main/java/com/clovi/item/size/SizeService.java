package com.clovi.item.size;

import com.clovi.item.size.domain.ItemSize;
import com.clovi.item.size.domain.Size;
import com.clovi.item.size.repository.ItemSizeRepository;
import com.clovi.item.size.repository.SizeRepository;
import com.clovi.member.Member;
import com.clovi.item.info.ItemInfo;
import com.clovi.item.size.dto.request.ItemSizeCreateRequest;
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
