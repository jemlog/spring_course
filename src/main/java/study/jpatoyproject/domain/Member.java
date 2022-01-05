package study.jpatoyproject.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.jpatoyproject.util.BaseEntity;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    private int age;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private Grade grade;

    @Embedded
    private Address address;

    @Builder
    public Member(String name, int age, Gender gender, Grade grade, Address address)
    {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.grade = grade;
        this.address = address;
    }



}
