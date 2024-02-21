package dev.rick.mandjesenpuutjesback20.repositories;

import dev.rick.mandjesenpuutjesback20.models.recipe.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    Optional<Recipe> findRecipeByName(String name);
}
