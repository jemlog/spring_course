package hello.core.lifecycleblog;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

public class BeanLifeCycleBlogTest {

    @Test
    public void lifeCycleTest(){
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetWorkClientBlog client = ac.getBean(NetWorkClientBlog.class);
        ac.close();
    }

    @Configuration
    static class LifeCycleConfig{

        @Scope("proto")
        @Bean(initMethod = "init", destroyMethod = "close")
        public NetWorkClientBlog netWorkClient()
        {
            NetWorkClientBlog netWorkClient = new NetWorkClientBlog();
            netWorkClient.setUrl("http://devjem.tistory.com");
            return netWorkClient;
        }

    }
}
