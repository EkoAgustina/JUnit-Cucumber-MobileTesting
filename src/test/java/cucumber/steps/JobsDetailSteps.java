package cucumber.steps;


import helpers.base_click;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.qameta.allure.Allure;
import java.io.ByteArrayInputStream;


import static helpers.BaseScreen.*;
import static helpers.base_expect.*;
import static helpers.base_fill.fill;
import static mappings.mapper.*;

public class JobsDetailSteps {
    String path_screenshot;


    /*
        Used to provide a Click action on an Element
     */
    @And("^User click \"(.*)\"$")
    public void userClick(String element){
        try {
            base_click.click(key_element(element));
        } catch(Exception e) {
                throw new RuntimeException(ANSI_RED+"Step failed with original error: "+ANSI_RESET+e.getMessage());
        }
    }
    /*
        Used to provide waiting time
     */
    @And("^Wait (.*) seconds$")
    public void userWaitSeconds(int time) {
        try {
            base_sleep(time);
        } catch (Exception e){
            throw new RuntimeException(ANSI_RED+"Step failed with original error: "+ANSI_RESET+e.getMessage());
        }
    }
    /*
        Used to verify the element is displayed
     */
    @Then("^Element \"(.*)\" (will be displayed|not displayed)$")
    public void verifyElementWillBeDisplayed(String element, String condition){
        try {
            element_displayed(key_element(element),condition);
        }catch (Exception e){
            throw new RuntimeException(ANSI_RED+"Step failed with original error: "+ANSI_RESET+e.getMessage());
        }
    }
    /*
        Used to verify the data obtained from the elements then compared with the test data
     */
    @Then("^Element \"(.*)\" is (equal|not equal) with (data|regex) \"(.*)\"$")
    public void VerifyValueIsWithData(String element, String condition,String match, String test_data ) {
        try {
            equal_data(key_element(element),key_data(test_data),match,condition);
        }
        catch (Exception e){
            throw new RuntimeException(ANSI_RED+"Step failed with original error: "+ANSI_RESET+e.getMessage());
        }
    }
    /*
        Used to take screenshots then save in your project folder and screenshots will be displayed in the Allure Report
     */
    @Then("^User take screenshot with file name \"(.*)\"$")
    public void userTakesScreenshotWithFileName(String screenshotName) {
        try {
            path_screenshot = String.valueOf(captureScreen(screenshotName));
            Allure.addAttachment("Your screenshot", new ByteArrayInputStream(takeScreenshotAllure()));
        }
        catch (Exception e){
            throw new RuntimeException(ANSI_RED+"You failed to take a screenshot!"+" and your original error: '"+ANSI_RESET+e.getMessage());
        }
    }

    @And("^Fill in \"(.*)\" with \"(.*)\"$")
    public void userFillsInWith(String element, String test_data) {
        try {
            fill(key_element(element),key_data(test_data));
        }
        catch (Exception e){
            throw new RuntimeException(ANSI_RED+"Step failed with original error: "+ANSI_RESET+e.getMessage());
        }

    }
}
