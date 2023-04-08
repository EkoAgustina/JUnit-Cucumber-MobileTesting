package helpers;

import static helpers.BaseScreen.*;

public class base_fill {
    public static void fill(String locator, String test_data){
        try {
            base_find(locator).sendKeys(test_data);
        } catch (Exception e){
            throw new RuntimeException("Failed to fill "+locator+" '"+e.getMessage()+"'");
        }
    }
}
