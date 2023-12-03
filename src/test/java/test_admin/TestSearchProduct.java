package test_admin;

import controllers.AdminController;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSearchProduct {
    private String searchField;
    private String value;
    private int resultSize;

    @When("search type is {string}")
    public void searchTypeIs(String string) {
        searchField = string;
    }
    @When("value is {string}")
    public void valueIs(String string) {
        value = string;
    }
    @Then("the products are found and number of results must be {int}")
    public void theProductsAreFoundAndNumberOfResultsMustBe(Integer int1) {
        resultSize = AdminController.searchForProducts(searchField,value).size();
        assertEquals(int1,resultSize);

    }
}
