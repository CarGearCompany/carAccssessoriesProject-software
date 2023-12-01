package test_admin;

import controllers.AdminController;
import exceptions.*;
import io.cucumber.java.en.*;
import models.CarGear;
import models.User;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestListAllUsers {
    private List<User> users;
    @Given("Database is already filled")
    public void databaseIsAlreadyFilled() throws InvalidPhoneNumberException, UserAlreadyExistsException, InvalidEmailFormatException, WeakPasswordException, ItemNotFoundException {
        CarGear.initData();
    }
    @When("the admin wants to list all users")
    public void theAdminWantsToListAllUsers() {
        users = AdminController.getAllUsers();
    }
    @Then("the result size of the users must be {int}")
    public void theResultSizeOfTheUsersMustBe(Integer int1) {
        Integer size = users.size();
        assertEquals(int1,size);
    }

}
