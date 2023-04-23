package com.clovi.video.repository;

import com.clovi.video.Video;
import com.clovi.search.dto.request.SearchRequestDto;
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
import java.util.stream.Collectors;

import static com.clovi.item.category.QCategory.category;
import static com.clovi.item.info.QItemInfo.itemInfo;
import static com.clovi.item.item.QItem.item;
import static com.clovi.video.QVideo.video;
import static com.clovi.videoitem.QVideoItem.videoItem;


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
                .innerJoin(item.itemInfo,itemInfo)
                .innerJoin(itemInfo.category, category)
                .where(
                        keywordContains(searchKeyword),
                        channelEq(channelName),
                        parentCategoryEq(parentCategoryNo),
                        childCategoryEq(childCategoryNo)
                )
                .orderBy(makeSort(pageable.getSort()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch().stream().distinct().collect(Collectors.toList());

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
                .innerJoin(item.itemInfo,itemInfo)
                .innerJoin(item.itemInfo.category,category)
                .where(
                        itemInfoEq(itemId)
                )
                .orderBy(video.id.asc())
                .fetch().stream().distinct().collect(Collectors.toList());

        return queryResults;
    }

    private BooleanExpression keywordContains(String searchKeyword) {
        if(searchKeyword == null) return null;

        BooleanExpression queryVideo1 = video.title.containsIgnoreCase(searchKeyword);
        BooleanExpression queryVideo2 = video.channel.name.containsIgnoreCase(searchKeyword);

        BooleanExpression queryItem1 = item.itemInfo.name.containsIgnoreCase(searchKeyword);
        BooleanExpression queryItem2 = item.itemInfo.brand.containsIgnoreCase(searchKeyword);

        BooleanExpression queryCategory1 = category.ParentCategory.name.equalsIgnoreCase(searchKeyword);
        BooleanExpression queryCategory2 = category.name.equalsIgnoreCase(searchKeyword);

        return (queryVideo1).or(queryVideo2)
                .or(queryItem1).or(queryItem2)
                .or(queryCategory1).or(queryCategory2);
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

    private BooleanExpression itemInfoEq(Long itemId) {
        // if(itemId == null) return null;
        return itemInfo.id.eq(itemId);
    }

    private OrderSpecifier[] makeSort(Sort sort) {
        List<OrderSpecifier> orders = new ArrayList<>();

        // 맨 처음 조건으로 영상 업로드 날짜 추가.
        orders.add(video.uploadDate.desc());
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
