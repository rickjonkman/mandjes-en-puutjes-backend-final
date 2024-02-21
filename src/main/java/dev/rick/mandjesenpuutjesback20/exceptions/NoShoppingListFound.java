package dev.rick.mandjesenpuutjesback20.exceptions;

public class NoShoppingListFound extends RuntimeException {
    public NoShoppingListFound(String username) {
        super("No lists found for user: "+username);
    }
}
