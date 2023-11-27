package launchers;

import controllers.LoginController;
import enums.UserType;
import exceptions.CategoryNotFoundException;
import exceptions.ProductNotFoundException;
import printers.MenuPrinter;
import scanners.CustomizedScanners;
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
            UserType currentUserType = LoginController.getCurrentUserType();
            switch (currentUserType) {
                case ADMIN:
                    adminHandler();
                    break;
                case CUSTOMER:
                    customerHandler();
                    break;
                case INSTALLER:
                    installerHandler();
                    break;
            }
        } catch (NullPointerException | CategoryNotFoundException e) {
            // to avoid the nullPointer Exception
        } catch (ProductNotFoundException e) {
            //
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
            int choice = CustomizedScanners.scanInt("choice", new Scanner(System.in));
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
                AdminView.listAllProducts();
                break;
            case 3:
                AdminView.addProduct();
                break;
            case 4:
                AdminView.addCategory();
                break;
            case 5:
                AdminView.removeUserView();
                break;
            case 6:
                AdminView.removeProduct();
                break;
            case 7:
                AdminView.removeCategory();
                break;
            case 8:
                AdminView.editProduct();
                break;
            case 9:
                AdminView.searchForUserByEmailView();
                break;
            case 10:
                AdminView.searchForProduct();
                break;
            case 11:
                AdminView.searchForCategoryByName();
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
            int choice = CustomizedScanners.scanInt("choice", new Scanner(System.in));
            adminOption(choice);
            if (choice == 13) {
                MenuPrinter.printFinishMsg();
                LogoutView.logout();
                break;
            }
        }
    }
    private static void customerOption(int choice) throws CategoryNotFoundException, ProductNotFoundException {
        switch (choice) {
            case 1:
                CustomerView.listAllProducts();
                break;
            case 2:
                CustomerView.purchaseProduct();
                break;
            case 3:
                CustomerView.displayOrderHistory();

                break;
            case 4:
                CustomerView.displaySchedules();

                break;
            case 5:
                CustomerView.requestService();
                break;
            case 6:
                CustomerView.displayRequests();
                break;
            case 7:
                CustomerView.editPassword();
                break;
            case 8:
                CustomerView.editLocation();
                break;

            default:
        }

    }
    private static void customerHandler() throws CategoryNotFoundException, ProductNotFoundException {
        while (true) {
            MenuPrinter.printCustomerMenu();
            int choice = CustomizedScanners.scanInt("choice", new Scanner(System.in));
            customerOption(choice);
            if (choice == 9) {
                MenuPrinter.printFinishMsg();
                LogoutView.logout();
                break;
            }
        }
    }
    private static void installerOption(int choice){
        switch (choice) {
            case 1:
                InstallerView.viewInstallationRequests();
                break;
            case 2:
                InstallerView.viewSchedule();
                break;
            case 3:
                InstallerView.addDatesToSchedule();

                break;
            case 4:
                InstallerView.editMyPassword();

                break;
            case 5:
                InstallerView.EditMyLocation();
                break;
            default:
        }
    }
    public static void installerHandler(){
        while (true) {
            MenuPrinter.printInstallerMenu();
            int choice = CustomizedScanners.scanInt("choice", new Scanner(System.in));
            installerOption(choice);
            if (choice == 6) {
                MenuPrinter.printFinishMsg();
                LogoutView.logout();
                break;
            }
        }
    }


    }



