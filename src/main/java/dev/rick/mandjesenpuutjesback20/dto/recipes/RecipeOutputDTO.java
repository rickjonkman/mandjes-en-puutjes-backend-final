package dev.rick.mandjesenpuutjesback20.dto.recipes;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Data
public class RecipeOutputDTO {

    private long id;
    private String name;
    private int servings;
    private int prepTime;
    private String imagePath;
    private List<TagDTO> tags;
    private List<String> supplies;
    private List<IngredientDTO> ingredientNames;
    private List<IngredientMeasuredDTO> measuredIngredients;
    private List<RecipeInstructionDTO> instructions;
    private String createdByUser;

}
