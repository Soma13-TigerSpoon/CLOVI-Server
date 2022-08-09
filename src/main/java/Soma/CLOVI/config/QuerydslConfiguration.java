package Soma.CLOVI.config;


import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

// Querydsl 의 쿼리를 작성할 JPAQueryFactory 클래스를 빈으로 등록

@Configuration
@RequiredArgsConstructor
public class QuerydslConfiguration {
    private final EntityManager em;

    @Bean
    public JPAQueryFactory queryFactory(){
        return new JPAQueryFactory(em);
    }
}
