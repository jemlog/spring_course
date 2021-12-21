package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    public void save(Order order){
        em.persist(order);
    }

    public Order findOne(Long id)
    {
        return em.find(Order.class,id);
    }

    public List<Order> findAll(){
        return em.createQuery("select o from Order o",Order.class)
                .getResultList();
    }

    public List<Order> findAllWithMemberDelivery() {   // 페치 조인 먹인 부분
        List<Order> resultList = em.createQuery("select o from Order o join fetch o.member m join fetch o.delivery d", Order.class).getResultList();
        return resultList;
    }
    //    public List<Order> findAll(OrderSearch orderSearch){}
}
