package dev.rick.mandjesenpuutjesback20.controllers;

import dev.rick.mandjesenpuutjesback20.dto.recipes.RecipeBasicDTO;
import dev.rick.mandjesenpuutjesback20.services.RecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @PostMapping("/add-new")
    public ResponseEntity<RecipeBasicDTO> addNewRecipe(Principal principal, @RequestBody RecipeBasicDTO inputDTO) {
        RecipeBasicDTO outputDTO = recipeService.addNewRecipe(principal, inputDTO);

        URI uri = URI
                .create(ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/api/recipes/"+outputDTO.getId())
                        .toUriString());
        return ResponseEntity.created(uri).body(outputDTO);
    }
}
