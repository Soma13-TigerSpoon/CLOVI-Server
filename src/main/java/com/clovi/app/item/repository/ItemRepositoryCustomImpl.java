package com.clovi.app.item.repository;

import com.clovi.app.item.domain.Item;
import com.clovi.app.search.dto.request.SearchRequest;
import com.clovi.app.itemInfo.domain.ItemInfo;
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

import static com.clovi.app.category.domain.QCategory.category;
import static com.clovi.app.itemInfo.domain.QItemInfo.itemInfo;
import static com.clovi.app.item.domain.QItem.item;
import static com.clovi.app.video.domain.QVideo.video;
import static com.clovi.app.videoItem.domain.QVideoItem.videoItem;

@Repository
@RequiredArgsConstructor
public class ItemRepositoryCustomImpl implements ItemRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Item> filterByConditions(SearchRequest searchRequest, Pageable pageable) {
        String searchKeyword = searchRequest.getKeyword();
        String channelName = searchRequest.getChannel();
        long parentCategoryNo = searchRequest.getParentCategory();
        long childCategoryNo = searchRequest.getChildCategory();

        List<Item> queryResults = queryFactory
                .selectFrom(item)
                .innerJoin(item.itemInfo,itemInfo)
                .innerJoin(itemInfo.category, category)
                .innerJoin(item.videoItems,videoItem)
                .innerJoin(videoItem.video,video)
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

        Page<Item> pagedResults = new PageImpl<>(queryResults, pageable, queryResults.size());

        return pagedResults;
    }

    @Override
    public List<Item> filterByKeyword(String searchKeyword) {
        List<Item> queryResults = queryFactory
                .selectFrom(item)
                .where(
                        keywordContains(searchKeyword)
                )
                .fetch();

        return queryResults;
    }

    private BooleanExpression keywordContains(String searchKeyword) {
        if(searchKeyword == null) return null;

        BooleanExpression queryItem1 = item.itemInfo.name.containsIgnoreCase(searchKeyword);
        BooleanExpression queryItem2 = item.itemInfo.brand.containsIgnoreCase(searchKeyword);

        BooleanExpression queryVideo1 = video.title.containsIgnoreCase(searchKeyword);
        BooleanExpression queryVideo2 = video.channel.name.containsIgnoreCase(searchKeyword);

        BooleanExpression queryCategory1 = category.ParentCategory.name.equalsIgnoreCase(searchKeyword);
        BooleanExpression queryCategory2 = category.name.equalsIgnoreCase(searchKeyword);

        return (queryItem1).or(queryItem2)
                .or(queryVideo1).or(queryVideo2)
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

    private OrderSpecifier[] makeSort(Sort sort) {
        List<OrderSpecifier> orders = new ArrayList<>();

        for(Sort.Order order : sort) {
            Order direction = order.isAscending() ? Order.ASC : Order.DESC;
            String property = order.getProperty();

            PathBuilder conditions = new PathBuilder(ItemInfo.class, "itemInfo");
            orders.add(new OrderSpecifier(direction, conditions.get(property)));
        }

        return orders.stream().toArray(OrderSpecifier[]::new);
    }

    @Override
    public List<ItemInfo> searchByIdList(List<Long> ItemIdList) {
        List<ItemInfo> queryResults = queryFactory.selectFrom(itemInfo)
                .where(itemInfo.id.in(ItemIdList))
                .orderBy(itemInfo.category.orders.desc())
                .fetch();

        return queryResults;
    }

    @Override
    public Page<ItemInfo> SearchPageSimple(Long postId, Pageable pageable) {
        return null;
    }

    @Override
    public Page<ItemInfo> SearchPageComplex(Long postId, Pageable pageable) {
        return null;
    }
}
