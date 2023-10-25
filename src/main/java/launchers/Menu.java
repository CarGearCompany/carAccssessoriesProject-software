package launchers;

import controllers.Login;
import enums.UserType;
import printers.MenuPrinter;
import scanners.CustomScanner;
import views.LoginView;

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
    private static void determineUserType()  {
        UserType currentUserType = Login.getCurrentUserType();
        switch (currentUserType){
            case ADMIN:

                break;
            case CUSTOMER:

                break;
            case INSTALLER:

                break;
        }

    }
    public static void mainMenuOptions(int choice){
        switch (choice){
            case 1:
                LoginView.login();
                determineUserType();
                break;
            case 2:
                break;
            case 3:
                break;
            default:
                logger.warning("Invalid Choice !!");
        }
    }
    public static void menuHandler(){
        MenuPrinter.printWelcomeMsg();
        while (true){
            MenuPrinter.printMainMenu();
            int choice = CustomScanner.scanInt("choice",new Scanner(System.in));
            mainMenuOptions(choice);
            if (choice == 4)
                MenuPrinter.printFinishMsg();
            break;
        }
    }



}



