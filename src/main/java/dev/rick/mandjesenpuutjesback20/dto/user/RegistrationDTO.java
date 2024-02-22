package dev.rick.mandjesenpuutjesback20.dto.user;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDTO {

    private String username;
    private boolean enabled;
    private String firstname;
    private boolean showMeat;
    private boolean showFish;
    private boolean showVegetarian;
    private boolean showVegan;
}
