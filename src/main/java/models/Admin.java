package models;

import enums.Gender;
import enums.UserType;
import exceptions.WeakPasswordException;

public class Admin extends User{
    public Admin() {
    }

    public Admin(Name name, int age, Gender gender, String password, ContactInfo contactInfo, UserType userType) throws WeakPasswordException {
        super(name, age, gender, password, contactInfo, userType);
        this.setUserType(UserType.ADMIN);
    }

}
