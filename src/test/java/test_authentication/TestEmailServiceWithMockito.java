package test_authentication;

import controllers.ForgetPasswordController;
import exceptions.InvalidEmailFormatException;
import exceptions.UserNotFoundException;
import helpers.EmailService;

import io.cucumber.java.en.When;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;

import io.cucumber.java.en.Then;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
public class TestEmailServiceWithMockito {

    private String email;
    private EmailService mockEmailService;
    private EmailService emailService;



    @When("user enters email    {string}")
    public void userEntersEmail(String string) throws UserNotFoundException, InvalidEmailFormatException {
        this.email = string;


    }
    @Before
    public void setup() throws FileNotFoundException {
        mockEmailService = mock(EmailService.class);
        emailService = new EmailService();
    }



    @Test
    public void testSendEmailVerification() {
        // when
        mockEmailService.sendEmailVerification("cargearcompany@gmail.com",email);
        // then
        verify(mockEmailService, times(1)).sendEmailVerification("cargearcompany@gmail.com",email);
    }





}
