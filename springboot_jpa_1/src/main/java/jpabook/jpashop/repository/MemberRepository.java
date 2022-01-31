package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository  // DB 종속적인 예외를 스프링 공통 예외로 처리해준다.
public class MemberRepository {

    @PersistenceContext    // 엔티티 매니져를 자동 주입 해준다.
    private EntityManager em;

    public void save(Member member)
    {
        em.persist(member);
    }

    public Member findOne(Long id)
    {
        Member member = em.find(Member.class, id);
        return member;
    }

    public List<Member> findAll()
    {   // sql은 테이블 대상, jpql은 클래스 대상이다.
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name",Member.class)
                .setParameter("name", name)
                .getResultList();

    }
}
