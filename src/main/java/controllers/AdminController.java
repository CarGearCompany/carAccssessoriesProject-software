package controllers;

import enums.UserType;
import exceptions.*;
import helpers.EmailFormatChecker;
import helpers.EmailService;
import models.*;


import java.util.Collections;
import java.util.List;
import java.util.Objects;


public class AdminController {
    private static final String SENDER = "cargearcompany@gmail.com";

    private AdminController() {
    }

    public static List<User> getAllUsers() {
        return CarGear.getUsers();
    }

    public static void addProduct(Category c, Product p) throws CategoryNotFoundException, ProductAlreadyExistException {
        if (!CarGear.getCategories().contains(c)) {
            throw new CategoryNotFoundException();
        }

        for (Product product :
                c.getProducts()) {
            if (product.getId() == p.getId()) {
                throw new ProductAlreadyExistException();

            }

        }
        c.addProducts(p);
    }

    public static List<User> searchForUser(String searchType, String value) {
        if (searchType.equalsIgnoreCase("first name")) {
            return CarGear.getUsers().stream().filter(user -> user.getName().getFirstName().equalsIgnoreCase(value)).toList();

        } else if (searchType.equalsIgnoreCase("last name")) {
            return CarGear.getUsers().stream().filter(user -> user.getName().getLastName().equalsIgnoreCase(value)).toList();

        } else if (searchType.equalsIgnoreCase("age")) {
            return CarGear.getUsers().stream().filter(user -> user.getAge() == Integer.parseInt(value)).toList();

        } else if (searchType.equalsIgnoreCase("gender")) {
            return CarGear.getUsers().stream().filter(user -> user.getGender().toString().equalsIgnoreCase(value)).toList();

        } else if (searchType.equalsIgnoreCase("email")) {
            return CarGear.getUsers().stream().filter(user -> user.getContactInfo().getEmail().equalsIgnoreCase(value)).toList();

        } else if (searchType.equalsIgnoreCase("phone number")) {
            return CarGear.getUsers().stream().filter(user -> user.getContactInfo().getPhoneNumber().equalsIgnoreCase(value)).toList();

        } else if (searchType.equalsIgnoreCase("city")) {
            return CarGear.getUsers().stream().filter(user -> user.getContactInfo().getLocation().getCity().equalsIgnoreCase(value)).toList();

        } else if (searchType.equalsIgnoreCase("user type")) {
            return CarGear.getUsers().stream().filter(user -> user.getUserType().toString().equalsIgnoreCase(value)).toList();

        }


        return Collections.emptyList();
    }


    public static void removeUser(String email) throws UserNotFoundException, AdminsCannotBeRemovedException, InvalidEmailFormatException {
        User user = CarGear.getUserByEmail(email);
        if (user.getUserType().equals(UserType.ADMIN)) {
            throw new AdminsCannotBeRemovedException();
        }
        CarGear.removeUser(user);
    }

    public static void promoteUser(String email) throws UserNotFoundException, AdminsCannotBePromotedException, InvalidEmailFormatException {
        User user = CarGear.getUserByEmail(email);

        if (user.getUserType().equals(UserType.ADMIN)) {
            throw new AdminsCannotBePromotedException();
        }

        CarGear.promoteUser(user);

    }

    public static void addCategory(Category category) throws CategoryAlreadyExistsException {
        String categoryName = category.getCategoryName();
        for (Category c :
                CarGear.getCategories()) {
            if (c.getCategoryName().equalsIgnoreCase(categoryName))
                throw new CategoryAlreadyExistsException();
        }
        CarGear.addCategory(category);
    }


    public static void removeProduct(Category c, int id) throws CategoryNotFoundException, ProductNotFoundException {

        if (!CarGear.getCategories().contains(c)) {
            throw new CategoryNotFoundException();
        }
        Product product = CarGear.getProductById(c,id);
        CarGear.removeProduct(c, product);
    }

    public static void removeCategory(String category) throws CategoryNotFoundException {
        Category category1 = CarGear.getCategoryByName(category);
        CarGear.removeCategory(category1);
    }


    public static List<Category> getAllCategories() {
        return CarGear.getCategories();
    }

    public static Category searchForCategoryByName(String category) throws CategoryNotFoundException {
        return CarGear.getCategoryByName(category);
    }

