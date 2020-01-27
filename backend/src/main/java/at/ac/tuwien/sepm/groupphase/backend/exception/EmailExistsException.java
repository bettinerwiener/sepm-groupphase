package at.ac.tuwien.sepm.groupphase.backend.exception;

public class EmailExistsException extends RuntimeException {
    public EmailExistsException(String message) {
        super(message);
    }
}
