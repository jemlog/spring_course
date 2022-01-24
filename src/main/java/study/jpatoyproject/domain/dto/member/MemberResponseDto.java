package study.jpatoyproject.domain.dto.member;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import study.jpatoyproject.domain.Address;
import study.jpatoyproject.domain.Gender;
import study.jpatoyproject.domain.Grade;
import study.jpatoyproject.domain.Member;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class MemberResponseDto implements Serializable {

    private Long id;
    private String name;
    private int age;
    private int money;
//    private Grade grade;
//    private Gender gender;
//    private Address address;


    public MemberResponseDto(Long id, String name, int age,int money) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.money = money;
    }

    public MemberResponseDto(Member member) {
        this.id = member.getId();
        this.name = member.getName();
        this.age = member.getAge();
        this.money = member.getMoney();
    }
}
