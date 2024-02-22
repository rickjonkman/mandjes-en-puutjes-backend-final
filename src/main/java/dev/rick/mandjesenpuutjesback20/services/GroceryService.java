package dev.rick.mandjesenpuutjesback20.services;

import dev.rick.mandjesenpuutjesback20.repositories.GroceryRepository;
import org.springframework.stereotype.Service;

@Service
public class GroceryService {

    private final GroceryRepository groceryRepository;

    public GroceryService(GroceryRepository groceryRepository) {
        this.groceryRepository = groceryRepository;
    }


}
