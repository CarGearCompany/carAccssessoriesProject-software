package controllers;

import enums.Gender;
import exceptions.*;
import models.*;
import enums.UserType;

public class SignUp {

    private SignUp(){

    }

    public static void signUp(Name name, ContactInfo contactInfo, int age, Gender gender, String password, UserType userType) throws UserAlreadyExistsException, CannotSignUpAsAdminException, WeakPasswordException {
        if(userType == UserType.ADMIN)
            throw new CannotSignUpAsAdminException();

        CarGear.addUser(new User(name, age, gender, password, contactInfo,userType));
    }

}
