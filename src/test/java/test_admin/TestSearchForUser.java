package test_admin;

import controllers.AdminController;
import exceptions.InvalidEmailFormatException;
import exceptions.UserNotFoundException;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSearchForUser {
    private String searchField;
    private String value;
    private int resultSize;

    @When("search type is  {string}")
    public void searchTypeIs(String string) {
        searchField = string;
    }
    @When("value is  {string}")
    public void valueIs(String string) {
        value = string;
    }
    @Then("the users are found and number of results must be {int}")
    public void theUsersAreFoundAndNumberOfResultsMustBe(Integer int1) {
        resultSize = AdminController.searchForUser(searchField,value).size();
        assertEquals(int1,resultSize);

    }
}
