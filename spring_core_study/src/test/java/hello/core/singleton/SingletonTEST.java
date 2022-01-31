package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonTEST {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer(){
        AppConfig appConfig = new AppConfig();

        MemberService memberService = appConfig.memberService();

        MemberService memberService1 = appConfig.memberService();


        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService = " + memberService);

        // is equal to 가 아니라 반대를 검증해줄때는 isNotSameAs로 판단
        Assertions.assertThat(memberService).isNotSameAs(memberService1);
        // 이전의 appconfig는 요청시마다 새로운 객체 생성함으로 메모리 낭비이다.
        // 객체는 딱 하나만 만들어지게 해야 한다. 공유 시키자!
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest()
    {
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        System.out.println("singletonService2 = " + singletonService2);
        System.out.println("singletonService1 = " + singletonService1);

        // same : 객체 인스턴스가 같은지 비교 참조값 검사
        // equal :
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);
        Assertions.assertThat(memberService1).isSameAs(memberService2);

    }
}
