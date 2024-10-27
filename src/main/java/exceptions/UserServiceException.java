package exceptions;

import java.util.function.Supplier;

public class UserServiceException extends Throwable {
    public UserServiceException(String message) {
        super(message);
    }

    public UserServiceException() {
    }

    public UserServiceException(Throwable cause) {
        super(cause);
    }

    public UserServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
