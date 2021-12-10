package hello.core.blog;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BookAppConfig {

    @Bean
    public Book getBook(){
        return new Book();
    }
}
