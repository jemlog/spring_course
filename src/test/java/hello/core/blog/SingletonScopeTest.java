package hello.core.blog;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class SingletonScopeTest {





    @Scope("singleton")
    static class scopeTest {

        @PostConstruct // 객체 생성, 의존관계 주입 완료 후에 호출해주는 어노테이션
        void init(){
            System.out.println("postConstruct call");
        }

        @PreDestroy
        void close()
        {

        }
    }
}
