package models;
public class Product {
    private int id;
    private ProductInfo productInfo;
    private boolean isAvailable;


    public Product(int id,ProductInfo productInfo, boolean isAvailable ) {
        setId(id);
        setProductInfo(productInfo);
        setAvailable(isAvailable);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ProductInfo getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(ProductInfo productInfo) {
        this.productInfo = productInfo;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
