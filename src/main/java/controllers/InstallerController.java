package controllers;

import exceptions.InvalidEmailFormatException;
import models.Schedule;
import models.CarGear;
import printers.Printer;

import java.util.List;

public class InstallerController {
   private InstallerController() {
    }


    public static void showSchedule(String email) throws InvalidEmailFormatException {
        List<Schedule> apps = CarGear.getAppointmentByEmail(email);
        for (Schedule a:
             apps) {
            Printer.printSchedule(a);

        }

    }
}
