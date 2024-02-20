package dev.rick.mandjesenpuutjesback20.models.shoppingList;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class ShoppingList {

    @Id
    @GeneratedValue
    private long id;


}
