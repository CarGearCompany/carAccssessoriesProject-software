package test_admin;

import controllers.AdminController;
import controllers.CustomerController;
import controllers.LoginController;
import exceptions.CannotEditIdException;
import exceptions.UserNotFoundException;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class TestEditProduct {
    private String category;
    private int id;
    private String editField;
    private String newValue;
    @When("category is {string}")
    public void categoryIs(String string) {
        this.category = string;
    }
    @When("id is {int}")
    public void idIs(Integer int1) {
        this.id = int1;
    }
    @When("edit field is {string}")
    public void editFieldIs(String string) {
        this.editField = string;
    }
    @When("new value is {string}")
    public void newValueIs(String string) {
       this.newValue = string;
    }

    @Then("he will fail to edit and cannot edit id exception will be thrown")
    public void heWillFailToEditAndCannotEditIdExceptionWillBeThrown() {
        assertThrows(CannotEditIdException.class, () -> AdminController.editProduct(category,id,editField,newValue));
    }
    @Then("the field will be edited successfully")
    public void theFieldWillBeEditedSuccessfully() {
        assertDoesNotThrow(()->{
            AdminController.editProduct(category,id,editField,newValue);
        });
    }

}
