package views;

import controllers.Admin;
import printers.Printer;
import scanners.CustomScanner;

import java.util.Scanner;

public class AdminView {
    private AdminView() {
    }

    public static void listAllUsersView(){
        Printer.printUsers(Admin.getAllUsers());
    }

    public static void searchForUserByEmailView(){
        CustomScanner.scanNonEmptyString("Email", new Scanner(System.in));

    }


}
