package dev.rick.mandjesenpuutjesback20.services;

import dev.rick.mandjesenpuutjesback20.converters.ShoppingListConverter;
import dev.rick.mandjesenpuutjesback20.dto.shoppingList.GroceryDTO;
import dev.rick.mandjesenpuutjesback20.dto.shoppingList.ShoppingListDTO;
import dev.rick.mandjesenpuutjesback20.dto.shoppingList.ShoppingListInputDTO;
import dev.rick.mandjesenpuutjesback20.exceptions.NoShoppingListFound;
import dev.rick.mandjesenpuutjesback20.exceptions.RecordNotFound;
import dev.rick.mandjesenpuutjesback20.models.shoppingList.Grocery;
import dev.rick.mandjesenpuutjesback20.models.shoppingList.ShoppingList;
import dev.rick.mandjesenpuutjesback20.models.user.User;
import dev.rick.mandjesenpuutjesback20.repositories.ShoppingListRepository;
import dev.rick.mandjesenpuutjesback20.repositories.UserRepository;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
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

    public ShoppingListDTO addNewShoppingList(Principal principal, ShoppingListInputDTO inputDTO) {

        Optional<ShoppingList> optionalShoppingList = listRepo.findShoppingListByNewList(true);
        if (optionalShoppingList.isPresent()) {
            ShoppingList foundList = optionalShoppingList.get();
            foundList.setNewList(false);
            listRepo.save(foundList);
        }

        User foundUser = findUserByUsername(principal.getName());

        ShoppingList newList = converter.convertToNewShoppingList(foundUser, inputDTO);
        listRepo.save(newList);

        return converter.convertShoppingListToDTO(newList);
    }

    public ShoppingListDTO getCurrentShoppingList(Principal principal) {
        ShoppingListDTO outputDTO;

        Optional<ShoppingList> optionalShoppingList = listRepo.findShoppingListByNewList(true);
        if (optionalShoppingList.isEmpty()) {
            throw new NoShoppingListFound(principal.getName());
        } else {
            ShoppingList shoppingList = optionalShoppingList.get();
            outputDTO = converter.convertShoppingListToDTO(shoppingList);
        }

        return outputDTO;
    }

    public List<ShoppingListDTO> getRecentShoppingLists(Principal principal) {
        List<ShoppingList> foundLists = listRepo.findShoppingListsByNewList(false);
        List<ShoppingListDTO> outputDTOList;

        outputDTOList = converter.convertShoppingListsToDTOList(foundLists);
        return outputDTOList;
    }

    public void addGroceryToShoppingList(long id, GroceryDTO groceryDTO, Principal principal) {

        Optional<ShoppingList> optionalShoppingList = listRepo.findById(id);
        if (optionalShoppingList.isEmpty()) {
            throw new NoShoppingListFound(principal.getName());
        } else {
            ShoppingList foundList = optionalShoppingList.get();
            foundList.addGroceryToList(converter.convertToGrocery(groceryDTO));
            listRepo.save(foundList);
        }
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
