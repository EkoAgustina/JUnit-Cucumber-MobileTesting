package helpers;

import org.openqa.selenium.ElementClickInterceptedException;

import static helpers.BaseScreen.*;

public class base_click {
    /*
        Used as a base function to provide a Click action on an Element
     */
    public static void click(String locator){
        try {
            base_find(locator).click();
        } catch (ElementClickInterceptedException e){
            System.out.println(ANSI_RED+"Elements cannot be clicked!"+ANSI_RESET);
            throw e;
        }
    }
}
