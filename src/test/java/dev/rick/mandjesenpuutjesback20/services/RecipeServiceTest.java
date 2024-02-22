package dev.rick.mandjesenpuutjesback20.services;

import dev.rick.mandjesenpuutjesback20.converters.RecipeConverter;
import dev.rick.mandjesenpuutjesback20.dto.recipes.RecipeOutputDTO;
import dev.rick.mandjesenpuutjesback20.models.recipe.*;
import dev.rick.mandjesenpuutjesback20.models.user.User;
import dev.rick.mandjesenpuutjesback20.repositories.IngredientRepository;
import dev.rick.mandjesenpuutjesback20.repositories.RecipeRepository;
import dev.rick.mandjesenpuutjesback20.repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc(addFilters = false)
class RecipeServiceTest {

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    UserRepository userRepository;

    @Mock
    IngredientRepository ingredientRepository;

    @Mock
    RecipeConverter recipeConverter;

    @InjectMocks
    private RecipeService recipeService;

    Recipe recipeOne = new Recipe();

    User rick = new User();

    Tag tagOne = new Tag();
    Tag tagTwo = new Tag();
    Tag tagThree = new Tag();

    Ingredient ingredientOne = new Ingredient();
    Ingredient ingredientTwo = new Ingredient();
    Ingredient ingredientThree = new Ingredient();

    Instruction stepOne = new Instruction();
    Instruction stepTwo = new Instruction();
    Instruction stepThree = new Instruction();

    RecipeOutputDTO recipeOutputDTO = new RecipeOutputDTO();



    @BeforeEach
    void setUp() {
        recipeOne.setName("Kippensoep");
        recipeOne.setId(1);
        recipeOne.setServings(4);
        recipeOne.setCreatedByUser(rick);
        recipeOne.setTags(List.of(tagOne, tagTwo, tagThree));
        recipeOne.setPrepTime(30);
        recipeOne.setImageFileName("foto.png");
        recipeOne.setIngredientNames(List.of(ingredientOne, ingredientTwo, ingredientThree));
        recipeOne.setInstructions(List.of(stepOne, stepTwo, stepThree));
        recipeOne.setSupplies(List.of("Soeplepel", "Pan"));


    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void itShouldCheckIfRecipeNameDoesNotExist() {

        given(recipeRepository.findRecipeByName(recipeOne.getName())).willReturn(Optional.of(recipeOne));
        boolean isPresent = recipeService.isRecipeNameAvailable(recipeOne.getName());

        assertTrue(isPresent);
    }



}