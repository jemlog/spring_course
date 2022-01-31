package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional  // jpa는 같은 트렌젝션 안에서 PK 값이 같으면 하나로 관리된다.
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void 회원가입() throws Exception {
        // given
        Member member = new Member();
        member.setName("kim");
        // when
        Long memberId = memberService.join(member);
        // then

        Assertions.assertEquals(member, memberRepository.findOne(memberId));
    }

    @Test()
    public void 중복_회원_예외() throws Exception {
        // given
        Member member1 = new Member();
        member1.setName("kim");
        memberService.join(member1);
        // when
        Member member2 = new Member();
        member2.setName("kim");
        // then
       //  특정 에러가 발생하는지는 assertThrows를 사용하면 된다.
        Assertions.assertThrows(IllegalStateException.class, () -> {
            memberService.join(member2);
        });
       // fail("여기 오면 안돼요");

    }

}