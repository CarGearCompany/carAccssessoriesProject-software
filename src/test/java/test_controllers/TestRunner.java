package test_controllers;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/features", glue = "test_controllers", snippets = CucumberOptions.SnippetType.CAMELCASE)
public class TestRunner {

}