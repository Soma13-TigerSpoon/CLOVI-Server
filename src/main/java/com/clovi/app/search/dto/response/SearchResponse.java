package com.clovi.app.search.dto.response;

import com.clovi.app.item.Item;
import com.clovi.app.item.dto.response.ItemResponse;
import com.clovi.app.video.Video;
import com.clovi.app.video.dto.response.VideoResponse;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
public class SearchResponse {
    private final Page<ItemResponse> items;
    private final Page<VideoResponse> videos;

    public SearchResponse(Page<Item> itemList, Page<Video> videoList) {
        items = itemList.map(item -> ItemResponse.from(item));
        videos = videoList.map(VideoResponse::new);
    }
}
