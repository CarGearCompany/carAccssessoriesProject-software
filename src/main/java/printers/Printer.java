package printers;

import enums.Gender;
import enums.UserType;
import models.CarGear;
import models.Category;
import models.Product;
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
        String string ="";
        if(u.getGender() == Gender.MALE){
            string=  FIRSTNAME + u.getName().getFirstName() + LASTNAME + u.getName().getLastName() + "\n"
                    +"He is " +u.getAge() +" years old , his Email is: " +u.getContactInfo().getEmail()+
                    " , he lives in " + u.getContactInfo().getLocation().getCity() + " at "+u.getContactInfo().getLocation().getStreet()+STREET
                    + " and his phone-number: " + u.getContactInfo().getPhoneNumber() +"\n"+"And he is a " +u.getUserType();
        } else if (u.getGender() == Gender.FEMALE) {
            string=  FIRSTNAME + u.getName().getFirstName() + LASTNAME + u.getName().getLastName() + "\n"
                    +"she is " +u.getAge() +" years old , her Email is: " +u.getContactInfo().getEmail()
                    +" , she lives in " + u.getContactInfo().getLocation().getCity() + " at "+u.getContactInfo().getLocation().getStreet()+STREET
                    + " and her phone-number: " + u.getContactInfo().getPhoneNumber() +"\n"+"And she is a " +u.getUserType();
        }
        return string;
    }
    public static void genarateCategoryString(Category c) {
        String string = "";
        for (Product p : c.getProducts()) {
            if(p.isAvailable()){
            string = "Its From : "+c.getCategoryName()+", The product ID is:- " + p.getId() + ", The product Name is:- "+ p.getProductInfo().getProductName() + ",its " + p.getProductInfo().getDescription()
                    +"its price is:- "+p.getProductInfo().getPrice()+", and we have "+p.getProductInfo().getQuantity() + " items.";}
            else
            {string = "Its From : "+c.getCategoryName()+", The product ID is:- " + p.getId() + ", The product Name is:- "+ p.getProductInfo().getProductName() + ",its " + p.getProductInfo().getDescription()
                        +"its price is:- "+p.getProductInfo().getPrice()+", and its out of stock";}
            logger.info(string);
        }
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
    public static void printUser(User u) {
        String string = genarateUserString(u);
        logger.info(string);
    }

    public static void printCategoryAllProducts(Category c) {
        genarateCategoryString(c);

    }

    public static void printProduct(Category c , Product p){
       String string = "Its From : "+c.getCategoryName()+", The product ID is:- " + p.getId() + ", The product Name is:- "+ p.getProductInfo().getProductName() + ",its " + p.getProductInfo().getDescription()
                +"its price is:- "+p.getProductInfo().getPrice()+", and we have "+p.getProductInfo().getQuantity() + " items.";
        logger.info(string);
        }
    }


