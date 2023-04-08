package helpers;


import static helpers.BaseScreen.*;

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
}
