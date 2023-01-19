package cucumber.tests;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Test;

@Test()
@CucumberOptions(

        features = "src/test/java/cucumber/features",
        glue = {"cucumber.steps","hooks"},
        plugin = {"json:target/cucumber.json","pretty","html:target/cucumber-reports","io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"},
        tags = "@TestPass",
        monochrome = true,
        publish = true
)
public class TestRunner extends AbstractTestNGCucumberTests {

}