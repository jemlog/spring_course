package study.jpatoyproject.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import study.jpatoyproject.auth.ConstSessionId;
import study.jpatoyproject.domain.Member;
import study.jpatoyproject.domain.dto.login.MemberLoginDto;
import study.jpatoyproject.domain.dto.member.MemberRequestDto;
import study.jpatoyproject.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final MemberService memberService;

    @PostMapping("/login")
    public String login(@Valid @RequestBody MemberLoginDto memberLoginDto,
                        BindingResult bindingResult,
                        HttpServletRequest request) {
        String requestURI = request.getRequestURI();

        log.info(memberLoginDto.getUsername());
        log.info(memberLoginDto.getPassword());

        if (bindingResult.hasErrors()) {
            log.info("[{}] 에러발생!!!", requestURI);
            return "에러발생";

        }

        Member findMember = memberService.findByUsername(memberLoginDto.getUsername())
                .filter(m -> m.getPassword().equals(memberLoginDto.getPassword()))
                .orElse(null);


       if (findMember == null) {
                log.info("[{}] 해당하는 유저가 없습니다!", requestURI);
               return "해당하는 유저가 없습니다";
           }

            HttpSession session = request.getSession();

           session.setAttribute(ConstSessionId.SESSION_ID, findMember);

            return "로그인 완료!!";

     }


    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
            return "로그아웃 완료!";
        }

        return "애초에 세션이 없다! 홈 화면으로 가자";
    }

}