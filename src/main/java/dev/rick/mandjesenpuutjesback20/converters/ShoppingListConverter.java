package dev.rick.mandjesenpuutjesback20.converters;

import dev.rick.mandjesenpuutjesback20.dto.shoppingList.GroceryDTO;
import dev.rick.mandjesenpuutjesback20.dto.shoppingList.ShoppingListDTO;
import dev.rick.mandjesenpuutjesback20.models.shoppingList.Grocery;
import dev.rick.mandjesenpuutjesback20.models.shoppingList.ShoppingList;
import dev.rick.mandjesenpuutjesback20.models.user.User;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Component
public class ShoppingListConverter {

    public ShoppingList convertToNewShoppingList(ShoppingListDTO shoppingListDTO, User user) {
        ShoppingList newList = new ShoppingList();
        newList.setOwner(user);
        newList.setCreationDate(shoppingListDTO.getCreationDate());
        newList.setNewList(true);
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

        dto.setId(shoppingList.getId());
        dto.setNewList(shoppingList.isNewList());
        dto.setCreationDate(shoppingList.getCreationDate());
        dto.setGroceries(groceryDTOList);

        return dto;
    }

    public Grocery convertToGrocery(GroceryDTO groceryDTO) {
        Grocery grocery = new Grocery();
        grocery.setProductName(groceryDTO.getGroceryName());
        return grocery;
    }

    public GroceryDTO convertGroceryToDTO(Grocery grocery) {
        GroceryDTO dto = new GroceryDTO();
        dto.setGroceryName(grocery.getProductName());
        return dto;
    }


}
