package dev.rick.mandjesenpuutjesback20.repositories;

import dev.rick.mandjesenpuutjesback20.models.recipe.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, String> {

}
