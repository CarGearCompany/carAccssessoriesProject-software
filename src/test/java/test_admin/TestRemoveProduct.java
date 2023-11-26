package test_admin;

import controllers.AdminController;
import exceptions.CategoryNotFoundException;
import exceptions.ProductNotFoundException;
import io.cucumber.java.en.*;
import models.CarGear;
import models.Category;
import static models.CarGear.getCategories;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestRemoveProduct {
    private Category category = new Category(null);
    private int productId;
    private int size;
    @When("the name of the  is Valid and the category to be removed is {string}")
    public void theNameOfTheIsValidAndTheCategoryToBeRemovedIs(String string) throws CategoryNotFoundException {
        category = CarGear.getCategoryByName(string);
    }
    @When("the product id is exist and equals {int}")
    public void theProductIdIsExistAndEquals(Integer int1) {
        this.productId = int1;
    }
    @Then("this product will be removed and the size of products will be {int}")
    public void thisProductWillBeRemovedAndTheSizeOfProductsWillBe(Integer int1) throws CategoryNotFoundException, ProductNotFoundException {
        AdminController.removeProduct(category,productId);

        for (Category c: getCategories()) {
            size += c.getProducts().size();
        }

        assertEquals(int1,size);
    }
    @When("the name of the  is invalid and the category to be removed is {string}")
    public void theNameOfTheIsInvalidAndTheCategoryToBeRemovedIs(String string) throws CategoryNotFoundException {
        category.setCategoryName(string);
    }
    @Then("nothing will be removed and an category not found exception will be thrown")
    public void nothingWillBeRemovedAndAnCategoryNotFoundExceptionWillBeThrown() {
        assertThrows(CategoryNotFoundException.class, () -> AdminController.removeProduct(category,productId));
    }


    @When("the product id does not exist and equals {int}")
    public void theProductIdDoesNotExistAndEquals(Integer int1) {
        this.productId = int1;
    }

    @Then("nothing will be removed and an Product not found exception will be thrown")
    public void nothingWillBeRemovedAndAnProductNotFoundExceptionWillBeThrown() {
        assertThrows(ProductNotFoundException.class, () -> AdminController.removeProduct(category,productId));
    }


}
