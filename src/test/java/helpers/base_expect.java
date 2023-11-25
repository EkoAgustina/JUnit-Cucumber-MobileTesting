package helpers;

import io.qameta.allure.Allure;


import java.util.regex.Pattern;

import static helpers.BaseScreen.*;
import static helpers.base_get.get_text;

public class base_expect {
    /*
        Used to verify if an element is displayed
     */
    public static String element_displayed(String locator, String condition){
        Boolean elDisplayed = base_find(locator).isDisplayed();
        switch (condition){
            case "will be displayed":
                if (elDisplayed.equals(true)){
                    System.out.println(ANSI_YELLOW+"Element '"+locator+"' is displayed"+ANSI_RESET);
                    Allure.addAttachment("Verify","Your element '"+locator+"' is displayed");
                }
                else {
                    throw new RuntimeException(ANSI_RED+"Element "+locator+" is "+elDisplayed+" Not Displayed! "+ANSI_RESET);
                }
                break;
            case "not displayed":
                if (elDisplayed.equals(false)){
                    System.out.println(ANSI_YELLOW+"Element is '"+locator+"' is not displayed as expected"+ANSI_RESET);
                    Allure.addAttachment("Verify","Your element '"+locator+"' is not displayed as expected");
                }
                else{
                    throw new RuntimeException(ANSI_RED+"Element "+locator+" is Displayed not as expected! "+ANSI_RESET);
                }
                break;
            default:
                throw new RuntimeException(ANSI_RED+"Unrecognized condition"+ANSI_RESET);

        }
        return null ;

    }
    /*
        Used to compare data obtained from elements with test data
     */
    public static String equal_data (String locator, String test_data, String match, String condition) {
        String equal;
        String element = base_get.get_text(locator);
        switch (match) {
            case "data":
                if (test_data == null || test_data.isEmpty()) {
                    throw new RuntimeException(ANSI_RED + "Test data not found!" + ANSI_RESET);
                } else {
                    equal = String.valueOf(element.equals(test_data));
                    switch (condition) {
                        case "equal":
                            if (equal.equals("true")) {
                                System.out.println(ANSI_YELLOW + "Your value '" + get_text(locator) + "' is equal with data '" + test_data + "'" + ANSI_RESET);
                                Allure.addAttachment("Verify","Your value '"+ get_text(locator)+"' is equal with data '"+test_data+"'");
                            } else {
                                throw new RuntimeException(ANSI_RED + "Your value '" + get_text(locator) + "' not equal with data '" + test_data + "' not as expected" + ANSI_RESET);
                            }
                            break;
                        case "not equal":
                            if (equal.equals("false")) {
                                System.out.println(ANSI_YELLOW + "Your value '" + get_text(locator) + "' is not equal with data '" + test_data + "' as expected" + ANSI_RESET);
                                Allure.addAttachment("Verify","Your value '"+ get_text(locator)+"' is not equal with data '"+test_data+"' "+"as expected");
                            } else {
                                throw new RuntimeException(ANSI_RED + "Your value '" + get_text(locator) + "' is equal with data '" + test_data + "' not as expected" + ANSI_RESET);
                            }
                            break;
                        default:
                            throw new RuntimeException(ANSI_RED+"Unrecognized condition"+ANSI_RESET);
                    }
                }
                break;
            case "regex":
                if (test_data == null || test_data.isEmpty()){
                        throw new RuntimeException(ANSI_RED+"Test data not found!"+ANSI_RESET);
                    }
                    else{
                        equal = String.valueOf(Pattern.matches(test_data,element));
                    switch (condition) {
                        case "equal":
                            if (equal.equals("true")) {
                                System.out.println(ANSI_YELLOW + "Your value '" + get_text(locator) + "' is equal with data '" + test_data + "'" + ANSI_RESET);
                                Allure.addAttachment("Verify","Your value '"+ get_text(locator)+"' is equal with data '"+test_data+"'");
                            } else {
                                throw new RuntimeException(ANSI_RED + "Your value '" + get_text(locator) + "' not equal with data '" + test_data + "' not as expected" + ANSI_RESET);
                            }
                            break;
                        case "not equal":
                            if (equal.equals("false")) {
                                System.out.println(ANSI_YELLOW + "Your value '" + get_text(locator) + "' is not equal with data '" + test_data + "' as expected" + ANSI_RESET);
                                Allure.addAttachment("Verify","Your value '"+ get_text(locator)+"' is not equal with data '"+test_data+"' "+"as expected");
                            } else {
                                throw new RuntimeException(ANSI_RED + "Your value '" + get_text(locator) + "' is equal with data '" + test_data + "' not as expected" + ANSI_RESET);
                            }
                            break;
                        default:
                            throw new RuntimeException(ANSI_RED + "Unrecognized condition" + ANSI_RESET);
                    }
                    }
                        break;
                    default:
                        throw new RuntimeException(ANSI_RED + "Parameters not recognized" + ANSI_RESET);
                }

                return null;

        }


    }


