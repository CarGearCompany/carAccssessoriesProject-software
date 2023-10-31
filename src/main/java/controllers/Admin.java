package controllers;

import enums.UserType;
import exceptions.AdminsCannotBeRemovedException;
import exceptions.AdminsCannotBePromotedException;
import exceptions.InvalidEmailFormatException;
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
        
        
        
    }

}
