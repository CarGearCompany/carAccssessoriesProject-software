package test_admin;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/features/Admin", glue = "test_admin", snippets = CucumberOptions.SnippetType.CAMELCASE)
public class TestRunner {

}
