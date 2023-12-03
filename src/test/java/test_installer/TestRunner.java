package test_installer;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/features/Installer", glue = "test_installer", snippets = CucumberOptions.SnippetType.CAMELCASE)
public class TestRunner {

}