    public static List<Product> searchForProducts(String searchType, String value) {

        if (searchType.equalsIgnoreCase("ID")) {
            return CarGear.getAllProducts().stream().filter(product -> product.getId() == Integer.parseInt(value)).toList();

        } else if (searchType.equalsIgnoreCase("Name")) {
            return CarGear.getAllProducts().stream().filter(product -> product.getProductInfo().getProductName().equalsIgnoreCase(value)).toList();
        } else if (searchType.equalsIgnoreCase("Description")) {
            return CarGear.getAllProducts().stream().filter(product -> product.getProductInfo().getDescription().equalsIgnoreCase(value)).toList();

        } else if (searchType.equalsIgnoreCase("Price")) {
            return CarGear.getAllProducts().stream().filter(product -> product.getProductInfo().getPrice() == Integer.parseInt(value)).toList();

        } else if (searchType.equalsIgnoreCase("Quantity")) {
            return CarGear.getAllProducts().stream().filter(product -> product.getProductInfo().getQuantity() == Integer.parseInt(value)).toList();

        } else if (searchType.equalsIgnoreCase("Availability")) {
            return CarGear.getAllProducts().stream().filter(product -> product.isAvailable() == Boolean.parseBoolean(value)).toList();

        }
        return Collections.emptyList();
    }


    public static void editProduct(String categoryName, int id, String editType, String newValue) throws ProductNotFoundException, CannotEditIdException, CategoryNotFoundException {
        Product product = CarGear.getProductById(Objects.requireNonNull(CarGear.getCategoryByName(categoryName)), id);

        if (editType.equalsIgnoreCase("ID")) {
            throw new CannotEditIdException();
        } else if (editType.equalsIgnoreCase("Name")) {
            product.getProductInfo().setProductName(newValue);
        } else if (editType.equalsIgnoreCase("Description")) {
            product.getProductInfo().setDescription(newValue);
        } else if (editType.equalsIgnoreCase("Price")) {
            product.getProductInfo().setPrice(Integer.parseInt(newValue));
        } else if (editType.equalsIgnoreCase("Quantity")) {
            product.getProductInfo().setQuantity(Integer.parseInt(newValue));
        }

    }

// maybe we should update the test of add request feature
    public static void addRequest(String customerEmail, String installerEmail, String date, String carModel, String category, int productId) throws UserNotFoundException, InvalidEmailFormatException, CategoryNotFoundException, ProductNotFoundException, ItemNotFoundException, AlreadyReservedDateException {
        Installer installer = (Installer) CarGear.getUserByEmail(installerEmail);
        Customer customer = (Customer) CarGear.getUserByEmail(customerEmail);
        Category c = CarGear.getCategoryByName(category);
        Product p = CarGear.getProductById(c, productId);
        Schedule s = installer.getScheduleByDate(date);
        String msg = "";
        String subj = "";
        EmailService emailService = new EmailService();
        if (Boolean.TRUE.equals(s.getReserved()))
            throw new AlreadyReservedDateException();
        else {
            Request request = new Request(installerEmail, customerEmail, date, carModel, p);
            installer.getRequests().add(request);
            customer.getRequests().add(request);
            installer.getSchedules().get(installer.getSchedules().indexOf(s)).setReserved(true);
            s.setCustomerEmail(customer.getContactInfo().getEmail());
            subj += "Installation Request Notification From Admin";
            msg += "Installer: " + installer.getName().getFirstName() + " " + installer.getName().getLastName() + "\n" +
                    "Requested by Customer : " + customer.getName().getFirstName() + " " + customer.getName().getLastName() + "\n" +
                    "Product Requested : " + p.getProductInfo().getProductName() + "\n" +
                    "For Car Model : " + carModel + "\n" +
                    "Date Booked For : " + date;
            emailService.sendEmail(SENDER, installerEmail, msg, subj, 1);
        }
    }


    public static void removeRequest(String installerEmail, String date) throws UserNotFoundException, InvalidEmailFormatException, ItemNotFoundException {
        if(EmailFormatChecker.hasCorrectEmailFormat(installerEmail)) {
            throw new InvalidEmailFormatException();
        }
        Installer installer = (Installer) CarGear.getUserByEmail(installerEmail);
        Request request = installer.getRequestByDate(date);
        String customerEmail = request.getCustomerEmail();
        Customer customer = (Customer) CarGear.getUserByEmail(customerEmail);
        Schedule schedule = installer.getScheduleByDate(date);

        installer.removeRequest(request);
        customer.removeRequest(request);

        installer.getSchedules().get(installer.getSchedules().indexOf(schedule)).setReserved(false);
    }

    public static List<Request> searchForRequests(String searchType, String value) {

        if (searchType.equalsIgnoreCase("customer email")) {
            return CarGear.getRequests().stream().filter(request -> request.getCustomerEmail().equals(value)).toList();

        } else if (searchType.equalsIgnoreCase("installer email")) {
            return CarGear.getRequests().stream().filter(request -> request.getInstallerEmail().equals(value)).toList();

        } else if (searchType.equalsIgnoreCase("date")) {
            return CarGear.getRequests().stream().filter(request -> request.getDate().equals(value)).toList();

        } else if (searchType.equalsIgnoreCase("car model")) {
            return CarGear.getRequests().stream().filter(request -> request.getCarModel().equals(value)).toList();

        } else if (searchType.equalsIgnoreCase("product id")) {
            return CarGear.getRequests().stream().filter(request -> request.getProduct().getId() == Integer.parseInt(value)).toList();

        }
        return Collections.emptyList();
    }


