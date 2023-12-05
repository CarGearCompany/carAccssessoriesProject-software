package models;
public class Product {
    private int id;
    private ProductInfo productInfo;


    public Product(int id,ProductInfo productInfo ) {
        setId(id);
        setProductInfo(productInfo);

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
        return this.getProductInfo().getQuantity() != 0;

    }


}
