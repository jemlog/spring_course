package hello.advanced.app.v2;

import hello.advanced.trace.TraceId;
import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV1;
import hello.advanced.trace.hellotrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV2 {

    private final HelloTraceV2 trace;

    public void save(TraceId traceId, String itemId)
    {
        TraceStatus status = null;
        try {  // try 블록 안에서만 유효
            status = trace.beginSync(traceId,"orderRepository.save()");
            if (itemId.equals("ex"))
            {
                throw new IllegalStateException("예외발생");
            }
            sleep(1000);
            trace.end(status);

        }catch (Exception e)
        {
            trace.exception(status,e);
            throw e; // 예외를 꼭 다시 던져줘야 한다.
        }




        // 저장 로직

    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
