package test_admin;

import controllers.AdminController;
import exceptions.CategoryAlreadyExistsException;
import exceptions.CategoryNotFoundException;
import exceptions.ProductAlreadyExistException;
import exceptions.ProductNotFoundException;
import io.cucumber.java.en.*;
import models.CarGear;
import models.Category;
import models.Product;
import models.ProductInfo;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestAddProduct {
    Category category;
    Product product;
    Product notValidProduct;
    @When("the admin enter the name {string} of the category")
    public void theAdminEnterTheNameOfTheCategory(String string) {
       category = CarGear.getCategoryByName(string);
    }
    @When("the product id is valid and equals {int}")
    public void theProductIdIsValidAndEquals(Integer int1) {
        product = new Product(int1 , new ProductInfo("name", "des" , 10 , null,5),true);
    }

    @Then("the product will be added successfully and nothing will be thrown")
    public void theProductWillBeAddedSuccessfullyAndNothingWillBeThrown() {
        assertDoesNotThrow(()->{
            AdminController.addProduct(category,product);
        });
    }

    @Then("the product will not be added and category not found exception will be thrown")
    public void theProductWillNotBeAddedAndCategoryNotFoundExceptionWillBeThrown() {
        assertThrows(CategoryNotFoundException.class, () -> AdminController.addProduct(category,product));
    }

    @When("the product id is already exists and equals {int}")
    public void theProductIdIsAlreadyExistsAndEquals(Integer int1) {
        notValidProduct = new Product(int1 , new ProductInfo("name", "des" , 10 , null,5),true);
    }
    @Then("the product will not be added and product already exist exception will be thrown")
    public void theProductWillNotBeAddedAndProductAlreadyExistExceptionWillBeThrown() {
        assertThrows(ProductAlreadyExistException.class, () -> AdminController.addProduct(category,notValidProduct));
    }


}
