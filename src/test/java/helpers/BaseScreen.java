package helpers;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
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
            System.out.println(ANSI_RED+"Elements  doesn't exist!"+ANSI_RESET);
            throw e;
        }
        return appium_element;
    }
    /*
        Used as a basic function for taking screenshots
        This function is only for hooks
     */
    public static byte[] takeScreenshotAllure(){
        final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        return screenshot;
    }
    /*
        Used as a basic function to take a screenshot and then save it in your project folder
     */
    public static File captureScreen(String screenshotName) throws IOException {
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
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
        String stringDate= DateFor.format(date);
        return stringDate;
    }
}
