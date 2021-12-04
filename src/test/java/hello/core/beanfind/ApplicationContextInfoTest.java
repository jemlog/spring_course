package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

//    @Test
//    @DisplayName("모든 빈 출력하기")
//    void findAllBean()
//    {
//        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
//        for (String beanDefinitionName : beanDefinitionNames) {
//            Object bean = ac.getBean(beanDefinitionName);
//            System.out.println("bean = " + bean);
//        }
//    }
//    @Test
//    @DisplayName("애플리케이션 빈 출력하기")
//    void findApplicationBean()
//    {
//        String[] beanDefinitionNames = ac.getBeanDefinitionNames(); // 모든 definitionNames를 가져옴
//        for (String beanDefinitionName : beanDefinitionNames) {
//            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName); // 해당 definitionName을 가지고 definition 찾음
//            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){   // 만약 찾은  definition의 role이 application이라면
//                Object bean = ac.getBean(beanDefinitionName);   // 그 이름으로 bean을 얻어온다.
//                System.out.println("bean = " + bean);
//            }
//        }
//    }
}
