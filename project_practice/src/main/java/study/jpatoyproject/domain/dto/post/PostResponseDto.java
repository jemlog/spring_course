package study.jpatoyproject.domain.dto.post;

import lombok.Data;
import lombok.NoArgsConstructor;
import study.jpatoyproject.domain.Address;
import study.jpatoyproject.domain.Gender;
import study.jpatoyproject.domain.Grade;

@Data
@NoArgsConstructor
public class PostResponseDto {

    private Long id;
    private String title;
    private String description;



    public PostResponseDto(Long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }
}
