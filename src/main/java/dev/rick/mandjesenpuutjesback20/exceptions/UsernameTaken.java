package dev.rick.mandjesenpuutjesback20.exceptions;

public class UsernameTaken extends RuntimeException {

    public UsernameTaken(String username) {
        super(username+" has already been taken.");
    }
}
