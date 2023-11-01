package views;

import controllers.Admin;
import exceptions.*;
import models.CarGear;
import models.Category;
import models.Product;
import models.ProductInfo;
import printers.Printer;
import scanners.CustomScanner;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class AdminView {
    private static final String EMAIL = "Email";
    private static final Logger logger = Logger.getLogger(AdminView.class.getName());
    private static final String CATEGORY = "category";
    private static final String WARNING = "This Category isn't exists";
    private AdminView() {
    }

    public static void listAllUsersView(){
        Printer.printUsers(Admin.getAllUsers());
    }

    public static void listAllProducts(){
            List<Category> categories = Admin.getAllCategories();
        for (Category c: categories) {
            Printer.printCategoryAllProducts(c);
        }

    }

    public static void searchForUserByEmailView() {
        String email = CustomScanner.scanNonEmptyString(EMAIL, new Scanner(System.in));
    while (true){
        try {
            Printer.printUser(Admin.searchForUserByEmail(email));
            break;
        }catch (UserNotFoundException e){
            logger.warning("User Doesn't Exist");
            email = CustomScanner.scanNonEmptyString(EMAIL, new Scanner(System.in));
        }catch (InvalidEmailFormatException e){
            logger.warning("Invalid email format! Must be a real email.");
            email = CustomScanner.scanNonEmptyString(EMAIL, new Scanner(System.in));
            }

        }
    }

    public static void removeUserView() {
        String email = CustomScanner.scanNonEmptyString(EMAIL, new Scanner(System.in));
    while (true) {
        try {
            Admin.removeUser(email);
            logger.info("User removed successfully.");
            break;
        } catch (UserNotFoundException e) {
            logger.warning("This user is not found.");
            email = CustomScanner.scanNonEmptyString(EMAIL, new Scanner(System.in));
        } catch (AdminsCannotBeRemovedException e) {
            logger.warning("Admins cannot be removed.");
            email = CustomScanner.scanNonEmptyString(EMAIL, new Scanner(System.in));
        } catch (InvalidEmailFormatException e) {
            logger.warning("Not a real email, please enter a valid one.");
            email = CustomScanner.scanNonEmptyString(EMAIL, new Scanner(System.in));
            }
        }
    }

    public static void promoteUserView() {
        String email = CustomScanner.scanNonEmptyString(EMAIL, new Scanner(System.in));
    while (true) {
        try {
            Admin.promoteUser(email);
            logger.info("This user is promoted to admin role successfully.");
            break;
        } catch (UserNotFoundException e) {
            logger.warning("This user is not found.");
            email = CustomScanner.scanNonEmptyString(EMAIL, new Scanner(System.in));
        } catch (AdminsCannotBePromotedException e) {
            logger.warning("Admins cannot be promoted.");
            email = CustomScanner.scanNonEmptyString(EMAIL, new Scanner(System.in));
        } catch (InvalidEmailFormatException e) {
            logger.warning("Not a real email, please enter a valid one.");
            email = CustomScanner.scanNonEmptyString(EMAIL, new Scanner(System.in));
        }
      }
    }


    public static void addProduct()  {
        String categoryString = CustomScanner.scanNonEmptyString("the category type", new Scanner(System.in));

        // product info
        int productId = CustomScanner.scanInt("productID", new Scanner(System.in));
        String productName = CustomScanner.scanNonEmptyString("productName ", new Scanner(System.in));
        String productDescription = CustomScanner.scanNonEmptyString("Description", new Scanner(System.in));
        int price = CustomScanner.scanInt("price", new Scanner(System.in));
        String img = CustomScanner.scanNonEmptyString("img path", new Scanner(System.in));
        int quantity = CustomScanner.scanInt("quantity", new Scanner(System.in));
        while (true) {
            try {
                Category category = CarGear.getCategoryByName(categoryString);
                Product product = new Product(productId, new ProductInfo(productName, productDescription, price, img, quantity), true);
                Admin.addProduct(category, product);
                logger.info("product added successfully");
                break;
            } catch (ItemAlreadyExistsExceprion e) {
                logger.warning("this product ID NotValid , please enter valid one");
                productId = CustomScanner.scanInt("productID", new Scanner(System.in));
            } catch (ItemNotFoundException e) {
                logger.warning("this Category doesn't exist");
                categoryString = CustomScanner.scanNonEmptyString("the category type", new Scanner(System.in));
            }

        }
    }

    public static void addCategory() {
        String categoryString = CustomScanner.scanNonEmptyString(CATEGORY, new Scanner(System.in));
        while (true) {
            try {
                Category category = new Category(categoryString);
                Admin.addCategory(category);
                logger.info("Category added successfully");
                break;
            } catch (ItemAlreadyExistsExceprion e) {
                logger.warning("This Category is already exists");
                categoryString = CustomScanner.scanNonEmptyString(CATEGORY, new Scanner(System.in));
            }
        }
    }

    public static void removeProduct() {
        String categoryString = CustomScanner.scanNonEmptyString("Category name", new Scanner(System.in));

        int id = CustomScanner.scanInt("ID", new Scanner(System.in));
        while (true) {
            try {
                Category category = CarGear.getCategoryByName(categoryString);
                Admin.removeProduct(category, id);
                logger.info("Product removed successfully");
                break;
            } catch (ItemNotFoundException e) {
                logger.warning(WARNING);
                categoryString = CustomScanner.scanNonEmptyString(CATEGORY, new Scanner(System.in));
            }
        }
    }

    public static void removeCategory() {
        String categoryString = CustomScanner.scanNonEmptyString(CATEGORY, new Scanner(System.in));
        while (true) {
            try {
                Category category = CarGear.getCategoryByName(categoryString);
                Admin.removeCategory(category);
                logger.info("Category removed successfully");
                break;
            } catch (ItemNotFoundException e) {
                logger.warning(WARNING);
                categoryString = CustomScanner.scanNonEmptyString(CATEGORY, new Scanner(System.in));
            }
        }
    }

    public static void editProduct() {
        // under construction
    }

    public static void searchForCategoryByName() {
        String categoryString = CustomScanner.scanNonEmptyString(CATEGORY, new Scanner(System.in));
        while (true) {
            try {
                Category category = Admin.searchForCategoryByName(categoryString);
                Printer.printCategoryAllProducts(category);
                break;
            } catch (ItemNotFoundException e) {
                logger.warning(WARNING);
                categoryString = CustomScanner.scanNonEmptyString(CATEGORY, new Scanner(System.in));
            }
        }
    }

    public static void searchForProductById() {
        String categoryString = CustomScanner.scanNonEmptyString(CATEGORY, new Scanner(System.in));
        int id = CustomScanner.scanInt("id",new Scanner(System.in));
        while (true) {
            try {
                Category category = CarGear.getCategoryByName(categoryString);
                Product product = Admin.searchForProductById(category,id);
                Printer.printProduct(category,product);
                break;
            } catch (ItemNotFoundException e) {
                logger.warning(WARNING);
                categoryString = CustomScanner.scanNonEmptyString(CATEGORY, new Scanner(System.in));
            }
        }
    }
}
