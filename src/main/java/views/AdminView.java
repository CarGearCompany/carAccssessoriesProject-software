package views;

import controllers.AdminController;
import exceptions.*;
import models.*;
import printers.Printer;
import scanners.CustomizedScanners;


import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class AdminView {
    private static final String EMAIL = "Email";
    private static final Logger logger = Logger.getLogger(AdminView.class.getName());
    private static final String CATEGORY = "category";
    private static final String WARNING = "This Category doesn't exist";
    private AdminView() {
    }

    public static void listAllUsersView(){
        Printer.printUsers(AdminController.getAllUsers());
    }

    public static void listAllProducts(){
            List<Category> categories = AdminController.getAllCategories();
        for (Category c: categories) {
            Printer.printCategoryAllProducts(c);
        }

    }

    public static void searchForUser() {
        String searchType = CustomizedScanners.scanNonEmptyString("search credential (what do you want to search by)",new Scanner(System.in));
        String value = CustomizedScanners.scanNonEmptyString("the value " ,new Scanner(System.in));

        assert searchType != null;
        List<User> users = AdminController.searchForUser(searchType,value);
        Printer.printUsers(users);
    }

    public static void removeUserView() {
        String email = CustomizedScanners.scanNonEmptyString(EMAIL, new Scanner(System.in));
    while (true) {
        try {
            AdminController.removeUser(email);
            logger.info("User removed successfully.");
            break;
        } catch (UserNotFoundException e) {
            logger.warning("This user is not found.");
            email = CustomizedScanners.scanNonEmptyString(EMAIL, new Scanner(System.in));
        } catch (AdminsCannotBeRemovedException e) {
            logger.warning("Admins cannot be removed.");
            email = CustomizedScanners.scanNonEmptyString(EMAIL, new Scanner(System.in));
        } catch (InvalidEmailFormatException e) {
            logger.warning("Not a real email, please enter a valid one.");
            email = CustomizedScanners.scanNonEmptyString(EMAIL, new Scanner(System.in));
            }
        }
    }

    public static void promoteUserView() {
        String email = CustomizedScanners.scanNonEmptyString(EMAIL, new Scanner(System.in));
    while (true) {
        try {
            AdminController.promoteUser(email);
            logger.info("This user is promoted to admin role successfully.");
            break;
        } catch (UserNotFoundException e) {
            logger.warning("This user is not found.");
            email = CustomizedScanners.scanNonEmptyString(EMAIL, new Scanner(System.in));
        } catch (AdminsCannotBePromotedException e) {
            logger.warning("Admins cannot be promoted.");
            email = CustomizedScanners.scanNonEmptyString(EMAIL, new Scanner(System.in));
        } catch (InvalidEmailFormatException e) {
            logger.warning("Not a real email, please enter a valid one.");
            email = CustomizedScanners.scanNonEmptyString(EMAIL, new Scanner(System.in));
        }
      }
    }


    public static void addProduct()  {
        String categoryString = CustomizedScanners.scanNonEmptyString("the category type", new Scanner(System.in));

        // product info
        int productId = CustomizedScanners.scanInt("productID", new Scanner(System.in));
        String productName = CustomizedScanners.scanNonEmptyString("productName ", new Scanner(System.in));
        String productDescription = CustomizedScanners.scanNonEmptyString("Description", new Scanner(System.in));
        int price = CustomizedScanners.scanInt("price", new Scanner(System.in));
        int quantity = CustomizedScanners.scanInt("quantity", new Scanner(System.in));
        while (true) {
            try {
                Category category = CarGear.getCategoryByName(categoryString);
                Product product = new Product(productId, new ProductInfo(productName, productDescription, price,  quantity));
                AdminController.addProduct(category, product);
                logger.info("product added successfully");
                break;
            } catch (ProductAlreadyExistException e) {
                logger.warning("this product ID NotValid , please enter valid one");
                productId = CustomizedScanners.scanInt("productID", new Scanner(System.in));
            } catch (CategoryNotFoundException e) {
                logger.warning("this Category doesn't exist");
                categoryString = CustomizedScanners.scanNonEmptyString("the category type", new Scanner(System.in));
            }

        }
    }

    public static void addCategory() {
        String categoryString = CustomizedScanners.scanNonEmptyString(CATEGORY, new Scanner(System.in));
        while (true) {
            try {
                Category category = new Category(categoryString);
                AdminController.addCategory(category);
                logger.info("Category added successfully");
                break;
            } catch (CategoryAlreadyExistsException e) {
                logger.warning("This Category is already exists");
                categoryString = CustomizedScanners.scanNonEmptyString(CATEGORY, new Scanner(System.in));
            }
        }
    }

    public static void removeProduct() {
        String categoryString = CustomizedScanners.scanNonEmptyString("Category name", new Scanner(System.in));

        int id = CustomizedScanners.scanInt("ID", new Scanner(System.in));
        while (true) {
            try {
                Category category = CarGear.getCategoryByName(categoryString);
                AdminController.removeProduct(category, id);
                logger.info("Product removed successfully");
                break;
            } catch (CategoryNotFoundException e) {
                logger.warning(WARNING);
                categoryString = CustomizedScanners.scanNonEmptyString(CATEGORY, new Scanner(System.in));
            } catch (ProductNotFoundException e) {
                logger.warning("This product doesn't exist");
                id = CustomizedScanners.scanInt("ID", new Scanner(System.in));
            }
        }
    }

    public static void removeCategory() {
        String categoryString = CustomizedScanners.scanNonEmptyString(CATEGORY, new Scanner(System.in));
        while (true) {
            try {
                Category category = CarGear.getCategoryByName(categoryString);
                AdminController.removeCategory(category);
                logger.info("Category removed successfully");
                break;
            } catch (CategoryNotFoundException e) {
                logger.warning(WARNING);
                categoryString = CustomizedScanners.scanNonEmptyString(CATEGORY, new Scanner(System.in));
            }
        }
    }



    public static void searchForCategoryByName() {
        String categoryString = CustomizedScanners.scanNonEmptyString(CATEGORY, new Scanner(System.in));
        while (true) {
            try {
                Category category = AdminController.searchForCategoryByName(categoryString);
                Printer.printCategoryAllProducts(category);
                break;
            } catch (ItemNotFoundException e) {
                logger.warning(WARNING);
                categoryString = CustomizedScanners.scanNonEmptyString(CATEGORY, new Scanner(System.in));
            } catch (CategoryNotFoundException e) {
                logger.warning("Category is not found");
            }
        }
    }

    public static void searchForProduct() {
        String searchType = CustomizedScanners.scanNonEmptyString("search credential (what do you want to search by)",new Scanner(System.in));
        String value = CustomizedScanners.scanNonEmptyString("the value " ,new Scanner(System.in));

        assert searchType != null;
        List<Product> products = AdminController.searchForProducts(searchType,value);
        Printer.printProducts(products);

    }

    public static void editProduct() {
        String categoryString = CustomizedScanners.scanNonEmptyString("category of the product ", new Scanner(System.in));
        int id = CustomizedScanners.scanInt("id of the product ", new Scanner(System.in));
        String editType = CustomizedScanners.scanNonEmptyString("edit credential (what field do you want to edit)",new Scanner(System.in));
        String newValue = CustomizedScanners.scanNonEmptyString("the new value " ,new Scanner(System.in));

        while(true){
            try{
                assert editType != null;
                AdminController.editProduct(categoryString,id,editType,newValue);
                logger.info("Product edited successfully.");
                break;
            } catch(ProductNotFoundException e){
                logger.warning("Product doesn't exist.");
                id = CustomizedScanners.scanInt("id of the product ", new Scanner(System.in));
            } catch (CannotEditIdException e) {
               logger.warning("ID's cannot be edited.");
               break;
            } catch (CategoryNotFoundException e) {
                logger.warning("Category not found");
            }
        }
    }


    public static void listAllRequests() {
        Printer.printRequests(CarGear.getRequests());
    }

    public static void addRequest() {
        String customerEmail = CustomizedScanners.scanNonEmptyString("Customer Email: ", new Scanner(System.in));
        String installerEmail = CustomizedScanners.scanNonEmptyString("installer Email: ", new Scanner(System.in));
        String date = CustomizedScanners.scanNonEmptyString("Date for the request: ", new Scanner(System.in));
        String carModel = CustomizedScanners.scanNonEmptyString("CarModel: ", new Scanner(System.in));
        String category = CustomizedScanners.scanNonEmptyString("Category: ", new Scanner(System.in));
        int productId = CustomizedScanners.scanInt("Product ID: ", new Scanner(System.in));

        while (true){
            try{
                AdminController.addRequest(customerEmail,installerEmail,date,carModel,category,productId);
                logger.info("Request added successfully.");
                break;
            } catch (UserNotFoundException e) {
                logger.warning("User not found");
                installerEmail = CustomizedScanners.scanNonEmptyString("installer Email: ", new Scanner(System.in));
                customerEmail = CustomizedScanners.scanNonEmptyString("Customer Email: ", new Scanner(System.in));
            } catch (MessagingException e) {
                logger.warning("Email flied to send");
            } catch (AlreadyReservedDateException e) {
                logger.warning("try to enter an available date");
                date = CustomizedScanners.scanNonEmptyString("Date for the request: ", new Scanner(System.in));
            } catch (CategoryNotFoundException e) {
                logger.warning("Category not found");
                category = CustomizedScanners.scanNonEmptyString("Category of the product", new Scanner(System.in));
            } catch (ProductNotFoundException e) {
                logger.warning("product not found");
                productId = CustomizedScanners.scanInt("Product ID:", new Scanner(System.in));
            } catch (InvalidEmailFormatException e) {
                logger.warning("Enter valid format for the email");
                installerEmail = CustomizedScanners.scanNonEmptyString("installer Email: ", new Scanner(System.in));
            } catch (ItemNotFoundException e) {
                logger.warning("enter an existing date");
                date = CustomizedScanners.scanNonEmptyString("Date for the request: ", new Scanner(System.in));
            }
        }
    }

    public static void removeRequest() {
        String installerEmail = CustomizedScanners.scanNonEmptyString("installer email that you want to remove request from : ", new Scanner(System.in));
        String date = CustomizedScanners.scanNonEmptyString("Date for the request: ", new Scanner(System.in));
        while(true){
            try{
                AdminController.removeRequest(installerEmail,date);
                logger.info("the request removed successfully");
                break;
            } catch (UserNotFoundException e) {
                logger.warning("Installer not found");
                installerEmail = CustomizedScanners.scanNonEmptyString("the installer email that you want to remove request from : ", new Scanner(System.in));
            } catch (InvalidEmailFormatException e) {
                logger.warning("Invalid email format");
                installerEmail = CustomizedScanners.scanNonEmptyString("the installer email that you want to remove request from : ", new Scanner(System.in));
            } catch (ItemNotFoundException e) {
                logger.warning("Installer not found");
                installerEmail = CustomizedScanners.scanNonEmptyString("the installer email that you want to remove request from : ", new Scanner(System.in));
            }

        }
    }

    public static void searchForRequest() {
        String searchType = CustomizedScanners.scanNonEmptyString("search credential (what do you want to search by)",new Scanner(System.in));
        String value = CustomizedScanners.scanNonEmptyString("the value " ,new Scanner(System.in));

        assert searchType != null;
        List<Request> requests = AdminController.searchForRequests(searchType,value);
        Printer.printRequests(requests);
    }

    public static void editRequest() {
        String installerEmail = CustomizedScanners.scanNonEmptyString("installer email that you want to edit their request from : ", new Scanner(System.in));
        String date = CustomizedScanners.scanNonEmptyString("Date for the request: ", new Scanner(System.in));
        String editType = CustomizedScanners.scanNonEmptyString("edit credential (what do you want to edit by)",new Scanner(System.in));
        String value = CustomizedScanners.scanNonEmptyString("the value " ,new Scanner(System.in));
        String category;
        assert editType != null;
        if (editType.equalsIgnoreCase("product id")) {
            category = CustomizedScanners.scanNonEmptyString("category: ", new Scanner(System.in));
        }else {
            category = null;
        }
        while (true){
            try {
                AdminController.editRequest(installerEmail , date , editType , value , category);
                logger.info("the request edited successfully");
                break;
            } catch (UserNotFoundException e) {
                logger.warning("User not found");
                installerEmail = CustomizedScanners.scanNonEmptyString("installer Email: ", new Scanner(System.in));
            } catch (InvalidEmailFormatException e) {
                logger.warning("Enter valid format for the email");
                installerEmail = CustomizedScanners.scanNonEmptyString("installer Email: ", new Scanner(System.in));
            } catch (CategoryNotFoundException e) {
                logger.warning("Category not found");
                category = CustomizedScanners.scanNonEmptyString("Category of the product", new Scanner(System.in));
            } catch (ProductNotFoundException e) {
                logger.warning("product not found");
                value = String.valueOf(CustomizedScanners.scanInt("Product ID:", new Scanner(System.in)));
            } catch (ItemNotFoundException e) {
                logger.warning("Installer not found");
                installerEmail = CustomizedScanners.scanNonEmptyString("the installer email that you want to remove request from : ", new Scanner(System.in));
            }
        }
    }
}
