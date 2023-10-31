package controllers;

import exceptions.UserNotFoundException;
import models.CarGear;
import models.User;

import java.util.List;

public class Admin {
    private Admin() {
    }

    public static List<User> getAllUsers(){
        return CarGear.getUsers();
    }

    public static User searchForUserByEmail(String email) throws UserNotFoundException {
        return CarGear.getUserByEmail(email);
    }
}
