package dev.rick.mandjesenpuutjesback20.models.shoppingList;

import dev.rick.mandjesenpuutjesback20.models.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "lists")
public class ShoppingList {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shopping_list_generator")
    @SequenceGenerator(name = "shopping_list_generator", sequenceName = "list_id_seq", allocationSize = 1)
    private int id;

    @OneToMany
    private List<Grocery> groceries = new ArrayList<>();

    @OneToOne
    private User listOwner;

    private LocalDate creationDate;

    private boolean currentList;

    public void addGroceryToList(Grocery grocery) {
        groceries.add(grocery);
    }

    public void removeGroceryFromList(Grocery grocery) {
        groceries.remove(grocery);
    }
}
