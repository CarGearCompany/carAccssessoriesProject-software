package controllers;

import enums.UserType;
import exceptions.*;
import helpers.EmailFormatChecker;
import models.CarGear;
import models.Category;
import models.Product;
import models.User;

import java.util.List;

public class AdminController {
    private AdminController() {
    }

    public static List<User> getAllUsers() {
        return CarGear.getUsers();
    }

    public static void addProduct(Category c,Product p) throws ItemNotFoundException, ItemAlreadyExistsExceprion {
        if(!CarGear.getCategories().contains(c)){
            throw new ItemNotFoundException();
        }
        if(c.getProducts().contains(p)){
            throw new ItemAlreadyExistsExceprion();
        }
        c.addProducts(p);
    }

    public static User searchForUserByEmail(String email) throws UserNotFoundException, InvalidEmailFormatException {
        if (!EmailFormatChecker.hasCorrectEmailFormat(email)) {
            throw new InvalidEmailFormatException();
        }
        if (!CarGear.getUserByEmail(email).getContactInfo().getEmail().equals(email)) {
            throw new UserNotFoundException();
        }
        return CarGear.getUserByEmail(email);
    }


    public static void removeUser(String email) throws UserNotFoundException, AdminsCannotBeRemovedException, InvalidEmailFormatException {
        User user = CarGear.getUserByEmail(email);
        if(user.getUserType().equals(UserType.ADMIN)){
            throw new AdminsCannotBeRemovedException();
        }
        CarGear.removeUser(user);
    }

    public static void promoteUser(String email) throws UserNotFoundException, AdminsCannotBePromotedException, InvalidEmailFormatException {
        User user = CarGear.getUserByEmail(email);
        if(user.getUserType().equals(UserType.ADMIN)){
            throw new AdminsCannotBePromotedException();
        }

        CarGear.promoteUser(user);

    }

    public static void addCategory(Category category) throws ItemAlreadyExistsExceprion {
        if (!CarGear.getCategories().contains(category)) {
            CarGear.addCategory(category);
        }

    }

    public static void removeProduct(Category c,int id) throws ItemNotFoundException {

        if (!CarGear.getProducts(c).contains(CarGear.getProductById(c,id))){
            throw new ItemNotFoundException();
        }
        CarGear.removeProduct(c,CarGear.getProductById(c,id));
    }

    public static void removeCategory(Category category) throws ItemNotFoundException {
        if (!CarGear.getCategories().contains(category)){
            throw new ItemNotFoundException();
        }
        CarGear.removeCategory(category);
    }


    public static List<Category> getAllCategories() {
        return CarGear.getCategories();
    }

    public static Category searchForCategoryByName(String category) throws ItemNotFoundException {
        if (!CarGear.getCategories().contains(CarGear.getCategoryByName(category))){
            throw new ItemNotFoundException();
        }
        return CarGear.getCategoryByName(category);
    }

    public static Product searchForProductById(Category category, int id) throws ItemNotFoundException {
        return CarGear.getProductById(category,id);
    }
}