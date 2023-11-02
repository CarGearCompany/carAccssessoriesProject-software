package test_admin;

import controllers.AdminController;
import exceptions.InvalidEmailFormatException;
import exceptions.ItemAlreadyExistsExceprion;
import io.cucumber.java.en.*;
import models.CarGear;
import models.Category;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestAddNewCategory
{
    private String name;
    private Category category;
    @When("the admin enter the {string} of the category")
    public void theAdminEnterTheOfTheCategory(String string) {
        this.name=string;
        category =new Category(name);
    }
    @Then("the category will be added successfully and nothing will be thrown")
    public void theCategoryWillBeAddedSuccessfullyAndNothingWillBeThrown() throws ItemAlreadyExistsExceprion {

        assertDoesNotThrow(()->{
            AdminController.addCategory(category);
        });
    }

    @Then("the category cant be added successfully and ItemAlreadyExist exception will be thrown")
    public void theCategoryCantBeAddedSuccessfullyAndItemAlreadyExistExceptionWillBeThrown() {
        assertThrows(ItemAlreadyExistsExceprion.class, () -> AdminController.addCategory(category));
    }


}
