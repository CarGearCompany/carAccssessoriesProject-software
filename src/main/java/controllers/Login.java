package controllers;

import exceptions.UserNotFoundException;
import models.CarGear;
import models.User;
import enums.UserType;

public class Login {
    private Login() {
    }
    public static boolean login(String email, String password)  throws UserNotFoundException {
        User user = CarGear.getUserByEmail(email);
        if(user.getPassword().equals(password)) {
            CarGear.setCurrentUser(user);
            return true;
        }
        return false;
    }

    public static UserType getCurrentUserType() {
        return CarGear.getCurrentUser().getUserType();
    }
}
