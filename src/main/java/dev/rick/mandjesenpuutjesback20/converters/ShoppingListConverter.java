package dev.rick.mandjesenpuutjesback20.converters;

import dev.rick.mandjesenpuutjesback20.dto.shoppingList.GroceryDTO;
import dev.rick.mandjesenpuutjesback20.dto.shoppingList.GroceryPlaceholderDTO;
import dev.rick.mandjesenpuutjesback20.dto.shoppingList.ShoppingListDTO;
import dev.rick.mandjesenpuutjesback20.dto.shoppingList.ShoppingListInputDTO;
import dev.rick.mandjesenpuutjesback20.models.shoppingList.Grocery;
import dev.rick.mandjesenpuutjesback20.models.shoppingList.GroceryPlaceholder;
import dev.rick.mandjesenpuutjesback20.models.shoppingList.ShoppingList;
import dev.rick.mandjesenpuutjesback20.models.user.User;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class ShoppingListConverter {

    public ShoppingList convertToNewShoppingList(User user, ShoppingListInputDTO inputDTO) {

        ShoppingList newList = new ShoppingList();
        newList.setOwner(user);
        newList.setCreationDate(LocalDate.now());
        newList.setNewList(true);
        newList.setGroceries(convertToGroceryList(inputDTO.getGroceries()));
        return newList;
    }

    public List<ShoppingListDTO> convertShoppingListsToDTOList(List<ShoppingList> shoppingLists) {
        List<ShoppingListDTO> shoppingListDTOS =  new ArrayList<>();

        for (ShoppingList shoppingList : shoppingLists) {
            ShoppingListDTO dto = convertShoppingListToDTO(shoppingList);
            shoppingListDTOS.add(dto);
        }

        return shoppingListDTOS;
    }

    public ShoppingListDTO convertShoppingListToDTO(ShoppingList shoppingList) {
        ShoppingListDTO dto = new ShoppingListDTO();

        List<GroceryDTO> groceryDTOList = new ArrayList<>();
        for (Grocery grocery : shoppingList.getGroceries()) {
            GroceryDTO convertedGrocery = convertGroceryToDTO(grocery);
            groceryDTOList.add(convertedGrocery);
        }

        dto.setUsername(shoppingList.getOwner().getUsername());
        dto.setId(shoppingList.getId());
        dto.setNewList(shoppingList.isNewList());
        dto.setCreationDate(shoppingList.getCreationDate());
        dto.setGroceries(groceryDTOList);

        return dto;
    }

    public List<Grocery> convertToGroceryList(List<GroceryDTO> groceryDTOList) {

        List<Grocery> groceries = new ArrayList<>();
        for (GroceryDTO dto : groceryDTOList) {
            Grocery grocery = convertToGrocery(dto);
            groceries.add(grocery);
        }
        return groceries;
    }

    public Grocery convertToGrocery(GroceryDTO groceryDTO) {
        Grocery grocery = new Grocery();
        grocery.setName(groceryDTO.getGroceryName());
        return grocery;
    }

    public GroceryDTO convertGroceryToDTO(Grocery grocery) {
        GroceryDTO dto = new GroceryDTO();
        dto.setGroceryName(grocery.getName());
        return dto;
    }


}
