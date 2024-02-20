package dev.rick.mandjesenpuutjesback20.exceptions;

public class RecordNotFound extends RuntimeException {

    public RecordNotFound(long userId) {
        super("No user found with ID: "+userId);
    }
}
