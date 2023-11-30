package views;

import controllers.ForgetPasswordController;
import exceptions.InvalidEmailFormatException;
import exceptions.PasswordsNotEqualsException;
import exceptions.UserNotFoundException;
import exceptions.WeakPasswordException;
import scanners.CustomizedScanners;

import javax.mail.MessagingException;
import java.util.Scanner;
import java.util.logging.Logger;

public class ForgetPasswordView {
    private static final Logger logger = Logger.getLogger(ForgetPasswordView.class.getName());
    private ForgetPasswordView() {
    }


    public static void forgetPassword() {
        String email;
        String newPass;
        String confirmPass;

        while (true) {
            try {
                email = CustomizedScanners.scanNonEmptyString("your Email", new Scanner(System.in));
                if (ForgetPasswordController.isThisEmailExist(email))
                    break;
            } catch (UserNotFoundException e) {
                logger.warning("User not found !");
                email = CustomizedScanners.scanNonEmptyString("your Email", new Scanner(System.in));
            } catch (InvalidEmailFormatException e) {
                logger.warning("Invalid email format");
                email = CustomizedScanners.scanNonEmptyString("your Email", new Scanner(System.in));
            } catch (MessagingException e) {
                logger.warning("Email flied to send");
            }
        }
        newPass = CustomizedScanners.scanNonEmptyString("Enter the new passWord", new Scanner(System.in));
        confirmPass = CustomizedScanners.scanNonEmptyString("Enter the new passWord", new Scanner(System.in));

        while (true) {
            try {
                assert newPass != null;
                ForgetPasswordController.forgetPassword(email, newPass, confirmPass);
                logger.warning("Password Edited successfully");
                break;
            } catch (PasswordsNotEqualsException e) {
                logger.warning("Password and the confirm password are not the same");
                newPass = CustomizedScanners.scanNonEmptyString("Enter the new passWord", new Scanner(System.in));
                confirmPass = CustomizedScanners.scanNonEmptyString("Enter the new passWord", new Scanner(System.in));
            } catch (WeakPasswordException e) {
                logger.warning("Weak Password");
                newPass = CustomizedScanners.scanNonEmptyString("Enter the new passWord", new Scanner(System.in));
                confirmPass = CustomizedScanners.scanNonEmptyString("Enter the new passWord", new Scanner(System.in));

            } catch (UserNotFoundException e) {
                logger.warning("User not found !");
                email = CustomizedScanners.scanNonEmptyString("your Email", new Scanner(System.in));
            } catch (InvalidEmailFormatException e) {
                logger.warning("Invalid email format");
                email = CustomizedScanners.scanNonEmptyString("your Email", new Scanner(System.in));
            }
        }

    }
}
