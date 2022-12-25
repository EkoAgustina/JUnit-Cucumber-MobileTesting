package helpers;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.NoSuchElementException;
import java.util.Properties;

import static Capabilities.BasicClass.initDevice;
import static mappings.mapper.locatorParser;

public class BaseScreen {

    public static String ANSI_RESET = "\u001B[0m";
    public static String ANSI_YELLOW = "\u001B[33m";
    public static String ANSI_RED = "\u001B[41m";

    static Properties prop=new Properties();
    public static AppiumDriver<MobileElement> driver;
    protected static WebDriverWait wait;

//    public static void openParentApps () throws Exception {
//        String appiumServerURL = "http://127.0.0.1:4723";
//        driver = new AppiumDriver(new URL(appiumServerURL), initDevice());
//        System.out.println("Apps started...");
//    }
    /*
        Used as a basic function to search for Elements
     */
    public static MobileElement eko_find(String locator){
        MobileElement appium_element = null;
        
        try {
            MobileElement mobile_element = driver.findElement(locatorParser(prop.getProperty("locator", locator)));
            WebDriverWait wait = new WebDriverWait(driver,5);
            appium_element = (MobileElement) wait.until(ExpectedConditions.visibilityOf(mobile_element));
        } catch (NoSuchElementException e){
            System.out.println(ANSI_RED+"Elements don't exist!"+ANSI_RESET);
            throw e;
        }
        return appium_element;
    }




}
