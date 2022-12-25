package Capabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import mappings.mapper;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URL;

public class BasicClass {
    public static DesiredCapabilities initDevice(){
        /*
          Desired Capabilities is used to send JSON Data to Appium Server
         */
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("automationName","UiAutomator2");
        cap.setCapability("platformName","Android");
        cap.setCapability("deviceName","127.0.0.1:21503");
        cap.setCapability("autoGrantPermissions","true");
        cap.setCapability("noReset","false");
        /*
          Use this if you want to run your app using appPackage and appActivity
         */
//        cap.setCapability("appPackage", "com.google.android.deskclock");
//        cap.setCapability("appActivity", "com.android.deskclock.DeskClock");

        /*
        Use this if you want to run the application using the application file path
         */
        cap.setCapability("app", mapper.file_path("/apk/app-clock.apk").getAbsolutePath());



        return cap;
    }


}
