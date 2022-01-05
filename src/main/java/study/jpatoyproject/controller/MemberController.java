package study.jpatoyproject.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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

    @GetMapping
    public Page<MemberResponseDto> findAll(@PageableDefault(size = 20) Pageable pageable) {

        Page<Member> members = memberService.findAll(pageable);
        return members.map(m -> new MemberResponseDto(m.getId(),
                            m.getName(), m.getAge(), m.getGrade(), m.getGender(), m.getAddress()));

    }

    @GetMapping("/search")
    public Page<MemberResponseDto> findAllByCondition(MemberSearch memberSearch, Pageable pageable)
    {
        return memberService.findAllByCondition(memberSearch, pageable);
    }

    @PostMapping("/join")
    public Long join(@RequestBody MemberRequestDto memberRequestDto)
    {
         return memberService.save(memberRequestDto);
    }

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
