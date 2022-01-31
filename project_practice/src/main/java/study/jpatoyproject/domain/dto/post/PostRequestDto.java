package study.jpatoyproject.domain.dto.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import study.jpatoyproject.domain.Gender;
import study.jpatoyproject.domain.Grade;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostRequestDto {

    private String title;
    private String description;

}
