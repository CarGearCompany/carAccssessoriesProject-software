package controllers;

import exceptions.InvalidEmailFormatException;
import models.Appointment;
import models.CarGear;
import printers.Printer;

import java.util.ArrayList;
import java.util.List;

public class InstallerController {
   private InstallerController() {
    }


    public static void showSchedule(String email) throws InvalidEmailFormatException {
        List<Appointment> apps = CarGear.getAppointmentByEmail(email);
        for (Appointment a:
             apps) {
            Printer.printSchedule(a);

        }

    }
}
