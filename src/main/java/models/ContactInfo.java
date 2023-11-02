package models;

import exceptions.InvalidEmailFormatException;
import exceptions.InvalidPhoneNumberException;
import exceptions.UserAlreadyExistsException;
import helpers.EmailFormatChecker;
import helpers.PhoneNumberChecker;

public class ContactInfo {
    private String email;
    private String phoneNumber;
    private Location location;

    public ContactInfo(String email, String phoneNumber, Location location) throws InvalidEmailFormatException, InvalidPhoneNumberException, UserAlreadyExistsException {
        setEmail(email);
        setPhoneNumber(phoneNumber);
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws InvalidEmailFormatException, UserAlreadyExistsException {
        if(CarGear.isEmailRegistered(email)){
            throw new UserAlreadyExistsException();
        }

        if(!EmailFormatChecker.hasCorrectEmailFormat(email)){
            throw new InvalidEmailFormatException();
        }
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) throws InvalidPhoneNumberException {
        if(!PhoneNumberChecker.isValidPhoneNumber(phoneNumber))
            throw new InvalidPhoneNumberException();
        this.phoneNumber = phoneNumber;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
