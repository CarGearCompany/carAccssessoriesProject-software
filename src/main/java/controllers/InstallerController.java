package controllers;

import exceptions.AlreadyReservedDateException;
import exceptions.WeakPasswordException;
import helpers.PasswordChecker;
import models.*;


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

    public static void addDateToSchedule(String newDate) throws AlreadyReservedDateException, WeakPasswordException {
        Installer installer = (Installer) CarGear.getCurrentUser();
        Schedule newSchedule = new Schedule(newDate,false,installer.getContactInfo().getEmail());
        for (Schedule s:
             installer.getSchedules()) {
            if (s.getDate().equals(newDate)){
                throw new AlreadyReservedDateException();
            }
        }

            installer.getSchedules().add(newSchedule);
            CarGear.getSchedules().add(newSchedule);

    }
}
