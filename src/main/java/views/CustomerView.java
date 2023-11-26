package views;

import controllers.AdminController;
import exceptions.CategoryNotFoundException;
import exceptions.ProductNotFoundException;
import exceptions.WeakPasswordException;
import models.CarGear;
import models.Category;
import models.Customer;
import models.User;
import printers.Printer;
import scanners.CustomizedScanners;
import controllers.CustomerController;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class CustomerView {
    private static final Logger logger = Logger.getLogger(CustomerView.class.getName());
    private CustomerView() {
    }

    public static void listAllProducts(){
        List<Category> categories = AdminController.getAllCategories();
        for (Category c: categories) {
            Printer.printCategoryAllProducts(c);
        }

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

    public static void purchaseProduct(){
        String category = CustomizedScanners.scanNonEmptyString("category", new Scanner(System.in));
        int id = CustomizedScanners.scanInt("ID of the product ", new Scanner(System.in));
        User customer = CarGear.getCurrentUser();
        Category c;

        while (true){
            try {
                c = CarGear.getCategoryByName(category);
                CustomerController.purchaseProduct(c,id, (Customer) customer);
            } catch (ProductNotFoundException e) {
                logger.warning("Product is not found. Try again");
                id = CustomizedScanners.scanInt("ID of the product ", new Scanner(System.in));
            } catch (CategoryNotFoundException e) {
                logger.warning("Category not found");
            }
        }









    }
}
