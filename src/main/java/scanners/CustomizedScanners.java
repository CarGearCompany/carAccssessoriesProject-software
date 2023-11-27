package scanners;
import enums.Gender;
import enums.UserType;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Logger;
public class CustomizedScanners {

    private static final Logger logger = Logger.getLogger(CustomizedScanners.class.getName());

    private CustomizedScanners() {
    }

    private static void printScanMsg(String credential) {
        String msg = "Enter " + credential + " :";
        logger.info(msg);
    }

    private static void printWarnMsg(String credential) {
        String msg = "invalid " + credential + "! Please, enter valid one";
        logger.warning(msg);
    }

    private static void printScanMsg2(String credential) {
        String msg = credential + " :";
        logger.info(msg);
    }

    public static String scanNonEmptyString2(String type, Scanner scanner) {
        printScanMsg2(type);
        String scanString;
        scanString = scanner.nextLine();
        if (!scanString.isEmpty())
            return scanString;
        printWarnMsg(type);
        return null; // invalid value
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

    public static int scanInt2(String credential, Scanner scanner) {
        printScanMsg2(credential);
        try {
            return scanner.nextInt();
        } catch (InputMismatchException inputMismatchException) {
            scanner.next();
            printWarnMsg(credential);
            return -1; // invalid value
        }
    }

    public static UserType scanUserType(Scanner scanner) {
        printScanMsg("user type");
        UserType userType;
        String userTypeAsString = scanner.nextLine();
        userType = stringToUserType(userTypeAsString);
        if (userType == null) {
            printWarnMsg("user type");
        }
        return userType;
    }
    public static Gender scanGender(Scanner scanner) {
        printScanMsg("Gender");
        Gender gender;
        String genderAsString = scanner.nextLine();
        gender = stringToGender(genderAsString);
        if (gender == null) {
            printWarnMsg("Gender");
        }
        return gender;
    }

    public static UserType stringToUserType(String userType) {
        if (userType.equalsIgnoreCase("admin"))
            return UserType.ADMIN;
        else if (userType.equalsIgnoreCase("customer"))
            return UserType.CUSTOMER;
        else if (userType.equalsIgnoreCase("installer"))
            return UserType.INSTALLER;
        return null;
    }

    public static Gender stringToGender(String gender) {
        if (gender.equalsIgnoreCase("male")||gender.equalsIgnoreCase("m"))
            return Gender.MALE;
        else if (gender.equalsIgnoreCase("female")||gender.equalsIgnoreCase("f"))
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

