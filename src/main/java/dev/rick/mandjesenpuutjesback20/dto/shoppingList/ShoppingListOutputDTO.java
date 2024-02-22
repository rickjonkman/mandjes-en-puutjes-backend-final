package dev.rick.mandjesenpuutjesback20.dto.shoppingList;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ShoppingListOutputDTO {

    private long id;
    private List<GroceryDTO> groceries;
    private String username;
    private LocalDate creationDate;
    private boolean newList;

}
