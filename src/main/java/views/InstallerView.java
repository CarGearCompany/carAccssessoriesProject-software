package views;

import controllers.CustomerController;
import controllers.InstallerController;
import exceptions.WeakPasswordException;
import models.CarGear;
import models.Customer;
import models.Installer;
import printers.Printer;
import scanners.CustomizedScanners;

import java.util.Scanner;
import java.util.logging.Logger;

public class InstallerView {
    private static final Logger logger = Logger.getLogger(InstallerView.class.getName());

    private InstallerView(){
    }


    public static void viewInstallationRequests() {
        Installer installer =  (Installer) CarGear.getCurrentUser();
        Printer.printRequests(installer.getRequests());
    }

    public static void viewSchedule() {
        Installer installer =  (Installer) CarGear.getCurrentUser();
        Printer.printSchedules(installer.getSchedules());
    }

    public static void addDatesToSchedule() {
        // under constructed
    }

    public static void editMyPassword() {
        String newPassword = CustomizedScanners.scanNonEmptyString("newPassword", new Scanner(System.in));
        while (true) {
            try {
                InstallerController.editPassword(newPassword);
                logger.info("Password changed successfully!");
                break;
            } catch (WeakPasswordException e) {
                logger.warning("Weak Password! Must contain at least 1 uppercase letter, 1 lowercase letter, 1 digit, 1 special character and must contain 8-16 characters.");
                newPassword = CustomizedScanners.scanNonEmptyString("Password", new Scanner(System.in));
            }
        }
    }

    public static void EditMyLocation() {
        String newCity = CustomizedScanners.scanNonEmptyString("City", new Scanner(System.in));
        String newStreet = CustomizedScanners.scanNonEmptyString("Street", new Scanner(System.in));

        InstallerController.editLocation(newCity,newStreet);
        logger.info("Location changed successfully!");

    }
}
