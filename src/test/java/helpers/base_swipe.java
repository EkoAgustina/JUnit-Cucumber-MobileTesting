package helpers;

import static helpers.BaseScreen.*;


import org.openqa.selenium.Dimension;

import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;

public class base_swipe {
    public static void swipeUp (String locator) {
        Dimension windowSize = driver.manage().window().getSize();
        TouchAction touchAction = new TouchAction(driver);
        int coordinateX = (int) (windowSize.width * 0.5);
        int coordinateY = (int) (windowSize.height * 0.5);
        int endCoordinate = (int) (windowSize.height * 0.25);

        // try {
        //     while (!elDisplayed(locator)) {
        //         touchAction.longPress(new PointOption().withCoordinates(coordinateX+50, coordinateY+50)).moveTo(new PointOption().withCoordinates(coordinateX+50, endCoordinate)).release().perform();
        //     }
            
        // } catch (Exception err) {
        //     throw err;
        // }
    }
}
