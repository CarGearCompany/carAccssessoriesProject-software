package controllers;

import models.CarGear;
import models.User;
import enums.UserType;

public class Login {
    private Login() {
    }
    public static boolean login(String email, String password)  {
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
