package models;

import enums.Gender;
import enums.UserType;
import exceptions.WeakPasswordException;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User{

    private List<Request> customerRequestsList ;
    private List<Product> purchasedProducts ;
    public Customer() {
    }

    public Customer(Name name, int age, Gender gender, String password, ContactInfo contactInfo, UserType userType) throws WeakPasswordException {
        super(name, age, gender, password, contactInfo, userType);
        this.setUserType(UserType.CUSTOMER);
        purchasedProducts = new ArrayList<>();
        customerRequestsList = new ArrayList<>();
    }


    public List<Request> getRequests() {
        return customerRequestsList;
    }

    public void addRequest(Request request){
        customerRequestsList.add(request);
    }
    public void removeRequest(Request request){
        customerRequestsList.remove(request);
    }

    public List<Product> getPurchasedProducts() {
        return purchasedProducts;
    }

    public void addProduct(Product product){
        purchasedProducts.add(product);
    }


}
