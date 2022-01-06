package study.jpatoyproject.domain;


import lombok.Getter;
import study.jpatoyproject.util.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class OrderItem extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "orderItem_id")
    private Long id;
}
