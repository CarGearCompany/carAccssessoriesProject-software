package views;

import exceptions.WeakPasswordException;
import scanners.SpecifiedScanner;
import controllers.CustomerController;

import java.util.Scanner;
import java.util.logging.Logger;

public class CustomerView {
    private static final Logger logger = Logger.getLogger(CustomerView.class.getName());
    private CustomerView() {
    }

    public static void editPassword() {
        String newPassword = SpecifiedScanner.scanNonEmptyString("newPassword", new Scanner(System.in));
      while (true) {
          try {
              CustomerController.editPassword(newPassword);
              logger.info("Password changed successfully!");
              break;
          } catch (WeakPasswordException e) {
              logger.warning("Weak Password! Must contain at least 1 uppercase letter, 1 lowercase letter, 1 digit, 1 special character and must contain 8-16 characters.");
              newPassword = SpecifiedScanner.scanNonEmptyString("Password", new Scanner(System.in));
          }
      }

    }

    public static void editLocation() {
        String newCity = SpecifiedScanner.scanNonEmptyString("City", new Scanner(System.in));
        String newStreet = SpecifiedScanner.scanNonEmptyString("Street", new Scanner(System.in));

        CustomerController.editLocation(newCity,newStreet);
        logger.info("Location changed successfully!");


    }
}
