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
        String string = "\n" +"""
                
                Registering Page
                ------------------------------
                
                
                
                1- Login
                2- SignUp
                3- Forget My Password
                4- Quit
                
                
                -------------------------------
                
                 """;
        logger.info(string);
    }

    public static void printAdminMenu(){
        String string ="\n" + """
                
                
                Admin Dashboard
                ---------------------------
                
                
                
                1- Manage users.
                2- Manage products.
                3- Manage installation requests.
                4- Logout.
                
                
                
                ----------------------------
                
                """;
        logger.info(string);
    }

    public static void printAdminManageUsersMenu(){

        String string = "\n" +"""
                
                
                Users Management
                -----------------------------
          
                
                1- List all users.
                2- Remove a user.
                3- Search for user.
                4- Promote user to admin
                5- Go back to main admin dashboard.
                
                
                -----------------------------
                
                """;
        logger.info(string);



    }

    public static void printAdminManageProductsMenu(){

        String string ="\n" + """
                
                
                
                Products Management
                -------------------------------
                
                
                
                1- List all products.
                2- Add a product to a category.
                3- Add a category.
                4- Remove a product.
                5- Remove a category.
                6- Edit a product.
                7- Search for a product.
                8- Search for a category.
                9- Go back to main admin dashboard.
                
                
                --------------------------------
                
                """;
        logger.info(string);



    }

    public static void printAdminManageRequestsMenu(){

        String string = "\n" +"""
                
                
                Requests Management
                ------------------------------------
                
             
               
                1- List all installation requests.
                2- Add a request.
                3- Remove a request.
                4- Search for a request.
                5- Edit a request.
                6- Go back to main admin dashboard.
                
                
                
                -------------------------------------
                
                """;
        logger.info(string);



    }
    public static void printCustomerMenu(){
        String string = "\n" +"""
                
                
                Customers Dashboard
                -------------------------------------
                
          
                1- List all products.
                2- Purchase a product.
                3- View my purchase orders history.
                4- Display installer's schedules.
                5- Request installation service.
                6- View my installation requests.
                7- Edit my password.
                8- Edit my location info.
                9- Logout.
                
                
                -------------------------------------
                
                """;
        logger.info(string);
    }

    public static void printInstallerMenu(){
        String string = "\n" +"""
                
                
                Installers Dashboard
                ---------------------------------------
               
          
                1- View my installation requests.
                2- View my Schedule
                3- Add new Date to my Schedule
                4- Edit my password
                5- Edit my location info (city , street).
                6- Logout.
                
                
                
                --------------------------------------------
    
                """;
        logger.info(string);

    }


}
