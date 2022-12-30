package hooks;


import helpers.BaseScreen;
import io.appium.java_client.AppiumDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.messages.types.TestCase;
import io.cucumber.messages.types.TestStep;

import java.net.URL;
import java.util.List;


import static Capabilities.BasicClass.initDevice;
import static helpers.BaseScreen.driver;

public class driverHooks {

    /*
        Used as a base function before a scenario is run
     */
    @Before
    public static void openParentApps () throws Exception {
        String appiumServerURL = "http://127.0.0.1:4723/wd/hub";
        try {
            driver = new AppiumDriver(new URL(appiumServerURL), initDevice());
            System.out.println("Apps started...");
        } catch (Exception e){
            System.out.println("Cannot started apps ....!");
            throw e;
        }
    }
    /*
        Used as a base function after the scenario is executed
     */
    @After
    public void handlingAfter(Scenario scenario) throws InterruptedException, NoSuchFieldException, IllegalAccessException {
        Thread.sleep(1000);
        if (scenario.isFailed()) {
            scenario.log("Scenario Failed "+scenario.getName()+" "+scenario.getStatus());
            scenario.attach(BaseScreen.takeScreenshot(), "image/png", BaseScreen.date());
        } else {
            scenario.log(scenario.getName()+" "+scenario.getStatus());
        }
    }


}
