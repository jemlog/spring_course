package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service   // 단순히 service 계층이라는 걸 알려준다. ComponentScan의 대상
@RequiredArgsConstructor
@Transactional(readOnly = true) // 무조건 트랜젝션 내부에서 데이터를 변경해야 한다.  보통 조회가 많으므로 기본을 true로 설정하자
public class MemberService {

    private final MemberRepository memberRepository;  // 한번 할당 후 변경되지 않는 데이터는 final 사용!

    /**
     * 회원 가입
     */
    @Transactional  // 기본값을 True로 설정 한 뒤에, 추가 삭제 수정이 일어나는 부분만 @Transactional을 다시 걸어주자
    public Long join(Member member)
    {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()) // 배열의 길이 체크 할 필요 없이 isEmpty 메서드로 처리!
        {
            throw new IllegalStateException("이미 존재하는 회원입니다");
        }

    }

    // 회원 전체 조회

    public List<Member> findMembers()
    {
        return memberRepository.findAll();
    }


    public Member findOne(Long memberId)
    {
        return memberRepository.findOne(memberId);
    }
}
