package models;

import enums.Gender;
import enums.UserType;
import exceptions.*;
import helpers.EmailFormatChecker;
import java.util.ArrayList;
import java.util.List;

public class CarGear {
    private static final List<User> users = new ArrayList<>();
    private static final List<Category> categories = new ArrayList<>();
    private static final List<Schedule> schedules= new ArrayList<>(); //this is the schedule for all installers.

    private static User currentUser = null;

    private CarGear() {
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        CarGear.currentUser = currentUser;
    }

    public static List<User> getUsers() {
        return users;
    }

    public static List<Category> getCategories(){
        return categories;
    }
    public static List<Schedule> getSchedules() { return schedules; }

    public static List<Product> getProductsOfCategory(Category category){
        return category.getProducts();
    }
    /*public static List<Request> getCustomerRequests(Customer customer){
        return customer.getRequests();
    }
    public static List<Product> getCustomerProducts(Customer customer){
        return customer.getPurchasedProducts();
    } */
    public static Category getCategoryOfProduct(Product product){

        for (Category c:getCategories()) {
            if(c.getProducts().contains(product))
                return c;

        }


        return null;
    }

    public static List<Product> getAllProducts(){
        List<Product> allProducts = new ArrayList<>();

        for (Category c:getCategories()) {
            allProducts.addAll(getProductsOfCategory(c));

        }

        return allProducts;


    }

    public static boolean isEmailRegistered(String email) {
        for (User user : users) {
            if (user.getContactInfo().getEmail().equalsIgnoreCase(email)) {
                return true;
            }

        }
        return false;
    }

    public static boolean isCategoryExists(String name){
        for(Category category : categories){
            if (category.getCategoryName().equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }

    public static List<Schedule> getScheduleByEmail(String email) throws InvalidEmailFormatException {
        if (!EmailFormatChecker.hasCorrectEmailFormat(email)) {
            throw new InvalidEmailFormatException();
        }

            return  CarGear.getSchedules().stream().filter(s -> s.getInstallerEmail().equalsIgnoreCase(email)).toList();
        }




    public static User getUserByEmail(String email) throws UserNotFoundException, InvalidEmailFormatException {
        if (!EmailFormatChecker.hasCorrectEmailFormat(email)) {
            throw new InvalidEmailFormatException();
        }

        int userIndex = 0;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getContactInfo().getEmail().equals(email)) {
                userIndex = i;
                break;
            } else
                userIndex = -1;
        }
        if (userIndex == -1)
            throw new UserNotFoundException();
        else {
            return users.get(userIndex);
        }
    }

