package dev.rick.mandjesenpuutjesback20.dto.recipes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IngredientMeasuredDTO {

    private int amount;
    private String unit;
    private String name;
}
