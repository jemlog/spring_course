package study.jpatoyproject.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import study.jpatoyproject.domain.Address;
import study.jpatoyproject.domain.Member;
import study.jpatoyproject.domain.dto.member.MemberResponseDto;
import study.jpatoyproject.domain.dto.member.MemberRequestDto;
import study.jpatoyproject.domain.dto.member.MemberSearch;
import study.jpatoyproject.service.MemberService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 페이징만 되는 전체 조회 기능

    @GetMapping
    public Page<MemberResponseDto> findAll(@PageableDefault(size = 20) Pageable pageable) {

        Page<Member> members = memberService.findAll(pageable);
        return members.map(m -> new MemberResponseDto(m.getId(),
                            m.getName(), m.getAge(),m.getMoney()));

    }

    @GetMapping("/moneySum")
    public Integer findAllAndSumMoney(@PageableDefault(size = 20) Pageable pageable) {

        Page<Member> members = memberService.findAll(pageable);
        Integer collect = members.getContent().stream()
                .collect(Collectors.summingInt(Member::getMoney));
        System.out.println("collect = " + collect);
        return collect;
    }

    @Cacheable(value = "member-single",key = "#id")
    @GetMapping("/cache/{id}")
    public MemberResponseDto findById(@PathVariable Long id)
    {
        Member member = memberService.findById(id);
        MemberResponseDto result = new MemberResponseDto(member);
        return result;
    }

    // 페이징과 검색 기능이 있는 조회
    @GetMapping("/search")
    public Page<MemberResponseDto> findAllByCondition(MemberSearch memberSearch, Pageable pageable)
    {
        return memberService.findAllByCondition(memberSearch, pageable);
    }

    // member 추가 기능
    @PostMapping("/signup")
    public Long join(@RequestBody MemberRequestDto memberRequestDto)
    {
         return memberService.save(memberRequestDto);
    }

    // member 삭제 기능
    @DeleteMapping("/{id}")
    public Result removeMember(@PathVariable("id") Long id)
    {
        memberService.delete(id);
        return new Result("successfully deleted");
    }




    @Data
    @AllArgsConstructor
    static class Result<T>
    {
        public T data;

    }
}
