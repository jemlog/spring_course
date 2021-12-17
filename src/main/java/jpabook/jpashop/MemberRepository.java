package jpabook.jpashop;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberRepository {

    @PersistenceContext  // 엔티티 매니져 자동으로 생성해서 주입해준다. 스프링 부트가 해줌
    private EntityManager em;

    public Long save(Member member)
    {
        em.persist(member);
        return member.getId();  // 커맨드와 쿼리를 분리해라.
    }

    public Member find(Long id)
    {
        return em.find(Member.class,id);
    }

}
