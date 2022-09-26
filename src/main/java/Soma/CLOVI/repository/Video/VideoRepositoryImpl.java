package Soma.CLOVI.repository.Video;

import Soma.CLOVI.domain.youtube.Video;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class VideoRepositoryImpl implements VideoCustomRepository{
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Video> search(String videoUrl) {
        return null;
    }

    @Override
    public Page<Video> SearchPageSimple(Long channelId, Long categoryId , Pageable pageable) {
        return null;
    }

    @Override
    public Page<Video> SearchPageComplex(Long channelId, Long categoryId , Pageable pageable) {
        return null;
    }
}
