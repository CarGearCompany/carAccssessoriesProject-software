package printers;

import enums.Gender;
import enums.UserType;
import models.User;

import java.util.List;
import java.util.logging.Logger;

public class Printer {
    private static final Logger logger = Logger.getLogger(Printer.class.getName());
    private static final String FIRSTNAME = "- FirstName: ";
    private static final String LASTNAME = " LastName: ";
    private static final String STREET = " Street ,";

    private Printer() {
    }
    private static String genarateUserString(User u){
        if(u.getGender() == Gender.MALE){
            return  FIRSTNAME + u.getName().getFirstName() + LASTNAME + u.getName().getLastName() + "\n"
                    +"He is " +u.getAge() +" years old , his Email is: " +u.getContactInfo().getEmail()+
                    " , he lives in " + u.getContactInfo().getLocation().getCity() + " at "+u.getContactInfo().getLocation().getStreet()+STREET
                    + " and his phone-number: " + u.getContactInfo().getPhoneNumber() +"\n";
        } else if (u.getGender() == Gender.FEMALE) {
            return  FIRSTNAME + u.getName().getFirstName() + LASTNAME + u.getName().getLastName() + "\n"
                    +"she is " +u.getAge() +" years old , her Email is: " +u.getContactInfo().getEmail()
                    +" , she lives in " + u.getContactInfo().getLocation().getCity() + " at "+u.getContactInfo().getLocation().getStreet()+STREET
                    + " and her phone-number: " + u.getContactInfo().getPhoneNumber() +"\n";
        }
        return null;
    }
    public static void printUsers(List<User> users) {
        String string;
        if (users.isEmpty())
            logger.info("No users!");

        logger.info("Customers :-");
        for (User u : users) {
            if(u.getUserType() == UserType.CUSTOMER ) {
                string = genarateUserString(u);
                logger.info(string);
            }
        }
        logger.info("Installers :-");
        for (User u : users) {
            if(u.getUserType() == UserType.INSTALLER) {
                string = genarateUserString(u);
                logger.info(string);
            }
        }

    }

}