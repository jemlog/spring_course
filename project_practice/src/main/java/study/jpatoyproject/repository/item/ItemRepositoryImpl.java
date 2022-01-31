package study.jpatoyproject.repository.item;

import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;


public class ItemRepositoryImpl implements CustomItemResitory {

    private final JPAQueryFactory queryFactory;
    public ItemRepositoryImpl(EntityManager em)
    {
        this.queryFactory = new JPAQueryFactory(em);
    }


}
