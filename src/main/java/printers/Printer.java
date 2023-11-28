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
                    +" He is " +u.getAge() +" years old , his email is: " +u.getContactInfo().getEmail()+
                    ", he lives in " + u.getContactInfo().getLocation().getCity() + " at "+u.getContactInfo().getLocation().getStreet()+STREET
                    + " and his phone-number is: " + u.getContactInfo().getPhoneNumber() +"\n"+" and he is a " +u.getUserType();
        } else if (u.getGender() == Gender.FEMALE) {
            string=  FIRSTNAME + u.getName().getFirstName() + LASTNAME + u.getName().getLastName() + "\n"
                    +"She is " +u.getAge() +" years old , her email is: " +u.getContactInfo().getEmail()
                    +", she lives in " + u.getContactInfo().getLocation().getCity() + " at "+u.getContactInfo().getLocation().getStreet()+STREET
                    + " and her phone-number is : " + u.getContactInfo().getPhoneNumber() +"\n"+" and she is a " +u.getUserType();
        }
        return string;
    }

    public static void genarateCategoryString(Category c) {
        String string = "";
        for (Product p : c.getProducts()) {
            if(p.isAvailable()){
            string = "The product is from category: "+c.getCategoryName()+", its ID is: " + p.getId() + ", named: "+ p.getProductInfo().getProductName() + ",it's " + p.getProductInfo().getDescription()
                    +" it falls at the price of: "+p.getProductInfo().getPrice()+", and we have "+p.getProductInfo().getQuantity() + " items of it.";}
            else
            {string = "The product is from category: "+c.getCategoryName()+",, its ID is: " + p.getId() + ", named: "+ p.getProductInfo().getProductName() + ",it's " + p.getProductInfo().getDescription()
                        +" it falls at the price of: "+p.getProductInfo().getPrice()+", and its out of stock";}
            logger.info(string);
        }
    }






    public static String generateAppointment(Schedule a){
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
    public static void printSchedule(Schedule a){
        String msg = generateAppointment(a);
        logger.info(msg);


    }

    public static void printSchedules(List<Schedule> schedules){
        if(schedules.isEmpty()){
            logger.info("Schedule is empty.");
        }
        else {
            for (Schedule s:schedules){
                printSchedule(s);
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
    public static void printUsers(List<User> users) {
        if(users.isEmpty()){
            logger.info("No users found .");
        }
        else {
            for (User u : users) {
                printUser(u);

            }
        }
    }

    public static void printProducts(List<Product> products) {
        if(products.isEmpty()){
            logger.info("No products found .");
        }
        else {
            for (Product p : products) {
                printProduct(Objects.requireNonNull(CarGear.getCategoryOfProduct(p)), p);

            }
        }
    }
    public static void printProduct(Category c , Product p){
       String string = "The product is from category : "+c.getCategoryName()+", it's ID is:- " + p.getId() + ", named:- "+ p.getProductInfo().getProductName() + ", its " + p.getProductInfo().getDescription()
                +", falls at the price of:- "+p.getProductInfo().getPrice()+", and we have "+p.getProductInfo().getQuantity() + " items of it.";
        logger.info(string);
        }

    public static void printRequests(List<Request> requests){
        if(requests.isEmpty()){
            logger.info("No requests found.");
        }
        else {
            for (Request r : requests) {
                printRequest(r);
            }
        }
    }
    public static void printRequest(Request request) {
        String string = "This request was requested by: " + request.getCustomerEmail() + ", and directed to: " + request.getInstallerEmail() +
                ", for the product: "+ request.getProduct().getProductInfo().getProductName() + "to the car model: " + request.getCarModel()+
                ", booked for "+request.getDate();

        logger.info(string);
    }
}




