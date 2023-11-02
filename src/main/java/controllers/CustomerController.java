package controllers;

import exceptions.WeakPasswordException;
import models.CarGear;
import models.Location;
import models.User;

public class CustomerController {
    private CustomerController() {
    }

    public static void editPassword(String newPassword) throws WeakPasswordException {
        User user = CarGear.getCurrentUser();
        user.setPassword(newPassword);
    }

    public static void editLocation(String newCity, String newStreet) {
        User user = CarGear.getCurrentUser();
        Location newLocation= new Location(newCity,newStreet);
        user.getContactInfo().setLocation(newLocation);

    }
}
