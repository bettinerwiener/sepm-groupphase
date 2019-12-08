package at.ac.tuwien.sepm.groupphase.backend.exception;

public class TicketNotAvailableException extends RuntimeException {
    public TicketNotAvailableException(String message) {
        super(message);
    }
}
