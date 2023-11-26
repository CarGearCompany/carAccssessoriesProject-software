package printers;
import java.util.logging.Logger;
public class MenuPrinter {
    private static final Logger logger = Logger.getLogger(MenuPrinter.class.getName());
    private MenuPrinter() {
    }

    public static void printWelcomeMsg() {
        String string = """
                
                Hello and Welcome to CarGear Application
                This Application Made By Nabeel and Mahmoud
                
                """;
        logger.info(string);
    }

    public static void printFinishMsg(){
        String string = """
                
                Bye Bye\s
                Have A Good Day :) <3
                
                """;
        logger.info(string);
    }

    public static void printMainMenu(){
        String string = """
                
                1- Login
                2- SignUp
                3- Forget My Password
                4- Quit
                
                 """;
        logger.info(string);
    }

    public static void printAdminMenu(){
        String string = """
                
                1-  List all users
                2-  List all products
                3-  Add a product to a category
                4-  Add a category
                5-  Remove a user
                6-  Remove a product
                7-  Remove a category
                8-  Edit a product
                9-  Search for user by email
                10- Search for a product
                11- Search for category by name
                12- Promote user to admin
                13- Logout
                
                """;
        logger.info(string);
    }

    public static void printCustomerMenu(){
        String string = """
          
                1- List all products
                2- Purchase product\s
                3- View my orders
                4- Edit my password
                5- Edit my location info (city , street)
                6- Logout
                
                """;
        logger.info(string);
    }
}
