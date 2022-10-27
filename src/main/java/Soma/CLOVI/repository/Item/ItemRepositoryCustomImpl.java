package Soma.CLOVI.repository.Item;

import Soma.CLOVI.domain.item.Item;
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
import java.util.stream.Collectors;

import static Soma.CLOVI.domain.category.QCategory.category;
import static Soma.CLOVI.domain.youtube.QVideo.video;
import static Soma.CLOVI.domain.item.QItem.item;
import static Soma.CLOVI.domain.ManyToMany.QVideoItem.videoItem;

@Repository
@RequiredArgsConstructor
public class ItemRepositoryCustomImpl implements ItemRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Item> filterByConditions(SearchRequestDto searchRequestDto, Pageable pageable) {
        String searchKeyword = searchRequestDto.getKeyword();
        String channelName = searchRequestDto.getChannel();
        long parentCategoryNo = searchRequestDto.getParentCategory();
        long childCategoryNo = searchRequestDto.getChildCategory();

        List<Item> queryResults = queryFactory
                .selectFrom(item)
                .innerJoin(item.videoItems, videoItem)
                .innerJoin(videoItem.video, video)
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

        BooleanExpression conditionA = item.name.contains(searchKeyword);
        BooleanExpression conditionB = item.brand.eq(searchKeyword);

        return conditionA.or(conditionB);
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

            PathBuilder conditions = new PathBuilder(Item.class, "item");
            orders.add(new OrderSpecifier(direction, conditions.get(property)));
        }

        return orders.stream().toArray(OrderSpecifier[]::new);
    }

    @Override
    public List<Item> searchByIdList(List<Long> ItemIdList) {
        List<Item> queryResults = queryFactory.selectFrom(item)
                .where(item.id.in(ItemIdList))
                .orderBy(item.category.orders.desc())
                .fetch();

        return queryResults;
    }

    @Override
    public Page<Item> SearchPageSimple(Long postId, Pageable pageable) {
        return null;
    }

    @Override
    public Page<Item> SearchPageComplex(Long postId, Pageable pageable) {
        return null;
    }
}
