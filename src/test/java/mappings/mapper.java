package mappings;

import org.yaml.snakeyaml.Yaml;

import io.appium.java_client.MobileElement;

import static helpers.BaseScreen.driver;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class mapper {
    /*
       Used to map Apps paths
    */
    public static String key_apps(String apps){
        String use_key;
        if (Files.exists(Paths.get(file_path("/apk/"+apps).getAbsolutePath()))){
            use_key = file_path("/apk/"+apps).getAbsolutePath();
        }
        else {
            throw new RuntimeException("Apps not found!");
        }
        return use_key;
    }
    /*
        Used to map Path File path
     */
    public static File file_path(String path){
        File base_path = new File(System.getProperty("user.dir"));
        return new File(base_path, path);
    }
    /*
        Used to parse the Locator on Element
     */
    public static MobileElement locatorParser(String locator){
        String el = locator.split("=> ")[1];
        MobileElement selector = null;

        switch (locator.split(" =>")[0]) {
            case "By.accessibilityId":
                selector = driver.findElementByAccessibilityId(el);
                break;
            case "By.xpath":
                selector = driver.findElementByXPath(el);
                break;
            default:
                throw new Error("Element not recognized!");
        }
        return selector;
    }
    /*
        Used to read and parse YAML files.
        So that it can be used to put Locator, Element, Test Data and others
     */
    public static Object LoadYaml(String selector, String element) {
        Map<String, Object> data;
        Yaml yaml = new Yaml();

        try {
            InputStream inputStream = new FileInputStream(selector);
            data = yaml.load(inputStream);
            
        }
        catch (Exception err) {
            throw new Error(err.getMessage());
        }

        return data.get(element);
    }

    public static String key_element(String element){
        String platformType = System.getenv("platform");
        String path_element = null;
        String selector = null;

        if (element == null || element.isEmpty()){
            throw new RuntimeException("Element is required ..!");
        }
        else{
            path_element = "src/test/java/resources/selector/"+element.split(":")[0]+".yml"+":"+element.split(":")[1];
            Map<String, Object> parseSelector = (Map<String, Object>) LoadYaml(path_element.split("\\:")[0],path_element.split("\\:")[1]);
            if (platformType.contains("ios")) {
                selector = parseSelector.get("ios").toString();
            }
            else if (platformType.contains("android")) {
                selector = parseSelector.get("android").toString();
            }
            return selector;
        }
    }
    public static String key_data(String data){
        String path_data = null;
        String test_data = null;
        if (data == null || data.isEmpty()){
            throw new RuntimeException("Data is required ..!");
        }
        else{
            path_data = "src/test/java/resources/test_data/"+data.split(":")[0]+".yml"+":"+data.split(":")[1];
            test_data = LoadYaml(path_data.split("\\:")[0],path_data.split("\\:")[1]).toString();
            System.out.println("check test data: "+ test_data);
            return test_data;
        }
    }
}

