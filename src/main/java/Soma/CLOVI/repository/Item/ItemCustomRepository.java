package Soma.CLOVI.repository.Item;

import Soma.CLOVI.domain.item.Item;
import Soma.CLOVI.domain.youtube.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ItemCustomRepository {
    List<Item> searchByIdList(List<Long> ItemIdList);
    Page<Item> SearchPageSimple(Long postId, Pageable pageable);
    Page<Item> SearchPageComplex(Long postId, Pageable pageable);
}