    public static void editRequest(String installerEmail, String date, String editType, String value, String category) throws UserNotFoundException, InvalidEmailFormatException, ProductNotFoundException, CategoryNotFoundException, ItemNotFoundException, AlreadyReservedDateException {
        Installer installer = (Installer) CarGear.getUserByEmail(installerEmail);
        Request request = installer.getRequestByDate(date);
        Customer customer = (Customer) CarGear.getUserByEmail(request.getCustomerEmail());
        Schedule schedule = installer.getScheduleByDate(date);
        Request newRequest;
        Schedule newSchedule;
        Installer newInstaller;
        Customer newCustomer;
        boolean dateInSchedule = false;
        Schedule currentSchedule = null;

        switch (editType){
            case "installer email":
                newRequest = new Request(value , request.getCustomerEmail() , date , request.getCarModel() , request.getProduct());
                // remove the old request
                installer.removeRequest(request);
                customer.removeRequest(request);
                // set the date in schedule to not reserved
                installer.getSchedules().get(installer.getSchedules().indexOf(schedule)).setReserved(false);
                // new Schedule
                newSchedule = new Schedule(date,true,value);
                newSchedule.setCustomerEmail(request.getCustomerEmail());
                newInstaller = (Installer) CarGear.getUserByEmail(value);

                //add the new request and schedule
                newInstaller.addSchedule(newSchedule);
                newInstaller.addRequest(newRequest);
                customer.addRequest(newRequest);
                break;
            case "customer email" :
                newRequest = new Request(installerEmail , value , date , request.getCarModel() , request.getProduct());
                // remove the old request
                installer.removeRequest(request);
                customer.removeRequest(request);
                // make new customer and give the request to his list
                newCustomer = (Customer) CarGear.getUserByEmail(value);
                newCustomer.addRequest(newRequest);
                // modify the old Schedule and add the new request in the installer
                installer.getSchedules().get(installer.getSchedules().indexOf(schedule)).setCustomerEmail(value);
                installer.addRequest(newRequest);
                break;
            case "date" :
                for (Schedule s:
                        installer.getSchedules()) {
                    if (s.getDate().equals(value)){
                        dateInSchedule = true;
                        currentSchedule = s;
                    }
                }

                if (dateInSchedule){
                    // if the date already exist in installer schedule and not reserved
                    if (Boolean.FALSE.equals(currentSchedule.getReserved())){
                        currentSchedule.setReserved(true);
                        installer.getSchedules().get(installer.getSchedules().indexOf(schedule)).setReserved(false);
                        installer.getSchedules().get(installer.getSchedules().indexOf(currentSchedule)).setCustomerEmail(schedule.getCustomerEmail());
                        // change the date of the old request
                        installer.getRequests().get(installer.getRequests().indexOf(request)).setDate(value);
                        customer.getRequests().get(customer.getRequests().indexOf(request)).setDate(value);
                    }else {
                        throw new AlreadyReservedDateException();
                    }
                }else {
                    // if the date not in the installer schedule
                    schedule.setReserved(false); // make the old schedule not reserved
                    Schedule newSchedule1 = new Schedule(value,false,installerEmail);
                    addScheduleToInstaller(installerEmail,newSchedule1);
                    // change the date of the old request
                    installer.getRequests().get(installer.getRequests().indexOf(request)).setDate(value);
                    customer.getRequests().get(customer.getRequests().indexOf(request)).setDate(value);
                    // the new schedule is not available now
                    installer.getSchedules().get(installer.getSchedules().indexOf(newSchedule1)).setReserved(true);
                }

                break;

            case "car model":
                installer.getRequests().get(installer.getRequests().indexOf(request)).setCarModel(value);
                customer.getRequests().get(customer.getRequests().indexOf(request)).setCarModel(value);
                break;

            case "product id" :
                if (category != null) {
                    Category category1 = CarGear.getCategoryByName(category);
                    Product newProduct = CarGear.getProductById(category1, Integer.parseInt(value));
                    installer.getRequests().get(installer.getRequests().indexOf(request)).setProduct(newProduct);
                    customer.getRequests().get(customer.getRequests().indexOf(request)).setProduct(newProduct);
                }
                break;
            default:
        }


    }

    public static void addScheduleToInstaller(String installerEmail, Schedule schedule) throws UserNotFoundException, InvalidEmailFormatException {
        Installer installer = (Installer) CarGear.getUserByEmail(installerEmail);
        installer.addSchedule(schedule);
    }

}