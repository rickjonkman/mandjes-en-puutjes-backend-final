package dev.rick.mandjesenpuutjesback20.models.recipe;

import dev.rick.mandjesenpuutjesback20.models.product.Product;
import dev.rick.mandjesenpuutjesback20.models.shoppingList.Grocery;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "ingredients")
public class Ingredient {

    @Id
    private String ingredientName;

    @OneToOne
    private Grocery grocery;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "recipes_ingredients",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private List<Recipe> recipes;
}
