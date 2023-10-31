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

    public static List<User> getAllUsers() {
        return CarGear.getUsers();
    }



public static User searchForUserByEmail(String email) throws UserNotFoundException, InvalidEmailFormatException {
        if (!EmailFormatChecker.hasCorrectEmailFormat(email)) {
            throw new InvalidEmailFormatException();
        }
        if (!CarGear.getUserByEmail(email).getContactInfo().getEmail().equals(email)) {
            throw new UserNotFoundException();
        }
        return CarGear.getUserByEmail(email);
    }


    public static void removeUser(String email) throws UserNotFoundException, AdminsCannotBeRemovedException, InvalidEmailFormatException {
        User user = CarGear.getUserByEmail(email);
        if(user.getUserType().equals(UserType.ADMIN)){
            throw new AdminsCannotBeRemovedException();
        }
        CarGear.removeUser(user);
    }

    public static void promoteUser(String email) throws UserNotFoundException, AdminsCannotBePromotedException, InvalidEmailFormatException {
        User user = CarGear.getUserByEmail(email);
        if(user.getUserType().equals(UserType.ADMIN)){
            throw new AdminsCannotBePromotedException();
        }

        CarGear.promoteUser(user);
        
        
 
    }
}