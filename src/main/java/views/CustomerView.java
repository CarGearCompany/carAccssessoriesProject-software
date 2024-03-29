package views;

import controllers.AdminController;
import exceptions.*;
import helpers.EmailService;
import models.CarGear;
import models.Category;
import models.Customer;
import models.Product;
import printers.Printer;
import scanners.CustomizedScanners;
import controllers.CustomerController;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class CustomerView {
    private static final Logger logger = Logger.getLogger(CustomerView.class.getName());
    private static final String PREFERRED_DATE = "Preferred Date: ";
    private static final String INSTALLER_EMAIL ="Installer Email: ";
    private static final String PRODUCT_ID = "Product ID: ";
    private static final String CATEGORY = "category";
    private static final String ID_OF_THE_PRODUCT = "ID of the product ";
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

    public static void purchaseProduct() throws CategoryNotFoundException, ProductNotFoundException {
        String category = CustomizedScanners.scanNonEmptyString(CATEGORY, new Scanner(System.in));
        int id = CustomizedScanners.scanInt(ID_OF_THE_PRODUCT, new Scanner(System.in));
        int quantity = CustomizedScanners.scanInt2("How many of it do you want to buy? ", new Scanner(System.in));
        String confirm = CustomizedScanners.scanNonEmptyString2("Confirm purchase (Y/N) ", new Scanner(System.in));
        Customer customer = (Customer) CarGear.getCurrentUser();
        Product product = CarGear.getProductById(CarGear.getCategoryByName(category),id);
        String msg;
        EmailService emailService = new EmailService();

            while (true) {
                try {
                    assert confirm != null;
                    CustomerController.purchaseProduct(emailService,category, id, customer,confirm,quantity);
                    logger.info("Product is purchased successfully.");
                  break;
                } catch (ProductNotFoundException e) {
                    logger.warning("Product is not found. Try again");
                    id = CustomizedScanners.scanInt(ID_OF_THE_PRODUCT, new Scanner(System.in));
                } catch (CategoryNotFoundException e) {
                    logger.warning("Category not found");
                    category = CustomizedScanners.scanNonEmptyString(CATEGORY, new Scanner(System.in));
                } catch (PurchaseNotConfirmedException e) {
                    logger.warning("Transaction not confirmed.");
                    logger.info("you can buy anything else if you want :)");
                    category = CustomizedScanners.scanNonEmptyString(CATEGORY, new Scanner(System.in));
                    id = CustomizedScanners.scanInt(ID_OF_THE_PRODUCT, new Scanner(System.in));
                } catch (OutOfStockException e) {
                    logger.warning("This product is out of stock, sorry!");
                    logger.info("you can buy anything else :)");
                    category = CustomizedScanners.scanNonEmptyString(CATEGORY, new Scanner(System.in));
                    id = CustomizedScanners.scanInt(ID_OF_THE_PRODUCT, new Scanner(System.in));
                } catch (NotEnoughItemsAvailableException e) {
                    msg = "Sorry! We only have " + product.getProductInfo().getQuantity() + " :(";
                    logger.warning(msg);
                    logger.info("you can buy anything else :)");
                    category = CustomizedScanners.scanNonEmptyString(CATEGORY, new Scanner(System.in));
                    id = CustomizedScanners.scanInt(ID_OF_THE_PRODUCT, new Scanner(System.in));
                }

            }
    }

    public static void displayOrderHistory() {
      Customer customer =  (Customer) CarGear.getCurrentUser();
       CustomerController.displayOrderHistory(customer);
    }

    public static void displaySchedules() {
        Printer.printSchedules(CarGear.getSchedules());
    }

    public static void requestService() {
        String installerEmail = CustomizedScanners.scanNonEmptyString(INSTALLER_EMAIL, new Scanner(System.in));
        String carModel = CustomizedScanners.scanNonEmptyString("Car Model:", new Scanner(System.in));
        String date = CustomizedScanners.scanNonEmptyString(PREFERRED_DATE, new Scanner(System.in));
        String category = CustomizedScanners.scanNonEmptyString("Category of the product", new Scanner(System.in));
        int productId = CustomizedScanners.scanInt(PRODUCT_ID, new Scanner(System.in));
        EmailService emailService = new EmailService();
        while (true){
            try{
                CustomerController.requestService(emailService,installerEmail , carModel , date , category , productId);
                logger.info("the Service is Requested successfully .");
                break;
            } catch (UserNotFoundException e) {
                logger.warning("Installer not found");
                installerEmail = CustomizedScanners.scanNonEmptyString(INSTALLER_EMAIL, new Scanner(System.in));
            } catch (AlreadyReservedDateException e) {
                logger.warning("try to enter an available date");
                date = CustomizedScanners.scanNonEmptyString(PREFERRED_DATE, new Scanner(System.in));
            } catch (CategoryNotFoundException e) {
                logger.warning("Category not found");
                 category = CustomizedScanners.scanNonEmptyString("Category of the product", new Scanner(System.in));
            } catch (ProductNotFoundException e) {
                logger.warning("product not found");
                productId = CustomizedScanners.scanInt(PRODUCT_ID, new Scanner(System.in));
            } catch (InvalidEmailFormatException e) {
                logger.warning("Enter valid format for the email");
                installerEmail = CustomizedScanners.scanNonEmptyString(INSTALLER_EMAIL, new Scanner(System.in));
            } catch (ItemNotFoundException e) {
                logger.warning("enter an existing date");
                date = CustomizedScanners.scanNonEmptyString(PREFERRED_DATE, new Scanner(System.in));
            }
        }
    }

    public static void displayRequests() {
        Customer customer =  (Customer) CarGear.getCurrentUser();
        CustomerController.displayRequests(customer);
    }

    public static void displayProductImage() {
        String category = CustomizedScanners.scanNonEmptyString("category name", new Scanner(System.in));
        int productID = CustomizedScanners.scanInt(PRODUCT_ID, new Scanner(System.in));

        while(true) {
            try {
                CustomerController.openImage(category, productID);
                logger.info("Image is opened, look at your taskbar :)");
                break;
            } catch (CategoryNotFoundException e) {
                logger.warning("Category not found, enter a valid one");
                category = CustomizedScanners.scanNonEmptyString("category name", new Scanner(System.in));
            } catch (ProductNotFoundException e) {
                logger.warning("Product not found, enter a valid id");
                 productID = CustomizedScanners.scanInt(PRODUCT_ID, new Scanner(System.in));
            } catch (IOException | URISyntaxException e) {
                logger.warning("Couldn't open image");
            }
        }

    }
}
