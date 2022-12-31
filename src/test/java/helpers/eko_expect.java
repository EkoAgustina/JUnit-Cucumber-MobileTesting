package helpers;

import org.testng.Assert;

import java.util.NoSuchElementException;

import static helpers.BaseScreen.*;

public class eko_expect {
    /*
        Used to verify if an element is displayed
     */
    public static String elment_displayed(String locator){
        Boolean displayed = eko_find(locator).isDisplayed();
        try {
            if (displayed == true){
                return String.valueOf(displayed);
            }else {
                throw new RuntimeException(ANSI_RED+"Element Not Displayed!"+ANSI_RESET);
            }
        } catch (NoSuchElementException e){
            System.out.println(ANSI_RED+"Incompatible elements!"+ANSI_RESET);
            throw e;
        }
    }
    /*
        Used to compare data obtained from elements with test data
     */
    public static String equal_data (String locator, String test_data){
        String equal;
        String element = String.valueOf(eko_get.get_text(locator));
        try {
            if (test_data==null){
                throw new RuntimeException(ANSI_RED+"Test data not found!"+ANSI_RESET);
            }
            else if (test_data.isEmpty()){
                throw new RuntimeException(ANSI_RED+"Empty test data!"+ANSI_RESET);
            }
            else {
                equal = String.valueOf(element.equals(test_data));
                System.out.println(ANSI_YELLOW+"Your value '"+element+"' is equal with data '"+test_data+"'"+ANSI_RESET);
            }
        } catch (Exception e){
            System.out.println(ANSI_RED+"Your value '"+element+"' not equal with data '"+test_data+"' not as expected!"+ANSI_RESET);
            throw e;
        }
        return equal;
    }



}
