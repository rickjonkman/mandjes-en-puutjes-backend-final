package dev.rick.mandjesenpuutjesback20.exceptions;

public class FailedToAddAuthority extends RuntimeException {

    public FailedToAddAuthority(String username) {
        super("Failed to add authority to "+username);
    }
}
