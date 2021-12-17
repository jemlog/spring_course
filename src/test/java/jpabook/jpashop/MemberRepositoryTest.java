package jpabook.jpashop;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest

public class MemberRepositoryTest {


    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional   // 모든 데이터의 이동은 transaction 안에서 이루어져야 한다.
    @Rollback(value = false)
    public void testMember() throws Exception {
        //given
        Member member = new Member(); // member를 em.persist 하는 순간 member라는 변수 자체가 영속성 컨텍스트 관리 들어간다.
        member.setUsername("memberA");

        //when
        Long memberId = memberRepository.save(member);
        Member findMember = memberRepository.find(memberId);
        //then
        Assertions.assertThat(member.getId()).isEqualTo(findMember.getId());
        Assertions.assertThat(member.getUsername()).isEqualTo(findMember.getUsername());
        Assertions.assertThat(findMember).isEqualTo(member); // JPA 영속성 컨텍스트 내부에서 ID가 같다면 1차 캐시에서 가져왔기에 똑같다.

    }
}