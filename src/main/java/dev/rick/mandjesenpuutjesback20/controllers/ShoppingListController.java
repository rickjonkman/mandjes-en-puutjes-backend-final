package dev.rick.mandjesenpuutjesback20.controllers;

import dev.rick.mandjesenpuutjesback20.dto.shoppingList.GroceryDTO;
import dev.rick.mandjesenpuutjesback20.dto.shoppingList.ShoppingListDTO;
import dev.rick.mandjesenpuutjesback20.services.ShoppingListService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/user/shopping-list")
public class ShoppingListController {

    private final ShoppingListService shoppingListService;

    public ShoppingListController(ShoppingListService shoppingListService) {
        this.shoppingListService = shoppingListService;
    }

    @PostMapping("/add-new")
    public ResponseEntity<ShoppingListDTO> addNewShoppingList(@RequestBody ShoppingListDTO shoppingListDTO, Principal principal) {
        ShoppingListDTO dto = shoppingListService.addNewShoppingList(shoppingListDTO, principal);

        URI uri = URI
                .create(ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/api/user/shopping-list/"+dto.getId())
                        .toUriString());
        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping("/get-current-list")
    public ResponseEntity<ShoppingListDTO> getCurrentShoppingList(Principal principal) {
        ShoppingListDTO outputDTO = shoppingListService.getCurrentShoppingList(principal);
        return ResponseEntity.ok(outputDTO);
    }

    @GetMapping("/get-recent-lists")
    public ResponseEntity<List<ShoppingListDTO>> getRecentShoppingLists(Principal principal) {
        List<ShoppingListDTO> outputList = shoppingListService.getRecentShoppingLists(principal);
        return ResponseEntity.ok(outputList);
    }

    @PutMapping("/add-grocery")
    public ResponseEntity<?> addGroceryToCurrentList(Principal principal, @RequestBody GroceryDTO groceryDTO) {
        String outputMessage = shoppingListService.addGroceryToRecentList(principal, groceryDTO);
        return ResponseEntity.ok(outputMessage);
    }

    @PutMapping("/remove-grocery")
    public ResponseEntity<?> removeGroceryFromCurrentList(Principal principal, @RequestBody GroceryDTO groceryDTO) {
        String outputMessage = shoppingListService.removeGroceryFromRecentList(principal, groceryDTO);
        return ResponseEntity.ok(outputMessage);
    }

}
