package hello.advanced.trace.threadlocal.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadLocalService {

    // 다 쓰고 나면 꼭 remove로 제공해줘야 한다. 보통 메모리 누수 발생한다.
    private  ThreadLocal<String> nameStore = new ThreadLocal<>();

    public String logic(String name)
    {
        log.info("저장 name={} -> nameStore={}",name,nameStore.get());
        nameStore.set(name);
        sleep(1000);
        log.info("조회 nameStore={}",nameStore.get());
        return nameStore.get();
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        }
        catch (InterruptedException e)
        {
              e.printStackTrace();
        }
    }
}
