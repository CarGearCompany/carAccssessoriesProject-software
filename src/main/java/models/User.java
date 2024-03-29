package models;

import enums.Gender;
import enums.UserType;
import exceptions.WeakPasswordException;
import helpers.PasswordChecker;

public class User {
    private Name name;
    private int age;
    private Gender gender;
    private String password;
    private ContactInfo contactInfo;
    private UserType userType;

    public User() {
    }

    public User(Name name, int age, Gender gender, String password, ContactInfo contactInfo , UserType userType) throws WeakPasswordException {
        this.name = name;
        this.age = age;
        this.gender = gender;
        setPassword(password);
        this.contactInfo = contactInfo;
        this.userType = userType;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws WeakPasswordException {

            if (PasswordChecker.isStrongPassword(password))
                this.password = password;
            else
                throw new WeakPasswordException();




    }

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType)  {
        this.userType = userType;
    }
}
