package test_customer;
import controllers.CustomerController;
import exceptions.*;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.CarGear;
import models.Customer;
import models.Product;

import javax.mail.MessagingException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestDisplayProductImage {

    private String category;
    private int id;
    private String imgTitle;




    @When("the customer enters category {string}")
    public void theCustomerEntersCategory(String string) {
      this.category = string;
    }
    @When("enters product id equals {int}")
    public void entersProductIdEquals(Integer int1) {
        this.id = int1;
    }
    @Then("the {string} image")
    public void theImage(String string) throws CategoryNotFoundException, IOException, URISyntaxException, ProductNotFoundException {
        CustomerController.openImage(category,id);
        assertEquals(CarGear.getProductById(CarGear.getCategoryByName(category),id).getProductInfo().getProductName(),string);

    }

    @Then("category not found exception will be thrown")
    public void categoryNotFoundExceptionWillBeThrown()  {

        assertThrows(CategoryNotFoundException.class,()->  CustomerController.openImage(category,id));
    }

    @Then("product not found exception will be thrown")
    public void productNotFoundExceptionWillBeThrown() {

        assertThrows(ProductNotFoundException.class,()->  CustomerController.openImage(category,id));
    }


}
