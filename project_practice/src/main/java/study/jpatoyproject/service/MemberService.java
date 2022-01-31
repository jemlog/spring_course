package study.jpatoyproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.jpatoyproject.domain.Address;
import study.jpatoyproject.domain.Member;
import study.jpatoyproject.domain.dto.member.MemberRequestDto;
import study.jpatoyproject.domain.dto.member.MemberResponseDto;
import study.jpatoyproject.domain.dto.member.MemberSearch;
import study.jpatoyproject.repository.member.MemberRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    // member 전체 조회
    public Page<Member> findAll(Pageable pageable)
    {
        return memberRepository.findAll(pageable);
    }

    public Page<MemberResponseDto> findAllByCondition(MemberSearch memberSearch, Pageable pageable){
        return memberRepository.findAllByCondition(memberSearch, pageable);
    }

    public Optional<Member> findByUsername(String userId)
    {
        return memberRepository.findByUsername(userId);
    }

    public Member findById(Long id)
    {
        return memberRepository.findById(id).get();
    }


    @Transactional
    public Long save(MemberRequestDto memberRequestDto)
    {
        Member createdMember = Member.builder()
                .name(memberRequestDto.getName())
                .username(memberRequestDto.getUsername())
                .password(memberRequestDto.getPassword())
                .age(memberRequestDto.getAge())
                .gender(memberRequestDto.getGender())
                .money(memberRequestDto.getMoney())
                .grade(memberRequestDto.getGrade())
                .address(new Address(memberRequestDto.getCity(), memberRequestDto.getStreet())).build();
        return memberRepository.save(createdMember).getId();
    }

    @Transactional
    public void delete(Long memberId)
    {
        Member findMember = memberRepository.findById(memberId).get();
        memberRepository.delete(findMember);
    }






}
