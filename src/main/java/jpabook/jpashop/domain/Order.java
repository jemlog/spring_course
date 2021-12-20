package jpabook.jpashop.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "order_id") // 소문자 _ 이 방식을 dba분들이 좋아한다
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id") // FK 이름
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL) // order를 저장하면 orderItems도 같이 저장한다.
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;  // 주문 상태 (order, cancel)


    // === 연관관계  === //
    // 핵심적으로 컨트롤 하는 쪽에 넣자
    public void setMember(Member member)
    {
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem)
    {
        this.orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery)
    {
        this.delivery = delivery;
        delivery.setOrder(this);
    }


    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    // ==== 생성 메서드 ===== //
    // 복잡한 생성은 생성 메서드가 있으면 좋다
    // 생성 지점 변경이 있어야 되면 이 메서드만 생성하면 됨! 응집성 향상
    public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems)
    {
        Order order = new Order();
        order.setMember(member);
        order.setDelivery(delivery);
        for(OrderItem orderItem : orderItems)
        {
            order.addOrderItem(orderItem);
        }
        order.setStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());
        return order;
    }

    /**
     * 주문 취소
     */
    public void cancel()
    {
        if(delivery.getStatus() == DeliveryStatus.COMP){
            throw new IllegalStateException("이미 배송 완료된 상품은 취소가 불가능 합니다.");
        }
        this.setStatus(OrderStatus.CANCEL);
        for(OrderItem orderItem : orderItems)
        {
            orderItem.cancel();  // 연관관계 편의 메서드 만들어줬을때 의미 있음
        }

    }

    /**
     * 전체 주문 가격 조회
     */
    public int getTotalPrice()
    {
        int totalPrice = 0;
        for(OrderItem orderItem: orderItems)
        {
            totalPrice  += orderItem.getTotalPrice();
        }
        return totalPrice;
    }

}
