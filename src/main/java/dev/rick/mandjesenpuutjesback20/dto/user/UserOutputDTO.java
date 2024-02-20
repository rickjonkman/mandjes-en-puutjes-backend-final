package dev.rick.mandjesenpuutjesback20.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserOutputDTO {

    private String username;
    private boolean enabled;
    private long userId;
    private String firstname;
    private boolean showMeat;
    private boolean showFish;
    private boolean showVegetarian;
    private boolean showVegan;
}
