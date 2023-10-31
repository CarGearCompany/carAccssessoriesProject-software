package controllers;

import exceptions.InvalidEmailFormatException;
import exceptions.UserNotFoundException;
import helpers.EmailFormatChecker;
import models.CarGear;
import models.User;

import java.util.List;

public class Admin {
    private Admin() {
    }

    public static List<User> getAllUsers(){
        return CarGear.getUsers();
    }

    public static User searchForUserByEmail(String email) throws UserNotFoundException, InvalidEmailFormatException {
        if(!EmailFormatChecker.hasCorrectEmailFormat(email)){
            throw new InvalidEmailFormatException();
        }
        return CarGear.getUserByEmail(email);
    }
}
