package dev.rick.mandjesenpuutjesback20.exceptions;

public class RecordNotFound extends RuntimeException {

    public RecordNotFound(String name) {
        super("Nothing found with name: "+name);
    }

    public RecordNotFound(long id) {
        super("Nothing found with ID: "+id);
    }


}
