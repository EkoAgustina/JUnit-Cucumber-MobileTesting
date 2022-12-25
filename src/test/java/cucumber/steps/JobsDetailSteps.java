package cucumber.steps;


import helpers.eko_click;
import helpers.eko_expect;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import mappings.mapper;

import java.io.FileNotFoundException;
import java.util.concurrent.TimeUnit;

import static helpers.BaseScreen.*;

public class JobsDetailSteps {

    /*
        - Used to provide a Click action on an Element
        - String element = parameter for the element used
     */
    @And("User click {string}")
    public void userClick(String element) throws FileNotFoundException {
        String path = mapper.key_element(element);
        String locator = mapper.LoadYaml(path.split("\\:")[0],path.split("\\:")[1]);

        try {
            eko_click.click(locator);
        }
        catch(Exception e) {
            System.out.println(ANSI_RED+"Your elements: '"+locator+"' , step is failed!"+ANSI_RESET);
            throw e;
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
            System.out.println(ANSI_RED+"Your time: '"+time+"' , step is failed!"+ANSI_RESET);
            throw e;
        }
    }
    /*
        - Used to verify the element is displayed
        - String element = parameter for the element used
     */
    @Then("Verify element {string} will be displayed")
    public void verifyElementWillBeDisplayed(String element){
        String path = mapper.key_element(element);
        String locator = mapper.LoadYaml(path.split("\\:")[0],path.split("\\:")[1]);

        try {
            eko_expect.elment_displayed(locator);
            System.out.println(ANSI_YELLOW+"Element '"+locator+"' is displayed"+ANSI_RESET);
        }catch (Exception e){
            System.out.println(ANSI_RED+"Your elements: '"+locator+"' , step is failed!"+ANSI_RESET);
            throw e;
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
        String path_element = mapper.key_element(element);
        String locator = mapper.LoadYaml(path_element.split("\\:")[0],path_element.split("\\:")[1]);

        String path_data = mapper.key_data(test_data);
        String expect_data = mapper.LoadYaml(path_data.split("\\:")[0],path_data.split("\\:")[1]);


        switch (condition){
            case "equal":
                eko_expect.equal_data(locator,expect_data);
                break;
            default:
                throw new RuntimeException(ANSI_RED+"Your element: '"+locator+"' condition '"+condition+"' and test data '"+expect_data+"' step is failed!"+ANSI_RESET);
        }
    }


}
