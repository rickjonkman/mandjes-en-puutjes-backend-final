package dev.rick.mandjesenpuutjesback20.repositories;

import dev.rick.mandjesenpuutjesback20.models.shoppingList.Grocery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroceryRepository extends JpaRepository<Grocery, Long> {

}
