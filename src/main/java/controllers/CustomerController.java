package controllers;

import exceptions.*;
import helpers.EmailService;
import helpers.PasswordChecker;
import models.*;
import printers.Printer;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;

public class CustomerController {
    private static final String SENDER = "cargearcompany@gmail.com";
    private CustomerController() {
    }

    public static void displayOrderHistory(Customer customer){
        Printer.printProducts(customer.getPurchasedProducts());
    }
    public static int purchaseProduct(EmailService emailService,String category, int id,Customer customer,String confirm,int reqQuantity) throws ProductNotFoundException, CategoryNotFoundException, PurchaseNotConfirmedException, OutOfStockException, NotEnoughItemsAvailableException {
        String msg = "";
        String subj = "";
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
                    subj+= "Purchase Order Notification.";
                    msg += "<br><br><br><br><br>"+"Customer Name: " + customer.getName().getFirstName() + " " + customer.getName().getLastName() +
                            "<br>" + "Product ID: " + id +
                            "<br>" + "Product Name: " + product.getProductInfo().getProductName()
                            + "<br>"+ "Quantity bought: " + reqQuantity;
                    emailService.sendEmail(SENDER, customerEmail, msg,subj,0);
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

    public static void requestService(EmailService emailService,String installerEmail, String carModel, String date, String category, int productId) throws UserNotFoundException, InvalidEmailFormatException, CategoryNotFoundException, ProductNotFoundException, AlreadyReservedDateException, ItemNotFoundException {
      Installer installer = (Installer) CarGear.getUserByEmail(installerEmail);
      Customer customer = (Customer) CarGear.getCurrentUser();
      Category c = CarGear.getCategoryByName(category);
      Product p = CarGear.getProductById(c,productId);
      Schedule s = installer.getScheduleByDate(date);
      String msg ="";
      String subj = "";

        if (Boolean.TRUE.equals(s.getReserved()))
            throw new AlreadyReservedDateException();
        else{
            Request request = new Request(installerEmail,customer.getContactInfo().getEmail(),date,carModel,p);
            installer.addRequest(request);
            customer.addRequest(request);
            installer.getSchedules().get(installer.getSchedules().indexOf(s)).setReserved(true);
            s.setCustomerEmail(customer.getContactInfo().getEmail());
            subj += "Installation Request Notification";
            msg+=    "<br><br><br><br><br>"+ "Installer: " + installer.getName().getFirstName() + " " + installer.getName().getLastName() + "<br>" +
                    "Requested by Customer : " + customer.getName().getFirstName() + " " + customer.getName().getLastName()  + "<br>" +
                    "Product Requested : " + p.getProductInfo().getProductName() + "<br>"+
                    "For Car Model : " + carModel + "<br>" +
                    "Date Booked For : " + date;
            emailService.sendEmail(SENDER,installerEmail,msg,subj,1);
        }

    }

    public static void displayRequests(Customer customer) {
        Printer.printRequests(customer.getRequests());
    }



    public static void openImage(String category, int id) throws IOException, CategoryNotFoundException, ProductNotFoundException, URISyntaxException {

            String imageName = CarGear.getProductById(CarGear.getCategoryByName(category),id).getProductInfo().getImgName();

            // Get the absolute path of the image resource
            String imagePath = Objects.requireNonNull(CustomerController.class.getResource("/imgs/" + imageName)).toURI().getPath();


            // Check if Desktop is supported (works on Windows, Linux, and macOS)
            if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();

                // Check if the file exists before attempting to open it
                File imageFile = new File(imagePath);
                if (imageFile.exists()) {
                    desktop.open(imageFile);
                }
            }
        }
    }











