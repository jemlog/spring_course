package jpabook.jpashop.api;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 *  Order
 *  Order -> Member
 *  Order -> Delivery
 */
@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

    private final OrderRepository orderRepository;

    @GetMapping("/api/v1/simple-orders")
    public List<Order> orderV1(){
        List<Order> all = orderRepository.findAll();
        for (Order order : all) {
            order.getMember().getName();
            order.getDelivery().getAddress();

        }
        return all;
    }
    @GetMapping("/api/v2/simple-orders")
    public Result orderV2() {
        List<Order> orders = orderRepository.findAll();
        List<SimpleOrderDto> collect = orders.stream().map(o -> new SimpleOrderDto(o)).collect(Collectors.toList());
        return new Result(collect);
    }

    @GetMapping("/api/v3/simple-orders")
    public Result orderV3() {

        List<Order> orders = orderRepository.findAllWithMemberDelivery();
        List<SimpleOrderDto> collect = orders.stream().map(o -> new SimpleOrderDto(o)).collect(Collectors.toList());
        return new Result(collect);
    }


    @Data
    @AllArgsConstructor
    static class Result<T>
    {
        private T data;
    }
    @Data
    static class SimpleOrderDto {
        private Long orderId;
        private String name;
        private LocalDateTime localDateTime;
        private OrderStatus orderStatus;
        private Address address;

        public SimpleOrderDto(Order order)
        {
            this.orderId = order.getId();
            this.name = order.getMember().getName();
            this.localDateTime = order.getOrderDate();
            this.orderStatus = order.getStatus();
            this.address = order.getDelivery().getAddress();
        }
    }
}
