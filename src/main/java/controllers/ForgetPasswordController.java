package controllers;

import exceptions.InvalidEmailFormatException;
import exceptions.PasswordsNotEqualsException;
import exceptions.UserNotFoundException;
import exceptions.WeakPasswordException;
import helpers.EmailFormatChecker;
import helpers.EmailService;
import models.CarGear;
import models.User;

import javax.mail.MessagingException;

public class ForgetPasswordController {
    private ForgetPasswordController() {
    }

    public static void forgetPassword(String email, String newPass, String confirmPass) throws UserNotFoundException, InvalidEmailFormatException, WeakPasswordException, PasswordsNotEqualsException {


        User user = CarGear.getUserByEmail(email);

            if (newPass.equals(confirmPass)) {
                user.setPassword(newPass);
            }
            else{
                    throw new PasswordsNotEqualsException();
            }
    }

    public static boolean isThisEmailExist(String email) throws InvalidEmailFormatException, UserNotFoundException, MessagingException {
        boolean userFound = false;

        if(!EmailFormatChecker.hasCorrectEmailFormat(email)) {
            throw new InvalidEmailFormatException();
        }

        for (User user:
                CarGear.getUsers()) {

            if (user.getContactInfo().getEmail().equals(email)) {
                userFound = Boolean.TRUE;
                break;
            }
        }

        if(!userFound){
            throw new UserNotFoundException();
        }
        EmailService.sendEmailVerification("cargearcompany@gmail.com", email);
        return true;
    }
}
