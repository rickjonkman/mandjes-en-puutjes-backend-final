package dev.rick.mandjesenpuutjesback20.exceptions;

public class ProductHasAlreadyBeenAdded extends RuntimeException {

    public ProductHasAlreadyBeenAdded(String name) {
        super("Product has already been added: " +name);
    }
}
