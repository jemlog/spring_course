package hello.core.blog;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;


import static org.junit.jupiter.api.Assertions.*;

class BookAppConfigTest {


    @Test
    @DisplayName("스프링 컨테이너 싱글톤 테스트")
    void testSingleton()
    {
        ApplicationContext ac = new AnnotationConfigApplicationContext(BookAppConfig.class);
        Book beanA = ac.getBean(Book.class);
        Book beanB = ac.getBean(Book.class);
        System.out.println("beanA is " + beanA);
        System.out.println("beanB is " + beanB);
    }






}