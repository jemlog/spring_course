package jpabook.jpashop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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

    @JsonIgnore
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Order> orders = new ArrayList<>();    // 초기화에 대해 고민할 필요 없다.
                                                       // 엔티티 persist하는 순간 컬렉션을 한번 감싼다. 내장 컬렉션으로 변경된다.
                                                       // 컬렉션 함부로 바꾸지 않는다!
}
