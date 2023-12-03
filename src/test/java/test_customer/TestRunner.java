package test_customer;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/features/Customer", glue = "test_customer", snippets = CucumberOptions.SnippetType.CAMELCASE)
public class TestRunner {

}