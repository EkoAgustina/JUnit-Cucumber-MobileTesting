package Capabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import mappings.mapper;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.DesiredCapabilities;


import java.io.File;
import java.net.URL;

import static mappings.mapper.file_path;

public class BasicClass {
    private static String APP        = file_path("/apk/app-clock.apk").getAbsolutePath();
    private static String deviceName = "127.0.0.1:21503";
    public static DesiredCapabilities initDevice(){
        /*
          Desired Capabilities is used to send JSON Data to Appium Server
         */
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("automationName","UiAutomator2");
        cap.setCapability("platformName","Android");
        cap.setCapability("deviceName",deviceName);
        cap.setCapability("autoGrantPermissions","true");
        cap.setCapability("noReset","false");
        cap.setCapability("printPageSourceOnFindFailure","true");
        cap.setCapability("app", APP);

        return cap;
    }


}
