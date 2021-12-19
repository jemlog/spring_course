package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
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

    private LocalDateTime localDateTime;

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

}
