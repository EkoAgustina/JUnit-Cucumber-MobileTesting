package helpers;

import org.openqa.selenium.ElementClickInterceptedException;

import static helpers.BaseScreen.*;

public class eko_click {


    /*
        Used as a base function to provide a Click action on an Element
     */
    public static void click(String locator){
        try {
            eko_find(locator).click();
        } catch (ElementClickInterceptedException e){
            System.out.println(ANSI_RED+"Elements cannot be clicked!"+ANSI_RESET);
            throw e;
        }
    }
}
