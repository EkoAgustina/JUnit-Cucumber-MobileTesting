package mappings;

import org.yaml.snakeyaml.Yaml;

import io.appium.java_client.MobileElement;

import static helpers.BaseScreen.driver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
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
        String selector = locator.split("=> ")[1];
        MobileElement loc = null;

        if (locator.split(" =>")[0].contains("By.accessibilityId")) {
            System.out.println("masuk if kah ?");
            loc = driver.findElementByAccessibilityId(selector);
        }
        else if (locator.split(" =>")[0].contains("By.xpath")) {
            loc = driver.findElementByXPath(selector);
        }
        return loc;
    }
    /*
        Used to read and parse YAML files.
        So that it can be used to put Locator, Element, Test Data and others
     */
    public static String LoadYaml(String selector, String element) {
        Map conf = new HashMap();
        Yaml yaml = new Yaml();
        String config   = conf.toString();

        try {
            InputStream stream = new FileInputStream(selector);
            conf = (Map) yaml.load(stream);

            config = (String) conf.get(element);

            if (conf == null || conf.isEmpty() == true) {
                throw new RuntimeException("Failed to read config file");
            }

        } catch (FileNotFoundException e) {
            System.out.println("No such file " + selector);
            throw new RuntimeException("No config file");
        } catch (Exception e1) {
            e1.printStackTrace();
            throw new RuntimeException("Failed to read config file");
        }

        return config;
    }

    public static String key_element(String element){
        String path_element = null;
        if (element == null || element.isEmpty()){
            throw new RuntimeException("Element is required ..!");
        }
        else{
            path_element = "src/test/java/resources/selector/"+element.split(":")[0]+".yml"+":"+element.split(":")[1];
            return LoadYaml(path_element.split("\\:")[0],path_element.split("\\:")[1]);
        }


    }
    public static String key_data(String data){
        String path_data = null;
        if (data == null || data.isEmpty()){
            throw new RuntimeException("Data is required ..!");
        }
        else{
            path_data = "src/test/java/resources/test_data/"+data.split(":")[0]+".yml"+":"+data.split(":")[1];
            return LoadYaml(path_data.split("\\:")[0],path_data.split("\\:")[1]);
        }
    }
}
