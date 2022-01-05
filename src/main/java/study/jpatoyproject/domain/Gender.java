package study.jpatoyproject.domain;


import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Gender {
    MAN("남자"), WOMAN("여자");

    private String description;


}
