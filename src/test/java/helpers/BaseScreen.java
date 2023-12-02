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
import java.time.Duration;
import java.util.Date;
import java.util.List;
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

    public static Duration sleep (int duration) {
        try {
             Thread.sleep(duration * 1000);
        }
        catch (InterruptedException err) {
            err.getMessage();
        }
        return null;
    }

    public static Boolean waitForExist (String locator) {
        Boolean el;
        try {
            wait(10).until(ExpectedConditions.visibilityOfElementLocated(locatorParser(locator)));
            el = true;
        } catch (Exception err) {
            el = false;
            sleep(3);
        }
        return el;
    }
    /*
        Used as a basic function to search for Elements
     */
    public static MobileElement base_find(String locator){
        
        try {
            waitForExist(locator);
            return driver.findElement(locatorParser(locator));
        } catch (ExceptionInInitializerError e){
            throw new RuntimeException(e.getMessage());    
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

    private static String padString(String str, int len) {
        StringBuilder sb = new StringBuilder(str);
        return sb.append(fill(' ', len - str.length())).toString();
    }
    private static String fill(char ch, int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(ch);
        }
        return sb.toString();
    }

    public static void customSpec(List<String> scenario,List<String> testStep, List<String> getSourceTagNames ){
        Properties properties = System.getProperties();
        String prop = (String) "[" +System.getenv("deviceName") + " - " + System.getenv("apps") + " - " + properties.get("os.name") + "]";
        int max = prop.length() + testStep.stream().map(String::length).max(Integer::compareTo).get();
        int maxBoxWidth = (int) (max * 1.1);
        for(String scenarioGetSourceTagNames:getSourceTagNames) {
            String propTagNames = prop + " " + scenarioGetSourceTagNames;
            String line = "+" + fill('-', maxBoxWidth + 2) + "+";
            System.out.println(line);
            System.out.printf("| %s |%n", padString(propTagNames, maxBoxWidth));
            for(String listScenario:scenario){
                String propScenario = prop + " " + listScenario;
                System.out.printf("| %s |%n", padString(propScenario, maxBoxWidth));
                for (String str : testStep) {
                    System.out.printf("| %s |%n", padString(prop + " ".repeat((listScenario.length() / 10)) + str, maxBoxWidth));
                }
            System.out.println(line);
        }
        }
    }
}
