package views;

import controllers.AdminController;
import exceptions.*;
import models.CarGear;
import models.Category;
import models.Product;
import models.ProductInfo;
import printers.Printer;
import scanners.SpecifiedScanner;

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

    public static void searchForUserByEmailView() {
        String email = SpecifiedScanner.scanNonEmptyString(EMAIL, new Scanner(System.in));
    while (true){
        try {
            Printer.printUser(AdminController.searchForUserByEmail(email));
            break;
        }catch (UserNotFoundException e){
            logger.warning("User Doesn't Exist");
            email = SpecifiedScanner.scanNonEmptyString(EMAIL, new Scanner(System.in));
        }catch (InvalidEmailFormatException e){
            logger.warning("Invalid email format! Must be a real email.");
            email = SpecifiedScanner.scanNonEmptyString(EMAIL, new Scanner(System.in));
            }

        }
    }

    public static void removeUserView() {
        String email = SpecifiedScanner.scanNonEmptyString(EMAIL, new Scanner(System.in));
    while (true) {
        try {
            AdminController.removeUser(email);
            logger.info("User removed successfully.");
            break;
        } catch (UserNotFoundException e) {
            logger.warning("This user is not found.");
            email = SpecifiedScanner.scanNonEmptyString(EMAIL, new Scanner(System.in));
        } catch (AdminsCannotBeRemovedException e) {
            logger.warning("Admins cannot be removed.");
            email = SpecifiedScanner.scanNonEmptyString(EMAIL, new Scanner(System.in));
        } catch (InvalidEmailFormatException e) {
            logger.warning("Not a real email, please enter a valid one.");
            email = SpecifiedScanner.scanNonEmptyString(EMAIL, new Scanner(System.in));
            }
        }
    }

    public static void promoteUserView() {
        String email = SpecifiedScanner.scanNonEmptyString(EMAIL, new Scanner(System.in));
    while (true) {
        try {
            AdminController.promoteUser(email);
            logger.info("This user is promoted to admin role successfully.");
            break;
        } catch (UserNotFoundException e) {
            logger.warning("This user is not found.");
            email = SpecifiedScanner.scanNonEmptyString(EMAIL, new Scanner(System.in));
        } catch (AdminsCannotBePromotedException e) {
            logger.warning("Admins cannot be promoted.");
            email = SpecifiedScanner.scanNonEmptyString(EMAIL, new Scanner(System.in));
        } catch (InvalidEmailFormatException e) {
            logger.warning("Not a real email, please enter a valid one.");
            email = SpecifiedScanner.scanNonEmptyString(EMAIL, new Scanner(System.in));
        }
      }
    }


    public static void addProduct()  {
        String categoryString = SpecifiedScanner.scanNonEmptyString("the category type", new Scanner(System.in));

        // product info
        int productId = SpecifiedScanner.scanInt("productID", new Scanner(System.in));
        String productName = SpecifiedScanner.scanNonEmptyString("productName ", new Scanner(System.in));
        String productDescription = SpecifiedScanner.scanNonEmptyString("Description", new Scanner(System.in));
        int price = SpecifiedScanner.scanInt("price", new Scanner(System.in));
        String img = SpecifiedScanner.scanNonEmptyString("img path", new Scanner(System.in));
        int quantity = SpecifiedScanner.scanInt("quantity", new Scanner(System.in));
        while (true) {
            try {
                Category category = CarGear.getCategoryByName(categoryString);
                Product product = new Product(productId, new ProductInfo(productName, productDescription, price, img, quantity), true);
                AdminController.addProduct(category, product);
                logger.info("product added successfully");
                break;
            } catch (ProductAlreadyExistException e) {
                logger.warning("this product ID NotValid , please enter valid one");
                productId = SpecifiedScanner.scanInt("productID", new Scanner(System.in));
            } catch (CategoryNotFoundException e) {
                logger.warning("this Category doesn't exist");
                categoryString = SpecifiedScanner.scanNonEmptyString("the category type", new Scanner(System.in));
            }

        }
    }

    public static void addCategory() {
        String categoryString = SpecifiedScanner.scanNonEmptyString(CATEGORY, new Scanner(System.in));
        while (true) {
            try {
                Category category = new Category(categoryString);
                AdminController.addCategory(category);
                logger.info("Category added successfully");
                break;
            } catch (CategoryAlreadyExistsException e) {
                logger.warning("This Category is already exists");
                categoryString = SpecifiedScanner.scanNonEmptyString(CATEGORY, new Scanner(System.in));
            }
        }
    }

    public static void removeProduct() {
        String categoryString = SpecifiedScanner.scanNonEmptyString("Category name", new Scanner(System.in));

        int id = SpecifiedScanner.scanInt("ID", new Scanner(System.in));
        while (true) {
            try {
                Category category = CarGear.getCategoryByName(categoryString);
                AdminController.removeProduct(category, id);
                logger.info("Product removed successfully");
                break;
            } catch (CategoryNotFoundException e) {
                logger.warning(WARNING);
                categoryString = SpecifiedScanner.scanNonEmptyString(CATEGORY, new Scanner(System.in));
            } catch (ProductNotFoundException e) {
                logger.warning("This product doesn't exist");
                id = SpecifiedScanner.scanInt("ID", new Scanner(System.in));
            }
        }
    }

    public static void removeCategory() {
        String categoryString = SpecifiedScanner.scanNonEmptyString(CATEGORY, new Scanner(System.in));
        while (true) {
            try {
                Category category = CarGear.getCategoryByName(categoryString);
                AdminController.removeCategory(category);
                logger.info("Category removed successfully");
                break;
            } catch (ItemNotFoundException e) {
                logger.warning(WARNING);
                categoryString = SpecifiedScanner.scanNonEmptyString(CATEGORY, new Scanner(System.in));
            }
        }
    }

    public static void editProduct() {
        // under construction
    }

    public static void searchForCategoryByName() {
        String categoryString = SpecifiedScanner.scanNonEmptyString(CATEGORY, new Scanner(System.in));
        while (true) {
            try {
                Category category = AdminController.searchForCategoryByName(categoryString);
                Printer.printCategoryAllProducts(category);
                break;
            } catch (ItemNotFoundException e) {
                logger.warning(WARNING);
                categoryString = SpecifiedScanner.scanNonEmptyString(CATEGORY, new Scanner(System.in));
            }
        }
    }

    public static void searchForProductById() {

    }

}
