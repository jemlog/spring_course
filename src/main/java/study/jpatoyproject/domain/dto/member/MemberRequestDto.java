package study.jpatoyproject.domain.dto.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import study.jpatoyproject.domain.Gender;
import study.jpatoyproject.domain.Grade;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberRequestDto {

    private String name;
    private int age;
    private int money;
    private Grade grade;
    private Gender gender;
    private String city;
    private String street;
}
