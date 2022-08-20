package Soma.CLOVI.repository.Item;

import Soma.CLOVI.domain.item.Item;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static Soma.CLOVI.domain.item.QItem.item;

@Repository
@RequiredArgsConstructor
public class ItemRepositoryImpl implements ItemCustomRepository {


    private final JPAQueryFactory queryFactory;
    @Override
    public List<Item> searchByIdList(List<Long> ItemIdList) {
        List<Item> queryResults = queryFactory.selectFrom(item)
                .where(item.id.in(ItemIdList))
                .orderBy(item.itemType.desc())
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
