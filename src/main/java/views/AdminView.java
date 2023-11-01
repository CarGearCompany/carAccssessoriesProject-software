package views;

import controllers.Admin;
import exceptions.AdminsCannotBePromotedException;
import exceptions.AdminsCannotBeRemovedException;
import exceptions.InvalidEmailFormatException;
import exceptions.UserNotFoundException;
import printers.Printer;
import scanners.CustomScanner;

import java.util.Scanner;
import java.util.logging.Logger;

public class AdminView {
    private static final String EMAIL = "Email";
    private static final Logger logger = Logger.getLogger(AdminView.class.getName());
    private AdminView() {
    }

    public static void listAllUsersView(){
        Printer.printUsers(Admin.getAllUsers());
    }

//    public static void listAllProducts(){
//        Printer.printProducts();
//    }

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



}
