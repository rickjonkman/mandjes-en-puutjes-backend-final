package dev.rick.mandjesenpuutjesback20.models.shoppingList;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "grocery_list")
public class CurrentShoppingList extends ShoppingList {

    @OneToMany
    private List<Product> groceries;


}
