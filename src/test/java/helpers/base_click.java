package helpers;


import static helpers.BaseScreen.*;
import static mappings.mapper.*;

import javax.management.RuntimeErrorException;

import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;

public class base_click {
    /*
        Used as a base function to provide a Click action on an Element
     */
    public static void click(String locator) throws InterruptedException {
        try {
            base_find(locator).click();
        } catch (Exception e){
            throw new RuntimeException("Failed to click "+locator+" '"+e.getMessage()+"'");
        }
    }

    /*
        Used as a base function to provide a Click action on an Element with coordinate
     */
    public static void cickCoordinate(String test_data) throws InterruptedException {
        TouchAction touchAction = new TouchAction(driver);
        int coordinate_x = (int) keyData_coordinate(test_data).get("x");
        int coordinate_y = (int) keyData_coordinate(test_data).get("y");
        
        try {
            touchAction.tap(new PointOption().withCoordinates(coordinate_x,coordinate_y));
        } catch (Exception e) {
           throw new RuntimeErrorException(null, "Failed to click coordinate "+coordinate_x+" "+coordinate_y+" '"+e.getMessage()+"'");
        }
    }
}
