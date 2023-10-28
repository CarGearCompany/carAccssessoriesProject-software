package exceptions;

public class UserAlreadyExistsException extends MyAppExceptions{
    public UserAlreadyExistsException() {
        super("this user is Already Exists , Try another email");
    }
}
