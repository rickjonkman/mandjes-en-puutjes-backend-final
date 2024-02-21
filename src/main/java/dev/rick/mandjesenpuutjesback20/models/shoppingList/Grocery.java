package dev.rick.mandjesenpuutjesback20.models.shoppingList;

import dev.rick.mandjesenpuutjesback20.models.product.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "groceries")
public class Grocery extends Product {

    @ManyToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private ShoppingList shoppingList;



}
