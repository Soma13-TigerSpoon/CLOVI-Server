package com.clovi.search.dto.response;

import com.clovi.item.item.Item;
import com.clovi.video.Video;
import com.clovi.item.item.dto.response.ItemResponseDto;
import com.clovi.video.dto.response.VideoResponseDto;
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
