package dev.rick.mandjesenpuutjesback20.repositories;

import dev.rick.mandjesenpuutjesback20.models.shoppingList.ShoppingList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShoppingListRepository extends JpaRepository<ShoppingList, Long> {

    Optional<ShoppingList> findShoppingListByCurrentListAndListOwner_Username(boolean currentList, String username);
}
