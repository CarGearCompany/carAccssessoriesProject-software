package launchers;

import exceptions.*;
import models.CarGear;


public class Main {
    public static void main(String[] args) throws UserAlreadyExistsException, InvalidPhoneNumberException, InvalidEmailFormatException, WeakPasswordException {
        CarGear.initData();
        Menu.menuHandler();
    }
}
