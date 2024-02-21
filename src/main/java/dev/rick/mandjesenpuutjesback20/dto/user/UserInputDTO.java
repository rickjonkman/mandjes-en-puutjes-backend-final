package dev.rick.mandjesenpuutjesback20.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class UserInputDTO {

    @Email
    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private String firstname;

    @NotNull
    private boolean enabled;

    @NotNull
    private boolean showMeat;

    @NotNull
    private boolean showFish;

    @NotNull
    private boolean showVegetarian;

    @NotNull
    private boolean showVegan;
}
