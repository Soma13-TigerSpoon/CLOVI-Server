package com.clovi.app.timeframe.repository;

import com.clovi.app.timeframe.domain.Timeframe;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

import static com.clovi.app.itemInfo.domain.QItemInfo.itemInfo;
import static com.clovi.app.item.domain.QItem.item;
import static com.clovi.app.timeShopItem.domain.QTimeShopItem.timeShopItem;
import static com.clovi.app.timeframe.domain.QTimeframe.timeframe;
import static com.clovi.app.video.domain.QVideo.video;

@Repository
@RequiredArgsConstructor
public class TimeframeRepositoryCustomImpl implements TimeframeRepositoryCustom {
    private final JPAQueryFactory queryFactory;
    @Override
    public List<Timeframe> getTimeFramesByVideoIdAndItemInfoId(Long videoId, Long itemInfoId) {
        List<Timeframe> queryResults = queryFactory
                .selectFrom(timeframe)
                .innerJoin(timeframe.video,video)
                .innerJoin(timeframe.items,timeShopItem)
                .innerJoin(timeShopItem.item,item)
                .innerJoin(item.itemInfo,itemInfo)
                .where(
                        videoIdEq(videoId),
                        itemInfoIdEq(itemInfoId),
                        NotDeleted()
                ).fetch().stream().collect(Collectors.toList());

        return queryResults;
    }
    private BooleanExpression videoIdEq(Long videoId){
        if(videoId == null) return null;
        return video.id.eq(videoId);
    }
    private BooleanExpression itemInfoIdEq(Long itemInfoId){
        if(itemInfoId == null) return null;
        return itemInfo.id.eq(itemInfoId);
    }
    private BooleanExpression NotDeleted(){
        return timeframe.deleted.isFalse();
    }
}
