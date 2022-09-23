package Soma.CLOVI.repository.Category;

import static Soma.CLOVI.domain.category.QCategory.category;

import Soma.CLOVI.domain.category.Category;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CategoryRepositoryImpl {

  private final JPAQueryFactory queryFactory;

  public List<Category> getAllCategory(){
    List<Category> queryResults = queryFactory.selectFrom(category)
        .where(category.ParentCategory.isNull())
        .orderBy(category.orders.asc())
        .fetch();
    return queryResults;
  }

  public List<Category> getChildCategoriesByParentId(Long id){
    List<Category> queryResults = queryFactory.selectFrom(category)
        .where(category.ParentCategory.id.eq(id))
        .fetch();

    return queryResults;
  }
}
