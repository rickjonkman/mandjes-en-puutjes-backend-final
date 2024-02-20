package dev.rick.mandjesenpuutjesback20.dto.user;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class RegistrationDTO {

    private String username;
    private boolean enabled;
    private long userId;
    private String firstname;
    private boolean showMeat;
    private boolean showFish;
    private boolean showVegetarian;
    private boolean showVegan;
}
