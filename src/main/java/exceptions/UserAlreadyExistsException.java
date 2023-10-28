package exceptions;

public class UserAlreadyExistsException extends Exception{
    public UserAlreadyExistsException() {
        super("this user is Already Exists , Try another email");
    }
}
