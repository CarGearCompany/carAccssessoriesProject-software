package models;

// class

public class User {
    private Name name;
    private int age;
    private String gender;
    private String password;
    private ContactInfo contactInfo;


    public User(Name name, int age, String gender, String password, ContactInfo contactInfo) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.password = password;
        this.contactInfo = contactInfo;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

}
