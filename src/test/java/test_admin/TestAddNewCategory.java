package test_admin;

import controllers.AdminController;
import exceptions.CategoryAlreadyExistsException;
import io.cucumber.java.en.*;
import models.Category;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestAddNewCategory
{
    private Category category;
    @When("the admin enter the {string} of the category")
    public void theAdminEnterTheOfTheCategory(String string) {
        category =new Category(string);
    }
    @Then("the category will be added successfully and nothing will be thrown")
    public void theCategoryWillBeAddedSuccessfullyAndNothingWillBeThrown() throws CategoryAlreadyExistsException {

        assertDoesNotThrow(()->{
            AdminController.addCategory(category);
        });
    }

    @Then("the category cant be added successfully and ItemAlreadyExist exception will be thrown")
    public void theCategoryCantBeAddedSuccessfullyAndCategoryAlreadyExistExceptionWillBeThrown() {
        assertThrows(CategoryAlreadyExistsException.class, () -> AdminController.addCategory(category));
    }


}
