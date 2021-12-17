package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
// @Component가 붙어있는 대상을 모두 스캔해서 스프링 빈으로 만들어준다.
@ComponentScan(                                  // shift 두번 누르면 검색창     // 수동으로 설정한 configuration이 들어가면 안된다.
        basePackages = "hello.core.member"
        ,
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)   // 컴포넌트 스캔으로 뒤지면서 제외할것들 선택

)
public class AutoAppConfig {

    @Bean(name = "memoryMemberRepository")
    public MemberRepository memberRepository()
    {
        return new MemoryMemberRepository();
    }

}
