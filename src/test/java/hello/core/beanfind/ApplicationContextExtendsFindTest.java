package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

public class ApplicationContextExtendsFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

    @Configuration
    static class TestConfig {
        @Bean
        public DiscountPolicy rateDiscountPolicy()
        {
               return new RateDiscountPolicy();
        }

        @Bean
        public DiscountPolicy fixDiscountPolicy()
        {
            return new FixDiscountPolicy();
        }

    }

    @Test
    @DisplayName("부모 타입으로 조회시 자식이 둘 이상 있으면 중복 오류 발생")
    void findBeanByParentTypeDuplicate()
    {
        Assertions.assertThrows(NoUniqueBeanDefinitionException.class,
                ()-> ac.getBean(DiscountPolicy.class));

    }

    @Test
    @DisplayName("부모 타입으로 조회시 자식이 빈 이름 지정")
    void findBeanByParentTypeBeanName()
    {
        DiscountPolicy rateDiscountPolicy = ac.getBean("rateDiscountPolicy", DiscountPolicy.class);
        org.assertj.core.api.Assertions.assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);

    }


    // getBean에서 부모 클래스로 조회하면 하위의 자식 클래스들은 모두 다 나온다.
    // 예를 들어 getBean(Object.class)로 조회하면 내가 등록한 빈, 스프링 내장 빈 모두 튀어나온다!

    @Test
    @DisplayName("부모 타입으로 전부 다 조회")
    void findAllBeanByParentType()
    {
        Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
        org.assertj.core.api.Assertions.assertThat(beansOfType.size()).isEqualTo(2);
    }
}
