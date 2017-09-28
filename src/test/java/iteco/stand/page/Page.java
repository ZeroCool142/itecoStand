package iteco.stand.page;

import iteco.stand.helper.BrowserFactory;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

public class Page {

    protected WebDriver driver;

    public static BrowserFactory.Browser browser;

    protected static Map<String,String> variables = new HashMap<>();

    public Page(WebDriver driver){
        this.driver = driver;
    }

}
