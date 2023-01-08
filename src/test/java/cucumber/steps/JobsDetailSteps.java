package cucumber.steps;


import cucumber.tests.TestRunner;
import helpers.BaseScreen;
import helpers.eko_click;
import helpers.eko_expect;
import helpers.eko_get;
import io.cucumber.core.runner.Runner;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.plugin.event.TestStep;
import io.cucumber.testng.TestNGCucumberRunner;
import io.qameta.allure.Allure;
import mappings.mapper;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import static helpers.BaseScreen.*;
import static helpers.eko_get.*;
import static helpers.eko_expect.equal_data;

public class JobsDetailSteps {
    String path_element;
    String locator;
    String path_data;
    String expect_data;
    String path_screenshot;

    protected Scenario scenario;

    /*
        - Used to provide a Click action on an Element
        - String element = parameter for the element used
     */
    @And("User click {string}")
    public void userClick(String element) throws FileNotFoundException {
        path_element = mapper.key_element(element);
        locator = mapper.LoadYaml(path_element.split("\\:")[0], path_element.split("\\:")[1]);

        if(locator == null || locator.isEmpty() == true){
            throw new RuntimeException(ANSI_RED+"Locator doesn't exist!"+ANSI_RESET);
        }
        else {
            try {
                eko_click.click(locator);
            }
            catch(Exception e) {
                throw new RuntimeException(ANSI_RED+"Step is failed! "+"Your element: '"+ locator+ANSI_RESET+ " and your original error: '"+e.getMessage()+"'");
            }
        }
    }
    /*
        - Used to provide waiting time
        - int time = timeout duration parameter
     */
    @And("User wait {int} seconds")
    public void userWaitSeconds(int time) throws InterruptedException {

        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (Exception e){
            throw new RuntimeException(ANSI_RED+"Step is failed! "+"Your time: '"+ time+ANSI_RESET+ " and your original error: '"+e.getMessage()+"'");
        }
    }
    /*
        - Used to verify the element is displayed
        - String element = parameter for the element used
     */
    @Then("Verify element {string} will be displayed")
    public void verifyElementWillBeDisplayed(String element){
        path_element = mapper.key_element(element);
        locator = mapper.LoadYaml(path_element.split("\\:")[0],path_element.split("\\:")[1]);

        if(locator == null || locator.isEmpty() == true){
            throw new RuntimeException(ANSI_RED+"Locator doesn't exist!"+ANSI_RESET);
        }
        else {
            try {
                eko_expect.elment_displayed(locator);
                System.out.println(ANSI_YELLOW+"Element '"+locator+"' is displayed"+ANSI_RESET);
                Allure.addAttachment("Verify","Your element '"+locator+"' is displayed");
            }catch (Exception e){
                throw new RuntimeException(ANSI_RED+"Step is failed! "+"Your element: '"+ locator+ANSI_RESET+ " and your original error: '"+e.getMessage()+"'");
            }
        }
    }
    /*
        - Used to verify the data obtained from the elements then compared with the test data
        - String element    = parameter for the element used
        - String condition  = parameters to provide conditions whether Equal or Not Equal
        - String test_data  = parameters for the test data used
     */
    @Then("Verify value {string} is {string} with data {string}")
    public void VerifyValueIsWithData(String element, String condition, String test_data) {
        path_element = mapper.key_element(element);
        locator = mapper.LoadYaml(path_element.split("\\:")[0],path_element.split("\\:")[1]);

        if(locator == null || locator.isEmpty() == true){
            throw new RuntimeException(ANSI_RED+"Locator doesn't exist!"+ANSI_RESET);
        }
        else{
            path_data = mapper.key_data(test_data);
            expect_data = mapper.LoadYaml(path_data.split("\\:")[0],path_data.split("\\:")[1]);

            if (expect_data == null || expect_data.isEmpty()){
                throw new RuntimeException(ANSI_RED+"Test data doesn't exist!"+ANSI_RESET);
            }
            else{
                String your_equal = equal_data(locator,expect_data);
                switch (condition){
                    case "equal":
                        if(your_equal.equals("true")){
                            Allure.addAttachment("Verify","Your value '"+ get_text(locator)+"' is equal with data '"+expect_data+"'");
                            System.out.println(ANSI_YELLOW+"Your value '"+get_text(locator)+"' is equal with data '"+expect_data+"'"+ANSI_RESET);
                        }
                        else {
                            throw new RuntimeException(ANSI_RED+"Your value '"+get_text(locator)+"' not equal with data '"+expect_data+"' not as expected"+ANSI_RESET);
                        }
                        break;
                    case "not equal":
                        if(your_equal.equals("false")){
                            Allure.addAttachment("Verify","Your value '"+get_text(locator)+"' is not equal with data '"+expect_data+"' as expected");
                            System.out.println(ANSI_YELLOW+"Your value '"+get_text(locator)+"' is not equal with data '"+expect_data+"' as expected");
                        }
                        else{
                            throw new RuntimeException(ANSI_RED+"Your value '"+get_text(locator)+"' is equal with data '"+expect_data+"' not as expected"+ANSI_RESET);
                        }
                        break;
                    default:
                        throw new RuntimeException(ANSI_RED+"Your element: '"+locator+"' condition '"+condition+"' and test data '"+expect_data+"' step is failed!"+ANSI_RESET);
                }
            }
        }
    }
    /*
        - Used to take screenshots then save in your project folder and screenshots will be displayed in the Allure Report
        - String screenshotName = Parameter for the filename of the screenshot
     */
    @Then("User take screenshot with file name {string}")
    public void userTakesScreenshotWithFileName(String screenshotName) throws IOException {
        try {
            path_screenshot = String.valueOf(captureScreen(screenshotName));
            Allure.addAttachment("Your screenshot", new ByteArrayInputStream(takeScreenshotAllure()));
        }
        catch (Exception e){
            throw new RuntimeException(ANSI_RED+"You failed to take a screenshot!"+ANSI_RESET+" and your original error: '"+e.getMessage());
        }

    }
}
