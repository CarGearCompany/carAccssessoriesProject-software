package controllers;

import exceptions.WeakPasswordException;
import helpers.PasswordChecker;
import models.Location;
import models.CarGear;
import models.User;


public class InstallerController {
   private InstallerController() {
    }


    public static void editPassword(String newPassword) throws WeakPasswordException {
        if(!PasswordChecker.isStrongPassword(newPassword))
            throw new WeakPasswordException();

        User user = CarGear.getCurrentUser();
        user.setPassword(newPassword);
    }

    public static void editLocation(String newCity, String newStreet) {
        User user = CarGear.getCurrentUser();
        Location newLocation= new Location(newCity,newStreet);
        user.getContactInfo().setLocation(newLocation);
    }
}
