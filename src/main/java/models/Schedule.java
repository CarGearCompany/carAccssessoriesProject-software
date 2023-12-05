package models;



public class Schedule {

    private String date;
    private Boolean reserved;
    private String installerEmail;
    private String customerEmail;

    public Schedule(String date, Boolean reserved, String installerEmail) {
        setDate(date);
        setReserved(reserved);
        setInstallerEmail(installerEmail);
        setCustomerEmail(null);
    }

    private void setInstallerEmail(String installerEmail) {
        this.installerEmail = installerEmail;
    }

    public void setReserved(Boolean reserved) {
        this.reserved = reserved;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return this.date;
    }

    public Boolean getReserved() {
        return this.reserved;
    }

    public String getInstallerEmail() {
        return this.installerEmail;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

}


