package study.jpatoyproject.domain;

import lombok.Getter;
import study.jpatoyproject.util.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "orders")
@Getter
public class Order extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "orders_id")
    private Long id;
}
