package launchers;

import exceptions.EmailAlreadyExistsException;
import exceptions.UserNotFoundException;
import models.CarGear;


public class Main {
    public static void main(String[] args) throws UserNotFoundException, EmailAlreadyExistsException {
        CarGear.initData();
        Menu.menuHandler();
    }
}
