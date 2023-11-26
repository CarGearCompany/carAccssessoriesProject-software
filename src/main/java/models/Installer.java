package models;

import enums.Gender;
import enums.UserType;
import exceptions.WeakPasswordException;

import java.util.ArrayList;
import java.util.List;

public class Installer extends User{
    private List<Schedule> scheduleList = new ArrayList<>();
    private List<Request> installerRequestList = new ArrayList<>();
    public Installer() {

    }

    public Installer(Name name, int age, Gender gender, String password, ContactInfo contactInfo, UserType userType) throws WeakPasswordException {
        super(name, age, gender, password, contactInfo, userType);
        this.setUserType(UserType.INSTALLER);
    }
}
