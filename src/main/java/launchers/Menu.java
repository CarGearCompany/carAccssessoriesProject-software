package launchers;

import controllers.Login;
import enums.UserType;
import printers.MenuPrinter;
import scanners.CustomScanner;
import views.*;

import java.util.Scanner;


public class Menu {
    private Menu() {

    }

    /*
        every menu should have a handler and option
        the handler who decide to call the function from the views
        and print it
        ---
        the option for user option
     */
    private static void determineUserType()  {
        try {
            UserType currentUserType = Login.getCurrentUserType();
            switch (currentUserType) {
                case ADMIN:
                    adminHandler();
                    break;
                case CUSTOMER:
                    customerHandler();
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

    private static void adminOption(int choice)  {
        switch (choice) {
            case 1:
                AdminView.listAllUsersView();
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                AdminView.removeUserView();
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                break;
            case 9:
                AdminView.searchForUserByEmailView();
                break;
            case 10:
                break;
            case 11:
                break;
            case 12:
                AdminView.promoteUserView();
                break;
            default:
        }
    }

    public static void adminHandler()  {
        while (true) {
            MenuPrinter.printAdminMenu();
            int choice = CustomScanner.scanInt("choice", new Scanner(System.in));
            adminOption(choice);
            if (choice == 13) {
                MenuPrinter.printFinishMsg();
                LogoutView.logout();
                break;
            }
        }
    }
    private static void customerOption(int choice){
        switch (choice) {
            case 1:

                break;
            case 2:
                break;
            case 3:

                break;
            case 4:
                CustomerView.editPassword();
                break;
            case 5:
                CustomerView.editLocation();
                break;
            default:
        }

    }
    private static void customerHandler(){
        while (true) {
            MenuPrinter.printCustomerMenu();
            int choice = CustomScanner.scanInt("choice", new Scanner(System.in));
            customerOption(choice);
            if (choice == 6) {
                MenuPrinter.printFinishMsg();
                LogoutView.logout();
                break;
            }
        }
    }
//    private static void installerOption(){
//        // not finished , Under construction
//    }
//    public static void installerHandler(){
//        // not finished , Under construction
//    }


    }



