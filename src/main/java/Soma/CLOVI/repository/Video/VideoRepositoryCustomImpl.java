package Soma.CLOVI.repository.Video;

import Soma.CLOVI.domain.youtube.Video;
import Soma.CLOVI.dto.requests.SearchRequestDto;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.ArrayList;

import static Soma.CLOVI.domain.category.QCategory.category;
import static Soma.CLOVI.domain.youtube.QVideo.video;
import static Soma.CLOVI.domain.item.QItem.item;
import static Soma.CLOVI.domain.ManyToMany.QVideoItem.videoItem;

@Repository
@RequiredArgsConstructor
public class VideoRepositoryCustomImpl implements VideoRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Video> filterByConditions(SearchRequestDto searchRequestDto, Pageable pageable) {
        String searchKeyword = searchRequestDto.getKeyword();
        String channelName = searchRequestDto.getChannel();
        long parentCategoryNo = searchRequestDto.getParentCategory();
        long childCategoryNo = searchRequestDto.getChildCategory();

        List<Video> queryResults = queryFactory
                .selectFrom(video)
                .innerJoin(video.videoItems, videoItem)
                .innerJoin(videoItem.item, item)
                .innerJoin(item.category, category)
                .where(
                        keywordContains(searchKeyword),
                        channelEq(channelName),
                        parentCategoryEq(parentCategoryNo),
                        childCategoryEq(childCategoryNo)
                )
                .orderBy(makeSort(pageable.getSort()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Page<Video> pagedResults = new PageImpl<>(queryResults, pageable, queryResults.size());

        return pagedResults;
    }

    @Override
    public List<Video> filterByKeyword(String searchKeyword) {
        List<Video> queryResults = queryFactory
                .selectFrom(video)
                .where(
                        keywordContains(searchKeyword)
                )
                .fetch();

        return queryResults;
    }

    @Override
    public List<Video> filterByItemId(Long itemId) {
        List<Video> queryResults = queryFactory
                .selectFrom(video)
                .innerJoin(video.videoItems, videoItem)
                .innerJoin(videoItem.item, item)
                .where(
                        itemEq(itemId)
                )
                .orderBy(video.id.asc())
                .fetch();

        return queryResults;
    }

    private BooleanExpression keywordContains(String searchKeyword) {
        if(searchKeyword == null) return null;
        return video.title.contains(searchKeyword);
    }

    private BooleanExpression channelEq(String channelName) {
        if(channelName == null) return null;
        return video.channel.name.eq(channelName);
    }

    private BooleanExpression parentCategoryEq(long parentCategoryNo) {
        if(parentCategoryNo == 0) return null;
        return category.ParentCategory.id.eq(parentCategoryNo);
    }

    private BooleanExpression childCategoryEq(long childCategoryNo) {
        if(childCategoryNo == 0) return null;
        return category.id.eq(childCategoryNo);
    }

    private BooleanExpression itemEq(Long itemId) {
        // if(itemId == null) return null;
        return item.id.eq(itemId);
    }

    private OrderSpecifier[] makeSort(Sort sort) {
        List<OrderSpecifier> orders = new ArrayList<>();

        for(Sort.Order order : sort) {
            Order direction = order.isAscending() ? Order.ASC : Order.DESC;
            String property = order.getProperty();

            PathBuilder conditions = new PathBuilder(Video.class, "video");
            orders.add(new OrderSpecifier(direction, conditions.get(property)));
        }

        return orders.stream().toArray(OrderSpecifier[]::new);
    }

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
