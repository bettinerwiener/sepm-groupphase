package at.ac.tuwien.sepm.groupphase.backend.exception;


public class LockedException extends RuntimeException {

    public LockedException(String message) {
        super(message);
    }
}
