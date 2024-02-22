//package dev.rick.mandjesenpuutjesback20.repositories;
//
//import dev.rick.mandjesenpuutjesback20.dto.recipes.RecipeOutputDTO;
//import dev.rick.mandjesenpuutjesback20.models.recipe.Ingredient;
//import dev.rick.mandjesenpuutjesback20.models.recipe.Instruction;
//import dev.rick.mandjesenpuutjesback20.models.recipe.Recipe;
//import dev.rick.mandjesenpuutjesback20.models.recipe.Tag;
//import dev.rick.mandjesenpuutjesback20.models.user.Authority;
//import dev.rick.mandjesenpuutjesback20.models.user.User;
//import jakarta.persistence.SecondaryTable;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.Set;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.BDDMockito.given;
//import static org.mockito.Mockito.when;
//
//@DataJpaTest
//class RecipeRepositoryTest {
//
//    @Autowired
//    private RecipeRepository recipeRepository;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    Recipe recipeOne = new Recipe();
//
//    User userOne = new User();
//
//    Authority authorityOne = new Authority("rick@novi.nl", "USER");
//
//    Tag tagOne = new Tag();
//    Tag tagTwo = new Tag();
//    Tag tagThree = new Tag();
//
//    Ingredient ingredientOne = new Ingredient();
//    Ingredient ingredientTwo = new Ingredient();
//    Ingredient ingredientThree = new Ingredient();
//
//    Instruction stepOne = new Instruction();
//    Instruction stepTwo = new Instruction();
//    Instruction stepThree = new Instruction();
//
//
//    @BeforeEach
//    void setUp() {
//
//        userOne.setFirstname("Rick");
//        userOne.setUsername("rick@novi.nl");
//        userOne.setPassword("wachtwoord");
//        userOne.setAuthorities(Set.of(authorityOne));
//        userOne.setEnabled(true);
//        userOne.setShowMeat(true);
//        userOne.setShowFish(true);
//        userOne.setShowVegetarian(true);
//        userOne.setShowVegan(true);
//        userRepository.save(userOne);
//
//        recipeOne.setName("Kippensoep");
//        recipeOne.setId(1);
//        recipeOne.setServings(4);
//        recipeOne.setCreatedByUser(userOne);
//        recipeOne.setTags(List.of(tagOne, tagTwo, tagThree));
//        recipeOne.setPrepTime(30);
//        recipeOne.setImageFileName("foto.png");
//        recipeOne.setIngredientNames(List.of(ingredientOne, ingredientTwo, ingredientThree));
//        recipeOne.setInstructions(List.of(stepOne, stepTwo, stepThree));
//        recipeOne.setSupplies(List.of("Soeplepel", "Pan"));
//        recipeRepository.save(recipeOne);
//    }
//
//    @AfterEach
//    void tearDown() {
//
//    }
//
//    @Test
//    void itShouldReturnARecipe() {
//        Recipe recipe = new Recipe();
//
////        given(recipeRepository.findById(recipeOne.getId())).willReturn(Optional.of(recipeOne));
//        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeOne.getId());
//        assertTrue(true);
//
//
//
//        assertEquals(1, recipe.getId());
//        assertEquals("Kippensoep", recipe.getName());
//        assertEquals(4, recipe.getServings());
//        assertEquals(userOne, recipe.getCreatedByUser());
//        assertEquals(List.of(tagOne, tagTwo, tagThree), recipe.getTags());
//        assertEquals(30, recipe.getPrepTime());
//        assertEquals("foto.png", recipe.getImageFileName());
//        assertEquals(List.of(ingredientOne, ingredientTwo, ingredientThree), recipe.getIngredientNames());
//        assertEquals(List.of(stepOne, stepTwo, stepThree), recipe.getInstructions());
//        assertEquals(List.of("Soeplepel", "Pan"), recipe.getSupplies());
//    }
//}