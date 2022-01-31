package jpabook.jpashop.api;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    // 프레젠테이션 계층을 위한 검증 로직이 엔티티에 들어가있는게 문제!
    // api 마다 validation이 필요있을수도 없을수도 있다. 유연성이 떨어진다.
    // 엔티티를 변경하면 api 스펙이 꼬여버린다.
    // 엔티티는 굉장히 여러곳에서 사용!
    // api 스펙을 위한 별도의 DTO를 생성해야 한다.
    // 간편가입, 소셜 가입 등등 등록 케이스도 매우 다양할 수 있다.
    // API를 만들때는 엔티티를 절대 외부로 노출시키지 말자.

    @GetMapping("/api/v1/members")
    public List<Member> membersV1(){
        return memberService.findMembers();
    }

    // 매우 중요!!
    @GetMapping("/api/v2/members")
    public Result memberV2(){
        List<Member> findMembers = memberService.findMembers();
        List<MemberDto> collect = findMembers.stream().map(m -> new MemberDto(m.getName())).collect(Collectors.toList());
        return new Result(collect);
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }

    @Data
    @AllArgsConstructor
    static class MemberDto {
        private String name;
    }

    @PostMapping("/api/v1/members")    // requestBody를 통해 객체 형식으로 받을 수 있다. @Valid를 통해 member 객체에서 검증 가능
    public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member) // json 데이터를 member로 변경해준다.
    {
        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }

    // v2의 장점
    // api 스펙은 변경되지 않는다.

    @PostMapping("/api/v2/members")
    public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest request)
    {
        Member member = new Member();
        member.setName(request.getName());
        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }

    @PutMapping("/api/v2/members/{id}")
    public UpdateMemberResponse updateMemberV2(@PathVariable("id") Long id,
                                               @RequestBody @Valid UpdateMemberRequest request) {


        memberService.update(id,request.getName());
        Member member = memberService.findOne(id); // 커맨드와 쿼리를 철저히 분리. update 메서드 내에서 조회 기능까지 포함하지 말자
                                                   // member를 반환하면 member를 쿼리로 조회하는 것과 같은 기능
        return new UpdateMemberResponse(member.getId(), member.getName());
    }

    @Data
    static class UpdateMemberRequest {
        private String name;
    }

    // DTO에는 lombok 어노테이션 막 쓰자
    @Data
    @AllArgsConstructor
    static class UpdateMemberResponse {
        private Long id;
        private String name;
    }

    @Data
    static class CreateMemberRequest {
        @NotEmpty
        private String name;
    }

    @Data
    static class CreateMemberResponse {
        private Long id;

        public CreateMemberResponse(Long id) {
            this.id = id;
        }
    }

}
