package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class ApplicationContextInfoTest {

    // ApplicationContext는 스프링 컨테이너이다
    // ApplicationContext는 인터페이스이다. 보통 쓰는 구현체는 어노테이션 기반인 AnnotationConfigApplicationContext이다
    // 스프링 컨테이너를 생성할때 구성 정보로 AppConfig.class를 넣어준다. @Configuration을 붙여주면 구성정보로 판단한다.
    // @Configuration 내부의 @Bean을 모두 호출 한 후 반환값들을 스프링 빈으로 등록해준다.
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 호출")
    void FindAllBeans()
    {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();  // 빈 마다 부여된 모든 스프링빈 이름을 가지고 옴
        for (String beanDefinitionName : beanDefinitionNames) {
            // 한번씩 돌아가면서 해당 bean name으로 bean을 조회할 수 있다.
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println(bean);
        }

    }

    @Test
    @DisplayName("이번에는 내가 등록한 스프링 빈들만 다 가지고 오자")
    void findBeansImade()
    {
        // 1. 빈 이름 다 가지고 온다.
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        // 2. 빈 이름에 해당하는 정의 다 가지고 온다.
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);
            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION)
            {
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("bean = " + bean);
            }
        }
        // 3. 정의 중 role이 applicaiton인것들 찾자
        // 4. 그것들만 getbean으로 뽑아내기
    }


}
