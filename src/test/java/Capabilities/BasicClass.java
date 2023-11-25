package Capabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import mappings.mapper;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.DesiredCapabilities;


import java.io.File;
import java.net.URL;

import static mappings.mapper.file_path;
import static mappings.mapper.key_apps;

public class BasicClass {
    public static DesiredCapabilities initDevice(){
        /*
          Desired Capabilities is used to send JSON Data to Appium Server
         */
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("automationName","UiAutomator2");
        cap.setCapability("platformName","Android");
        cap.setCapability("deviceName",System.getenv("deviceName"));
        cap.setCapability("autoGrantPermissions","true");
        cap.setCapability("noReset","false");
        cap.setCapability("printPageSourceOnFindFailure","true");
        cap.setCapability("app", key_apps(System.getenv("apps")));
        cap.setCapability("appWaitDuration","30000");
        cap.setCapability("appWaitActivity","SplashActivity, SplashActivity,OtherActivity, *, *.SplashActivity");

        return cap;
    }


}
