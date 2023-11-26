package printers;

import enums.Gender;
import enums.UserType;
import models.*;

import java.util.List;
import java.util.Objects;
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
    public static String generateProductAvailability(Product product){
        if(product.isAvailable())
            return "Available";
        else
            return "Not available";
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
    public static void appendHorizontalLine(StringBuilder stringBuilder, int count) {
        stringBuilder.append("\n");
        stringBuilder.append("-".repeat(count));
    }





    public static String generateAppointment(Appointment a){
        String msg = "";
        if(Boolean.TRUE.equals(a.getReserved())){
            msg = " Installer Email: " + a.getInstallerEmail() + ", Date: " + a.getDate() + ", this date is already booked by Customer who has email:" +
            a.getCustomerEmail();

        }
        else {
            msg = " Installer Email: " + a.getInstallerEmail() + ", Date: " + a.getDate() + ", this date is available for booking." ;

        }

        return msg;





    }
    public static void printSchedule(Appointment a){
        String msg = generateAppointment(a);
        logger.info(msg);


    }
    public static void printUser(User u) {
        String string = genarateUserString(u);
        logger.info(string);
    }

    public static void printCategoryAllProducts(Category c) {
        genarateCategoryString(c);

    }

    public static void printProducts(List<Product> products) {
        if(products.isEmpty()){
            logger.info("No products found that match your given information.");
        }
        else {
            for (Product p : products) {
                printProduct(Objects.requireNonNull(CarGear.getCategoryOfProduct(p)), p);

            }
        }
    }
    public static void printProduct(Category c , Product p){
       String string = "Its From : "+c.getCategoryName()+", The product ID is:- " + p.getId() + ", The product Name is:- "+ p.getProductInfo().getProductName() + ",its " + p.getProductInfo().getDescription()
                +", its price is:- "+p.getProductInfo().getPrice()+", and we have "+p.getProductInfo().getQuantity() + " items of it.";
        logger.info(string);
        }
    }


