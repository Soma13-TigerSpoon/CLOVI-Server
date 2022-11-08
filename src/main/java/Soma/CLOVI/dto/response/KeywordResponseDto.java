package Soma.CLOVI.dto.response;

import Soma.CLOVI.domain.item.Item;
import Soma.CLOVI.domain.youtube.Video;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class KeywordResponseDto {
    private final List<String> itemNames;
    private final List<String> videoTitles;

    public KeywordResponseDto(List<Item> itemList, List<Video> videoList) {
        itemNames = itemList.stream().map(Item::getName).collect(Collectors.toList());
        videoTitles = videoList.stream().map(Video::getTitle).collect(Collectors.toList());
    }
}
