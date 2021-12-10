package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;   // 추상화에만 의존, DIP 준수
    // memberServiceImpl을 대상으로 한다. 생성자에 붙여준다. 매개변수를 의존성 자동 주입 해준다.
    @Autowired  // 생성자에 붙여줘야 한다. 멤버변수에 일치하는 녀석을 스프링 빈에서 찾아와준다. 1차적으로 타입으로 조회
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId); //
    }

    // 테스트 용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
