package hooks;



import io.appium.java_client.AppiumDriver;

import io.cucumber.java.*;

import java.net.URL;



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
                scenario.log(scenario.getName() +" "+scenario.getStatus());
            }
        }
    }


}
