package dev.rick.mandjesenpuutjesback20.exceptions;

public class GroceryNotInList extends RuntimeException {

    public GroceryNotInList(String groceryName) {
        super("Grocery not in list: "+groceryName);
    }
}
