package hooks;


import Capabilities.BasicClass;
import helpers.BaseScreen;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.*;
import io.cucumber.messages.types.TestStep;
import io.cucumber.plugin.event.*;
import io.cucumber.plugin.event.Status;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;


import static Capabilities.BasicClass.initDevice;
import static helpers.BaseScreen.*;

public class driverHooks {
    private static String appiumBaseUrl = "http://127.0.0.1:";
    private static String appiumPort    = "4723";
    private static URL appiumServerURl;

    /*
        Used as a base function before a scenario is run
     */
    @Before
    public static void openParentApps () throws Exception {
        appiumServerURl = new URL(appiumBaseUrl+appiumPort+"/wd/hub");
        try {
            driver = new AppiumDriver(appiumServerURl,initDevice());
            System.out.println("Apps started...");
        } catch (Exception e){
            throw new RuntimeException(ANSI_RED+"Cannot started apps!"+" and original error '"+e.getMessage()+"'"+ANSI_RESET);
        }
    }
    /*
        Used as a base function after the scenario is executed
     */
    @After
    public void handlingAfter(Scenario scenario) throws InterruptedException {
        Thread.sleep(1000);
        if (driver != null) {
            if (scenario.isFailed()) {
                scenario.log("Scenario "+scenario.getName()+" "+scenario.getStatus());
                scenario.attach(takeScreenshotAllure(), "image/png", scenario.getName()+scenario.getStatus());

            } else {
                scenario.log(scenario.getName()+" "+scenario.getStatus());

            }
        }
    }
}
