package at.ac.tuwien.sepm.groupphase.backend.exception;

public class NotSavedException extends RuntimeException {
    public NotSavedException(String message) {
        super(message);
    }
}
