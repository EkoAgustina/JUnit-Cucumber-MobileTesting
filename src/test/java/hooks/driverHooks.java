package hooks;

import io.appium.java_client.AppiumDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import mappings.mapper;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.IOException;
import java.net.URL;

import static Capabilities.BasicClass.initDevice;
import static helpers.BaseScreen.driver;

public class driverHooks {

    @Before
    public static void openParentApps () throws Exception {
        String appiumServerURL = "http://127.0.0.1:4723";
        driver = new AppiumDriver(new URL(appiumServerURL), initDevice());
        System.out.println("Apps started...");
    }

    @After
    public void addScreenshot(Scenario scenario) throws InterruptedException, IOException {

        Thread.sleep(1000);
        //validate if scenario has failed
        if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", mapper.date());
        }
    }
}
