package models;

public class ProductInfo {
    private String productName;
    private String description;
    private Integer price;
    private Integer quantity;

    public ProductInfo(String productName, String description, Integer price, Integer quantity) {
        setProductName(productName);
        setDescription(description);
        setPrice(price);
        setQuantity(quantity);
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }


    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
