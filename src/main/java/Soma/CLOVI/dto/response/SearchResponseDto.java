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
        items = itemList.map(ItemResponseDto::new);
        videos = videoList.map(VideoResponseDto::new);
    }
}
