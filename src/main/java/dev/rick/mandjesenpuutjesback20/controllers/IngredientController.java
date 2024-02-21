package dev.rick.mandjesenpuutjesback20.controllers;

import dev.rick.mandjesenpuutjesback20.dto.recipes.IngredientMeasuredDTO;
import dev.rick.mandjesenpuutjesback20.dto.recipes.RecipeIngredientsDTO;
import dev.rick.mandjesenpuutjesback20.services.IngredientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/recipes")
public class IngredientController {

    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping("/{recipeId}/add-ingredients")
    public ResponseEntity<List<IngredientMeasuredDTO>> addIngredientsToRecipe(@PathVariable("recipeId") long id, @RequestBody List<IngredientMeasuredDTO> inputDTO) {
        List<IngredientMeasuredDTO> ingredientsMeasured = ingredientService.addIngredientsToRecipe(id, inputDTO);

        URI uri = URI
                .create(ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/api/recipes/{recipeId}/get-ingredients")
                        .toUriString());

        return ResponseEntity.created(uri).body(ingredientsMeasured);
    }

    @GetMapping("/{recipeId}/get-ingredients")
    public ResponseEntity<RecipeIngredientsDTO> getIngredientsFromRecipe(@PathVariable("recipeId") long id) {
        RecipeIngredientsDTO outputDTO = ingredientService.getIngredientsFromRecipe(id);
        return ResponseEntity.ok(outputDTO);
    }

    @PutMapping("/{recipeId}/add-ingredient")
    public ResponseEntity<?> addIngredientToRecipe(@PathVariable("recipeId") long id, @RequestBody IngredientMeasuredDTO inputDTO) {
        ingredientService.addIngredientToRecipe(id, inputDTO);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{recipeId}/remove-ingredient")
    public ResponseEntity<?> removeIngredientFromRecipe(@PathVariable("recipeId") long id, @RequestBody IngredientMeasuredDTO inputDTO) {
        ingredientService.removeIngredientFromRecipe(id, inputDTO);
        return ResponseEntity.noContent().build();
    }

}
