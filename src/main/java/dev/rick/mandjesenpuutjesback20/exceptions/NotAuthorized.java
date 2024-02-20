package dev.rick.mandjesenpuutjesback20.exceptions;

public class NotAuthorized extends RuntimeException {

    public NotAuthorized() {
        super("You are not authorized to perform this action.");
    }
}
