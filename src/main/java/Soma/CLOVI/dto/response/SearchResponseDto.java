package Soma.CLOVI.dto.response;

import Soma.CLOVI.domain.item.Item;
import Soma.CLOVI.domain.youtube.Video;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
public class SearchResponseDto {
    private Page<ItemResponseDto> items;
    private Page<VideoResponseDto> videos;

    public SearchResponseDto(Page<Item> itemList, Page<Video> videoList) {
        items = itemList.map(item -> new ItemResponseDto(item));
        videos = videoList.map(video -> new VideoResponseDto(video));
    }
}
