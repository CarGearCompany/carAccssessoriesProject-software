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

    public static void searchForUserByEmailView() {
        String email = CustomScanner.scanNonEmptyString(EMAIL, new Scanner(System.in));

        try {
            Printer.printUser(Admin.searchForUserByEmail(email));
        }catch (UserNotFoundException e){
            logger.warning("User Doesn't Exist");
        }catch (InvalidEmailFormatException e){
            logger.warning("Invalid email format! Must be a real email.");
        }

    }

    public static void removeUserView() {
        String email = CustomScanner.scanNonEmptyString(EMAIL, new Scanner(System.in));
        try {
            Admin.removeUser(email);
            logger.info("User removed successfully.");
        } catch (UserNotFoundException e) {
            logger.warning("This user is not found.");
        } catch (AdminsCannotBeRemovedException e) {
            logger.warning("Admins cannot be removed.");
        } catch (InvalidEmailFormatException e) {
            logger.warning("Not a real email, please enter a valid one.");
        }



    }

    public static void promoteUserView() {
        String email = CustomScanner.scanNonEmptyString(EMAIL, new Scanner(System.in));
        try {
            Admin.promoteUser(email);
            logger.info("This user is promoted to admin role successfully.");
        } catch (UserNotFoundException e) {
            logger.warning("This user is not found.");
        } catch (AdminsCannotBePromotedException e) {
            logger.warning("Admins cannot be promoted.");
        } catch (InvalidEmailFormatException e) {
            logger.warning("Not a real email, please enter a valid one.");
        }



    }



}
