package Capabilities;

import org.openqa.selenium.remote.DesiredCapabilities;

import static mappings.mapper.key_apps;

public class BasicClass {
    /*
      Desired Capabilities is used to send JSON Data to Appium Server
    */
    public static DesiredCapabilities initDevice(){
        
        DesiredCapabilities cap = new DesiredCapabilities();
        String platformType = System.getenv("platform");

        switch (platformType) {
            case "android":
                cap.setCapability("automationName","UiAutomator2");
                cap.setCapability("platformName","Android");
                cap.setCapability("deviceName",System.getenv("deviceName"));
                cap.setCapability("autoGrantPermissions","true");
                cap.setCapability("noReset","false");
                cap.setCapability("printPageSourceOnFindFailure","true");
                cap.setCapability("app", key_apps(System.getenv("apps")));
                cap.setCapability("appWaitDuration","30000");
                cap.setCapability("appWaitActivity","SplashActivity, SplashActivity,OtherActivity, *, *.SplashActivity"); 
                break;
            case "ios":
                cap.setCapability("automationName","XCUITest");
                cap.setCapability("platformName","iOS");
                cap.setCapability("deviceName",System.getenv("deviceName"));
                cap.setCapability("udid", System.getenv("iosUdid"));
                cap.setCapability("autoGrantPermissions","true");
                cap.setCapability("noReset","false");
                cap.setCapability("printPageSourceOnFindFailure","true");
                cap.setCapability("app", key_apps(System.getenv("apps")));
                cap.setCapability("appWaitDuration","30000");
                cap.setCapability("appWaitActivity","SplashActivity, SplashActivity,OtherActivity, *, *.SplashActivity");
                break;
            default:
                throw new Error("Platform not recognized");
        }
        

        return cap;
    }


}
