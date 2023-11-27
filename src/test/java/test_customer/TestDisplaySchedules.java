package test_customer;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.jupiter.api.Assertions.assertEquals;
import models.CarGear;
import models.Customer;

public class TestDisplaySchedules {
    private Customer customer;
    private int resultSize;

    @When("the customer wants to see the schedules")
    public void theCustomerWantsToSeeTheSchedules() {
       customer = (Customer) CarGear.getCurrentUser();

    }
    @Then("the schedules will be printed and the result size will be {int}")
    public void theSchedulesWillBePrintedAndTheResultSizeWillBe(Integer int1) {
        resultSize = CarGear.getSchedules().size();
        assertEquals(int1,resultSize);
    }



}
