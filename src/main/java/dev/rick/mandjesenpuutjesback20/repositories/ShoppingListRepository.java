package dev.rick.mandjesenpuutjesback20.repositories;

import dev.rick.mandjesenpuutjesback20.models.shoppingList.ShoppingList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ShoppingListRepository extends JpaRepository<ShoppingList, Long> {

    Optional<ShoppingList> findByNewListAndOwner_Username(boolean newList, String username);

    @Query("SELECT s FROM ShoppingList s ORDER BY s.creationDate DESC LIMIT 4")
    Optional<List<ShoppingList>> findShoppingListsByNewListAndOwner_Username(boolean newList, String username);
}
