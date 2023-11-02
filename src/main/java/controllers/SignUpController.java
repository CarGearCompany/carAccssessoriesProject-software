package controllers;

import enums.Gender;
import exceptions.*;
import models.*;
import enums.UserType;

public class SignUpController {

    private SignUpController(){

    }

    public static void signUp(Name name, ContactInfo contactInfo, int age, Gender gender, String password, UserType userType) throws CannotSignUpAsAdminException, WeakPasswordException, UserAlreadyExistsException {
        if(userType == UserType.ADMIN)
            throw new CannotSignUpAsAdminException();

        CarGear.addUser(new User(name, age, gender, password, contactInfo,userType));
    }

}
