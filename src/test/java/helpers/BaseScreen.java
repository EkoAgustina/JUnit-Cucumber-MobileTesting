package helpers;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


import static mappings.mapper.locatorParser;

public class BaseScreen {
    public static String ANSI_RESET = "\u001B[0m";
    public static String ANSI_YELLOW = "\u001B[33m";
    public static String ANSI_RED = "\u001B[41m";

    static Properties prop=new Properties();
    public static AppiumDriver<MobileElement> driver;
    public static void base_sleep(int duration) throws InterruptedException {
        TimeUnit.SECONDS.sleep(duration);
    }
    public static WebDriverWait wait (int period){
        return new WebDriverWait(driver,period);
    }
    /*
        Used as a basic function to search for Elements
     */
    public static MobileElement base_find(String locator){
        try {
            return locatorParser(locator);
        } catch (NoSuchElementException e){
            throw new RuntimeException("Elements  doesn't exist! and original error :"+e.getMessage());
        }
    }
    /*
        Used as a basic function for taking screenshots
        This function is only for hooks
     */
    public static byte[] takeScreenshotAllure(){
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
    /*
        Used as a basic function to take a screenshot and then save it in your project folder
     */
    public static File captureScreen(String screenshotName) {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            if(Files.exists(Paths.get("./user_screenshots/" + screenshotName + ".png"))){
                FileUtils.copyFile(scrFile, new File("./user_screenshots/"+screenshotName+"1"+".png"));
            }
            else {
                FileUtils.copyFile(scrFile, new File("./user_screenshots/"+screenshotName+".png"));
            }
        } catch (Exception e){
            throw new RuntimeException("Failed to capture screen: "+e.getMessage());
        }
        return null;
    }
    /*
        Used as a basic function to retrieve the Format Date
     */
    public static String date(){
        Date date = new Date();
        SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
        return DateFor.format(date);
    }
}
