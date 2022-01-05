package hello.advanced.app.v2;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV1;
import hello.advanced.trace.hellotrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV2 {

    private final OrderServiceV2 orderService;
    private final HelloTraceV2 trace;

    @GetMapping("/v2/request")
    public String request(String itemId) // 클린코드 3장 : 함수는 한가지 작업만 해야한다. request 메서드에 try catch를
                                         // 걸었다면 catch 구문에서 메서드가 끝나게 하자. 다른 로직 추가 금지!
    {
        TraceStatus status = null;
        try {  // try 블록 안에서만 유효
               // 클린코드 3장 : try catch 구문 안의 코드는 한줄로 줄이자. 외부로 메서드 추출 해주는게 좋음
            status = trace.begin("orderController.request()");
            orderService.orderItem(status.getTraceId(),itemId);  // try catch가 없으면 예외가 그대로 나가버린다.
            trace.end(status);
            return "ok";
        }catch (Exception e)
        {
            trace.exception(status,e);  // e를 throw 안해주면 에러가 발생하지 않는다. 정상 흐름으로 변하면 안된다.
            throw e; // 예외를 꼭 다시 던져줘야 한다.
        }


    }
}
