package test_admin;

import controllers.AdminController;
import exceptions.CategoryNotFoundException;
import exceptions.ItemNotFoundException;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.CarGear;
import models.Category;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestRemoveCategory {
    String name ;
    Category c;
    @When("the name of the category to be removed is {string}")
    public void theNameOfTheCategoryToBeRemovedIs(String string) {
        this.name = string;
    }
    @Then("this category will be removed and the size of categories will be {int}")
    public void thisCategoryWillBeRemovedAndTheSizeOfCategoriesWillBe(Integer int1) throws ItemNotFoundException, CategoryNotFoundException {
        c = CarGear.getCategoryByName(name);
        assert c != null;
        AdminController.removeCategory(c);
        Integer size = CarGear.getCategories().size();

        assertEquals(int1,size);
    }


    @Then("nothing will be removed and an item not found exception will be thrown")
    public void nothingWillBeRemovedAndAnItemNotFoundExceptionWillBeThrown() throws CategoryNotFoundException {
        c = CarGear.getCategoryByName(name);
        assertThrows(NullPointerException.class, () -> AdminController.removeCategory(c));
    }

}
