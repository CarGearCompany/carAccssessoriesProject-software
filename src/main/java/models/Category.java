package models;

import exceptions.ItemAlreadyExistsExceprion;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private String categoryName;
    private final List<Product> products ;

    public Category(String categoryName) throws ItemAlreadyExistsExceprion {
        setCategoryName(categoryName);
       products = new ArrayList<>();
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) throws ItemAlreadyExistsExceprion {
        if (CarGear.isCategoryExists(categoryName)){
            throw new ItemAlreadyExistsExceprion();
        }
        this.categoryName = categoryName;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addProducts(Product product){
        products.add(product);
    }
    public void removeProduct(Product product){
        products.remove(product);
    }

}
