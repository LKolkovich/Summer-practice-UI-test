package utils.Exceptions;

public class ElementNotVisibleException extends RuntimeException{

    public ElementNotVisibleException(String message) {
        super(message);
    }

    public ElementNotVisibleException(String message, Throwable cause) {
        super(message, cause);
    }
}
