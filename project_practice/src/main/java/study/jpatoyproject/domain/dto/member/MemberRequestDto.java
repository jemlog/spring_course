package study.jpatoyproject.domain.dto.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import study.jpatoyproject.domain.Gender;
import study.jpatoyproject.domain.Grade;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberRequestDto {

    private String name;
    @NotEmpty(message = "아이디를 꼭 입력해야 합니다.")
    private String username;
    @NotEmpty(message = "비밀번호를 꼭 입력해야 합니다.")
    private String password;
    private int age;
    private int money;
    private Grade grade;
    private Gender gender;
    private String city;
    private String street;
}
