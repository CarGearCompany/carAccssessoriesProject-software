package controllers;

import exceptions.*;
import helpers.EmailService;
import models.CarGear;
import models.User;

public class ForgetPasswordController {
    private ForgetPasswordController() {
    }

    public static User getUser(String email) throws UserNotFoundException, InvalidEmailFormatException {
        return CarGear.getUserByEmail(email);
    }
    public static String getCode(String email)  {
        return EmailService.sendEmailVerification("cargearcompany@gmail.com", email);
    }

    public static void areEqual(String string1, String string2,int flag,User user) throws NotEqualCodesException, NotEqualPasswordsException, WeakPasswordException {
        if(string1.equals(string2)&&flag == 1)
            user.setPassword(string1);
        if(!string1.equals(string2)&&flag == 1)
            throw new NotEqualPasswordsException();
        if(!string1.equals(string2)&&flag == 0)
            throw new NotEqualCodesException();
    }

}
