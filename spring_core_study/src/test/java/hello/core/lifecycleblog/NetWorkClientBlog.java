package hello.core.lifecycleblog;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetWorkClientBlog implements InitializingBean, DisposableBean {

    @Override
    public void destroy() throws Exception {

    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }



    private String url;

    public NetWorkClientBlog()
    {
        System.out.println("생성자 호출, url = " + url);
        connect();

    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    // 서비스 시작 시 호출
    public void connect()
    {
        System.out.println("connect : " + url);
    }

    public void call(String message)
    {
        System.out.println("call " + url + " message = " + message);
    }

    // 서비스 종료 시 호출
    public void disconnect(){
        System.out.println("close " + url);
    }


    @PostConstruct
    public void init(){
        System.out.println("after Properties Set");
        connect();
        call("초기화 연결 메세지");
    }

    @PreDestroy
    public void close() {
        System.out.println("after destroy set");
        disconnect();
    }


}
