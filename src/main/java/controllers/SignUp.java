package controllers;

import enums.Gender;
import exceptions.*;
import helpers.EmailFormatChecker;
import helpers.PasswordChecker;
import helpers.PhoneNumberChecker;
import models.*;
import enums.UserType;

public class SignUp {

    private SignUp(){

    }

    public static void signUp(Name name, ContactInfo contactInfo, int age, Gender gender, String password, UserType userType) throws EmailAlreadyExistsException, WeakPasswordException, InvalidEmailFormatException, CannotSignUpAsAdminException, UserNotFoundException,InvalidPhoneNumberException {
        if(userType == UserType.ADMIN){
            throw new CannotSignUpAsAdminException();
        }
        if(!PasswordChecker.isStrongPassword(password)){
            throw new WeakPasswordException();
        }
        if(!PhoneNumberChecker.isValidPhoneNumber(contactInfo.getPhoneNumber())){
            throw new InvalidPhoneNumberException();
        }
        CarGear.addUser(new User(name, age, gender, password, contactInfo,userType));

    }

}
