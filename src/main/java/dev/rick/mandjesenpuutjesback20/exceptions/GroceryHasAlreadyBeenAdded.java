package dev.rick.mandjesenpuutjesback20.exceptions;

public class GroceryHasAlreadyBeenAdded extends RuntimeException {

    public GroceryHasAlreadyBeenAdded(String groceryName) {
        super("Grocery has already been added: " +groceryName);
    }
}
