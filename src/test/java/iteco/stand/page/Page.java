package iteco.stand.page;

import iteco.stand.helper.BrowserFactory;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

public class Page {

    protected WebDriver driver;

    protected static Map<String,String> variables = new HashMap<>();

    public Page(WebDriver driver){
        this.driver = driver;
    }

    public static void setProperty(String property, String value){
        variables.put(property, value);
    }

    public static void getProperty(String property){
        variables.get(property);
    }

}
