package views;

import controllers.AdminController;
import exceptions.*;
import models.CarGear;
import models.Category;
import models.Product;
import models.ProductInfo;
import printers.Printer;
import scanners.CustomizedScanners;


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
        String email = CustomizedScanners.scanNonEmptyString(EMAIL, new Scanner(System.in));
    while (true){
        try {
            Printer.printUser(AdminController.searchForUserByEmail(email));
            break;
        }catch (UserNotFoundException e){
            logger.warning("User Doesn't Exist");
            email = CustomizedScanners.scanNonEmptyString(EMAIL, new Scanner(System.in));
        }catch (InvalidEmailFormatException e){
            logger.warning("Invalid email format! Must be a real email.");
            email = CustomizedScanners.scanNonEmptyString(EMAIL, new Scanner(System.in));
            }

        }
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
                Product product = new Product(productId, new ProductInfo(productName, productDescription, price,  quantity), true);
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
            } catch (ItemNotFoundException e) {
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
            } catch (CannotEditIdException e) {
               logger.warning("ID's cannot be edited.");
            }
        }
    }


}
