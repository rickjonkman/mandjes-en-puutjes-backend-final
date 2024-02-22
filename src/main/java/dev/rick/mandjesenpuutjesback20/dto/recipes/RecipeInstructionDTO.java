package dev.rick.mandjesenpuutjesback20.dto.recipes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RecipeInstructionDTO {

    private int step;

    private String instruction;
}
