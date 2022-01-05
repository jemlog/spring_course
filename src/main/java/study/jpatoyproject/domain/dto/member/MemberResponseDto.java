package study.jpatoyproject.domain.dto.member;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import study.jpatoyproject.domain.Address;
import study.jpatoyproject.domain.Gender;
import study.jpatoyproject.domain.Grade;

@Data
@NoArgsConstructor
public class MemberResponseDto {

    private Long id;
    private String name;
    private int age;
    private Grade grade;
    private Gender gender;
    private Address address;


    public MemberResponseDto(Long id, String name, int age, Grade grade, Gender gender, Address address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.grade = grade;
        this.gender = gender;
        this.address = address;
    }
}
