package com.clovi.app.search.dto.response;

import com.clovi.app.item.domain.Item;
import com.clovi.app.video.domain.Video;
import com.clovi.app.video.dto.response.VideoResponse;
import com.clovi.app.item.dto.response.ItemResponse;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
public class SearchResponse {
    private final Page<ItemResponse> items;
    private final Page<VideoResponse> videos;

    public SearchResponse(Page<Item> itemList, Page<Video> videoList) {
        items = itemList.map(ItemResponse::new);
        videos = videoList.map(VideoResponse::new);
    }
}
