package com.clovi.dto.response;

import com.clovi.domain.item.Item;
import com.clovi.domain.item.ItemInfo;
import com.clovi.domain.youtube.Video;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
public class SearchResponseDto {
    private final Page<ItemResponseDto> items;
    private final Page<VideoResponseDto> videos;

    public SearchResponseDto(Page<Item> itemList, Page<Video> videoList) {
        items = itemList.map(ItemResponseDto::new);
        videos = videoList.map(VideoResponseDto::new);
    }
}
