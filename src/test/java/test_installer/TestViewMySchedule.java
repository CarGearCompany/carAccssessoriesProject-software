package test_installer;

import controllers.InstallerController;
import exceptions.AlreadyReservedDateException;
import exceptions.WeakPasswordException;
import io.cucumber.java.en.*;
import models.CarGear;
import models.Installer;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestViewMySchedule {
    private int resultSize;
    Installer installer;
    @When("the installer wants to list all his scheduleList")
    public void theInstallerWantsToListAllHisScheduleList() {
         installer = (Installer) CarGear.getCurrentUser();
        resultSize = installer.getSchedules().size();
    }
    @When("the installer add a valid date {string}")
    public void theInstallerAddAValidDate(String string) throws AlreadyReservedDateException, WeakPasswordException {
        installer = (Installer) CarGear.getCurrentUser();
        InstallerController.addDateToSchedule(string);
        resultSize = installer.getSchedules().size();
    }
    @Then("the result size of the scheduleList must be {int}")
    public void theResultSizeOfTheScheduleListMustBe(Integer int1) {
        assertEquals(int1,resultSize);
    }

}
