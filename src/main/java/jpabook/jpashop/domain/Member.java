package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded  // 둘중에 하나만 있어도 되지만 보통 둘다 해준다.
    private Address address;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Order> orders = new ArrayList<>();
}
