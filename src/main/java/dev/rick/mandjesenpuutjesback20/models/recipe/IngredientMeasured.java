package dev.rick.mandjesenpuutjesback20.models.recipe;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class IngredientMeasured {

    private int amount;
    private String unit;
    private String name;
}
