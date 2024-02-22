package dev.rick.mandjesenpuutjesback20.models.recipe;

import dev.rick.mandjesenpuutjesback20.exceptions.ItemNotInList;
import dev.rick.mandjesenpuutjesback20.exceptions.ProductHasAlreadyBeenAdded;
import dev.rick.mandjesenpuutjesback20.models.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "recipes")
public class Recipe {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "servings", nullable = false)
    private int servings;

    @Column(name = "minutes", nullable = false)
    private int prepTime;

    @Column(name = "image")
    private String imageFileName;

    @ElementCollection
    @CollectionTable(name = "recipe_tags", joinColumns = @JoinColumn(name = "recipe_id"))
    private List<Tag> tags;

    @ElementCollection
    @CollectionTable(name = "recipe_supplies", joinColumns = @JoinColumn(name = "recipe_id"))
    private List<String> supplies;

    @ManyToMany(mappedBy = "recipes")
    private List<Ingredient> ingredientNames;

    @ElementCollection
    @CollectionTable(name = "recipe_ingredients_measured", joinColumns =
    @JoinColumn(name = "recipe_id"))
    private List<IngredientMeasured> measuredIngredients;

    @ElementCollection
    @CollectionTable(name = "recipe_instructions", joinColumns =
    @JoinColumn(name = "recipe_id"))
    private List<Instruction> instructions;

    @ManyToOne
    @JoinColumn(name = "creator")
    private User createdByUser;

    @Column(name = "saved")
    private int savedByUsers;


    public void addIngredient(Ingredient ingredient) {
        if (this.ingredientNames.contains(ingredient)) {
            throw new ProductHasAlreadyBeenAdded(ingredient.getIngredientName());
        } else {
            this.ingredientNames.add(ingredient);
        }
    }

    public void addIngredientMeasured(IngredientMeasured measured) {
        if (this.measuredIngredients.contains(measured)) {
            throw new ProductHasAlreadyBeenAdded(measured.getName());
        } else {
            this.measuredIngredients.add(measured);
        }
    }

    public void removeIngredient(Ingredient ingredient) {
        if (this.ingredientNames.contains(ingredient)) {
            this.ingredientNames.remove(ingredient);
        } else {
            throw new ItemNotInList(ingredient.getIngredientName());
        }
    }

    public void removeMeasuredIngredient(IngredientMeasured measured) {
        if (this.measuredIngredients.contains(measured)) {
            this.measuredIngredients.remove(measured);
        } else {
            throw new ItemNotInList(measured.getName());
        }
    }
}
