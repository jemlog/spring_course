package study.jpatoyproject.domain.dto.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.criteria.CriteriaBuilder;

@Data
public class MemberSearch {

    private String name;
    private Integer age;
}
