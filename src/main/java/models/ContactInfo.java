package models;


public class ContactInfo {
    private String email;
    private String phoneNumber;
    private Location location;

    public ContactInfo(String email, String phoneNumber, Location location)  {
        setEmail(email);
        setPhoneNumber(phoneNumber);
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email)  {

        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {

        this.phoneNumber = phoneNumber;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
