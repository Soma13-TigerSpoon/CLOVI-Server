package com.clovi.dto.response;

import com.clovi.domain.item.Item;
import com.clovi.domain.item.ItemInfo;
import com.clovi.domain.youtube.Video;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class KeywordResponseDto {
    private List<String> itemNames;
    private final List<String> videoTitles;

    public KeywordResponseDto(List<Item> itemInfoList, List<Video> videoList) {
        for(Item item : itemInfoList){
            itemNames.add(item.getItemInfo().getName());
        }
        videoTitles = videoList.stream().map(Video::getTitle).collect(Collectors.toList());
    }
}
