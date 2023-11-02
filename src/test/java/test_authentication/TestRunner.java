package test_authentication;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/features/RegistrationAndAuthentication", glue = "test_authentication", snippets = CucumberOptions.SnippetType.CAMELCASE)
public class TestRunner {

}