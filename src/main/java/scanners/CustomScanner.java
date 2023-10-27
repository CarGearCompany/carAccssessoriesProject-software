package scanners;
import enums.Gender;
import enums.UserType;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Logger;
public class CustomScanner {

    private static final Logger logger = Logger.getLogger(CustomScanner.class.getName());

    private CustomScanner() {
    }

    private static void printScanMsg(String credential) {
        String msg = "Enter " + credential + " :";
        logger.info(msg);
    }

    private static void printWarnMsg(String credential) {
        String msg = "invalid " + credential + "! Please, enter valid one";
        logger.warning(msg);
    }

    public static int scanInt(String credential, Scanner scanner) {
        printScanMsg(credential);
        try {
            return scanner.nextInt();
        } catch (InputMismatchException inputMismatchException) {
            scanner.next();
            printWarnMsg(credential);
            return -1; // invalid value
        }
    }

    public static UserType scanUserType(String type, Scanner scanner){
        printScanMsg(type);
        UserType userType;
        String userTypeString = scanner.nextLine();
        userType = stringToUserType(userTypeString);

        return null;
    }
    private static UserType stringToUserType(String userType) {
        if (userType.equalsIgnoreCase("admin"))
            return UserType.ADMIN;
        else if (userType.equalsIgnoreCase("customer"))
            return UserType.CUSTOMER;
        else if (userType.equalsIgnoreCase("installer"))
            return UserType.INSTALLER;
        return null;
    }

    private static Gender stringToGender(String gender) {
        if (gender.equalsIgnoreCase("male")||gender.equalsIgnoreCase("M"))
            return Gender.MALE;
        else if (gender.equalsIgnoreCase("female")||gender.equalsIgnoreCase("F"))
            return Gender.FEMALE;
        return null;
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

