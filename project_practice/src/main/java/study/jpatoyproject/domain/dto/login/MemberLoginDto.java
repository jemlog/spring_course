package study.jpatoyproject.domain.dto.login;

import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class MemberLoginDto {

    @NotNull
    private String username;
    @NotNull
    private String password;
}
