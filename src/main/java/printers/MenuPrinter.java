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
}
