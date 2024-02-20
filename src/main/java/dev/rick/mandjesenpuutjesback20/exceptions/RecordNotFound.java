package dev.rick.mandjesenpuutjesback20.exceptions;

public class RecordNotFound extends RuntimeException {

    public RecordNotFound(String username) {
        super("No user found with username: "+username);
    }

    public RecordNotFound(long userId) {
        super("No user found with ID: "+userId);
    }
}
