package views;

import controllers.Admin;
import exceptions.InvalidEmailFormatException;
import exceptions.UserNotFoundException;
import printers.Printer;
import scanners.CustomScanner;

import java.util.Scanner;
import java.util.logging.Logger;

public class AdminView {
    private static final Logger logger = Logger.getLogger(AdminView.class.getName());
    private AdminView() {
    }

    public static void listAllUsersView(){
        Printer.printUsers(Admin.getAllUsers());
    }

    public static void searchForUserByEmailView(){
        String email = CustomScanner.scanNonEmptyString("Email", new Scanner(System.in));

        try {
            Printer.printUser(Admin.searchForUserByEmail(email));
        }catch (UserNotFoundException e){
            logger.warning("User Doesn't Exist");
        }catch (InvalidEmailFormatException e){
            logger.warning("Invalid email format! Must be a real email.");
        }

    }


}
