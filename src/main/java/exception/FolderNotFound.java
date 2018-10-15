package exception;

public class FolderNotFound extends RuntimeException {

    public FolderNotFound(String message) {
        super(message);
    }
}
