package models;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private String categoryName;
    private final List<Product> products ;

    public Category(String categoryName) {
        this.categoryName = categoryName;
       products = new ArrayList<>();
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
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
