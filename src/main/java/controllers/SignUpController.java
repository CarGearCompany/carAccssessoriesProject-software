package controllers;

import enums.Gender;
import exceptions.*;
import helpers.EmailFormatChecker;
import helpers.PasswordChecker;
import helpers.PhoneNumberChecker;
import models.*;
import enums.UserType;

public class SignUpController {

    private SignUpController(){

    }

    public static void signUp(Name name, ContactInfo contactInfo, int age, Gender gender, String password, UserType userType) throws CannotSignUpAsAdminException, UserAlreadyExistsException, InvalidEmailFormatException, InvalidPhoneNumberException, WeakPasswordException {
        if(EmailFormatChecker.hasCorrectEmailFormat(contactInfo.getEmail())) {
            throw new InvalidEmailFormatException();
        }
        if(!PhoneNumberChecker.isValidPhoneNumber(contactInfo.getPhoneNumber())){
            throw new InvalidPhoneNumberException();
        }
        if(CarGear.isEmailRegistered(contactInfo.getEmail())){
            throw new UserAlreadyExistsException();
        }
        if(userType == UserType.ADMIN)
            throw new CannotSignUpAsAdminException();

        if(!PasswordChecker.isStrongPassword(password))
            throw new WeakPasswordException();

        if(userType == UserType.CUSTOMER)
            CarGear.addUser(new Customer(name, age, gender, password, contactInfo,userType));

        if(userType == UserType.INSTALLER)
            CarGear.addUser(new Installer(name, age, gender, password, contactInfo,userType));
    }

}
