package hooks;



import io.appium.java_client.AppiumDriver;

import io.cucumber.java.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static Capabilities.BasicClass.initDevice;
import static helpers.BaseScreen.*;
import static hooks.ITestStepStarted.*;

public class driverHooks {
    private static String appiumBaseUrl = "http://127.0.0.1:";
    private static String appiumPort    = System.getenv("appiumPort");
    private static URL appiumServerURl;
    public static List<String> scenarioList =new ArrayList<String>();
    public static List<String> stepList =new ArrayList<String>();
    public static List<String> scenarioGetSourceTagNames =new ArrayList<String>();

    /*
        Used as a base function before a scenario is run
     */
    @Before
    public static void openParentApps () throws Exception {
        appiumServerURl = new URL(appiumBaseUrl+appiumPort);
        try {
            driver = new AppiumDriver(appiumServerURl,initDevice());
            System.out.println("Apps started...");
            base_sleep(3);
        } catch (Exception e){

            throw new RuntimeException(ANSI_RED+"Cannot started apps!"+" and original error"+ANSI_RESET+"'"+e.getMessage()+"'");
        }
    }
    /*
        Used as a base function after the scenario is executed
     */
    @After
    public void handlingAfter(Scenario scenario){
        scenarioList.add(scenario.getName() + " ["+scenario.getStatus()+"]");
        scenarioGetSourceTagNames.add(System.getenv("cucumberTags"));
        stepList.add(currentStep);
        if (driver != null) {
            if (scenario.isFailed()) {
                scenario.log("Scenario "+scenario.getName()+" "+scenario.getStatus());
                scenario.attach(takeScreenshotAllure(), "image/png", scenario.getName()+scenario.getStatus());

            } 
            customSpec(scenarioList,getAllStep,scenarioGetSourceTagNames);
        }
    }

    @BeforeStep
    public  void handlingBeforeStep() throws InterruptedException {
        base_sleep(3);
    }


}
