package controllers;

import enums.UserType;
import exceptions.*;
import helpers.EmailFormatChecker;
import models.*;


import java.util.Collections;
import java.util.List;
import java.util.Objects;


public class AdminController {
    private AdminController() {
    }

    public static List<User> getAllUsers() {
        return CarGear.getUsers();
    }

    public static void addProduct(Category c,Product p) throws CategoryNotFoundException, ProductAlreadyExistException {
        if(!CarGear.getCategories().contains(c)){
            throw new CategoryNotFoundException();
        }

        for (Product product:
                c.getProducts()) {
            if(product.getId()==p.getId()){
                throw new ProductAlreadyExistException();

            }

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
        if (!EmailFormatChecker.hasCorrectEmailFormat(email)) {
            throw new InvalidEmailFormatException();
        }
        if (!CarGear.getUserByEmail(email).getContactInfo().getEmail().equals(email)) {
            throw new UserNotFoundException();
        }
        if(user.getUserType().equals(UserType.ADMIN)){
            throw new AdminsCannotBePromotedException();
        }

        CarGear.promoteUser(user);

    }

    public static void addCategory(Category category) throws CategoryAlreadyExistsException, CategoryNotFoundException {
        if (CarGear.getCategories().contains(CarGear.getCategoryByName(category.getCategoryName()))) {
            throw new CategoryAlreadyExistsException();
        }else
            {
            CarGear.addCategory(category);
            }

        }




    public static void removeProduct(Category c,int id) throws CategoryNotFoundException, ProductNotFoundException {

        if(!CarGear.getCategories().contains(c)){
            throw new CategoryNotFoundException();
        }

        if (!CarGear.getProductsOfCategory(c).contains(CarGear.getProductById(c,id))){
            throw new ProductNotFoundException();
        }
        CarGear.removeProduct(c, CarGear.getProductById(c,id));
    }

    public static void removeCategory(Category category) throws ItemNotFoundException, CategoryNotFoundException {
        if (CarGear.getCategoryByName(category.getCategoryName()) == null){
            throw new ItemNotFoundException();
        }
        if (!CarGear.getCategories().contains(category)){
            throw new ItemNotFoundException();
        }
        CarGear.removeCategory(category);
    }


    public static List<Category> getAllCategories() {
        return CarGear.getCategories();
    }

    public static Category searchForCategoryByName(String category) throws ItemNotFoundException, CategoryNotFoundException {
        if (!CarGear.getCategories().contains(CarGear.getCategoryByName(category))){
            throw new ItemNotFoundException();
        }
        return CarGear.getCategoryByName(category);
    }

    public static List<Product> searchForProducts(String searchType,String value) {

        if (searchType.equalsIgnoreCase("ID")) {
            return CarGear.getAllProducts().stream().filter(product -> product.getId() == Integer.parseInt(value)).toList();

        }
        else if (searchType.equalsIgnoreCase("Name")) {
            return CarGear.getAllProducts().stream().filter(product -> product.getProductInfo().getProductName().equalsIgnoreCase(value)).toList();

        }
        else if (searchType.equalsIgnoreCase("Description")) {
            return CarGear.getAllProducts().stream().filter(product -> product.getProductInfo().getDescription().equalsIgnoreCase(value)).toList();

        }
        else if (searchType.equalsIgnoreCase("Price")) {
            return CarGear.getAllProducts().stream().filter(product -> product.getProductInfo().getPrice() == Integer.parseInt(value)).toList();

        }
        else if (searchType.equalsIgnoreCase("Quantity")) {
            return CarGear.getAllProducts().stream().filter(product -> product.getProductInfo().getQuantity() == Integer.parseInt(value)).toList();

        }
        else if (searchType.equalsIgnoreCase("Availability")) {
            return CarGear.getAllProducts().stream().filter(product -> product.isAvailable() == Boolean.parseBoolean(value)).toList();

        }
        return Collections.emptyList();
    }


    public static void editProduct(String categoryName,int id,String editType,String newValue) throws ProductNotFoundException, CannotEditIdException, CategoryNotFoundException {
        Product product = CarGear.getProductById(Objects.requireNonNull(CarGear.getCategoryByName(categoryName)),id);

        if (editType.equalsIgnoreCase("ID")) {
            throw new CannotEditIdException();
        }
        else if (editType.equalsIgnoreCase("Name")) {
            product.getProductInfo().setProductName(newValue);
        }
        else if (editType.equalsIgnoreCase("Description")) {
            product.getProductInfo().setDescription(newValue);
        }
        else if (editType.equalsIgnoreCase("Price")) {
            product.getProductInfo().setPrice(Integer.parseInt(newValue));
        }
        else if (editType.equalsIgnoreCase("Quantity")) {
            product.getProductInfo().setQuantity(Integer.parseInt(newValue));
        }
        else if (editType.equalsIgnoreCase("Availability")) {
            product.setAvailable(Boolean.parseBoolean(newValue));

        }


    }






}