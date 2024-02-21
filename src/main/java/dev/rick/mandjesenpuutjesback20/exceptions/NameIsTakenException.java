package dev.rick.mandjesenpuutjesback20.exceptions;

public class NameIsTakenException extends RuntimeException {

    public NameIsTakenException(String name) {
        super("This name has already been taken: "+name);
    }
}
