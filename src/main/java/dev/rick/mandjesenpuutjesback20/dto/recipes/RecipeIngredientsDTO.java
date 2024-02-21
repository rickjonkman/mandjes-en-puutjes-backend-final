package dev.rick.mandjesenpuutjesback20.dto.recipes;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class RecipeIngredientsDTO {

    private List<IngredientDTO> ingredients;
    private List<IngredientMeasuredDTO> ingredientsMeasured;
}
