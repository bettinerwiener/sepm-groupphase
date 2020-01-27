package at.ac.tuwien.sepm.groupphase.backend.exception;


public class CantCancelTicketException extends RuntimeException {

    public CantCancelTicketException(String message) {
        super(message);
    }
}