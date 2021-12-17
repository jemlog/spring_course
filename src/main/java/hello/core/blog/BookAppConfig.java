package hello.core.blog;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration   // 붙어있으면 싱글톤으로 관리
public class BookAppConfig {

    @Bean
    public Book getBook(){
        return new Book();
    }
}
