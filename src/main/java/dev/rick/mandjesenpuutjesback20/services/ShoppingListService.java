package dev.rick.mandjesenpuutjesback20.services;

import com.google.gson.Gson;
import dev.rick.mandjesenpuutjesback20.dto.shoppingList.GroceryDTO;
import dev.rick.mandjesenpuutjesback20.dto.shoppingList.ShoppingListOutputDTO;
import dev.rick.mandjesenpuutjesback20.dto.shoppingList.ShoppingListInputDTO;
import dev.rick.mandjesenpuutjesback20.exceptions.NoShoppingListFound;
import dev.rick.mandjesenpuutjesback20.models.shoppingList.Grocery;
import dev.rick.mandjesenpuutjesback20.models.shoppingList.ShoppingList;
import dev.rick.mandjesenpuutjesback20.models.user.User;
import dev.rick.mandjesenpuutjesback20.repositories.GroceryRepository;
import dev.rick.mandjesenpuutjesback20.repositories.ShoppingListRepository;
import dev.rick.mandjesenpuutjesback20.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShoppingListService {

    private final ShoppingListRepository listRepo;
    private final UserRepository userRepository;
    private final GroceryRepository groceryRepository;

    public ShoppingListService(ShoppingListRepository listRepo,
                               UserRepository userRepository,
                               GroceryRepository groceryRepository) {
        this.listRepo = listRepo;
        this.userRepository = userRepository;
        this.groceryRepository = groceryRepository;
    }

    public ShoppingListOutputDTO createNewShoppingList(Principal principal, ShoppingListInputDTO inputDTO) {
        Optional<ShoppingList> optionalShoppingList = listRepo.findShoppingListByCurrentListAndListOwner_Username(true, principal.getName());
        if (optionalShoppingList.isPresent()) {
            ShoppingList foundList = optionalShoppingList.get();
            foundList.setCurrentList(false);
            listRepo.save(foundList);
        }

        User foundUser = userRepository.findUserByUsername(principal.getName());

        ShoppingList newShoppingList = new ShoppingList();
        newShoppingList.setCurrentList(true);
        newShoppingList.setListOwner(foundUser);

        List<Grocery> groceries = convertToGroceryList(inputDTO.getGroceries(), newShoppingList);
        newShoppingList.setGroceries(groceries);

        listRepo.save(newShoppingList);

        return convertShoppingListToDTO(newShoppingList);
    }

    public ShoppingListOutputDTO getCurrentShoppingList(Principal principal) {
        Optional<ShoppingList> optionalShoppingList = listRepo.findShoppingListByCurrentListAndListOwner_Username(true, principal.getName());
        if (optionalShoppingList.isEmpty()) {
            throw new NoShoppingListFound(principal.getName());
        } else {
            ShoppingList foundList = optionalShoppingList.get();
            return convertShoppingListToDTO(foundList);
        }
    }

    public ShoppingListOutputDTO addGroceryToList(Principal principal, String groceryName) {
        Optional<ShoppingList> optionalShoppingList = listRepo.findShoppingListByCurrentListAndListOwner_Username(true, principal.getName());
        if (optionalShoppingList.isEmpty()) {
            throw new NoShoppingListFound(principal.getName());
        } else {
            ShoppingList foundList = optionalShoppingList.get();

            Grocery newGrocery = convertToGrocery(groceryName);

            foundList.addGroceryToList(newGrocery);
            listRepo.save(foundList);

            return convertShoppingListToDTO(foundList);
        }
    }

    public ShoppingListOutputDTO removeGroceryFromList(Principal principal, String groceryName) {
        Optional<ShoppingList> optionalShoppingList = listRepo.findShoppingListByCurrentListAndListOwner_Username(true, principal.getName());
        if (optionalShoppingList.isEmpty()) {
            throw new NoShoppingListFound(principal.getName());
        } else {
            ShoppingList foundList = optionalShoppingList.get();

            Grocery grocery = convertToGrocery(groceryName);


            foundList.removeGroceryFromList(grocery);
            listRepo.save(foundList);

            return convertShoppingListToDTO(foundList);
        }
    }

    public ShoppingListOutputDTO convertShoppingListToDTO(ShoppingList shoppingList) {
        ShoppingListOutputDTO dto = new ShoppingListOutputDTO();
        dto.setUsername(shoppingList.getListOwner().getUsername());
        dto.setId(shoppingList.getId());
        dto.setNewList(shoppingList.isCurrentList());
        dto.setGroceries(convertGroceryListToDTO(shoppingList.getGroceries()));
        dto.setCreationDate(shoppingList.getCreationDate());
        return dto;
    }

    public List<GroceryDTO> convertGroceryListToDTO(List<Grocery> groceries) {
        List<GroceryDTO> dtoList = new ArrayList<>();

        for (Grocery grocery : groceries) {
            GroceryDTO dto = convertToGroceryDTO(grocery);
            dtoList.add(dto);
        }

        return dtoList;
    }

    public GroceryDTO convertToGroceryDTO(Grocery grocery) {
        GroceryDTO dto = new GroceryDTO();
        dto.setGroceryName(grocery.getName());
        return dto;
    }

    public List<Grocery> convertToGroceryList(List<String> groceryNames, ShoppingList list) {
        List<Grocery> groceryList = new ArrayList<>();

        for (String groceryName : groceryNames) {
            Grocery newGrocery = convertToGrocery(groceryName);
            newGrocery.setShoppingList(list);
            groceryList.add(newGrocery);
        }

        return groceryList;
    }

    public Grocery convertToGrocery(String groceryName) {
        Grocery grocery = new Grocery();
        grocery.setName(groceryName);
        groceryRepository.save(grocery);
        return grocery;
    }



}
