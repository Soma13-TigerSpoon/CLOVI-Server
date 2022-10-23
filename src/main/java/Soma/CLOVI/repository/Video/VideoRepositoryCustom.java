package Soma.CLOVI.repository.Video;

import Soma.CLOVI.domain.youtube.Video;
import Soma.CLOVI.dto.requests.SearchRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface VideoRepositoryCustom {
    Page<Video> filterByConditions(SearchRequestDto searchRequestDto, Pageable pageable);
    List<Video> filterByKeyword(String searchKeyword);

    List<Video> search(String videoUrl);
    Page<Video> SearchPageSimple(Long channelId, Long categoryId, Pageable pageable);
    Page<Video> SearchPageComplex(Long channelId, Long categoryId, Pageable pageable);
}
