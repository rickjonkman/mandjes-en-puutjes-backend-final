package dev.rick.mandjesenpuutjesback20.controllers;

import dev.rick.mandjesenpuutjesback20.dto.shoppingList.ShoppingListOutputDTO;
import dev.rick.mandjesenpuutjesback20.dto.shoppingList.ShoppingListInputDTO;
import dev.rick.mandjesenpuutjesback20.services.ShoppingListService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.security.Principal;

@RestController
@RequestMapping("/api/users/shopping-list")
public class ShoppingListController {

    private final ShoppingListService shoppingListService;

    public ShoppingListController(ShoppingListService shoppingListService) {
        this.shoppingListService = shoppingListService;
    }

    @PostMapping("/add-new")
    public ResponseEntity<ShoppingListOutputDTO> addNewShoppingList(Principal principal, @RequestBody ShoppingListInputDTO inputDTO) {
        ShoppingListOutputDTO outputDTO = shoppingListService.createNewShoppingList(principal, inputDTO);

        URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/api/users/shopping-list/"+outputDTO.getId())
                .toUriString());

        return ResponseEntity.created(uri).body(outputDTO);
    }

    @GetMapping("/get-current-list")
    public ResponseEntity<ShoppingListOutputDTO> getCurrentShoppingList(Principal principal) {
        ShoppingListOutputDTO outputDTO = shoppingListService.getCurrentShoppingList(principal);
        return ResponseEntity.ok(outputDTO);
    }

    @PutMapping("/current-list/add-grocery")
    public ResponseEntity<ShoppingListOutputDTO> addGroceryToList(Principal principal, @RequestBody String groceryName) {
        ShoppingListOutputDTO dto = shoppingListService.addGroceryToList(principal, groceryName);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/current-list/remove-grocery")
    public ResponseEntity<ShoppingListOutputDTO> removeGroceryFromList(Principal principal, @RequestBody String groceryName) {
        ShoppingListOutputDTO dto = shoppingListService.removeGroceryFromList(principal, groceryName);
        return ResponseEntity.ok(dto);
    }
}
