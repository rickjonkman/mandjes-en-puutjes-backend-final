package dev.rick.mandjesenpuutjesback20.services;

import dev.rick.mandjesenpuutjesback20.converters.IngredientConverter;
import dev.rick.mandjesenpuutjesback20.dto.recipes.IngredientDTO;
import dev.rick.mandjesenpuutjesback20.dto.recipes.IngredientMeasuredDTO;
import dev.rick.mandjesenpuutjesback20.dto.recipes.RecipeIngredientsDTO;
import dev.rick.mandjesenpuutjesback20.exceptions.RecordNotFound;
import dev.rick.mandjesenpuutjesback20.models.recipe.Ingredient;
import dev.rick.mandjesenpuutjesback20.models.recipe.IngredientMeasured;
import dev.rick.mandjesenpuutjesback20.models.recipe.Recipe;
import dev.rick.mandjesenpuutjesback20.repositories.IngredientRepository;
import dev.rick.mandjesenpuutjesback20.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class IngredientService {

    private final IngredientRepository ingredientRepository;
    private final RecipeRepository recipeRepository;
    private final IngredientConverter converter;

    public IngredientService(IngredientRepository ingredientRepository,
                             RecipeRepository recipeRepository,
                             IngredientConverter converter) {
        this.ingredientRepository = ingredientRepository;
        this.recipeRepository = recipeRepository;
        this.converter = converter;
    }

    public List<IngredientMeasuredDTO> addIngredientsToRecipe(long id, List<IngredientMeasuredDTO> inputDTO) {
        Recipe foundRecipe = findRecipeById(id);

        List<IngredientMeasuredDTO> outputDTOList;

        List<IngredientMeasured> measuredList = converter.convertToMeasuredList(inputDTO);
        List<Ingredient> ingredientList = converter.convertMeasuredListToIngredientList(measuredList);
        foundRecipe.setMeasuredIngredients(measuredList);
        foundRecipe.setIngredientNames(ingredientList);
        recipeRepository.save(foundRecipe);

        outputDTOList = converter.convertMeasuredListToDTOList(measuredList);
        return outputDTOList;

    }

    public RecipeIngredientsDTO getIngredientsFromRecipe(long id) {
        RecipeIngredientsDTO outputDTO = new RecipeIngredientsDTO();

        Recipe foundRecipe = findRecipeById(id);
        List<IngredientMeasuredDTO> measuredDTOList = converter.convertMeasuredListToDTOList(foundRecipe.getMeasuredIngredients());
        List<IngredientDTO> ingredientDTOList = converter.convertIngredientListToDTO(foundRecipe.getIngredientNames());

        outputDTO.setIngredients(ingredientDTOList);
        outputDTO.setIngredientsMeasured(measuredDTOList);

        return outputDTO;
    }

    public void addIngredientToRecipe(long id, IngredientMeasuredDTO inputDTO) {
        Recipe foundRecipe = findRecipeById(id);
        IngredientMeasured measured = converter.convertToMeasured(inputDTO);
        Ingredient ingredient = converter.convertMeasuredToIngredient(measured);

        foundRecipe.addIngredientMeasured(measured);
        foundRecipe.addIngredient(ingredient);
        recipeRepository.save(foundRecipe);
    }

    public void removeIngredientFromRecipe(long id, IngredientMeasuredDTO inputDTO) {
        Recipe foundRecipe = findRecipeById(id);
        IngredientMeasured measured = converter.convertToMeasured(inputDTO);
        Ingredient ingredient = converter.convertMeasuredToIngredient(measured);

        foundRecipe.removeMeasuredIngredient(measured);
        foundRecipe.removeIngredient(ingredient);
        recipeRepository.save(foundRecipe);
    }


    public Recipe findRecipeById(long id) {
        Optional<Recipe> optionalRecipe = recipeRepository.findById(id);
        if (optionalRecipe.isEmpty()) {
            throw new RecordNotFound(id);
        } else {
            return optionalRecipe.get();
        }
    }
}
