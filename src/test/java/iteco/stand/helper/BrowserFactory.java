package iteco.stand.helper;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class BrowserFactory {

    private static WebDriver driver;

    public static synchronized WebDriver getDriver(Browser browser, String url){
        switch (browser){
            case IE:
                InternetExplorerDriverManager.getInstance().arch32().setup();
                driver = new InternetExplorerDriver();
                break;
            case Firefox:
                FirefoxDriverManager.getInstance().setup();
                driver = new FirefoxDriver();
                break;
            case Chrome:
                ChromeDriverManager.getInstance().setup();
                driver = new ChromeDriver();
                break;
            default:
                throw new InvalidArgumentException("Ivalid argument" + browser);
        }
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.get(url);
        return driver;
    }
    public enum Browser{
        IE,Chrome,Firefox
    }
}
