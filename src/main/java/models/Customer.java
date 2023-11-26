package models;

import enums.Gender;
import enums.UserType;
import exceptions.WeakPasswordException;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User{

    public Customer() {
    }

    public Customer(Name name, int age, Gender gender, String password, ContactInfo contactInfo, UserType userType) throws WeakPasswordException {
        super(name, age, gender, password, contactInfo, userType);
    }
}
