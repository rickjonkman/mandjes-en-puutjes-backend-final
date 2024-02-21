package dev.rick.mandjesenpuutjesback20.models.shoppingList;

import dev.rick.mandjesenpuutjesback20.exceptions.GroceryHasAlreadyBeenAdded;
import dev.rick.mandjesenpuutjesback20.exceptions.GroceryNotInList;
import dev.rick.mandjesenpuutjesback20.models.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "shopping_lists")
public class ShoppingList {

    @Id
    @GeneratedValue
    private long id;

    @OneToMany (mappedBy = "shoppingList")
    private List<Grocery> groceries;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @Column(name = "created", nullable = false)
    private LocalDate creationDate;

    @Column(name = "is_new")
    private boolean newList;

    public void addGroceryToList(Grocery grocery) {
        if (groceries.contains(grocery)) {
            throw new GroceryHasAlreadyBeenAdded(grocery.getProductName());
        } else {
            this.groceries.add(grocery);
        }
    }

    public void removeGroceryFromList(Grocery grocery) {
        if (groceries.contains(grocery)) {
            this.groceries.remove(grocery);
        } else {
            throw new GroceryNotInList(grocery.getProductName());
        }
    }
}
