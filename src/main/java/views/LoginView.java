package views;

import controllers.LoginController;
import exceptions.InvalidEmailFormatException;
import exceptions.UserNotFoundException;
import exceptions.WeakPasswordException;
import helpers.EmailService;
import helpers.PasswordChecker;
import models.CarGear;
import models.User;
import scanners.CustomizedScanners;

import javax.mail.MessagingException;
import java.util.Scanner;
import java.util.logging.Logger;

public class LoginView {
    private static final Logger logger = Logger.getLogger(LoginView.class.getName());
    private static final String EMAIL = "email";
    private LoginView() {
    }

    public static void login() {
        String email = CustomizedScanners.scanNonEmptyString(EMAIL, new Scanner(System.in));
        String password = CustomizedScanners.scanNonEmptyString("Password", new Scanner(System.in));
        while (true) {
            try {
                if (LoginController.login(email, password)) {
                    String msg = "Hi, " + CarGear.getCurrentUser().getName().getFirstName();
                    logger.info(msg);
                    break;
                } else {
                    logger.warning("wrong Password , try again or choose Forget My Password");
                    password = CustomizedScanners.scanNonEmptyString("Password", new Scanner(System.in));
                }
            } catch (UserNotFoundException e) {
                logger.warning("User not found.");
                email = CustomizedScanners.scanNonEmptyString(EMAIL, new Scanner(System.in));
            } catch (InvalidEmailFormatException e) {
                logger.warning("Not a real email, please enter a valid one.");
                email = CustomizedScanners.scanNonEmptyString(EMAIL, new Scanner(System.in));
            }

        }
    }

    public static void forgetPassword() throws UserNotFoundException, InvalidEmailFormatException, MessagingException, WeakPasswordException {
        String email = CustomizedScanners.scanNonEmptyString(EMAIL, new Scanner(System.in));
        User user = CarGear.getUserByEmail(email);
        String newPass;
        String newPassConfirm;

                    EmailService.sendEmailVerification("cargearcompany@gmail.com", email);
                    newPass = CustomizedScanners.scanNonEmptyString("your new password", new Scanner(System.in));
                    newPassConfirm = CustomizedScanners.scanNonEmptyString2("Confirm your new password", new Scanner(System.in));
                while (true) {
                    try {
                    assert newPass != null;
                    if (newPass.equals(newPassConfirm)) {
                        user.setPassword(newPass);
                        logger.info("New password updated successfully.");
                        break;
                    }
                    else {
                        logger.warning("Mismatch between the 2 passwords, please reenter them");
                        newPass = CustomizedScanners.scanNonEmptyString("your new password", new Scanner(System.in));
                        newPassConfirm = CustomizedScanners.scanNonEmptyString2("Confirm your new password", new Scanner(System.in));
                    }






            } catch (WeakPasswordException e) {
                logger.warning("Weak Password! Must contain at least 1 uppercase letter, 1 lowercase letter, 1 digit, 1 special character and must contain 8-16 characters.");
                newPass = CustomizedScanners.scanNonEmptyString("your new password", new Scanner(System.in));
                newPassConfirm = CustomizedScanners.scanNonEmptyString2("Confirm your new password", new Scanner(System.in));
            }
        }
    }
}
