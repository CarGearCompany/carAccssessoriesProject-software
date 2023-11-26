package models;

public class Request {
    private String installerEmail;
    private String customerEmail;
    private String date;
    private String carModel;
    private Product product;

    public Request(String installerEmail, String customerEmail, String date, String carModel, Product product) {
        this.installerEmail = installerEmail;
        this.customerEmail = customerEmail;
        this.date = date;
        this.carModel = carModel;
        this.product = product;
    }

    public String getInstallerEmail() {
        return installerEmail;
    }

    public void setInstallerEmail(String installerEmail) {
        this.installerEmail = installerEmail;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}
