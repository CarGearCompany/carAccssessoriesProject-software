package views;

import exceptions.WeakPasswordException;
import scanners.CustomizedScanners;
import controllers.CustomerController;

import java.util.Scanner;
import java.util.logging.Logger;

public class CustomerView {
    private static final Logger logger = Logger.getLogger(CustomerView.class.getName());
    private CustomerView() {
    }

    public static void editPassword() {
        String newPassword = CustomizedScanners.scanNonEmptyString("newPassword", new Scanner(System.in));
      while (true) {
          try {
              CustomerController.editPassword(newPassword);
              logger.info("Password changed successfully!");
              break;
          } catch (WeakPasswordException e) {
              logger.warning("Weak Password! Must contain at least 1 uppercase letter, 1 lowercase letter, 1 digit, 1 special character and must contain 8-16 characters.");
              newPassword = CustomizedScanners.scanNonEmptyString("Password", new Scanner(System.in));
          }
      }

    }

    public static void editLocation() {
        String newCity = CustomizedScanners.scanNonEmptyString("City", new Scanner(System.in));
        String newStreet = CustomizedScanners.scanNonEmptyString("Street", new Scanner(System.in));

        CustomerController.editLocation(newCity,newStreet);
        logger.info("Location changed successfully!");


    }
}
