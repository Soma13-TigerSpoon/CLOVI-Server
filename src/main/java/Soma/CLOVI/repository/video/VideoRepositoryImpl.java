package Soma.CLOVI.repository.video;

import Soma.CLOVI.domain.youtube.Video;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static Soma.CLOVI.domain.youtube.QVideo.video;

@Repository
@RequiredArgsConstructor
public class VideoRepositoryImpl implements VideoCustomRepository{
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Video> search(String videoUrl) {
        return null;
    }

    @Override
    public Page<Video> SearchPageSimple(Long postId, Pageable pageable) {
        return null;
    }

    @Override
    public Page<Video> SearchPageComplex(Long postId, Pageable pageable) {
        return null;
    }
}
