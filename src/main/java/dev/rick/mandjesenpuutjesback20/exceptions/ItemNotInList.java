package dev.rick.mandjesenpuutjesback20.exceptions;

public class ItemNotInList extends RuntimeException {

    public ItemNotInList(String name) {
        super("Item not in list: "+name);
    }
}
