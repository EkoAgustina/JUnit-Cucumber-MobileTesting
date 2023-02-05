package helpers;

import java.util.NoSuchElementException;
import java.util.regex.Pattern;

import static helpers.BaseScreen.*;

public class base_expect {
    /*
        Used to verify if an element is displayed
     */
    public static boolean elment_displayed(String locator){
        try {
           return base_find(locator).isDisplayed();
        } catch (Exception e){
            System.out.println("gagal");
            throw new RuntimeException(ANSI_RED+"Element Not Displayed! "+ANSI_RESET+e.getMessage());
        }
    }
    /*
        Used to compare data obtained from elements with test data
     */
    public static String equal_data (String locator, String test_data, String match){
//        String with = match;
        String equal;
        String element = String.valueOf(base_get.get_text(locator));
        switch (match){
            case "data":
                try {
                    if (test_data == null || test_data.isEmpty() == true){
                        throw new RuntimeException(ANSI_RED+"Test data not found!"+ANSI_RESET);
                    }
                    else {
                        equal = String.valueOf(element.equals(test_data));
                    }
                } catch (Exception e){
                    throw new RuntimeException(ANSI_RED+"Your value '"+element+"' not equal with data '"+test_data+"' not as expected!"+ANSI_RESET);

                }
                break;
            case "regex":
                try {
                    if (test_data == null || test_data.isEmpty() == true){
                        throw new RuntimeException(ANSI_RED+"Test data not found!"+ANSI_RESET);
                    }
                    else{
                        equal = String.valueOf(Pattern.matches(test_data,element));
                    }
                } catch (Exception e){
                    throw new RuntimeException(ANSI_RED+"Your value '"+element+"' not equal with data '"+test_data+"' not as expected!"+ANSI_RESET);
                }
                break;
            default:
                throw new RuntimeException(ANSI_RED+"Parameters not recognized"+ANSI_RESET);
        }

        return equal ;

    }



}
