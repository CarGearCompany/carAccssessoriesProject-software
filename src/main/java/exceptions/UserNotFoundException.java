package exceptions;

public class UserNotFoundException extends MyAppExceptions{
    public UserNotFoundException() {
        super("InvalidEmail");
    }
}
