package dev.rick.mandjesenpuutjesback20.services;

import dev.rick.mandjesenpuutjesback20.converters.ShoppingListConverter;
import dev.rick.mandjesenpuutjesback20.dto.shoppingList.GroceryDTO;
import dev.rick.mandjesenpuutjesback20.dto.shoppingList.ShoppingListDTO;
import dev.rick.mandjesenpuutjesback20.exceptions.NoShoppingListFound;
import dev.rick.mandjesenpuutjesback20.exceptions.RecordNotFound;
import dev.rick.mandjesenpuutjesback20.models.shoppingList.ShoppingList;
import dev.rick.mandjesenpuutjesback20.models.user.User;
import dev.rick.mandjesenpuutjesback20.repositories.ShoppingListRepository;
import dev.rick.mandjesenpuutjesback20.repositories.UserRepository;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class ShoppingListService {

    private final ShoppingListRepository listRepo;
    private final ShoppingListConverter converter;
    private final UserRepository userRepository;

    public ShoppingListService(ShoppingListRepository listRepo, ShoppingListConverter converter,
                               UserRepository userRepository) {
        this.listRepo = listRepo;
        this.converter = converter;
        this.userRepository = userRepository;
    }

    public ShoppingListDTO addNewShoppingList(ShoppingListDTO listDTO, Principal principal) {
        ShoppingList foundList = findCurrentShoppingListByUsername(principal.getName());
        if (foundList != null) {
            foundList.setNewList(false);
            listRepo.save(foundList);
        }

        User foundUser = findUserByUsername(principal.getName());

        ShoppingList newList = converter.convertToNewShoppingList(listDTO, foundUser);
        listRepo.save(newList);

        return converter.convertShoppingListToDTO(newList);
    }

    public ShoppingListDTO getCurrentShoppingList(Principal principal) {
        ShoppingList foundList = findCurrentShoppingListByUsername(principal.getName());
        return converter.convertShoppingListToDTO(foundList);
    }

    public List<ShoppingListDTO> getRecentShoppingLists(Principal principal) {
        Optional<List<ShoppingList>> optionalLists = listRepo.findShoppingListsByNewListAndOwner_Username(false, principal.getName());
        if (optionalLists.isEmpty()) {
            throw new NoShoppingListFound(principal.getName());
        } else {
            List<ShoppingList> foundLists = optionalLists.get();
            return converter.convertShoppingListsToDTOList(foundLists);
        }
    }

    public String addGroceryToRecentList(Principal principal, GroceryDTO groceryDTO) {
        ShoppingList foundList = findCurrentShoppingListByUsername(principal.getName());
        if (foundList == null) {
            throw new NoShoppingListFound(principal.getName());
        } else {
            foundList.addGroceryToList(converter.convertToGrocery(groceryDTO));
            return "Added to list: " + groceryDTO.getGroceryName();
        }
    }

    public String removeGroceryFromRecentList(Principal principal, GroceryDTO groceryDTO) {
        ShoppingList foundList = findCurrentShoppingListByUsername(principal.getName());
        if (foundList == null) {
            throw new NoShoppingListFound(principal.getName());
        } else {
            foundList.removeGroceryFromList(converter.convertToGrocery(groceryDTO));
            return "removed from list: " + groceryDTO.getGroceryName();
        }
    }

    public ShoppingList findCurrentShoppingListByUsername(String username) {
        Optional<ShoppingList> optionalShoppingList = listRepo.findByNewListAndOwner_Username(true, username);
        return optionalShoppingList.orElse(null);
    }

    public User findUserByUsername(String username) {
        Optional<User> optionalUser = userRepository.findById(username);
        if (optionalUser.isEmpty()) {
            throw new RecordNotFound(username);
        } else {
            return optionalUser.get();
        }
    }
}
