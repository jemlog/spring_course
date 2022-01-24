package study.jpatoyproject.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.jpatoyproject.domain.post.Post;
import study.jpatoyproject.util.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String username;

    private String password;

    private String name;

    private int age;

    private int money;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private Grade grade;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")
    private List<Post> postList = new ArrayList<>();


    public void spentMoney(int spent){
        money -= spent;
        if(money < 0)
        {
            throw new IllegalStateException("돈이 0원보다 적으면 안된다.");
        }
    }

    public void addMoney(int earn)
    {
        money += earn;
    }



    @Builder
    public Member(String name, String username, String password , int age,int money, Gender gender, Grade grade, Address address)
    {
        this.name = name;
        this.username = username;
        this.password = password;
        this.age = age;
        this.money = money;
        this.gender = gender;
        this.grade = grade;
        this.address = address;
    }



}
