package views;

import exceptions.WeakPasswordException;
import scanners.CustomScanner;
import controllers.Customer;

import java.util.Scanner;
import java.util.logging.Logger;

public class CustomerView {
    private static final Logger logger = Logger.getLogger(CustomerView.class.getName());
    private CustomerView() {
    }

    public static void editPassword() {
        String newPassword = CustomScanner.scanNonEmptyString("newPassword", new Scanner(System.in));
      while (true) {
          try {
              Customer.editPassword(newPassword);
              logger.info("Password changed successfully!");
              break;
          } catch (WeakPasswordException e) {
              logger.warning("Weak Password! Must contain at least 1 uppercase letter, 1 lowercase letter, 1 digit, 1 special character and must contain 8-16 characters.");
              newPassword = CustomScanner.scanNonEmptyString("Password", new Scanner(System.in));
          }
      }

    }

    public static void editLocation() {
        String newCity = CustomScanner.scanNonEmptyString("City", new Scanner(System.in));
        String newStreet = CustomScanner.scanNonEmptyString("Street", new Scanner(System.in));

        Customer.editLocation(newCity,newStreet);
        logger.info("Location changed successfully!");


    }
}