package launchers;

import controllers.Login;
import enums.UserType;
import models.CarGear;
import printers.MenuPrinter;
import scanners.CustomScanner;
import views.LoginView;
import views.LogoutView;
import views.SignUpView;

import java.util.Scanner;
import java.util.logging.Logger;

public class Menu {
    private static final Logger logger = Logger.getLogger(Menu.class.getName());

    private Menu() {

    }

    /*
        every menu should have a handler and option
        the handler who decide to call the function from the views
        and print it
        ---
        the option for user option
     */
    private static void determineUserType() throws NullPointerException {
        try {
            UserType currentUserType = Login.getCurrentUserType();
            switch (currentUserType) {
                case ADMIN:
                    adminHandler();
                    break;
                case CUSTOMER:
                    //customerHandler();
                    break;
                case INSTALLER:
                    //installerHandler();
                    break;
            }
        } catch (NullPointerException e) {
            // to avoid the nullPointer Exception
        }

    }

    public static void mainMenuOptions(int choice) {
        switch (choice) {
            case 1:
                LoginView.login();
                determineUserType();
                break;
            case 2:
                SignUpView.signUp();
                break;
            case 3:
                break;
            default:
        }
    }

    public static void menuHandler() {
        MenuPrinter.printWelcomeMsg();
        while (true) {
            MenuPrinter.printMainMenu();
            int choice = CustomScanner.scanInt("choice", new Scanner(System.in));
            mainMenuOptions(choice);
            if (choice == 4) {
                MenuPrinter.printFinishMsg();
                break;
            }
        }
    }

    private static void adminOption(int choice) {
        switch (choice) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            default:
        }
    }

    public static void adminHandler() {
        while (true) {
            MenuPrinter.printAdminMenu();
            int choice = CustomScanner.scanInt("choice", new Scanner(System.in));
            adminOption(choice);
            if (choice == 6) {
                MenuPrinter.printFinishMsg();
                LogoutView.logout();
                break;
            }
        }
    }
//    private static void customerOption(){
//        // not finished , Under construction
//    }
//    private static void customerHandler(){
//        // not finished , Under construction
//    }
//    private static void installerOption(){
//        // not finished , Under construction
//    }
//    public static void installerHandler(){
//        // not finished , Under construction
//    }


    }



