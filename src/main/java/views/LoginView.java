package views;

import controllers.Login;
import exceptions.InvalidEmailFormatException;
import exceptions.UserNotFoundException;
import models.CarGear;
import scanners.CustomScanner;

import java.util.Scanner;
import java.util.logging.Logger;

public class LoginView {
    private static final Logger logger = Logger.getLogger(LoginView.class.getName());
    private static final String EMAIL = "Email";
    private LoginView() {
    }

    public static void login() {
        String email = CustomScanner.scanNonEmptyString(EMAIL, new Scanner(System.in));
        String password = CustomScanner.scanNonEmptyString("Password", new Scanner(System.in));
        while (true) {
            try {
                if (Login.login(email, password)) {
                    String msg = "Hi, " + CarGear.getCurrentUser().getName().getFirstName();
                    logger.info(msg);
                    break;
                } else {
                    logger.warning("wrong Password , try again or choose Forget My Password");
                    password = CustomScanner.scanNonEmptyString("Password", new Scanner(System.in));
                }
            } catch (UserNotFoundException e) {
                logger.warning("User not found.");
                email = CustomScanner.scanNonEmptyString(EMAIL, new Scanner(System.in));
            } catch (InvalidEmailFormatException e) {
                logger.warning("Not a real email, please enter a valid one.");
                email = CustomScanner.scanNonEmptyString(EMAIL, new Scanner(System.in));
            }

        }
    }

}
