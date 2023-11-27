package controllers;

import exceptions.*;
import helpers.EmailSenderService;
import helpers.PasswordChecker;
import models.*;
import printers.Printer;

import javax.mail.MessagingException;

public class CustomerController {
    private static final String SENDER = "cargearcompany@gmail.com";
    private CustomerController() {
    }

    public static void displayOrderHistory(Customer customer){
        Printer.printProducts(customer.getPurchasedProducts());

    }
    public static int purchaseProduct(String category, int id,Customer customer,String confirm,int reqQuantity) throws ProductNotFoundException, CategoryNotFoundException, PurchaseNotConfirmedException, MessagingException, OutOfStockException, NotEnoughItemsAvailableException {
        String msg = "";
        String customerEmail = customer.getContactInfo().getEmail();
        int newQuantity;
        Category c = CarGear.getCategoryByName(category);
        Product product = CarGear.getProductById(c,id);
        int availQuantity = product.getProductInfo().getQuantity();
        if(product.isAvailable()) { // if product is available
            if(reqQuantity <=availQuantity) { // if required quantity less or equal than available quantity
                if (confirm.equalsIgnoreCase("y")) { // if confirmed answer is yes

                    newQuantity = availQuantity - reqQuantity;
                    product.getProductInfo().setQuantity(newQuantity);
                    customer.addProduct(product);
                    msg += "Customer Name: " + customer.getName().getFirstName() + " " + customer.getName().getLastName() + "\n" + "Product ID: " + id + "\n" + "Product Name: " + product.getProductInfo().getProductName()
                    + "\n"+ "Quantity bought: " + reqQuantity;
                    EmailSenderService.sendEmail(SENDER, customerEmail, msg);
                    return newQuantity;
                } else
                    throw new PurchaseNotConfirmedException();
            }else{
                throw new NotEnoughItemsAvailableException();

            }
        }
        else
            throw new OutOfStockException();
    }
    public static void editPassword(String newPassword) throws WeakPasswordException {
        if(!PasswordChecker.isStrongPassword(newPassword))
            throw new WeakPasswordException();

        User user = CarGear.getCurrentUser();
        user.setPassword(newPassword);
    }

    public static void editLocation(String newCity, String newStreet) {
        User user = CarGear.getCurrentUser();
        Location newLocation= new Location(newCity,newStreet);
        user.getContactInfo().setLocation(newLocation);

    }
}
