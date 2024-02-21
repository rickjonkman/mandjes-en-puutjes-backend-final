package dev.rick.mandjesenpuutjesback20.dto.shoppingList;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ShoppingListDTO {

    private long id;
    private List<GroceryDTO> groceries;
    private String username;
    private LocalDate creationDate;
    private boolean newList;

}
