package test_admin;

import controllers.AdminController;
import exceptions.InvalidEmailFormatException;
import exceptions.InvalidPhoneNumberException;
import exceptions.UserAlreadyExistsException;
import exceptions.WeakPasswordException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.CarGear;
import models.Category;
import models.Product;
import models.User;
import printers.Printer;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestListAllProducts {

    private List<Category> categories;
    private int size = 0;

    @When("the admin wants to list all products")
    public void theAdminWantsToListAllProducts() {
        categories = AdminController.getAllCategories();

        for (Category c: categories) {
            size += c.getProducts().size();

        }


    }
    @Then("the result size of the products must be {int}")
    public void theResultSizeOfTheProductsMustBe(Integer int1) {

        assertEquals(int1,size);
    }
}
