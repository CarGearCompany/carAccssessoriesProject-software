package models;

import enums.Gender;
import enums.UserType;
import exceptions.EmailAlreadyExistsException;
import exceptions.UserNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class CarGear {
    private static final List<User> users = new ArrayList<>();
    private static User currentUser = null;

    private CarGear() {
    }

    public static void addUser(User user) throws EmailAlreadyExistsException, UserNotFoundException {
        if(CarGear.getUserByEmail(user.getContactInfo().getEmail())!= null){
            throw new EmailAlreadyExistsException();
        }
        users.add(user);

    }
    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        CarGear.currentUser = currentUser;
    }

    public static List<User> getUsers() {
        return users;
    }

    public static User getUserByEmail(String email) throws UserNotFoundException {
        int userIndex = 0 ;
        for (int i = 0 ; i < users.size() ; i++){
            if(users.get(i).getContactInfo().getEmail().equals(email)){
                userIndex = i;
                break;
            }
            else
                userIndex = -1;
        }
        if (userIndex == -1)
            throw new UserNotFoundException();
        else
            return users.get(userIndex);
    }

    private static void clearData(){
        CarGear.setCurrentUser(null);
        CarGear.getUsers().clear();

    }

    public static void initData() throws UserNotFoundException, EmailAlreadyExistsException { //this method use to initialize all the data in the main
        clearData();

        //the first admin data
        User firstAdmain = new User(
                new Name("Nabeel","Jamous"),
                20,
                Gender.MALE,
                "Nabeel123",
                new ContactInfo("nabeel@gmail.com","0592757823",
                                new Location("Nablus","TunisST")),
                UserType.ADMIN);

        //the second admin data
        User secAdmain = new User(
                new Name("Mahmoud","AbuHanoud"),
                21,
                Gender.MALE,
                "Mahmoud123",
                new ContactInfo("mahmoud@gmail.com","0593021843",
                        new Location("Nablus","Asira")),
                UserType.ADMIN);

    addUser(firstAdmain);
    addUser(secAdmain);
    }


}
