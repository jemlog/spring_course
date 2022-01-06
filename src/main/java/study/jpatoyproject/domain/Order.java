package study.jpatoyproject.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "orders")
@Getter
public class Order {

    @Id @GeneratedValue
    @Column(name = "orders_id")
    private Long id;
}
