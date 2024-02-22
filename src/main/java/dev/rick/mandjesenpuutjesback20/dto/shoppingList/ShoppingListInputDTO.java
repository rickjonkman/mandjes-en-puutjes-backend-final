package dev.rick.mandjesenpuutjesback20.dto.shoppingList;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ShoppingListInputDTO {

    private List<String> groceries;

}
