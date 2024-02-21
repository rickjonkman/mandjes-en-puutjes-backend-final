package dev.rick.mandjesenpuutjesback20.dto.recipes;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RecipeBasicDTO {

    private long id;

    private String name;

    private int servings;

    private int prepTime;

    private List<TagDTO> tags;

    private String creator;
}
