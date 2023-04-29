package com.clovi.app.search.dto.response;

import com.clovi.app.item.Item;
import com.clovi.app.video.Video;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class KeywordResponse {
    private List<String> itemNames;
    private final List<String> videoTitles;

    public KeywordResponse(List<Item> itemInfoList, List<Video> videoList) {
        for(Item item : itemInfoList){
            itemNames.add(item.getItemInfo().getName());
        }
        videoTitles = videoList.stream().map(Video::getTitle).collect(Collectors.toList());
    }
}
