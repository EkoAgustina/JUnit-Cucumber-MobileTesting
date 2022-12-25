package hooks;


import helpers.BaseScreen;
import io.appium.java_client.AppiumDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import java.net.URL;


import static Capabilities.BasicClass.initDevice;
import static helpers.BaseScreen.driver;

public class driverHooks {
    /*
        Used as a base function before a scenario is run
     */
    @Before
    public static void openParentApps () throws Exception {
        String appiumServerURL = "http://127.0.0.1:4723";
        driver = new AppiumDriver(new URL(appiumServerURL), initDevice());
        System.out.println("Apps started...");
    }
    /*
        Used as a base function after the scenario is executed
     */
    @After
    public void handlingAfter(Scenario scenario) throws InterruptedException {
        Thread.sleep(1000);
        if (scenario.isFailed()) {
            scenario.log("Scenario Failed "+scenario.getName()+" "+scenario.getStatus());
            scenario.attach(BaseScreen.takeScreenshot(), "image/png", BaseScreen.date());
        } else {
            scenario.log(scenario.getName()+" "+scenario.getStatus());
        }
    }
}
