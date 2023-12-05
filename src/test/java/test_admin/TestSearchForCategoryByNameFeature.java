package test_admin;

import controllers.AdminController;
import exceptions.CategoryNotFoundException;
import exceptions.ItemNotFoundException;
import exceptions.UserNotFoundException;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class TestSearchForCategoryByNameFeature
{
    private String categoryName;
    @When("the admin should enter name of category  {string}")
    public void theAdminShouldEnterNameOfCategory(String string) {
        this.categoryName = string;
    }
    @Then("the object is returned and nothing will be thrown")
    public void theObjectIsReturnedAndNothingWillBeThrown() {
        assertDoesNotThrow(() -> {
            AdminController.searchForCategoryByName(categoryName);
        });
    }

    @Then("nothing will returned and the item not found exception will be thrown")
    public void nothingWillReturnedAndTheItemNotFoundExceptionWillBeThrown() {
        assertThrows(CategoryNotFoundException.class,() -> {
            AdminController.searchForCategoryByName(categoryName);
        });
    }

}
