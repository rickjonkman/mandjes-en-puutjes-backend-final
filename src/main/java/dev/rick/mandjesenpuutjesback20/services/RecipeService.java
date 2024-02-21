package dev.rick.mandjesenpuutjesback20.services;

import dev.rick.mandjesenpuutjesback20.converters.RecipeConverter;
import dev.rick.mandjesenpuutjesback20.dto.recipes.RecipeBasicDTO;
import dev.rick.mandjesenpuutjesback20.exceptions.NameIsTakenException;
import dev.rick.mandjesenpuutjesback20.models.recipe.Recipe;
import dev.rick.mandjesenpuutjesback20.models.user.User;
import dev.rick.mandjesenpuutjesback20.repositories.RecipeRepository;
import dev.rick.mandjesenpuutjesback20.repositories.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeConverter converter;
    private final UserRepository userRepository;

    public RecipeService(RecipeRepository recipeRepository, RecipeConverter converter,
                         UserRepository userRepository) {
        this.recipeRepository = recipeRepository;
        this.converter = converter;
        this.userRepository = userRepository;
    }

    public RecipeBasicDTO addNewRecipe(Principal principal, RecipeBasicDTO inputDTO) {
        boolean nameAvailability = isRecipeNameAvailable(inputDTO.getName());
        User recipeCreator = findUserByUsername(principal.getName());

        RecipeBasicDTO outputDTO = new RecipeBasicDTO();

        if (nameAvailability) {
            Recipe newRecipe = converter.convertBasicInputToRecipe(inputDTO, recipeCreator);
            recipeRepository.save(newRecipe);

            outputDTO = converter.convertToBasicDTO(newRecipe);
        }

        return outputDTO;

    }

    public boolean isRecipeNameAvailable(String name) {
        Optional<Recipe> optionalRecipe = recipeRepository.findRecipeByName(name);
        if (optionalRecipe.isPresent()) {
            throw new NameIsTakenException(name);
        } else {
            return true;
        }
    }

    public User findUserByUsername(String username) {
        Optional<User> optionalUser = userRepository.findById(username);
        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException(username);
        } else {
            return optionalUser.get();
        }
    }
}
