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

    public static boolean isEmailRegistered(String email) {
        for (User user : users) {
            if (user.getContactInfo().getEmail().equalsIgnoreCase(email)) {
                return true;
            }

        }
        return false;
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

    public static void addUser(User user) throws UserAlreadyExistsException {
        if (users.contains(user))
            throw new UserAlreadyExistsException();
        users.add(user);
    }


    public static void addCategory(Category category) throws CategoryAlreadyExistsExceprion{
        categories.add(category);
    }

    public static void removeUser(User user) {
        users.remove(user);
    }

    public static void removeCategory(Category category) {
        categories.remove(category);
    }

    private static void clearData() {
        CarGear.setCurrentUser(null);
        CarGear.getUsers().clear();
        CarGear.getCategories().clear();
    }


    public static void promoteUser(User user) {
        user.setUserType(UserType.ADMIN);
    }


    public static void initData() throws UserAlreadyExistsException, InvalidPhoneNumberException, InvalidEmailFormatException, WeakPasswordException, CategoryAlreadyExistsExceprion { //this method use to initialize all the data in the main
        clearData();

        //the first admin data
        User firstAdmin = new User(
                new Name("Nabeel", "Jamous"),
                20,
                Gender.MALE,
                "Nabeel@123",
                new ContactInfo("nabeel@gmail.com", "0592757823",
                        new Location("Nablus", "TunisST")),
                UserType.ADMIN);

        //the second admin data
        User secAdmin = new User(
                new Name("Mahmoud", "AbuHanoud"),
                21,
                Gender.MALE,
                "Mahmoud@123",
                new ContactInfo("mahmoud@gmail.com", "0593021843",
                        new Location("Nablus", "Balata")),
                UserType.ADMIN);

        User firstCustomer = new User(
                new Name("Saleh", "Sawalha"),
                21,
                Gender.MALE,
                "Saleh@123",
                new ContactInfo("saleh@gmail.com", "0597846668",
                        new Location("Ramallah", "Ersal")),
                UserType.CUSTOMER);

        User secCustomer = new User(
                new Name("Jana", "Hosam"),
                25,
                Gender.FEMALE,
                "Jana@123",
                new ContactInfo("jana@gmail.com", "0598765432",
                        new Location("Jenin", "Jenin")),
                UserType.CUSTOMER);

        User firstInstaller = new User(
                new Name("Mahmoud", "Jawabreh"),
                30,
                Gender.MALE,
                "Mahmoud@123",
                new ContactInfo("asamr@gmail.com", "0591234567",
                        new Location("Gaza", "Gaza")),
                UserType.INSTALLER);

        User secInstaller = new User(
                new Name("Hala", "Qasem"),
                27,
                Gender.FEMALE,
                "Hala@123",
                new ContactInfo("hala@gmail.com", "0591478963",
                        new Location("Tulkarm", "Tulkarm")),
                UserType.INSTALLER);
        Category interior = new Category("Interior");
        Category exterior = new Category("Exterior");
        Category electronic = new Category("Electronic");

        addCategory(interior);
        addCategory(exterior);
        addCategory(electronic);

        Product firstInterior = new Product(0,new ProductInfo("Steering wheel cover","description1",15,null,20),true);
        Product secInterior = new Product(1,new ProductInfo("Seat cover","description2",65,null,15),true);
        Product firstExterior = new Product(2,new ProductInfo("Spoiler","description3",40,null,10),true);
        Product secExterior = new Product(3,new ProductInfo("Car Cover","description4",25,null,5),true);
        Product firstElectronic = new Product(4,new ProductInfo("Stereo System","description5",180,null,10),true);
        Product secElectronic = new Product(5,new ProductInfo("Camera","description6",70,null,14),true);

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

