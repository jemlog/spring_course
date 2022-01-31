package study.jpatoyproject.auth.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import study.jpatoyproject.auth.ConstSessionId;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession(false);

        if(session == null || session.getAttribute(ConstSessionId.SESSION_ID) == null)
        {
            log.info("세션이 없습니다 처음 화면으로 이동합니다.");
            return false;
        }

        log.info("로그인 통과했습니다!");
        return true;

    }
}
