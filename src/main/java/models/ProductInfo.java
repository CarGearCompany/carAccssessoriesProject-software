package models;

public class ProductInfo {
    private String productName;
    private String description;
    private int price;
    private String img;
    private int quantity;

    public ProductInfo(String productName, String description, int price, String img, int quantity) {
        setProductName(productName);
        setDescription(description);
        setPrice(price);
        setImg(img);
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
