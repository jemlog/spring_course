package study.jpatoyproject.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Grade {
    BRONZE("4급"), SILVER("3급"), GOLD("2급"), VIP("1급");

    private String description;
}