    public static Category getCategoryByName(String string) throws CategoryNotFoundException {
        int categoryIndex = -1;
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).getCategoryName().equals(string)) {
                categoryIndex = i;
                break;
            }
        }
        if (categoryIndex != -1){

            return categories.get(categoryIndex);
        }
        throw new CategoryNotFoundException();
    }

    public static Product getProductById(Category category,int id) throws ProductNotFoundException {
        int productIndex = -1;
        for (int i = 0; i < category.getProducts().size(); i++) {
            if (category.getProducts().get(i).getId() == id) {
                productIndex = i;
                break;
            }
        }
        if (productIndex == -1){
            throw new ProductNotFoundException();}
        else {
            return category.getProducts().get(productIndex);
        }
    }

    public static void addUser(User user) throws UserAlreadyExistsException {
        if (users.contains(user))
            throw new UserAlreadyExistsException();
        users.add(user);
    }


    public static void addCategory(Category category) {
        categories.add(category);
    }

    public static void removeUser(User user) {
        users.remove(user);
    }
    public static void removeProduct(Category category,Product product){
        category.removeProduct(product);
    }

    public static void removeCategory(Category category) {
        categories.remove(category);
    }

    private static void clearData() {
        CarGear.setCurrentUser(null);
        CarGear.getUsers().clear();
        CarGear.getCategories().clear();
    }

    public static void addAppointment(Schedule schedule){ schedules.add(schedule);}

    public static void removeAppointment(Schedule schedule){ schedules.remove(schedule);}

    public static void promoteUser(User user) {
        user.setUserType(UserType.ADMIN);
    }


    public static void initData() throws UserAlreadyExistsException, InvalidPhoneNumberException, InvalidEmailFormatException, WeakPasswordException { //this method use to initialize all the data in the main
        clearData();

        //the first admin data
        Admin firstAdmin = new Admin(
                new Name("Nabeel", "Jamous"),
                20,
                Gender.MALE,
                "Nabeel@123",
                new ContactInfo("nabeel@gmail.com", "0592757823",
                        new Location("Nablus", "TunisST")),
                UserType.ADMIN);

        //the second admin data
        Admin secAdmin = new Admin(
                new Name("Mahmoud", "AbuHanoud"),
                21,
                Gender.MALE,
                "Mahmoud@123",
                new ContactInfo("mahmoud@gmail.com", "0593021843",
                        new Location("Nablus", "Balata")),
                UserType.ADMIN);

        Customer firstCustomer = new Customer(
                new Name("Saleh", "Sawalha"),
                21,
                Gender.MALE,
                "Saleh@123",
                new ContactInfo("saleh@gmail.com", "0597846668",
                        new Location("Ramallah", "Ersal")),
                UserType.CUSTOMER);

        Customer secCustomer = new Customer(
                new Name("Jana", "Hosam"),
                25,
                Gender.FEMALE,
                "Jana@123",
                new ContactInfo("jana@gmail.com", "0598765432",
                        new Location("Jenin", "Jenin")),
                UserType.CUSTOMER);

        Installer firstInstaller = new Installer(
                new Name("Mahmoud", "Jawabreh"),
                30,
                Gender.MALE,
                "Mahmoud@123",
                new ContactInfo("asamr@gmail.com", "0591234567",
                        new Location("Gaza", "Gaza")),
                UserType.INSTALLER);

        Installer secInstaller = new Installer(
                new Name("Hala", "Qasem"),
                27,
                Gender.FEMALE,
                "Hala@123",
                new ContactInfo("hala@gmail.com", "0591478963",
                        new Location("Tulkarm", "Tulkarm")),
                UserType.INSTALLER);
        Category interior = new Category("interior");
        Category exterior = new Category("exterior");
        Category electronic = new Category("electronic");

        addCategory(interior);
        addCategory(exterior);
        addCategory(electronic);

        Product firstInterior = new Product(0,new ProductInfo("Steering wheel cover","description1",15,20),true);
        Product secInterior = new Product(1,new ProductInfo("Seat cover","description2",65,15),true);
        Product firstExterior = new Product(2,new ProductInfo("Spoiler","description3",40,10),true);
        Product secExterior = new Product(3,new ProductInfo("Car Cover","description4",25,0),false);
        Product firstElectronic = new Product(4,new ProductInfo("Stereo System","description5",180,10),true);
        Product secElectronic = new Product(5,new ProductInfo("Camera","description6",70,14),true);

        Schedule firstSchedule = new Schedule("15/12/2023", false,"asamr@gmail.com" );
        Schedule secondSchedule = new Schedule("7/1/2024", false,"asamr@gmail.com" );
        Schedule thirdSchedule = new Schedule("8/2/2024", false,"hala@gmail.com" );
        Schedule fourthSchedule = new Schedule("15/2/2024", false,"hala@gmail.com" );


    addAppointment(firstSchedule);
    addAppointment(secondSchedule);
    addAppointment(thirdSchedule);
    addAppointment(fourthSchedule);

    addUser(firstAdmin);
    addUser(secAdmin);
    addUser(firstCustomer);
    addUser(secCustomer);
    addUser(firstInstaller);
    addUser(secInstaller);

    interior.addProducts(firstInterior);
    interior.addProducts(secInterior);
    exterior.addProducts(firstExterior);
    exterior.addProducts(secExterior);
    electronic.addProducts(firstElectronic);
    electronic.addProducts(secElectronic);
    }
}

