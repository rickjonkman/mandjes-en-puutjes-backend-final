package dev.rick.mandjesenpuutjesback20.dto.user;

import dev.rick.mandjesenpuutjesback20.models.user.Authority;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserAuthDTO {

    private String username;
    private String password;
    private Set<Authority> authoritySet;
}
