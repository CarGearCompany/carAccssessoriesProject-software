package views;

import controllers.ForgetPasswordController;
import exceptions.*;
import helpers.EmailService;
import models.User;
import scanners.CustomizedScanners;
import java.util.Scanner;
import java.util.logging.Logger;

public class ForgetPasswordView {


    private static final Logger logger = Logger.getLogger(ForgetPasswordView.class.getName());
    private ForgetPasswordView() {
    }


    public static void forgetPassword() {
        String email;
        User user;
        String newPass;
        String confirmPass;
        String verificationCode;
        String userCode;
        EmailService emailService = new EmailService();

            while(true) {
                try {
                    email = CustomizedScanners.scanNonEmptyString("your email", new Scanner(System.in));
                    user = ForgetPasswordController.getUser(email);
                    break;
                } catch (UserNotFoundException e) {
                    logger.warning("User not found !");

                } catch (InvalidEmailFormatException e) {
                    logger.warning("Invalid email format");

                }
            }

            verificationCode = ForgetPasswordController.getCode(emailService,email);
            userCode = CustomizedScanners.scanNonEmptyString("the code", new Scanner(System.in));

            while(true) {
                try {


                    ForgetPasswordController.areEqual(verificationCode, userCode, 0,user);

                    newPass = CustomizedScanners.scanNonEmptyString("your new password", new Scanner(System.in));
                    confirmPass = CustomizedScanners.scanNonEmptyString2("confirm your new password", new Scanner(System.in));

                    assert newPass != null;
                    ForgetPasswordController.areEqual(newPass, confirmPass, 1,user);

                    logger.info("Password updated successfully");
                    break;


                } catch (NotEqualCodesException e) {
                    logger.warning("Wrong code, please try again.");
                    userCode = CustomizedScanners.scanNonEmptyString("the code", new Scanner(System.in));

                } catch (NotEqualPasswordsException e) {
                    logger.warning("Mismatch between the 2 passwords, please re-enter them:");

                } catch (WeakPasswordException e) {
                    logger.warning("Weak Password! Must contain at least 1 uppercase letter, 1 lowercase letter, 1 digit, 1 special character and must contain 8-16 characters.");

                }


            }




    }
}
