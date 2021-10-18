package cl.bci.users.exception;

public class UserException extends RuntimeException {

    private static final long serialVersionUID = 768930651528715006L;

    public UserException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public UserException(String message) {
        super(message);
    }
}
