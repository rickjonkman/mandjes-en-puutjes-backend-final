package dev.rick.mandjesenpuutjesback20.exceptions;

public class FailedToAddAuthority extends RuntimeException {

    public FailedToAddAuthority(long userId) {
        super("Failed to add authority to "+userId);
    }
}
