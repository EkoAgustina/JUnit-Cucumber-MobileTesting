package helpers;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Properties;


import static mappings.mapper.locatorParser;

public class BaseScreen {
    public static String ANSI_RESET = "\u001B[0m";
    public static String ANSI_YELLOW = "\u001B[33m";
    public static String ANSI_RED = "\u001B[41m";

    static Properties prop=new Properties();
    public static AppiumDriver<MobileElement> driver;
    protected static WebDriverWait wait;

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
    /*
        Used as a basic function for taking screenshots
     */
    public static byte[] takeScreenshot(){
        final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        return screenshot;
    }
    /*
        Used as a basic function to retrieve the Format Date
     */
    public static String date(){
        Date date = new Date();
        SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
        String stringDate= DateFor.format(date);
        return stringDate;
    }
}
