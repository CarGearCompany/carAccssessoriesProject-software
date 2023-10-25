package scanners;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Logger;
public class CustomScanner {

    private static final Logger logger = Logger.getLogger(CustomScanner.class.getName());

    private CustomScanner() {
    }

    private static void printScanMsg(String type) {
        String msg = "Enter " + type + " :";
        logger.info(msg);
    }

    private static void printWarnMsg(String type) {
        String msg = "invalid " + type + "! Please, enter valid one";
        logger.warning(msg);
    }

    public static int scanInt(String type, Scanner scanner) {
        printScanMsg(type);
        try {
            return scanner.nextInt();
        } catch (InputMismatchException inputMismatchException) {
            scanner.next();
            printWarnMsg(type);
            return -1; // invalid value
        }
    }
    public static String scanNonEmptyString(String type, Scanner scanner) {
        printScanMsg(type);
        String scanString;
        scanString = scanner.nextLine();
        if (!scanString.isEmpty())
            return scanString;
        printWarnMsg(type);
        return null; // invalid value
    }
}

