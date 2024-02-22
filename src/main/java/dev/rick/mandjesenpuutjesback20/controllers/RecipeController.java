package dev.rick.mandjesenpuutjesback20.controllers;

import dev.rick.mandjesenpuutjesback20.dto.recipes.RecipeBasicDTO;
import dev.rick.mandjesenpuutjesback20.dto.recipes.RecipeOutputDTO;
import dev.rick.mandjesenpuutjesback20.services.RecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.security.Principal;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping("/auth/add-new")
    public ResponseEntity<RecipeBasicDTO> addNewRecipe(Principal principal, @RequestBody RecipeBasicDTO inputDTO) {
        RecipeBasicDTO outputDTO = recipeService.addNewRecipe(principal, inputDTO);

        URI uri = URI
                .create(ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/api/recipes/"+outputDTO.getId())
                        .toUriString());
        return ResponseEntity.created(uri).body(outputDTO);
    }

    @GetMapping("/open/{recipeId}/get")
    public ResponseEntity<RecipeOutputDTO> getRecipeById(@PathVariable("recipeId") long recipeId) {
        RecipeOutputDTO outputDTO = recipeService.getRecipeById(recipeId);
        return ResponseEntity.ok(outputDTO);
    }
}
